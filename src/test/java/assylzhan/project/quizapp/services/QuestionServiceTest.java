package assylzhan.project.quizapp.services;

import assylzhan.project.quizapp.exceptions.NotFoundException;
import assylzhan.project.quizapp.models.Paragraph;
import assylzhan.project.quizapp.models.Question;
import assylzhan.project.quizapp.repositories.ParagraphRepository;
import assylzhan.project.quizapp.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private ParagraphRepository paragraphRepository;

    @InjectMocks
    private QuestionService questionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateQuestion() {
        Long paragraphId = 1L;
        Paragraph paragraph = new Paragraph();
        paragraph.setId(paragraphId);
        Question question = new Question();
        question.setQuestion("What is 2 + 2?");
        when(paragraphRepository.findById(paragraphId)).thenReturn(Optional.of(paragraph));
        when(questionRepository.save(question)).thenReturn(question);
        Question createdQuestion = questionService.createQuestion(question, paragraphId);
        assertNotNull(createdQuestion);
        assertEquals(paragraph, createdQuestion.getParagraph());
        verify(paragraphRepository, times(1)).findById(paragraphId);
        verify(questionRepository, times(1)).save(question);
    }

    @Test
    public void testGetAllQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        questions.add(new Question());
        when(questionRepository.findAll()).thenReturn(questions);
        List<Question> returnedQuestions = questionService.getAllQuestions();
        assertEquals(2, returnedQuestions.size());
        verify(questionRepository, times(1)).findAll();
    }

    @Test
    public void testGetQuestionById() {
        Long questionId = 1L;
        Question question = new Question();
        question.setId(questionId);

        when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));
        Question returnedQuestion = questionService.getQuestionById(questionId);

        assertNotNull(returnedQuestion);
        assertEquals(questionId, returnedQuestion.getId());
        verify(questionRepository, times(1)).findById(questionId);
    }

    @Test
    public void testUpdateQuestion() {
        Long questionId = 1L;
        Question existingQuestion = new Question();
        existingQuestion.setId(questionId);
        existingQuestion.setQuestion("What is 2 + 2?");
        Question updatedQuestion = new Question();
        updatedQuestion.setQuestion("What is 3 + 3?");
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(existingQuestion));
        when(questionRepository.save(existingQuestion)).thenReturn(existingQuestion);
        Question savedQuestion = questionService.updateQuestion(questionId, updatedQuestion);
        assertNotNull(savedQuestion);
        assertEquals(updatedQuestion.getQuestion(), savedQuestion.getQuestion());
        verify(questionRepository, times(1)).findById(questionId);
        verify(questionRepository, times(1)).save(existingQuestion);
    }

    @Test
    public void testDeleteQuestion() {
        Long questionId = 1L;
        questionService.deleteQuestion(questionId);

        verify(questionRepository, times(1)).deleteById(questionId);
    }

    @Test
    public void testGetQuestionsForUser() {
        Integer numOfQuestions = 5;
        Long paragraphId = 1L;
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        questions.add(new Question());
        when(questionRepository.findRandomQuestions(numOfQuestions, paragraphId)).thenReturn(questions);

        List<Question> returnedQuestions = questionService.getQuestionsForUser(numOfQuestions, paragraphId);
        assertNotNull(returnedQuestions);
        assertEquals(2, returnedQuestions.size());
        verify(questionRepository, times(1)).findRandomQuestions(numOfQuestions, paragraphId);
    }

    @Test
    public void testGetAllQuestionsByParagraphId() {
        Long paragraphId = 1L;
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        questions.add(new Question());

        when(questionRepository.findAllByParagraphId(paragraphId)).thenReturn(questions);
        List<Question> returnedQuestions = questionService.getAllQuestionsByParagraphId(paragraphId);
        assertNotNull(returnedQuestions);
        assertEquals(2, returnedQuestions.size());
        verify(questionRepository, times(1)).findAllByParagraphId(paragraphId);
    }

}
