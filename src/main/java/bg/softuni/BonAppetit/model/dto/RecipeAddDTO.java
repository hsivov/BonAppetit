package bg.softuni.BonAppetit.model.dto;

import bg.softuni.BonAppetit.model.entity.CategoryName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RecipeAddDTO {
    @Size(min = 2, max = 40)
    private String name;
    @Size(min = 2, max = 80)
    private String ingredients;
    @NotNull
    private CategoryName category;

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
}
