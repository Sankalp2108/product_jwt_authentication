  package com.product.Product.Controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.Product.dto.UserAuth;
import com.product.Product.filter.JwtAuthFilter;
import com.product.Product.Repository.ProductRepo;
import com.product.Product.Repository.UserInfoRepo;
import com.product.Product.model.Product;
import com.product.Product.model.UserInfo;
import com.product.Product.service.JwtService;
import com.product.Product.service.UserInfoService;

import jakarta.servlet.http.HttpServletRequest;

import javax.xml.crypto.Data;

  @RestController
public class ProductController {
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;


	//Creating the Product
	@PostMapping("/product")
	public ResponseEntity<?> create(@RequestBody Product pro,HttpServletRequest httpServletRequest)
	{
		String userName = (String)httpServletRequest.getAttribute(jwtAuthFilter.passKey);
		pro.createdAt();
		pro.updatedAt();
		pro.setCreatedBy(userName);
		pro.setUpdatedBy(userName);
		productRepo.save(pro);
		return new ResponseEntity<>(pro , HttpStatus.OK);
	}
	
	//Creating the User Info
	@PostMapping("/userinfo")
	public ResponseEntity<?> create(@RequestBody UserInfo userInfo)
	{
		userInfoService.addUser(userInfo);
		return new ResponseEntity<>(userInfo , HttpStatus.OK);
	}
	
	
	//Reading the Product
	@GetMapping("/product")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> read()
	{
		List<Product> product = productRepo.findAll();
		if(product.size()!=0)
		{
			return new ResponseEntity<List<Product>>(product , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("No Product Available" , HttpStatus.NOT_FOUND);
		}
		
	}
	
	//Reading the Product By Id
	@GetMapping("/product/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<?> readById(@PathVariable String id)
	{
		Optional<Product> product = productRepo.findById(id);
		if(product.isPresent())
		{
			return new ResponseEntity<>(product.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No Product With Such id : " + id , HttpStatus.NOT_FOUND);
		}
	}
	
	
	//Reading the Product By Id
	@DeleteMapping("/product/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> deleteById(@PathVariable String id)
	{
		Optional<Product> product = productRepo.findById(id);
		if(product.isPresent())
		{
			productRepo.deleteById(id);
			return new ResponseEntity<>("Delete Successfully "+id,HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No Product With Such id : " + id , HttpStatus.NOT_FOUND);
		}
	}
	
	//Update the Product
	@PutMapping("/product/{id}")
	public ResponseEntity<?> updateById(@PathVariable String id , @RequestBody Product pro ,HttpServletRequest httpServletRequest)
	{
		String userName = (String)httpServletRequest.getAttribute(jwtAuthFilter.passKey);
		Optional<Product> product = productRepo.findById(id);
		if(product.isPresent())
		{
			Product productToSave = product.get();
			productToSave.setPrice(pro.getPrice() != 0 ? pro.getPrice() : productToSave.getPrice());
			productToSave.setProduct(pro.getProduct()!= null ? pro.getProduct() : productToSave.getProduct());
			productToSave.setId(pro.getId() != null ? pro.getId() : productToSave.getId());
			productToSave.setDesc(pro.getDesc() != null ? pro.getDesc() : productToSave.getDesc());
			productToSave.setCreatedAt(pro.getCreatedAt() != null ? pro.getCreatedAt() : productToSave.getCreatedAt());
			productToSave.setUpdatedAt(pro.getUpdatedAt() != null ? pro.getUpdatedAt() : productToSave.updatedAt());
			productToSave.setUpdatedBy(userName);
			productRepo.save(productToSave);
			return new ResponseEntity<>("Successfully Upadate" , HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No Element With Such Id : " + id , HttpStatus.NOT_FOUND);
		}
	}
	
	//Patch Method
	@PatchMapping("/product/{id}")
	public ResponseEntity<?> patchById(@PathVariable String id , @RequestBody Product pro,HttpServletRequest httpServletRequest)
	{
		String userName = (String)httpServletRequest.getAttribute(jwtAuthFilter.passKey);
		Optional<Product> product = productRepo.findById(id);
		if(product.isPresent())
		{
			Product productToSave = product.get();
			productToSave.setPrice(pro.getPrice() != 0 ? pro.getPrice() : productToSave.getPrice());
			productToSave.setProduct(pro.getProduct()!= null ? pro.getProduct() : productToSave.getProduct());
			productToSave.setId(pro.getId() != null ? pro.getId() : productToSave.getId());
			productToSave.setDesc(pro.getDesc() != null ? pro.getDesc() : productToSave.getDesc());
			productToSave.setCreatedAt(pro.getCreatedAt() != null ? pro.getCreatedAt() : productToSave.createdAt());
			productToSave.setUpdatedAt(pro.getUpdatedAt() != null ? pro.getUpdatedAt() : productToSave.updatedAt());
			productToSave.setUpdatedBy(userName);
			productRepo.save(productToSave);
			return new ResponseEntity<>("Successfully Upadate" , HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No Element With Such Id : " + id , HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/product/string/{product}")
	public List<Product> findByProduct(@PathVariable String product)
	{
		return productRepo.findByProduct(product);
		
	}
	
	@GetMapping("/product/stringAll/{product}")
	public List<Product> findAllByProduct(@PathVariable String product)
	{
		return productRepo.findAllByProduct(product);
	}
	
	@PostMapping("/authenticate")
	public String authenticateAndGettoken(@RequestBody UserAuth userAuth)
	{
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuth.getUsername(),userAuth.getPassword()));
		if(authentication.isAuthenticated())
		{
			return jwtService.generateToken(userAuth.getUsername());
		}
		else
		{
			throw new UsernameNotFoundException("Invalid User Request !!");
		}
		
	}
	
//    @RequestMapping("/create")
//    public String index(HttpServletRequest httpServletRequest) {
//        return (String) httpServletRequest.getAttribute(jwtAuthFilter.passKey);
//    }
    
//    @GetMapping("/token")
//    public ResponseEntity<?> findByToken(HttpServletRequest httpServletRequest) {
//		List<String> roles = (List<String>) httpServletRequest.getAttribute("roles");
//		List<Product> product = productRepo.findAll();
//			if ("ROLE_ADMIN" == roles.get(0)) {
//				return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<>("This Token Has No Authority To View Product", HttpStatus.NOT_FOUND);
//			}
//	}
    
//    @PutMapping("/token/{id}")
//    public ResponseEntity<?> updateByToken(HttpServletRequest httpServletRequest , @PathVariable String id)
//    {
//    	String userName = (String)httpServletRequest.getAttribute(jwtAuthFilter.passKey);
//		Optional<Product> product = productRepo.findById(id);
//		if(product.isPresent())
//		{
//			Product productToSave = product.get();
//			productToSave.setUpdatedBy(userName);
//			productToSave.setUpdatedAt(productToSave.updatedAt());
//			productRepo.save(productToSave);
//			return new ResponseEntity<>("Successfully Updated : " + id , HttpStatus.OK);
//		}
//		else
//		{
//			return new ResponseEntity<>("No Product With Such Id : " + id , HttpStatus.NOT_FOUND);
//		}
//    	
//    }
    
    
	
	
}
	

		

