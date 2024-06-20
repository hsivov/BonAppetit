package bg.softuni.BonAppetit.repository;

import bg.softuni.BonAppetit.model.entity.Category;
import bg.softuni.BonAppetit.model.entity.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(CategoryName name);
}
