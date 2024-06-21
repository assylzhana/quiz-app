package assylzhan.project.quizapp.repositories;

import assylzhan.project.quizapp.models.Paragraph;
import assylzhan.project.quizapp.models.UserQuizScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserQuizScoreRepository extends JpaRepository<UserQuizScore, Long> {
    List<UserQuizScore> findByUserId(Long userId);

    UserQuizScore findByParagraph(Paragraph paragraph);

    UserQuizScore findByUserIdAndParagraphId(Long userId, Long paragraphId);
}
