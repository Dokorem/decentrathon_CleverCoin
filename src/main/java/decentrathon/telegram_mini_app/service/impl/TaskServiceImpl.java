package decentrathon.telegram_mini_app.service.impl;

import decentrathon.telegram_mini_app.dto.TaskDTO;
import decentrathon.telegram_mini_app.entity.Task;
import decentrathon.telegram_mini_app.entity.Theme;
import decentrathon.telegram_mini_app.entity.Theory;
import decentrathon.telegram_mini_app.repository.TaskRepository;
import decentrathon.telegram_mini_app.repository.ThemeRepository;
import decentrathon.telegram_mini_app.repository.TheoryRepository;
import decentrathon.telegram_mini_app.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final ThemeRepository themeRepository;

    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public Task createTask(String question, String[] answers, String correctAnswer,
                           int difficult, String themeName) {
        if(this.taskRepository.existsByQuestion(question)) {
            return null;
        }
        Theme currentTheme = this.themeRepository
                .findByThemeNameIgnoreCase(themeName);
        Task task = Task.builder()
                .question(question)
                .answers(answers)
                .correctAnswer(correctAnswer)
                .difficult(difficult)
                .theme(currentTheme)
                .build();

        return this.taskRepository.save(task);
    }

    @Override
    public Optional<Task> getTaskById(int id) {
        return this.taskRepository.findById(id);
    }

    @Override
    public List<Task> generateTasksForTheory(int difficult, int themeId) {
        Theme theme = themeRepository.findById(themeId)
                .orElse(null);

        if(theme != null) {
            int minDifficult = difficult - 1;
            int maxDifficult = difficult + 1;
            Pageable pageable = PageRequest.of(0, 10);

            return this.taskRepository.generateThemesByTheme(theme, minDifficult, maxDifficult, pageable);
        } else {
            return null;
        }
    }

}
