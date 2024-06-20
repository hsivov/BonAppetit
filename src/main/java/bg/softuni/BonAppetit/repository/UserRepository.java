package bg.softuni.BonAppetit.repository;

import bg.softuni.BonAppetit.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
}
