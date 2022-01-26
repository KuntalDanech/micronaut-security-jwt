package micronaut.security.repository;

import java.util.Optional;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import micronaut.security.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByUserName(String userName);
}