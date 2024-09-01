package repository;

import entity.User;

public interface UserRepository {
	public void addUser(String username, String password);
	public User findUserByUsername(String username);
}
