package decentrathon.telegram_mini_app.repository;

import decentrathon.telegram_mini_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getByChatId(String chatId);

}
