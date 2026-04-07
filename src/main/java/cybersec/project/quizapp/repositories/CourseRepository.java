package cybersec.project.quizapp.repositories;

import cybersec.project.quizapp.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findByName(String name);
    List<Course> findAllByName(String searchTerm);
    List<Course> findByThemeListId(Long themeId);
}
