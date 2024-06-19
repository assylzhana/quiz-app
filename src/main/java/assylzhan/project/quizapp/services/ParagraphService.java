package assylzhan.project.quizapp.services;

import assylzhan.project.quizapp.models.Course;
import assylzhan.project.quizapp.models.Theme;
import assylzhan.project.quizapp.models.Paragraph;
import assylzhan.project.quizapp.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import assylzhan.project.quizapp.repositories.ParagraphRepository;

import java.util.List;

@Service
public class ParagraphService {

    @Autowired
    private ParagraphRepository paragraphRepository;
    @Autowired
    private ThemeRepository themeRepository;


    public void findParagraphByName(String name) {
        paragraphRepository.findByName(name);
    }

    public Paragraph addParagraph(Paragraph paragraph) {
        return paragraphRepository.save(paragraph);
    }

    public Paragraph getParagraphById(Long id) {
        return paragraphRepository.findById(id).orElse(null);
    }

    public Paragraph updateParagraph(Paragraph paragraph) {
        return paragraphRepository.save(paragraph);
    }

    public void removeParagraphFromTheme(Long id) {
        List<Theme> themes = themeRepository.findByParagraphListId(id);
        for(Theme theme : themes){
            theme.getParagraphList().removeIf(paragraph ->paragraph.getId().equals(id));
            themeRepository.save(theme);
        }
    }

    public List<Paragraph> findParagraph(String searchTerm) {
        return paragraphRepository.findAllByName(searchTerm);
    }

    public void deleteParagraph(Long id) {
        paragraphRepository.deleteById(id);
    }

    public Paragraph getParagraphByName(String paragraphName) {
        return paragraphRepository.findByName(paragraphName);
    }

    public Paragraph findParagraphByQuestionId(Long id) {
        return paragraphRepository.findByQuestionId(id);
    }
}
