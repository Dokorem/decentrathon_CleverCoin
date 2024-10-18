package decentrathon.telegram_mini_app.service;

import decentrathon.telegram_mini_app.dto.TheoryDTO;
import decentrathon.telegram_mini_app.entity.Theory;

import java.util.List;
import java.util.Optional;

public interface TheoryService {

    Theory createTheory(TheoryDTO dto);

    List<Theory> findAllTheories();

    Optional<Theory> findTheoryById(int theoryId);

    String getShortDescriptionOfTheTheoryById(int theoryId);

}
