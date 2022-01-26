package micronaut.security.service;

import java.util.Optional;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import micronaut.security.model.User;
import micronaut.security.repository.UserRepository;

@Slf4j
@Singleton
public class UserServiceImpl implements IUserService {

	@Inject
	UserRepository userRepository;
	
	@Override
	public User registerUser(User user) {
		log.info("User Registered "+user);
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findByUserName(String userName) {
		log.info("Find by user name "+userName);
		return userRepository.findByUserName(userName);
	}

}
