package assylzhan.project.quizapp.repositories;

import assylzhan.project.quizapp.models.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParagraphRepository extends JpaRepository<Paragraph,Long> {
    void findByName(String name);
}
