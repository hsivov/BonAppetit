package bg.softuni.BonAppetit.service.impl;

import bg.softuni.BonAppetit.config.SessionUser;
import bg.softuni.BonAppetit.model.dto.UserLoginDTO;
import bg.softuni.BonAppetit.model.dto.UserRegisterDTO;
import bg.softuni.BonAppetit.model.entity.User;
import bg.softuni.BonAppetit.repository.UserRepository;
import bg.softuni.BonAppetit.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SessionUser sessionUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, SessionUser sessionUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionUser = sessionUser;
    }

    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {
        boolean existByUsernameOrEmail = userRepository.existsByUsernameOrEmail(
                userRegisterDTO.getUsername(),
                userRegisterDTO.getEmail()
        );

        if (existByUsernameOrEmail) {
            return false;
        }

        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }

        User user = new User();

        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        userRepository.save(user);

        return true;
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<User> byUsername = userRepository.findByUsername(userLoginDTO.getUsername());

        if (byUsername.isEmpty()) {
            return false;
        }

        User user = byUsername.get();

        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            return false;
        }

        sessionUser.login(user);

        return true;
    }

    @Override
    public void logout() {
        sessionUser.logout();
    }
}
