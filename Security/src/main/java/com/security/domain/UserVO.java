package com.security.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class UserVO implements UserDetails {
	private String id;
	private String pw;
	private String name;
	private List<String> roles;
	
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", pw=" + pw + ", name=" + name + ", role=" + roles + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> grants = new ArrayList<SimpleGrantedAuthority>();
        for(String role : roles) {
        	grants.add(new SimpleGrantedAuthority(role));
        } 
        return grants;
	}

	@Override
	public String getPassword() {
		return pw;
	}
	@Override
	public String getUsername() {
		return name;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}
