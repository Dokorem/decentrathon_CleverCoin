package decentrathon.telegram_mini_app.service;

import decentrathon.telegram_mini_app.dto.TheoryDTO;
import decentrathon.telegram_mini_app.repository.TaskRepository;
import decentrathon.telegram_mini_app.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskAnswerService {
    @Autowired
    private TaskService taskService;
    public ResponseEntity<Boolean> answerOnTheTask(
            int id,
            String answer
    ) {
        if (taskService.getTaskById(id).get().getCorrectAnswer().equals(answer)) {
            return ResponseEntity.ok(false);
        } else {
            return ResponseEntity.ok(true);
        }
    }
}
