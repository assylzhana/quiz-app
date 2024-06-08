package assylzhan.project.quizapp.controllers;

import assylzhan.project.quizapp.models.Course;
import assylzhan.project.quizapp.services.CourseService;
import assylzhan.project.quizapp.services.ParagraphService;
import assylzhan.project.quizapp.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ParagraphService paragraphService;

    @Autowired
    private ThemeService themeService;

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/support")
    public String supportPage(){
        return "support";
    }

    @GetMapping("/course")
    public String coursePage(){
        return "course";
    }
    private static class Counter {
        private int count = 0;

        public int getCount() {
            return ++count;
        }
    }
    @GetMapping("/course/{name}")
    public String courseName(@PathVariable String name,
                             Model model){
        Course course = courseService.getCourseByName(name);
        model.addAttribute("course", course);
        return "themes";
    }
}
