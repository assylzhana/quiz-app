package assylzhan.project.quizapp.controllers;

import assylzhan.project.quizapp.models.User;
import assylzhan.project.quizapp.models.UserQuizScore;
import assylzhan.project.quizapp.services.ParagraphService;
import assylzhan.project.quizapp.services.QuestionService;
import assylzhan.project.quizapp.services.UserQuizScoreService;
import assylzhan.project.quizapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ParagraphService paragraphService;
    @Autowired
    private UserQuizScoreService userQuizScoreService;
    @Autowired
    private UserService userService;

    @PostMapping("/submit")
    public ResponseEntity<Void> submitQuizScore(@RequestParam("score") double score,
                                                @RequestParam String paragraphName) {
        User user = userService.getCurrentUser();
        UserQuizScore userQuizScore = new UserQuizScore();
        userQuizScore.setUser(user);
        userQuizScore.setParagraph(paragraphService.getParagraphByName(paragraphName));
        userQuizScore.setScore((int) score);
        userQuizScoreService.saveUserQuizScore(userQuizScore);
        user.setTotalScore((int) (user.getTotalScore() + score));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}