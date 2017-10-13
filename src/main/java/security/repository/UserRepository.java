package security.repository;

import org.springframework.data.repository.CrudRepository;
import security.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
