package bg.softuni.BonAppetit.service;

import bg.softuni.BonAppetit.model.dto.UserLoginDTO;
import bg.softuni.BonAppetit.model.dto.UserRegisterDTO;

public interface UserService {
    boolean register(UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDTO userLoginDTO);

    void logout();
}
