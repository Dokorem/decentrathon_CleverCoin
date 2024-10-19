package decentrathon.telegram_mini_app.service.impl;

import decentrathon.telegram_mini_app.client.AIClient;
import decentrathon.telegram_mini_app.entity.Theme;
import decentrathon.telegram_mini_app.entity.Theory;
import decentrathon.telegram_mini_app.repository.ThemeRepository;
import decentrathon.telegram_mini_app.repository.TheoryRepository;
import decentrathon.telegram_mini_app.service.TheoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheoryServiceImpl implements TheoryService {

    private final ThemeRepository themeRepository;

    private final TheoryRepository theoryRepository;

    private final AIClient aiClient;

    @Override
    @Transactional
    public Theory createTheory(String title, String content, int difficult, String themeName) {
        if(this.theoryRepository.existsByTitle(title)) {
            return null;
        }
        Theme currentTheme = this.themeRepository
                .findByThemeNameIgnoreCase(themeName);
        Theory theory = Theory.builder()
                .title(title)
                .content(content)
                .difficult(difficult)
                .theme(currentTheme)
                .build();
        currentTheme.getTheories().add(theory);
        this.themeRepository.save(currentTheme);

        return theory;
    }

    @Override
    public List<Theory> findAllTheories() {
        return this.theoryRepository.findAll();
    }

    @Override
    public Optional<Theory> findTheoryById(int theoryId) {
        return theoryRepository.findById(theoryId);
    }

    /**
     * TODO: Создать ИИ-интеграцию с чатом гпт для короткого описание темы в Theory
     */

    @Override
    public String getShortDescriptionOfTheTheoryById(int theoryId) {
        Theory currentTheory = this.theoryRepository
                .findById(theoryId)
                .orElse(null);

        if(currentTheory != null) {
            return aiClient
                    .getShortDescription("Сократи текст, но чтобы смысл и основной посыл остался таким же - "
                            + currentTheory.getContent())
                    .getChoices()
                    .get(0)
                    .getMessage()
                    .getContent();
        } else {
            return null;
        }
    }
}
