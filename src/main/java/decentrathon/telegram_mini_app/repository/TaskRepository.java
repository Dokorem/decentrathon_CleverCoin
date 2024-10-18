package decentrathon.telegram_mini_app.repository;

import decentrathon.telegram_mini_app.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    boolean existsByQuestion(String question);

}
