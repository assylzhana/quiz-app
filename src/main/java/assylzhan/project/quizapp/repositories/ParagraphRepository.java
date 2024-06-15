package assylzhan.project.quizapp.repositories;

import assylzhan.project.quizapp.models.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParagraphRepository extends JpaRepository<Paragraph,Long> {
    void findByName(String name);

    List<Paragraph> findAllByName(String searchTerm);
}
