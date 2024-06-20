package assylzhan.project.quizapp.controllers;

import assylzhan.project.quizapp.models.Course;
import assylzhan.project.quizapp.models.Paragraph;
import assylzhan.project.quizapp.models.Question;
import assylzhan.project.quizapp.models.Theme;
import assylzhan.project.quizapp.services.CourseService;
import assylzhan.project.quizapp.services.ParagraphService;
import assylzhan.project.quizapp.services.QuestionService;
import assylzhan.project.quizapp.services.ThemeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ParagraphService paragraphService;

    @Autowired
    private ThemeService themeService;

    @Autowired
    private QuestionService questionService;

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
    @GetMapping("/course/{courseName}/{themeName}")
    public String paragraph(@PathVariable(name = "courseName") String courseName,
                            @PathVariable(name = "themeName") String themeName,
                            Model model) {
        Course course = courseService.getCourseByName(courseName);
        if (course == null) {
            System.err.println("Course not found: " + courseName);
            return "error";
        }
        Theme theme = themeService.findThemeCourse(themeName, course);
        if (theme == null) {
            System.err.println("Theme not found: " + themeName);
            return "error";
        }
        model.addAttribute("course", course);
        model.addAttribute("theme", theme);
        return "paragraphs";
    }

    @GetMapping("/quiz/{paragraphName}")
    public String paragraph(@PathVariable(name = "paragraphName") String paragraphName,
                            Model model){
        Paragraph paragraph = paragraphService.getParagraphByName(paragraphName);
        model.addAttribute("paragraph", paragraph);
        return "paragraph";
    }

    @GetMapping("/quiz/admin/{id}")
    public String addQuestions(@PathVariable Long id, Model model){
        Paragraph paragraph = paragraphService.getParagraphById(id);
        model.addAttribute("paragraph", paragraph);
        return "admin-quiz-add";
    }

    @GetMapping("/quiz/{paragraphName}/start")
    public String quizPage(@PathVariable String paragraphName, Model model) {
        Paragraph paragraph = paragraphService.getParagraphByName(paragraphName);
        List<Question> questions = questionService.getAllQuestionsByParagraphId(paragraph.getId()); // assuming this method exists
        ObjectMapper objectMapper = new ObjectMapper();
        String questionsJson = "";
        try {
            questionsJson = objectMapper.writeValueAsString(questions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("paragraph", paragraph);
        model.addAttribute("questionsJson", questionsJson);
        return "quiz-start";
    }

}
