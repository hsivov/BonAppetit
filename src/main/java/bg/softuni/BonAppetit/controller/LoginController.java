package bg.softuni.BonAppetit.controller;

import bg.softuni.BonAppetit.config.SessionUser;
import bg.softuni.BonAppetit.model.dto.UserLoginDTO;
import bg.softuni.BonAppetit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class LoginController {
    private final UserService userService;
    private final SessionUser sessionUser;

    public LoginController(UserService userService, SessionUser sessionUser) {
        this.userService = userService;
        this.sessionUser = sessionUser;
    }

    @ModelAttribute("loginData")
    public UserLoginDTO createEmptyDTO() {
        return new UserLoginDTO();
    }

    @GetMapping("/login")
    public String login() {
        if (sessionUser.isLogged()) {
            return "redirect:/home";
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO, BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (sessionUser.isLogged()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginData", userLoginDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginData", bindingResult
            );

            return "redirect:/users/login";
        }

        boolean isSuccessfulLogin = userService.login(userLoginDTO);

        if (!isSuccessfulLogin) {
            redirectAttributes.addFlashAttribute("loginData", userLoginDTO);
            redirectAttributes.addFlashAttribute("hasLoginError", true);

            return "redirect:/users/login";
        }

        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logout() {
        userService.logout();

        return "redirect:/";
    }
}
