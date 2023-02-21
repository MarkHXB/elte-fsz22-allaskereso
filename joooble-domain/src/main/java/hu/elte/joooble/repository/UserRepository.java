package hu.elte.joooble.repository;

import hu.elte.joooble.domain.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByCredential_Email(String email);
}
