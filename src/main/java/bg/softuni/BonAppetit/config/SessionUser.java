package bg.softuni.BonAppetit.config;

import bg.softuni.BonAppetit.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SessionUser {
    private long id;
    private String username;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean isLogged() {
        return id != 0;
    }

    public void login(User user) {
        this.username = user.getUsername();
        this.id = user.getId();
    }

    public void logout() {
        this.username = null;
        this.id = 0;
    }
}
