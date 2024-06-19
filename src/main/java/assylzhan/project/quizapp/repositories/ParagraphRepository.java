package assylzhan.project.quizapp.repositories;

import assylzhan.project.quizapp.models.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParagraphRepository extends JpaRepository<Paragraph,Long> {
    Paragraph findByName(String name);

    List<Paragraph> findAllByName(String searchTerm);

    @Query("SELECT p FROM Paragraph p JOIN p.questionLists q WHERE q.id = :questionId")
    Paragraph findByQuestionId(@Param("questionId") Long questionId);
}
