package decentrathon.telegram_mini_app.controller;

import decentrathon.telegram_mini_app.service.TheoryCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TheoryCreateController {
    @Autowired
    private TheoryCreateService theoryCreateService;
    @PostMapping("/createTheory")
    public ResponseEntity<String> createTheory(@RequestParam String title,
                                               @RequestParam String content,
                                               @RequestParam int difficulty,
                                               @RequestParam String themeName) {
        return theoryCreateService.theoryCreate(
                title,
                content,
                difficulty,
                themeName
        );
    }
}
