package decentrathon.telegram_mini_app.controller;

import decentrathon.telegram_mini_app.dto.ThemeDTO;
import decentrathon.telegram_mini_app.entity.Theme;
import decentrathon.telegram_mini_app.service.ThemeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-clever-coin")
@RequiredArgsConstructor
@Slf4j
public class ThemeRestController {

    private final ThemeService themeService;

    @PostMapping("/createTheme")
    public ResponseEntity<?> createTheme(@RequestBody ThemeDTO dto) {
        if(dto != null) {
            String themeName = dto.themeName();
            if(themeName != null && !themeName.isEmpty()) {
                Theme theme = this.themeService.createTheme(themeName);

                if(theme != null) {
                    return ResponseEntity
                            .ok()
                            .body(theme);
                }
            }
        }

        return ResponseEntity
                .badRequest()
                .body("Ошибка при создании темы, либо тема с таким названием уже существует!");
    }

    @PostMapping("/getThemes")
    public ResponseEntity<?> getThemes() {
        return ResponseEntity
                .ok()
                .body(this.themeService.findAllThemes());
    }

    @GetMapping("/getThemesNames")
    public ResponseEntity<?> getThemesNames() {
        List<String> themesNames = this.themeService
                .findAllThemes()
                .stream()
                .map(Theme::getThemeName)
                .toList();

        return ResponseEntity
                .ok()
                .body(themesNames);
    }

}
