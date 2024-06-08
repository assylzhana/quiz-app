package assylzhan.project.quizapp.services;

import assylzhan.project.quizapp.models.Course;
import assylzhan.project.quizapp.models.Theme;
import assylzhan.project.quizapp.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

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
}
