package decentrathon.telegram_mini_app.entity;

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
@Table(name = "t_theory")
public class Theory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "c_title", nullable = false)
    private String title;

    @Column(name = "c_content", nullable = false, length = 5000)
    private String content;

    @Column(name = "c_difficult", nullable = false)
    private int difficult;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

}
