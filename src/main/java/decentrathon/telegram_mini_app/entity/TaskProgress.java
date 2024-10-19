package decentrathon.telegram_mini_app.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_task_progress")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskProgress {
    @Id
    @Column(name = "chat_id", nullable = false)
    private String chatId;

    @Column(name = "task_id", nullable = false)
    private Integer taskId;

    @Column(name = "progress")
    private Integer progress;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}