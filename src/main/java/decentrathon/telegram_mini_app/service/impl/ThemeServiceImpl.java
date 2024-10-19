package decentrathon.telegram_mini_app.service.impl;

import decentrathon.telegram_mini_app.entity.Theme;
import decentrathon.telegram_mini_app.repository.ThemeRepository;
import decentrathon.telegram_mini_app.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    @Override
    public Theme createTheme(String themeName) {
        if(themeName != null && !themeName.isEmpty()) {
            if(!this.themeRepository.existsByThemeNameIgnoreCase(themeName)) {
                Theme theme = new Theme(themeName);
                return this.themeRepository.save(theme);
            }
        }

        return null;
    }

    @Override
    public List<Theme> findAllThemes() {
        return this.themeRepository.findAll();
    }

    @Override
    public Optional<Theme> findThemeById(int id) {
        return this.themeRepository.findById(id);
    }
}
