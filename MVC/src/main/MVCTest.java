package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MVCTest {

	static private final String HOST = "localhost";
	static private final String PORT = "3306";
	static private final String DB_NAME = "homepage";
	static private final String JDBC_DRIBER = "jdbc:mysql://"+HOST+":"+PORT+"/"+DB_NAME+"?useUnicode=true&characterEncoding=utf-8"; 
	static private final String DB_USER = "root";
	static private final String DB_PW = "";
	static private final String JDBC_CLASS_NAME = "com.mysql.jdbc.Driver";

	static private Connection conn = null;
	static private PreparedStatement pst = null;

	public static void main(String[] arg) {
/*		User u = new User("test@test.com", "1234", "test");
//		System.out.println(u);

//		System.out.println(insertUser(u));

		u.setName("updateName");
		u.setPw("updatePw");
		System.out.println(updateUser(u));*/
		

		// 사용자삭제실행
//		User u = selectUser("test@test.com");
//		System.out.println(u);
//
//
//		try {
//			deleteUser(u.getEmail()); // select결과가 없을경우 nullPointException
////			System.out.println(selectUser(u.getName()));
//		} catch (NullPointerException ne) {
//			System.out.println("없는사용자.");
//		}

		// 모든사용자 가지고오기.
		List<List<User>> list = selectAllUaser();
		System.out.println(list);

	}

	static List<List<User>> selectAllUaser() {
		List<List<User>> list = new ArrayList<List<User>>();
//		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		String query = "SELECT user_email, user_pw, user_name FROM account;";
		try {
			Class.forName(JDBC_CLASS_NAME);
			conn = DriverManager.getConnection(JDBC_DRIBER, DB_USER, DB_PW);
			pst = conn.prepareStatement(query);
			ResultSet result = pst.executeQuery();
			while(result.next()) {
				List<User> userList = new ArrayList<User>();
				User user = new User(result.getString("user_email"), result.getString("user_pw"), result.getString("user_name"));
				
//				Map<String, String> map = new HashMap<String, String>();
//				map.put("user_email", result.getString("user_email"));
//				map.put("user_pw", result.getString("user_pw"));
//				map.put("user_name", result.getString("user_name"));
				
				userList.add(user);
				list.add(userList);
//				mapList.add(map);
			}
			result.close();
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try { conn.close(); } catch (Exception e) {}
			try { pst.close(); } catch (Exception e) {}
		}
//		System.out.println(mapList);
		return list;
	}

	// 사용자 삭제
	static int deleteUser(String user_email) {
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

	// 사용자 가지고오기 
	static User selectUser(String user_email) {
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
	static int updateUser(User u) {
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
	static int insertUser(User u) {
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

//class User {
//	private String email;
//	private String name;
//	private String pw;
//	
//	User(String email, String pw, String name) {
//		this.email = email;
//		this.name = name;
//		this.pw = pw;
//	}
//	User(String email, String pw) {
//		this(email, pw, "");
//	}
//	
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getPw() {
//		return pw;
//	}
//	public void setPw(String pw) {
//		this.pw = pw;
//	}
//	public String toString() {
//		return this.email+", "+this.name+", "+this.pw;
//	}
//}
