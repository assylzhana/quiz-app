package assylzhan.project.quizapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import assylzhan.project.quizapp.repositories.ParagraphRepository;

@Service
public class ParagraphService {

    @Autowired
    private ParagraphRepository paragraphRepository;

    public void findParagraphByName(String name) {
        paragraphRepository.findByName(name);
    }
}
