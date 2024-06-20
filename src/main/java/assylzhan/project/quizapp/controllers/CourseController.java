package assylzhan.project.quizapp.controllers;

import assylzhan.project.quizapp.models.Course;
import assylzhan.project.quizapp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("view")
    public List<Course> viewCourses(){
        return courseService.getCourses();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    private void addCourse(@RequestBody Course course){
        courseService.addCourse(course);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    private void deleteCourse(@PathVariable Long id) {
        Course course = courseService.findCourseById(id);
        if (course != null) {
            courseService.deleteCourse(course);
        }
    }
    @GetMapping("/search")
    public List<Course> searchCourses(@RequestParam("searchTerm") String searchTerm) {
        return courseService.findCourseByName(searchTerm);
    }
}
