package assylzhan.project.quizapp.services;

import assylzhan.project.quizapp.dto.ParagraphScoreDTO;
import assylzhan.project.quizapp.models.Paragraph;
import assylzhan.project.quizapp.models.User;
import assylzhan.project.quizapp.models.UserQuizScore;
import assylzhan.project.quizapp.repositories.UserQuizScoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<UserQuizScore> findByUserAndParagraph(User user, Paragraph paragraph) {
        return userQuizScoreRepository.findByUserAndParagraph(user, paragraph);
    }

    public List<ParagraphScoreDTO> findParagraphsAndScoresByUser(User currentUser) {
        return userQuizScoreRepository.findParagraphsAndScoresByUser(currentUser);
    }

    @Transactional
    public void deleteUserQuizScoresByUser(User user) {
        userQuizScoreRepository.deleteByUser(user);
    }
}
