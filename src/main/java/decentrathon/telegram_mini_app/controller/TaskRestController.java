package decentrathon.telegram_mini_app.controller;

import decentrathon.telegram_mini_app.dto.TaskDTO;
import decentrathon.telegram_mini_app.dto.TaskGetProgressDTO;
import decentrathon.telegram_mini_app.dto.TaskProgressDTO;
import decentrathon.telegram_mini_app.entity.Task;
import decentrathon.telegram_mini_app.entity.TaskProgress;
import decentrathon.telegram_mini_app.entity.User;
import decentrathon.telegram_mini_app.repository.TaskProgressRepository;
import decentrathon.telegram_mini_app.repository.UserRepository;
import decentrathon.telegram_mini_app.service.TaskProgressService;
import decentrathon.telegram_mini_app.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api-clever-coin")
@RequiredArgsConstructor
public class TaskRestController {

    private final TaskService taskService;

    private final TaskProgressService taskProgressService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskProgressRepository taskProgressRepository;

    @PostMapping("/getTasks")
    public ResponseEntity<?> getTasks(
            @RequestParam("difficult") int difficult,
            @RequestParam("themeId") int themeId) {
        List<Task> generatedTasks = taskService.generateTasksForTheory(difficult, themeId);

        if(generatedTasks != null && generatedTasks.isEmpty()) {
            return ResponseEntity
                    .ok()
                    .body(generatedTasks);
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Возникла ошибка при получении теста!");
        }
    }

    @PostMapping("/createTask")
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO) {
        Task task = taskService.createTask(
                taskDTO.question(),
                taskDTO.answers(),
                taskDTO.correctAnswer(),
                taskDTO.difficult(),
                taskDTO.themeName()
        );

        if(task != null) {
            return ResponseEntity
                    .ok()
                    .body(task);
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Произошла ошибка при создании задаения, либо такое задание уже существует!");
        }
    }
    @PostMapping("/saveProgress")
    public ResponseEntity<String> saveProgress(@RequestBody TaskProgressDTO progressDTO) {
        if (taskProgressService.taskInProgress(progressDTO.chatId(), progressDTO.taskId())) {
            TaskProgress taskProgress = taskProgressRepository
                    .findByChatIdAndTaskId(progressDTO.chatId(), progressDTO.taskId());
            taskProgress.setProgress(taskProgress.getProgress() + (progressDTO.correctAnswer() ? 1 : 0));
            taskProgress.setUpdatedAt(LocalDateTime.now());
            taskProgressRepository.save(taskProgress);
        } else {
            TaskProgress progress = new TaskProgress(progressDTO.chatId(), progressDTO.taskId(), progressDTO.correctAnswer() ? 1 : 0, LocalDateTime.now());
            taskProgressRepository.save(progress);
        }

        return ResponseEntity.ok("Прогресс сохранён");
    }

    @GetMapping("/getProgress/{chatId}/{taskId}")
    public ResponseEntity<TaskGetProgressDTO> getProgress(@PathVariable String chatId, @PathVariable Integer taskId) {
        TaskProgress progress = taskProgressRepository.findByChatIdAndTaskId(chatId, taskId);
        return ResponseEntity.ok(new TaskGetProgressDTO(progress.getChatId(), progress.getTaskId(), progress.getProgress() * 20));
    }
    @Transactional
    @PostMapping("/completeTask")
    public ResponseEntity<String> completeTask(@RequestBody TaskProgressDTO progressDTO) {
        int pointsCount = userRepository.getByChatId(progressDTO.chatId()).getPointsCount();
        User user = userRepository.getByChatId(progressDTO.chatId());
        user.setPointsCount(pointsCount + taskProgressRepository.findByChatIdAndTaskId(progressDTO.chatId(), progressDTO.taskId()).getProgress() * 20);
        userRepository.save(user);
        taskProgressRepository.deleteByChatIdAndTaskId(progressDTO.chatId(), progressDTO.taskId());
        return ResponseEntity.ok("Задание завершено и прогресс сохранён");
    }
}