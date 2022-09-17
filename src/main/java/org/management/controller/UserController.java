package org.management.controller;

import java.io.IOException;
import java.util.List;

import org.management.props.AppProperties;
import org.management.service.UserServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.binding.ActiveAccount;
import com.management.binding.Login;
import com.management.binding.User;

@RestController
public class UserController {

	@Autowired
	private UserServiceImple userService;

	public UserController(UserServiceImple userService, AppProperties appProp) {
		super();
		this.userService = userService;
		appProp.getMessages();
	}

	@PostMapping("/registration")
	public ResponseEntity<String> registration(@RequestBody User user) throws IOException {
		boolean isSaved = userService.userSave(user);
		if (isSaved) {
			return new ResponseEntity<>("Registration done Successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Registration Failed", HttpStatus.BAD_GATEWAY);
		}
	}

	@PostMapping("/activate")
	public ResponseEntity<String> activateAccount(@RequestBody ActiveAccount activeAccount) {
		boolean isActive = userService.activeUserAcc(activeAccount);
		if (isActive) {
			return new ResponseEntity<>("Account Activated", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid Temporary Pwd", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> allUsers = userService.getAllUsers();
		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<User> findUserById(@PathVariable Integer userId) {
		User isUser = userService.getUserById(userId);
		return new ResponseEntity<>(isUser, HttpStatus.OK);
	}

	@DeleteMapping("/user/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
		boolean isDeleted = userService.deleteUserById(userId);
		if (isDeleted) {
			return new ResponseEntity<String>("Deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/status/{userId}/{accStatus}")
	public ResponseEntity<String> updateStatus(@PathVariable Integer userId, @PathVariable String accStatus) {
		boolean isChangeStatus = userService.changeAccountStatus(userId, accStatus);
		if (isChangeStatus) {
			return new ResponseEntity<String>("Status Change", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Faied", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Login login) {
		String isLogin = userService.login(login);
		return new ResponseEntity<String>(isLogin, HttpStatus.OK);
	}

	@GetMapping("/forgotPwd/{email}")
	public ResponseEntity<String> forgotPwd(@PathVariable String email) throws IOException {
		String status = userService.forgotPwd(email);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
}
