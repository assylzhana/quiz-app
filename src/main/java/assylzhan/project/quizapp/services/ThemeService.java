package assylzhan.project.quizapp.services;

import assylzhan.project.quizapp.models.Course;
import assylzhan.project.quizapp.models.Theme;
import assylzhan.project.quizapp.repositories.CourseRepository;
import assylzhan.project.quizapp.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Theme addTheme(Theme theme) {
        return themeRepository.save(theme);
    }


    public Theme findThemeByCourse(String themeName, Course course) {
        Long courseId = course.getId();
        return themeRepository.findByNameAndCourse(themeName, courseId);
    }
    public void deleteTheme(Long id) {
        themeRepository.deleteById(id);
    }

    public void removeThemeFromCourses(Long themeId) {
        List<Course> courses = courseRepository.findByThemeListId(themeId);

        for (Course course : courses) {
            course.getThemeList().removeIf(theme -> theme.getId().equals(themeId));
            courseRepository.save(course);
        }
    }

    public Theme getById(Long id) {
        return themeRepository.findById(id).orElse(null);
    }

    public Theme updateTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    public List<Theme> findThemeByName(String searchTerm) {
        return themeRepository.findAllByName(searchTerm);
    }

    public Theme findThemeCourse(String themeName, Course course) {
        return themeRepository.findByNameCourse(themeName, course.getName());
    }
}
