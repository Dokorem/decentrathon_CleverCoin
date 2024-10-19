package decentrathon.telegram_mini_app.controller;

import decentrathon.telegram_mini_app.service.TaskAnswerService;
import decentrathon.telegram_mini_app.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskAnswerController {
    @Autowired
    private TaskAnswerService taskAnswerService;
    @PostMapping("/answerOnTheTask")
    public ResponseEntity<Boolean> answerOnTheTask(@RequestParam int id,
                                               @RequestParam String answer) {
        return taskAnswerService.answerOnTheTask(id, answer);
    }
}