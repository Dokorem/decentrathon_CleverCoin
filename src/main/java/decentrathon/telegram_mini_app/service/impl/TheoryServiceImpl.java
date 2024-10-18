package decentrathon.telegram_mini_app.service.impl;

import decentrathon.telegram_mini_app.dto.TheoryDTO;
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

    @Override
    @Transactional
    public Theory createTheory(TheoryDTO dto) {
        if(this.theoryRepository.existsByTitle(dto.title())) {
            return null;
        }
        Theory theory = Theory.builder()
                .title(dto.title())
                .content(dto.content())
                .difficult(dto.difficult())
                .theme(this.themeRepository
                                .findByThemeNameIgnoreCase(dto.themeName()))
                .build();

        return this.theoryRepository.save(theory);
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
        return "";
    }
}
