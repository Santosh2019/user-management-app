package org.management.controller;

import org.management.service.UserServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserServiceImple userService;

	/*
	 * @PostMapping("/register") public ResponseEntity<String>
	 * registerUser(@RequestBody UserRegistration user) {
	 * 
	 * String status;
	 * 
	 * boolean isSaved = userService.userRegistraion(user); isSaved = true;
	 * 
	 * if (isSaved) {
	 * 
	 * status = "Registeration Done Successfully";
	 * 
	 * } else {
	 * 
	 * status = "Failded"; }
	 * 
	 * return new ResponseEntity<String>(status, HttpStatus.CREATED); }
	 */

}
