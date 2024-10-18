package decentrathon.telegram_mini_app.entity;

import decentrathon.telegram_mini_app.utils.TasksAnswersConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "c_question", nullable = false, length = 500)
    private String question;

    @Convert(converter = TasksAnswersConverter.class)
    @Column(name = "c_answers", length = 2000)
    private String[] answers;

    @Column(name = "c_correct_answer", length = 500)
    private String correctAnswer;

    @Column(name = "c_difficult")
    private int difficult;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

}
