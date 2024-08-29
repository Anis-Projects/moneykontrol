package repository;

import entity.User;

public interface UserRepository {
	public void addUser(User user);
	public User findUserByUsername(String username);
}
