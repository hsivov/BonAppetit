package bg.softuni.BonAppetit.repository;

import bg.softuni.BonAppetit.model.dto.RecipeDTO;
import bg.softuni.BonAppetit.model.entity.Category;
import bg.softuni.BonAppetit.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByCategory(Optional<Category> category);
}
