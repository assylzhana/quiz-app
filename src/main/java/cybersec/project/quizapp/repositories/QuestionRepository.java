package cybersec.project.quizapp.repositories;

import cybersec.project.quizapp.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByParagraphId(Long paragraphId);
    @Query(value = "SELECT * FROM questions WHERE paragraph_id = :paragraphId ORDER BY random() LIMIT :limit", nativeQuery = true)
    List<Question> findRandomQuestions(@Param("limit") int limit, @Param("paragraphId") Long paragraphId);

    List<Question> findAllByParagraphId(Long id);
}
