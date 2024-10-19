package decentrathon.telegram_mini_app.service;

import decentrathon.telegram_mini_app.dto.TheoryDTO;
import decentrathon.telegram_mini_app.entity.Theme;
import decentrathon.telegram_mini_app.entity.Theory;
import decentrathon.telegram_mini_app.repository.ThemeRepository;
import decentrathon.telegram_mini_app.repository.TheoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TheoryCreateService {
    @Autowired
    private TheoryService theoryService;
    @Autowired
    private ThemeRepository themeRepository;
    public ResponseEntity<String> theoryCreate(
            String title,
            String content,
            int difficulty,
            String themeName
    ) {
        if (themeRepository.findByThemeNameIgnoreCase(themeName) == null) {
            return ResponseEntity.ok("not created");

        } else {
            theoryService.createTheory(
                    title,
                    content,
                    difficulty,
                    themeName
            );
            return ResponseEntity.ok("created");
        }
    }
}
