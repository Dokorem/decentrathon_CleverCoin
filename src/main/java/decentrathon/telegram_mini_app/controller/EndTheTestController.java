package decentrathon.telegram_mini_app.controller;

import decentrathon.telegram_mini_app.service.EndTheTestService;
import decentrathon.telegram_mini_app.service.TaskAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndTheTestController {
    @Autowired
    private EndTheTestService endTheTestService;
    @PostMapping("/endTheTest")
    public ResponseEntity<String> endTheTest(@RequestParam String chatId,
                                               @RequestParam int correctAnswers) {
        return endTheTestService.endTheTest(chatId, correctAnswers);
    }
}