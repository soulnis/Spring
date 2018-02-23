package com.security.service;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.domain.UserVO;
import com.security.persistence.UserDAO;

@Service
public class UserService implements UserDetailsService {

	@Inject
	private UserDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserVO user = dao.login(username);
       if(null == user) {
            throw new UsernameNotFoundException("User Not Found");
        }
       return user;
	}
}
