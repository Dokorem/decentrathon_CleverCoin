package decentrathon.telegram_mini_app.repository;

import decentrathon.telegram_mini_app.entity.TaskProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskProgressRepository extends JpaRepository<TaskProgress, Long> {
    TaskProgress findByChatIdAndTaskId(String chatId, Integer taskId);
    void deleteByChatIdAndTaskId(String chatId, Integer taskId);
}
