package assylzhan.project.quizapp.repositories;

import assylzhan.project.quizapp.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findByName(String name);
}
