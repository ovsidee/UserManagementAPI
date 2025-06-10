package pl.edu.pja.s31719.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.s31719.model.User;

import java.util.Optional;

public interface UserRepositorySpringData extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}