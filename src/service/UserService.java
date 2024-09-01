package service;

import entity.User;
import repository.UserImplRepository;

public class UserService {
	private UserImplRepository userRepository;

	public UserService(UserImplRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	public void addUser(String username, String password) {
		userRepository.addUser(username, password);
	}
}
