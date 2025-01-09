package com.ting.user.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ting.user.dto.UserDTO;
import com.ting.user.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public UserDTO getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@PostMapping
	public UserDTO createUser(@RequestBody UserDTO UserDTO) {
		return userService.createUser(UserDTO);
	}

	@GetMapping("/find")
	public UserDTO getUserByEmailOrUsername(@RequestParam(required = false) String email,
			@RequestParam(required = false) String username) {
		if (email != null) {
			return userService.findByEmailOrUsername(email, null);
		} else if (username != null) {
			return userService.findByEmailOrUsername(null, username);
		} else {
			throw new IllegalArgumentException("Either email or username must be provided");
		}
	}

	@PutMapping("/{id}")
	public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO UserDTO) {
		return userService.updateUser(id, UserDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
