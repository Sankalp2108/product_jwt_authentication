package com.product.Product.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.product.Product.Repository.UserInfoRepo;
import com.product.Product.model.UserInfo;

@Component
public class UserInfoUserDetailsService implements UserDetailsService{
	
	
	@Autowired
	private UserInfoRepo userInfoRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<UserInfo> userInfo = userInfoRepo.findByName(username);
		return userInfo.map(UserInfoUserDetails::new)
				.orElseThrow(()->new UsernameNotFoundException("User Name Not Found" + username));
	}

}
