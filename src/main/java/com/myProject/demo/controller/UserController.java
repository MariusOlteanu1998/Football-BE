package com.myProject.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.myProject.demo.model.UserModel;
import com.myProject.demo.service.UserService;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
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
	public void deleteUserById(@PathVariable int id) {
		userService.deleteUserById(id);
	}
	
}
