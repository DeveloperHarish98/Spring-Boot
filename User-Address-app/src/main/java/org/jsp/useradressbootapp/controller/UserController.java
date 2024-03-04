package org.jsp.useradressbootapp.controller;

import org.jsp.useradressbootapp.dto.ResponseStructure;
import org.jsp.useradressbootapp.dto.User;
import org.jsp.useradressbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseStructure<User> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User User) {
		return userService.updateUser(User);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<User>> findById(@PathVariable(name = "id") int id) {
		return userService.findById(id);
	}


	@PostMapping("/verifyByPhone")
	public ResponseEntity<ResponseStructure<User>> verifyByPhone(@RequestParam long phone,
			@RequestParam String password) {
		return userService.verifyByPhone(phone, password);
	}

	@PostMapping("/verifyByEmail")
	public ResponseEntity<ResponseStructure<User>> verifyByEmail(@RequestParam String email,
			@RequestParam String password) {
		return userService.verifyByEmail(email, password);
	
	}
}