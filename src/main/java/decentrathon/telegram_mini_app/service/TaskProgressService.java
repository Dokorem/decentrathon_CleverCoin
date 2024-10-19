package decentrathon.telegram_mini_app.service;

import decentrathon.telegram_mini_app.entity.TaskProgress;

public interface TaskProgressService {
    boolean taskInProgress(String chatId, int taskId);

    TaskProgress getTaskProgress(String chatId, int taskId);


}
