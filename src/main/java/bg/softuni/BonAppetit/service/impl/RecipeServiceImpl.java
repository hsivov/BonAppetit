package bg.softuni.BonAppetit.service.impl;

import bg.softuni.BonAppetit.config.SessionUser;
import bg.softuni.BonAppetit.model.dto.HomeRecipesViewModel;
import bg.softuni.BonAppetit.model.dto.RecipeAddDTO;
import bg.softuni.BonAppetit.model.dto.RecipeDTO;
import bg.softuni.BonAppetit.model.entity.Category;
import bg.softuni.BonAppetit.model.entity.CategoryName;
import bg.softuni.BonAppetit.model.entity.Recipe;
import bg.softuni.BonAppetit.model.entity.User;
import bg.softuni.BonAppetit.repository.CategoryRepository;
import bg.softuni.BonAppetit.repository.RecipeRepository;
import bg.softuni.BonAppetit.repository.UserRepository;
import bg.softuni.BonAppetit.service.RecipeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final SessionUser sessionUser;

    public RecipeServiceImpl(CategoryRepository categoryRepository,
                             UserRepository userRepository,
                             RecipeRepository recipeRepository, SessionUser sessionUser) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.sessionUser = sessionUser;
    }

    @Override
    public boolean addRecipe(RecipeAddDTO recipeAddDTO) {
        Optional<Category> byCategoryName = categoryRepository.findByCategoryName(recipeAddDTO.getCategory());
        Optional<User> byUsername = userRepository.findByUsername(sessionUser.getUsername());

        if (byCategoryName.isPresent() && byUsername.isPresent()) {
            Recipe recipe = new Recipe();

            recipe.setName(recipeAddDTO.getName());
            recipe.setIngredients(recipeAddDTO.getIngredients());
            recipe.setCategory(byCategoryName.get());
            recipe.setAddedBy(byUsername.get());

            recipeRepository.save(recipe);

            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void addToFavourites(long id, long recipeId) {

        Optional<User> userOptional = userRepository.findById(id);
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (userOptional.isEmpty() || recipeOptional.isEmpty()) {
            return;
        }

        userOptional.get().addToFavourites(recipeOptional.get());

        userRepository.save(userOptional.get());
    }

    @Override
    @Transactional
    public HomeRecipesViewModel getHomeViewData() {
        Optional<Category> mainDish = categoryRepository.findByCategoryName(CategoryName.MAIN_DISH);
        Optional<Category> cocktail = categoryRepository.findByCategoryName(CategoryName.COCKTAIL);
        Optional<Category> desert = categoryRepository.findByCategoryName(CategoryName.DESSERT);

        if (mainDish.isEmpty() || cocktail.isEmpty() || desert.isEmpty()) {
            return null;
        }

        List<RecipeDTO> mainDishes = recipeRepository.findByCategory(mainDish).stream()
                .map(RecipeDTO::createFromEntity)
                .toList();

        List<RecipeDTO> cocktails = recipeRepository.findByCategory(cocktail).stream()
                .map(RecipeDTO::createFromEntity)
                .toList();

        List<RecipeDTO> deserts = recipeRepository.findByCategory(desert).stream()
                .map(RecipeDTO::createFromEntity)
                .toList();

        Optional<User> user = userRepository.findByUsername(sessionUser.getUsername());

        if (user.isEmpty()) {
            return null;
        }

        List<RecipeDTO> favoriteRecipes = user.get().getFavoriteRecipes().stream()
                .map(RecipeDTO::createFromEntity)
                .sorted(Comparator.comparing(RecipeDTO::getId))
                .toList();

        return new HomeRecipesViewModel(mainDishes, deserts, cocktails, favoriteRecipes);
    }
}
