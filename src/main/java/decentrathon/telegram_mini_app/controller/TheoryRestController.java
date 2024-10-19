package decentrathon.telegram_mini_app.controller;

import decentrathon.telegram_mini_app.dto.TheoryDTO;
import decentrathon.telegram_mini_app.entity.Theory;
import decentrathon.telegram_mini_app.service.TheoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-clever-coin")
@RequiredArgsConstructor
public class TheoryRestController {

    private final TheoryService theoryService;

    @PostMapping("/createTheory")
    public ResponseEntity<?> createTheory(@RequestBody TheoryDTO dto) {
        if(dto != null) {
            Theory theory = this.theoryService
                    .createTheory(dto.title(), dto.content(), dto.difficult(), dto.themeName());
            if(theory != null) {
                return ResponseEntity
                        .ok()
                        .body(theory);
            } else {
                return ResponseEntity
                        .badRequest()
                        .body("Ошибка при создании теории!");
            }
        }

        return ResponseEntity
                .badRequest()
                .body("Переданный объект пуст!");
    }

    @PostMapping(value = "/getTheories")
    public ResponseEntity<?> getAllTheories() {
        if(this.theoryService != null) {
            return ResponseEntity
                    .ok()
                    .body(this.theoryService.findAllTheories());
        }

        return ResponseEntity
                .badRequest()
                .body("Ошибка на сервере!");
    }

    @GetMapping("/getTheoryShortDescription")
    public ResponseEntity<String> getTheoryShortDescription(@RequestParam("theoryId") int theoryId) {
        String shortTheoryDescription = this.theoryService
                .getShortDescriptionOfTheTheoryById(theoryId);
        if(shortTheoryDescription != null && !shortTheoryDescription.isEmpty()) {
            return ResponseEntity
                    .ok()
                    .body(shortTheoryDescription);
        }
        return ResponseEntity
                .badRequest()
                .body("Ошибка при получении короткого описания теории!");
    }

    @GetMapping("/getTheory")
    public ResponseEntity<Theory> getTheory(@RequestParam("theoryId") int theoryId) {
        return ResponseEntity
                .ok(this.theoryService.findTheoryById(theoryId).orElse(null));
    }


}
