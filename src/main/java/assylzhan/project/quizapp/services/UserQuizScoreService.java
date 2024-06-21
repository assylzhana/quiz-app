package assylzhan.project.quizapp.services;

import assylzhan.project.quizapp.models.Paragraph;
import assylzhan.project.quizapp.models.UserQuizScore;
import assylzhan.project.quizapp.repositories.UserQuizScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQuizScoreService {
    @Autowired
    private UserQuizScoreRepository userQuizScoreRepository;

    public UserQuizScore saveUserQuizScore(UserQuizScore userQuizScore) {
        return userQuizScoreRepository.save(userQuizScore);
    }

    public List<UserQuizScore> getUserQuizScores(Long userId) {
        return userQuizScoreRepository.findByUserId(userId);
    }

    public UserQuizScore getByParagraph(Paragraph paragraph) {
        return userQuizScoreRepository.findByParagraph(paragraph);
    }

    public int getUserQuizScore(Long userId, Long paragraphId) {
        UserQuizScore userQuizScore = userQuizScoreRepository.findByUserIdAndParagraphId(userId, paragraphId);
        return userQuizScore != null ? userQuizScore.getScore() : 0;
    }
}
