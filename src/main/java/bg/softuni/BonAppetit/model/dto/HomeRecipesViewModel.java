package bg.softuni.BonAppetit.model.dto;

import java.util.List;

public class HomeRecipesViewModel {
    private List<RecipeDTO> mainDishes;
    private List<RecipeDTO> deserts;
    private List<RecipeDTO> cocktails;
    private List<RecipeDTO> favourites;

    public HomeRecipesViewModel(List<RecipeDTO> mainDishes, List<RecipeDTO> deserts, List<RecipeDTO> cocktails, List<RecipeDTO> favourites) {
        this.mainDishes = mainDishes;
        this.deserts = deserts;
        this.cocktails = cocktails;
        this.favourites = favourites;
    }

    public List<RecipeDTO> getMainDishes() {
        return mainDishes;
    }

    public void setMainDishes(List<RecipeDTO> mainDishes) {
        this.mainDishes = mainDishes;
    }

    public List<RecipeDTO> getDeserts() {
        return deserts;
    }

    public void setDeserts(List<RecipeDTO> deserts) {
        this.deserts = deserts;
    }

    public List<RecipeDTO> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<RecipeDTO> cocktails) {
        this.cocktails = cocktails;
    }

    public List<RecipeDTO> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<RecipeDTO> favourites) {
        this.favourites = favourites;
    }
}
