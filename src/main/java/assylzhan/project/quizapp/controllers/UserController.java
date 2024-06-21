package assylzhan.project.quizapp.controllers;

import assylzhan.project.quizapp.models.User;
import assylzhan.project.quizapp.models.UserQuizScore;
import assylzhan.project.quizapp.services.ParagraphService;
import assylzhan.project.quizapp.services.QuestionService;
import assylzhan.project.quizapp.services.UserQuizScoreService;
import assylzhan.project.quizapp.services.UserService;
import assylzhan.project.quizapp.models.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
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
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/submit")
    public ResponseEntity<Void> submitQuizScore(@RequestParam("score") double score,
                                                @RequestParam String paragraphName) {
        User user = userService.getCurrentUser();
        Paragraph paragraph = paragraphService.getParagraphByName(paragraphName);
        UserQuizScore userQuizScore = userQuizScoreService.findByUserAndParagraph(user, paragraph)
                .orElse(new UserQuizScore());
        int newScore = (int) score;
        if (userQuizScore.getId() != null) {
            user.setTotalScore(user.getTotalScore() - userQuizScore.getScore() + newScore);
        } else {
            user.setTotalScore(user.getTotalScore() + newScore);
        }
        userQuizScore.setUser(user);
        userQuizScore.setParagraph(paragraph);
        userQuizScore.setScore(newScore);
        userQuizScoreService.saveUserQuizScore(userQuizScore);
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/update-profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateProfile(@RequestBody User updatedUser) {
        User currentUser = userService.getCurrentUser();
        currentUser.setUsername(updatedUser.getUsername());
        currentUser.setEmail(updatedUser.getEmail());
        userService.saveUser(currentUser);
        return ResponseEntity.ok(currentUser);
    }

    @PostMapping("/change-password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> changePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        User currentUser = userService.getCurrentUser();
        if (passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            userService.saveUser(currentUser);
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect old password");
        }
    }

    @DeleteMapping("/delete-account")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteAccount() {
        User currentUser = userService.getCurrentUser();
        userQuizScoreService.deleteUserQuizScoresByUser(currentUser);
        userService.deleteUser(currentUser);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
