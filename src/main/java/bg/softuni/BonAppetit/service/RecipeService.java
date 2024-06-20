package bg.softuni.BonAppetit.service;

import bg.softuni.BonAppetit.model.dto.HomeRecipesViewModel;
import bg.softuni.BonAppetit.model.dto.RecipeAddDTO;

public interface RecipeService {
    boolean addRecipe(RecipeAddDTO recipeAddDTO);

    HomeRecipesViewModel getHomeViewData();

    void addToFavourites(long id, long recipeId);
}
