package decentrathon.telegram_mini_app.service;

import decentrathon.telegram_mini_app.entity.Theme;

import java.util.List;
import java.util.Optional;

public interface ThemeService {

    Theme createTheme(String themeName);

    List<Theme> findAllThemes();

    Optional<Theme> findThemeById(int id);

}
