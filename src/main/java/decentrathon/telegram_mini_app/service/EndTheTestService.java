package decentrathon.telegram_mini_app.service;

import decentrathon.telegram_mini_app.dto.TheoryDTO;
import decentrathon.telegram_mini_app.repository.ThemeRepository;
import decentrathon.telegram_mini_app.repository.UserRepository;
import decentrathon.telegram_mini_app.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EndTheTestService {
    @Autowired
    private UserServiceImpl userService;
    public ResponseEntity<String> endTheTest(
            String chatId,
            int correctAnswers
    ) {
        int result = correctAnswers * 20;
        userService.addPoints(chatId, result);
        return ResponseEntity.ok(String.valueOf(result));
    }
}
