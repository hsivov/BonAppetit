package bg.softuni.BonAppetit.model.dto;

import bg.softuni.BonAppetit.model.entity.CategoryName;
import bg.softuni.BonAppetit.model.entity.Recipe;

public class RecipeDTO {
    private Long id;
    private String name;
    private String ingredients;
    private CategoryName category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }

    public static RecipeDTO createFromEntity(Recipe recipe){
        RecipeDTO recipeDTO = new RecipeDTO();

        recipeDTO.setId(recipe.getId());
        recipeDTO.setName(recipe.getName());
        recipeDTO.setIngredients(recipe.getIngredients());
        recipeDTO.setCategory(recipe.getCategory().getCategoryName());

        return recipeDTO;
    }
}
