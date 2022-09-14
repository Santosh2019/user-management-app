package org.management.controller;

import org.management.props.AppProperties;
import org.management.service.UserServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.binding.User;
import com.management.constants.AppConstants;

@RestController
public class UserController {

	@Autowired
	private UserServiceImple userService;

	public UserController(UserServiceImple userService, AppProperties appProp) {
		super();
		this.userService = userService;
		appProp.getMessages();
		System.out.println(appProp.getMessages());
	}

	@PostMapping("/register")
	public ResponseEntity<String> save(@RequestBody User user) {

		String status = AppConstants.EMPTY_STR;

		boolean isSaved = userService.userSave(user);

		if (isSaved) {

			status = AppConstants.REGISTRATION_SAVE_SUCCESS;

		} else {

			status = AppConstants.REGISTRATION_FAILED;

		}

		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}

}
