package cybersec.project.quizapp.services;

import cybersec.project.quizapp.models.Course;
import cybersec.project.quizapp.repositories.CourseRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }
    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }

    public Course findCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course getCourseByName(String name) {
        return courseRepository.findByName(name);
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public List<Course> findCourseByName(String searchTerm) {
        return courseRepository.findAllByName(searchTerm);
    }

    public List<Course> vulnerableSearch(String searchTerm) {
        String sql = "SELECT * FROM courses WHERE course_name = '" + searchTerm + "'";
        return entityManager.createNativeQuery(sql, Course.class).getResultList();
    }
}
