package assylzhan.project.quizapp.controllers;

import assylzhan.project.quizapp.exceptions.NotFoundException;
import assylzhan.project.quizapp.models.Paragraph;
import assylzhan.project.quizapp.models.Question;
import assylzhan.project.quizapp.services.ParagraphService;
import assylzhan.project.quizapp.services.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/quiz")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private ParagraphService paragraphService;

    @PostMapping("{paragraphId}/create")
    public ResponseEntity<Question> createQuestion(@Valid @RequestBody Question question,
                                                   @PathVariable Long paragraphId) {
        Question createdQuestion = questionService.createQuestion(question, paragraphId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }

    @GetMapping("/view")
    public ResponseEntity<List<Question>> getAllQuestions(@RequestParam Long paragraphId) {
        List<Question> questions = questionService.getAllQuestionsByParagraphId(paragraphId);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        try {
            Question question = questionService.getQuestionById(id);
            return ResponseEntity.ok(question);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/question/{id}/update")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @Valid @RequestBody Question question) {
        try {
            Question updatedQuestion = questionService.updateQuestion(id, question);
            return ResponseEntity.ok(updatedQuestion);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/question/{id}/delete")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/fetch-questions")
    public ResponseEntity<List<Question>> getQuestionsForUser(@RequestParam Integer numOfQuestions,
                                                              @RequestParam Long paragraphId) {
        List<Question> randomQuestions = questionService.getQuestionsForUser(numOfQuestions, paragraphId);
        return ResponseEntity.ok(randomQuestions);
    }
}
