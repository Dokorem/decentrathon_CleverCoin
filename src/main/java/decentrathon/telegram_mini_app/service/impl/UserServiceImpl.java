package decentrathon.telegram_mini_app.service.impl;

import decentrathon.telegram_mini_app.entity.User;
import decentrathon.telegram_mini_app.repository.UserRepository;
import decentrathon.telegram_mini_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public boolean existByChatId(String chatId) {
        try {
            return !userRepository.getByChatId(chatId).getChatId().isEmpty();
        } catch (NullPointerException | NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public int addPoints(String chatId, int pointsToAdd) {
        User user = userRepository.getByChatId(chatId);
        if (user.getChatId().isEmpty()) {
            return 0;
        }
        user.setPointsCount(user.getPointsCount() + pointsToAdd);
        userRepository.save(user);

        return pointsToAdd;
    }
}
