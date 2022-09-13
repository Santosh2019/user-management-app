package org.management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.management.dao.UserMasterRepository;
import org.management.entity.UserMaster;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.management.binding.ActiveAccount;
import com.management.binding.Login;
import com.management.binding.User;

@Service
public class UserServiceImple implements UserService {

	@Autowired
	private UserMasterRepository repo;

	@Override
	public boolean userSave(User user) {
		// TODO Auto-generated method stub

		UserMaster entity = new UserMaster();

		BeanUtils.copyProperties(user, entity);

		entity.setPassword(generateRndmPwd());

		entity.setAccStatus("In-Active");

		UserMaster save = repo.save(entity);

		// TODO Send mail to register email id

		return save.getUserId() != null;
	}

	@Override
	public boolean activeUserAcc(ActiveAccount activeAccount) {
		// TODO Auto-generated method stub
		UserMaster entity = new UserMaster();

		entity.setEmail(activeAccount.getEmail());

		entity.setAccStatus(activeAccount.getTempPwd());

		Example<UserMaster> of = Example.of(entity);

		List<UserMaster> findAll = repo.findAll(of);
		if (findAll.isEmpty()) {

			return false;
		} else {

			UserMaster userMaster = findAll.get(0);
			userMaster.setPassword(activeAccount.getNewPwd());
			userMaster.setAccStatus("Active");
			repo.save(userMaster);
			return true;
		}
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub

		List<UserMaster> findAll = repo.findAll();

		List<User> users = new ArrayList<>();

		for (UserMaster entity : findAll) {

			User user = new User();
			BeanUtils.copyProperties(entity, user);
			users.add(user);
		}
		return users;
	}

	@Override
	public User getUserById(Integer userId) {
		// TODO Auto-generated method stub

		Optional<UserMaster> findById = repo.findById(userId);
		if (findById.isPresent()) {

			User user = new User();

			UserMaster userMaster = findById.get();

			BeanUtils.copyProperties(userMaster, user);

			return user;
		}

		return null;
	}

	@Override
	public boolean deleteUserById(Integer userId) {

		try {

			repo.deleteById(userId);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean changeAccountStatus(Integer userId, String accStatus) {
		Optional<UserMaster> findbyId = repo.findById(userId);

		if (findbyId.isPresent()) {

			UserMaster userMaster = findbyId.get();

			userMaster.setAccStatus(accStatus);
			repo.save(userMaster);
			return true;

		}

		return false;
	}

	@Override
	public String login(Login login) {

		/*
		 * UserMaster entity = new UserMaster();
		 * 
		 * entity.setEmail(login.getEmaiId()); entity.setPassword(login.getPassword());
		 * 
		 * Example<UserMaster> of = Example.of(entity);
		 * 
		 * List<UserMaster> findAll = repo.findAll(of);
		 */

		UserMaster userMaster = repo.findByEmailAndPassword(login.getEmaiId(), login.getPassword());

		if (userMaster == null) {

			return "Invalid Credetianls";
		} else {
			if (userMaster.getAccStatus().equals("Active")) {

				return "Success";

			}

			else {

				return "Account Not Activated";
			}

		}

	}

	@Override
	public String forgotPwd(String email) {

		UserMaster entity = repo.findByEmail(email);

		if (entity == null) {
			return "Invalid Email";
		}

		return null;
	}

	private String generateRndmPwd() {
		// create a string of all characters
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		// create random string builder
		StringBuilder sb = new StringBuilder();

		// create an object of Random class
		Random random = new Random();

		// specify length of random string
		int length = 6;

		for (int i = 0; i < length; i++) {

			// generate random index number
			int index = random.nextInt(alphabet.length());

			// get character specified by index
			// from the string
			char randomChar = alphabet.charAt(index);

			// append the character to string builder
			sb.append(randomChar);
		}
		return sb.toString();

	}
}
