package decentrathon.telegram_mini_app.service.impl;

import decentrathon.telegram_mini_app.dto.TaskDTO;
import decentrathon.telegram_mini_app.entity.Task;
import decentrathon.telegram_mini_app.entity.Theory;
import decentrathon.telegram_mini_app.repository.TaskRepository;
import decentrathon.telegram_mini_app.repository.ThemeRepository;
import decentrathon.telegram_mini_app.repository.TheoryRepository;
import decentrathon.telegram_mini_app.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    public Task createTask(TaskDTO dto) {
        if(this.taskRepository.existsByQuestion(dto.question())) {
            return null;
        }

        Task task = Task.builder()
                .question(dto.question())
                .answers(dto.answers())
                .correctAnswer(dto.correctAnswer())
                .difficult(dto.difficult())
                .theme(this.themeRepository
                        .findByThemeNameIgnoreCase(dto.themeName()))
                .build();

        return this.taskRepository.save(task);
    }

    @Override
    public Optional<Task> getTaskById(int id) {
        return this.taskRepository.findById(id);
    }

    @Override
    public List<Task> generateTasksForTheory(Theory theory) {
        return this.taskRepository.findAll();
    }

}
