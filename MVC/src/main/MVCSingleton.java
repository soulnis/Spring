package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MVCSingleton {
	public static void main(String[] arg) {
		UserDao udao = UserDao.getInstance();
		List<List<User>> list = udao.selectAllUaser();
		System.out.println(list);
	}
}

class Dao {
	Connection conn;
	String tableName = "";

	Dao() {
		this(null, "");
	}
	
	Dao(String tableName) {
		this(null, tableName);
	}

	Dao(Connection conn, String tableName) {
		this.tableName = tableName;
		this.conn = conn;
	}

	void rollback() {
		if(conn != null) {
			try {
				conn.rollback();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	void close(AutoCloseable...acs) {
		if(acs.length > 0) {
			for(int i = 0; i < acs.length ;i++) {
				if (acs[i] instanceof Connection) {
					try { ((Connection)acs[i]).close(); } catch (SQLException e) { e.printStackTrace(); } 
				} else if (acs[i] instanceof PreparedStatement) {
					try { ((PreparedStatement)acs[i]).close(); } catch (SQLException e) { e.printStackTrace(); } 
				}
			}
		}
	}
}

class UserDao {

	protected static String JDBC_DRIBER = "jdbc:mysql://localhost:3306/homepage?useUnicode=true&characterEncoding=utf-8"; 
	protected static String DB_USER = "root";
	protected static String DB_PW = "root1234";
	protected static String JDBC_CLASS_NAME = "com.mysql.jdbc.Driver";

	protected Connection conn;
	protected PreparedStatement pst;
	
	protected static UserDao userDao = new UserDao();
	protected UserDao() { }

	public static UserDao getInstance() {
		return userDao;
	}
	
	// 모든 사용자 가지고오기
	List<List<User>> selectAllUaser() {
		List<List<User>> list = new ArrayList<List<User>>();
		String query = "SELECT user_email, user_pw, user_name FROM account;";
		try {
			Class.forName(JDBC_CLASS_NAME);
			conn = DriverManager.getConnection(JDBC_DRIBER, DB_USER, DB_PW);
			pst = conn.prepareStatement(query);
			ResultSet result = pst.executeQuery();
			while (result.next()) {
				List<User> userList = new ArrayList<User>();
				User user = new User(result.getString("user_email"), result.getString("user_pw"),
						result.getString("user_name"));

				userList.add(user);
				list.add(userList);
			}
			result.close();
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
			try {
				pst.close();
			} catch (Exception e) {
			}
		}
		return list;
	}

	// 사용자 삭제
	int deleteUser(String user_email) {
		int deleteNum = 0;
		String query = "DELETE FROM account WHERE user_email = ?;";
		try {
			Class.forName(JDBC_CLASS_NAME);
			conn = DriverManager.getConnection(JDBC_DRIBER, DB_USER, DB_PW);
			pst = conn.prepareStatement(query);
			pst.setString(1, user_email);
			deleteNum = pst.executeUpdate();
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
			return deleteNum;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try { conn.close(); } catch (Exception e) { }
			try { pst.close(); } catch (Exception e) { }		
			System.out.println(deleteNum);
		}
		
		return deleteNum;
	}

	// 특정 사용자 가지고오기 
	User selectUser(String user_email) {
		User user = null;
		String query = "SELECT user_email, user_pw, user_name FROM account WHERE user_email = ?;";
		try {
			Class.forName(JDBC_CLASS_NAME);
			conn = DriverManager.getConnection(JDBC_DRIBER, DB_USER, DB_PW);
			pst = conn.prepareStatement(query);
			pst.setString(1, user_email);
			ResultSet result = pst.executeQuery();

			if(result.next()) {
				user = new User(result.getString("user_email"), result.getString("user_pw"), result.getString("user_pw"));
			}
			result.close();
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
			return user;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try { conn.close(); } catch (Exception e) { }
			try { pst.close(); } catch (Exception e) { }		
		}
		return user;
	}

	// 사용자 업데이트
	int updateUser(User u) {
		int updateNum = 0;
		String query = ""+
		" UPDATE"+
		" account SET"+
		" user_name = ?,"+
		" user_pw = ?"+
		" WHERE user_email = ?;";

		try {
			Class.forName(JDBC_CLASS_NAME);
			conn = DriverManager.getConnection(JDBC_DRIBER, DB_USER, DB_PW);
			pst = conn.prepareStatement(query);
			pst.setString(1, u.getName());
			pst.setString(2, u.getPw());
			pst.setString(3, u.getEmail());
			updateNum = pst.executeUpdate();
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
			return 0;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try { conn.close(); } catch (Exception e) { }
			try { pst.close(); } catch (Exception e) { }		
		}
		return updateNum;
	}

	// 사용자 생성
	int insertUser(User u) {
		int insertNum = 0;
		String query = ""+
		" INSERT INTO"+
		" account(user_email, user_pw, user_name)"+
		" VALUES(?, ?, ?);";

		try {
			Class.forName(JDBC_CLASS_NAME);
			conn = DriverManager.getConnection(JDBC_DRIBER, DB_USER, DB_PW);
			pst = conn.prepareStatement(query);
			pst.setString(1, u.getEmail());
			pst.setString(2, u.getPw());
			pst.setString(3, u.getName());
			insertNum = pst.executeUpdate();
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
			return 0;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try { conn.close(); } catch (Exception e) { }
			try { pst.close(); } catch (Exception e) { }
		}
		return insertNum;
	}
}

class User {
	private String email;
	private String name;
	private String pw;
	
	User(String email, String pw, String name) {
		this.email = email;
		this.name = name;
		this.pw = pw;
	}
	User(String email, String pw) {
		this(email, pw, "");
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String toString() {
		return this.email+", "+this.name+", "+this.pw;
	}
}
