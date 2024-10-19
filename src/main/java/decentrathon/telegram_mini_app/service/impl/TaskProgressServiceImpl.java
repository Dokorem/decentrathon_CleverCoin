package decentrathon.telegram_mini_app.service.impl;

import decentrathon.telegram_mini_app.entity.Task;
import decentrathon.telegram_mini_app.entity.TaskProgress;
import decentrathon.telegram_mini_app.entity.Theme;
import decentrathon.telegram_mini_app.repository.TaskProgressRepository;
import decentrathon.telegram_mini_app.repository.TaskRepository;
import decentrathon.telegram_mini_app.repository.ThemeRepository;
import decentrathon.telegram_mini_app.service.TaskProgressService;
import decentrathon.telegram_mini_app.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TaskProgressServiceImpl implements TaskProgressService {

    private final TaskProgressRepository taskProgressRepository;

    @Override
    public boolean taskInProgress(String chatId, int taskId) {
        try {
            return taskProgressRepository.findByChatIdAndTaskId(chatId, taskId) != null;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public TaskProgress getTaskProgress(String chatId, int taskId) {
        return taskProgressRepository.findByChatIdAndTaskId(chatId, taskId);
    }
}
