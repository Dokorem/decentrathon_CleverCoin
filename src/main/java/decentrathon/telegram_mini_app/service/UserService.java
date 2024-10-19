package decentrathon.telegram_mini_app.service;

import decentrathon.telegram_mini_app.dto.TheoryDTO;
import decentrathon.telegram_mini_app.entity.Theory;
import decentrathon.telegram_mini_app.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean existByChatId(String chatId);

    int addPoints(String chatId, int pointsToAdd);

}
