package assylzhan.project.quizapp.controllers;

import assylzhan.project.quizapp.models.Course;
import assylzhan.project.quizapp.models.Theme;
import assylzhan.project.quizapp.services.CourseService;
import assylzhan.project.quizapp.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/theme")
public class ThemeController {

    @Autowired
    private ThemeService themeService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/{courseId}")
    public ResponseEntity<List<Theme>> viewThemes(@PathVariable Long courseId) {
        Course course = courseService.findCourseById(courseId);
        if (course != null) {
            List<Theme> themes = course.getThemeList();
            return new ResponseEntity<>(themes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{courseId}")
    public ResponseEntity<Theme> addThemeToCourse(@PathVariable Long courseId, @RequestBody Theme theme) {
        Course course = courseService.findCourseById(courseId);
        if (course != null) {
            course.getThemeList().add(theme);
            courseService.addCourse(course);
            Theme savedTheme = themeService.addTheme(theme);
            return new ResponseEntity<>(savedTheme, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
