package decentrathon.telegram_mini_app.service;

import decentrathon.telegram_mini_app.dto.TheoryDTO;
import decentrathon.telegram_mini_app.dto.TheoryResponseDTO;
import decentrathon.telegram_mini_app.entity.Theory;

import java.util.List;
import java.util.Optional;

public interface TheoryService {

    Theory createTheory(String title, String content, int difficult, String themeName);

    List<Theory> findAllTheories();

    Optional<Theory> findTheoryById(int theoryId);

    String getShortDescriptionOfTheTheoryById(int theoryId);

}
