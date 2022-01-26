package micronaut.security.service;

import java.util.Optional;

import micronaut.security.model.User;

public interface IUserService {
	public User registerUser(User user);
	Optional<User> findByUserName(String userName);
}