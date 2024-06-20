package assylzhan.project.quizapp.services;

import assylzhan.project.quizapp.exceptions.NotFoundException;
import assylzhan.project.quizapp.models.Paragraph;
import assylzhan.project.quizapp.models.Question;
import assylzhan.project.quizapp.repositories.ParagraphRepository;
import assylzhan.project.quizapp.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ParagraphRepository paragraphRepository;

    public Question createQuestion(Question question, Long paragraphId) {
        Optional<Paragraph> paragraphOptional = paragraphRepository.findById(paragraphId);
        if (!paragraphOptional.isPresent()) {
            throw new NotFoundException("Paragraph not found with id: " + paragraphId);
        }
        Paragraph paragraph = paragraphOptional.get();
        question.setParagraph(paragraph);
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public Question updateQuestion(Long id, Question question) {
        Question existingQuestion = questionRepository.findById(id).orElse(null);

        existingQuestion.setQuestion(question.getQuestion());
        existingQuestion.setQuestionType(question.getQuestionType());
        existingQuestion.setChoices(question.getChoices());

        existingQuestion.setCorrectAnswers(question.getCorrectAnswers());

        return questionRepository.save(existingQuestion);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public List<Question> getQuestionsForUser(Integer numOfQuestions, Long paragraphId) {
        return questionRepository.findRandomQuestions(numOfQuestions, paragraphId);
    }

    public List<Question> getAllQuestionsByParagraphId(Long id) {
        return questionRepository.findAllByParagraphId(id);
    }
}
