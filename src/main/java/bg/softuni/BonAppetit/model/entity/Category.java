package bg.softuni.BonAppetit.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryName categoryName;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
        setDescription(categoryName);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private void setDescription(CategoryName name){
        String description = "";

        switch (name) {
            case DESSERT -> description = "Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy.";
            case MAIN_DISH -> description = "Heart of the meal, substantial and satisfying; main dish delights taste buds.";
            case COCKTAIL -> description = "Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass.";
        }

        this.description = description;
    }
}
