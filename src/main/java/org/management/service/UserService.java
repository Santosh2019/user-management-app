package org.management.service;

import java.io.IOException;
import java.util.List;

import com.management.binding.ActiveAccount;
import com.management.binding.Login;
import com.management.binding.User;

public interface UserService {

	public boolean userSave(User user)throws IOException;

	public boolean activeUserAcc(ActiveAccount activeAccount);

	public List<User> getAllUsers();

	public User getUserById(Integer userId);

	public boolean deleteUserById(Integer userId);

	public boolean changeAccountStatus(Integer userId, String accStatus);

	public String login(Login login);

	public String forgotPwd(String email)throws IOException;

}