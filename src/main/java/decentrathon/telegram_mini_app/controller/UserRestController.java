package decentrathon.telegram_mini_app.controller;


import decentrathon.telegram_mini_app.dto.TheoryDTO;
import decentrathon.telegram_mini_app.entity.Theory;
import decentrathon.telegram_mini_app.repository.UserRepository;
import decentrathon.telegram_mini_app.service.TheoryService;
import decentrathon.telegram_mini_app.service.UserService;
import decentrathon.telegram_mini_app.utils.TheoryConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-clever-coin")
@RequiredArgsConstructor
public class UserRestController {
    @Autowired
    private final UserRepository userRepository;
    @GetMapping("/getUserPointsCount/{chatId}")
    public ResponseEntity<Integer> getUserPointsCount(@PathVariable String chatId) {
        return ResponseEntity.ok(userRepository.getByChatId(chatId).getPointsCount());
    }
}
