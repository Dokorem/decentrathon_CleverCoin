package decentrathon.telegram_mini_app.repository;

import decentrathon.telegram_mini_app.entity.Theory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheoryRepository extends JpaRepository<Theory, Integer> {

    boolean existsByTitle(String title);

}
