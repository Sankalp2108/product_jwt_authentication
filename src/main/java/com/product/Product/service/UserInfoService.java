package com.product.Product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.product.Product.Repository.UserInfoRepo;
import com.product.Product.model.UserInfo;

@Service
public class UserInfoService {
	
	@Autowired
	private UserInfoRepo userInfoRepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public String addUser(UserInfo userInfo)
	{
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfoRepo.save(userInfo);
		return "User Added Successfully";
	}

}
