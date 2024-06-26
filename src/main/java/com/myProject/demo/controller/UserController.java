package com.myProject.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.demo.model.UserModel;
import com.myProject.demo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping
	public List<UserModel> getAllUsers(){
		
		return userService.getAllUsers();
	}

	
	@GetMapping("/{id}")
	public Optional<UserModel> getAllUsersById(@PathVariable int id){
		
		return userService.getAllUsersById(id);
	}
	
	@PutMapping("/{id}")
	public Optional<UserModel> updateUserById(@PathVariable int id, @RequestBody UserModel user) {
		
		return userService.updateUsers(id, user);
	}
	
	@PostMapping
	public UserModel insert(@RequestBody UserModel user) {
		
		return userService.insertUser(user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable int id) {
		
		userService.deleteUser(id);
	}
	
}
