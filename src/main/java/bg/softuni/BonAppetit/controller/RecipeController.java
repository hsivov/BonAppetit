package bg.softuni.BonAppetit.controller;

import bg.softuni.BonAppetit.config.SessionUser;
import bg.softuni.BonAppetit.model.dto.RecipeAddDTO;
import bg.softuni.BonAppetit.model.entity.CategoryName;
import bg.softuni.BonAppetit.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RecipeController {
    private final RecipeService recipeService;
    private final SessionUser sessionUser;

    public RecipeController(RecipeService recipeService, SessionUser sessionUser) {
        this.recipeService = recipeService;
        this.sessionUser = sessionUser;
    }

    @ModelAttribute("recipeData")
    public RecipeAddDTO emptyDTO() {
        return new RecipeAddDTO();
    }

    @GetMapping("/recipe/add")
    public String addRecipe(Model model) {
        if (!sessionUser.isLogged()) {
            return "redirect:/";
        }

        model.addAttribute("categories", CategoryName.values());

        return "recipe-add";
    }

    @PostMapping("/recipe/add")
    public String addRecipe(@Valid RecipeAddDTO recipeAddDTO, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes){
        if (!sessionUser.isLogged()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeData", recipeAddDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.recipeData", bindingResult
            );

            return "redirect:/recipe/add";
        }

        boolean success = recipeService.addRecipe(recipeAddDTO);

        if (!success) {
            return "redirect:/recipe/add";
        }

        return "redirect:/home";
    }

    @PostMapping("/add-to-favourites/{recipeId}")
    public String addFavourite(@PathVariable long recipeId) {
        if (!sessionUser.isLogged()) {
            return "redirect:/";
        }

        recipeService.addToFavourites(sessionUser.getId(), recipeId);

        return "redirect:/home";
    }
}
