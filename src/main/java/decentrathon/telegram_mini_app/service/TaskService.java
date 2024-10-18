package decentrathon.telegram_mini_app.service;

import decentrathon.telegram_mini_app.dto.TaskDTO;
import decentrathon.telegram_mini_app.entity.Task;
import decentrathon.telegram_mini_app.entity.Theory;
import decentrathon.telegram_mini_app.repository.TheoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task createTask(TaskDTO dto);

    Optional<Task> getTaskById(int id);

    List<Task> generateTasksForTheory(Theory theory);

}
