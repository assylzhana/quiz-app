package cybersec.project.quizapp.repositories;

import cybersec.project.quizapp.dto.ParagraphScoreDTO;
import cybersec.project.quizapp.models.Paragraph;
import cybersec.project.quizapp.models.User;
import cybersec.project.quizapp.models.UserQuizScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserQuizScoreRepository extends JpaRepository<UserQuizScore, Long> {
    List<UserQuizScore> findByUserId(Long userId);

    UserQuizScore findByParagraph(Paragraph paragraph);

    UserQuizScore findByUserIdAndParagraphId(Long userId, Long paragraphId);

    Optional<UserQuizScore> findByUserAndParagraph(User user, Paragraph paragraph);

    @Query("SELECT new cybersec.project.quizapp.dto.ParagraphScoreDTO(p.name, uqs.score) " +
            "FROM UserQuizScore uqs JOIN uqs.paragraph p " +
            "WHERE uqs.user = :user")
    List<ParagraphScoreDTO> findParagraphsAndScoresByUser(User user);

    void deleteByUser(User user);
}
