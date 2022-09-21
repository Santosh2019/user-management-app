package org.management.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.management.dao.UserMasterRepository;
import org.management.entity.UserMaster;
import org.management.utils.EmailUtils;
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

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public boolean userSave(User user) throws IOException {
		UserMaster entity = new UserMaster();
		BeanUtils.copyProperties(user, entity);

		entity.setPassword(generateRndmPwd());
		entity.setAccStatus("In-Active");

		UserMaster save = repo.save(entity);
		String subject = "Your Registeration Success";
		String filename = "REG-EMAIL-BODY.txt";
		String body = readEmailBody(entity.getFullName(), entity.getPassword(), filename);
		emailUtils.sendEmail(user.getEmail(), subject, body);
		System.out.println(body);
		return save.getUserId() != null;
	}

	@Override
	public boolean activeUserAcc(ActiveAccount activeAccount) {
		UserMaster entity = new UserMaster();
		entity.setEmail(activeAccount.getEmail());
		entity.setPassword(activeAccount.getTempPwd());
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
		UserMaster userMaster = repo.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (userMaster == null) {
			return "Invalid Credetianls";
		} else {
			if (userMaster.getAccStatus().equals("Active")) {
				return "Login Success";
			} else {
				return "Account Not Activated";
			}
		}
	}

	@Override
	public String forgotPwd(String email) throws IOException {
		UserMaster entity = repo.findByEmail(email);
		System.out.println(entity);
		if (entity == null) {
			return "Invalid Email";
		}
		String subject = "Forget Password";
		String filename = "RECOVER-EMAIL-BODY.txt";
		String body = readEmailBody(entity.getFullName(), entity.getPassword(), filename);
		boolean sendEmail = emailUtils.sendEmail(email, subject, body);
		if (sendEmail) {
			return "Password sent to your registered email Id";
		}
		return null;
	}

	private String generateRndmPwd() {
		String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		int length = 7;
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(alphaNumeric.length());
			char randomChar = alphaNumeric.charAt(index);
			sb.append(randomChar);
		}
		return sb.toString();
	}

	private String readEmailBody(String fullName, String pwd, String filename) throws IOException {
		String mailBody = null;
		String url = "";
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			StringBuffer buffer = new StringBuffer();
			String line = br.readLine();
			while (line != null) {
				buffer.append(line);
				line = br.readLine();
			}
			br.close();
			mailBody = buffer.toString();
			mailBody = mailBody.replace("{FULLNAME}", fullName);
			mailBody = mailBody.replace("{TEMP-PWD}", pwd);
			mailBody = mailBody.replace("{URL}", url);
			mailBody = mailBody.replace("{PWD}", pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mailBody;
	}
}
