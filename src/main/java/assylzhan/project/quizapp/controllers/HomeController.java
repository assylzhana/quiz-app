package assylzhan.project.quizapp.controllers;

import assylzhan.project.quizapp.models.*;
import assylzhan.project.quizapp.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Autowired
    private UserService userService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profile(Model model){
        model.addAttribute("user", userService.getCurrentUser());
        return "profile";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/sign-up")
    public String signUp(){
        return "sign-up";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/sign-up")
    public String register(User user){
        userService.addUser(user);
        return "redirect:/sign-in";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/sign-in")
    public String signIn(){
        return "sign-in";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/support")
    public String supportPage(){
        return "support";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/settings")
    public String settings(Model model){
        model.addAttribute("user", userService.getCurrentUser());
        return "settings";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/course")
    public String coursePage(Model model){
        model.addAttribute("user", userService.getCurrentUser());
        return "course";
    }



    private static class Counter {
        private int count = 0;

        public int getCount() {
            return ++count;
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/course/{name}")
    public String courseName(@PathVariable String name,
                             Model model){
        Course course = courseService.getCourseByName(name);
        model.addAttribute("course", course);
        model.addAttribute("user", userService.getCurrentUser());
        return "themes";
    }

    @PreAuthorize("isAuthenticated()")
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
        model.addAttribute("user", userService.getCurrentUser());
        return "paragraphs";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/quiz/{paragraphName}")
    public String paragraph(@PathVariable(name = "paragraphName") String paragraphName,
                            Model model){
        Paragraph paragraph = paragraphService.getParagraphByName(paragraphName);
        model.addAttribute("paragraph", paragraph);
        model.addAttribute("user", userService.getCurrentUser());
        return "paragraph";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/quiz/admin/{id}")
    public String addQuestions(@PathVariable Long id, Model model){
        Paragraph paragraph = paragraphService.getParagraphById(id);
        model.addAttribute("paragraph", paragraph);
        return "admin-quiz-add";
    }
    @PreAuthorize("hasRole('ROLE_USER')")
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
