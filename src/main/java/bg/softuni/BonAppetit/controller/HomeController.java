package bg.softuni.BonAppetit.controller;

import bg.softuni.BonAppetit.config.SessionUser;
import bg.softuni.BonAppetit.model.dto.HomeRecipesViewModel;
import bg.softuni.BonAppetit.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final RecipeService recipeService;
    private final SessionUser sessionUser;

    public HomeController(RecipeService recipeService, SessionUser sessionUser) {
        this.recipeService = recipeService;
        this.sessionUser = sessionUser;
    }

    @GetMapping("/")
    public String index() {
        if (sessionUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public ModelAndView home() {
        if (!sessionUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        HomeRecipesViewModel viewModel = recipeService.getHomeViewData();

        return new ModelAndView("home", "recipes", viewModel);
    }
}
