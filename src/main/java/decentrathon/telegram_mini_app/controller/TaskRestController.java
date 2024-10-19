package decentrathon.telegram_mini_app.controller;

import decentrathon.telegram_mini_app.dto.TaskDTO;
import decentrathon.telegram_mini_app.entity.Task;
import decentrathon.telegram_mini_app.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-clever-coin")
@RequiredArgsConstructor
public class TaskRestController {

    private final TaskService taskService;

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

}