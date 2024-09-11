package com.login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.dto.ReqRes;
import com.login.entity.OurUsers;
import com.login.service.UserManagementService;

@RestController
public class UserManagmentController {

	@Autowired
	private UserManagementService userManagementService ;
	
	@PostMapping("/auth/register")
	public ResponseEntity<ReqRes> register(@RequestBody ReqRes register){
		return ResponseEntity.ok(userManagementService.register(register));
	}
	

	@PostMapping("/auth/login")
	public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
		return ResponseEntity.ok(userManagementService.login(req));	
	}
	
	@PostMapping("/auth/refresh")
	public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
		return ResponseEntity.ok(userManagementService.refreshToken(req));	
	}
	
	 @GetMapping("/admin/get-all-users")
	    public ResponseEntity<ReqRes> getAllUsers(){
		 System.out.println("Hiiii");
	        return ResponseEntity.ok(userManagementService.getAllUsers());

	    }
	
	@GetMapping("/admin/get-user/{id}")
	public ResponseEntity<ReqRes> getUserByID(@PathVariable Integer id){
		return ResponseEntity.ok(userManagementService.getUsersById(id));	
	}
	
	@PostMapping("/admin/update/{userId}")
	public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody OurUsers req){
		return ResponseEntity.ok(userManagementService.updateUser(userId, req));	
	}
	
	 @GetMapping("/adminuser/get-profile")
	    public ResponseEntity<ReqRes> getMyProfile(){
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String email = authentication.getName();
	        ReqRes response = userManagementService.getMyInfo(email);
	        return  ResponseEntity.status(response.getStatusCode()).body(response);
	    }

	    @DeleteMapping("/admin/delete/{userId}")
	    public ResponseEntity<ReqRes> deleteUSer(@PathVariable Integer userId){
	        return ResponseEntity.ok(userManagementService.deleteUser(userId));
	    }
	
	
}
