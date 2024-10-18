package decentrathon.telegram_mini_app.repository;

import decentrathon.telegram_mini_app.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Integer> {

    Theme findByThemeNameIgnoreCase(String name);

}
