package decentrathon.telegram_mini_app.repository;

import decentrathon.telegram_mini_app.entity.Task;
import decentrathon.telegram_mini_app.entity.Theme;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    boolean existsByQuestion(String question);

    @Query("select e from Task e " +
           "where e.theme = :theme " +
           "and e.difficult between :minDifficult and :maxDifficult " +
           "order by function('RAND')")
    List<Task> generateThemesByTheme(@Param("theme") Theme theme,
                                     @Param("minDifficult") int minDifficult,
                                     @Param("maxDifficult") int maxDifficult,
                                     Pageable pageable);

}
