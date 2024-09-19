package com.myProject.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.myProject.demo.model.UserModel;
import com.myProject.demo.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserModel> getAllUsers(){
		logger.info("Received request to fetch all users");
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public Optional<UserModel> getAllUsersById(@PathVariable int id){
		logger.info("Received request to fetch user with ID: {}", id);
		return userService.getAllUsersById(id);
	}

	@PutMapping("/{id}")
	public Optional<UserModel> updateUserById(@PathVariable int id, @RequestBody UserModel user) {
		logger.info("Received request to update user with ID: {}", id);
		return Optional.ofNullable(userService.updateUserById(id, user));
	}

	@PostMapping
	public UserModel insert(@RequestBody UserModel user) {
		logger.info("Received request to insert new user with ID: {}", user.getId());
		return userService.insertUser(user);
	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable int id) {
		logger.info("Received request to delete user with ID: {}", id);
		userService.deleteUserById(id);
	}
}
