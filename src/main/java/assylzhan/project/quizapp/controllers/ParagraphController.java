package assylzhan.project.quizapp.controllers;

import assylzhan.project.quizapp.models.Course;
import assylzhan.project.quizapp.models.Paragraph;
import assylzhan.project.quizapp.models.Theme;
import assylzhan.project.quizapp.services.CourseService;
import assylzhan.project.quizapp.services.ParagraphService;
import assylzhan.project.quizapp.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paragraph")
public class ParagraphController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ThemeService themeService;

    @Autowired
    private ParagraphService paragraphService;

    @GetMapping("/{themeId}")
    public ResponseEntity<List<Paragraph>> viewParagraphs(@PathVariable Long themeId) {
        Theme theme = themeService.findThemeById(themeId);
        if (theme == null) {
            return ResponseEntity.notFound().build();
        }

        List<Paragraph> paragraphs = theme.getParagraphList();
        return ResponseEntity.ok(paragraphs);
    }


    @PostMapping("/{themeId}/add")
    public ResponseEntity<Paragraph> addParagraphToTheme(@PathVariable Long themeId, @RequestBody Paragraph paragraph) {
        Theme theme = themeService.findThemeById(themeId);
        if (theme == null) {
            return ResponseEntity.notFound().build();
        }
        paragraph.setTheme(theme);
        Paragraph savedParagraph = paragraphService.addParagraph(paragraph);
        theme.getParagraphList().add(savedParagraph);
        themeService.saveTheme(theme);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedParagraph);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Paragraph> updateParagraph(@PathVariable Long id, @RequestBody Paragraph updatedParagraph) {
        Paragraph paragraph = paragraphService.getParagraphById(id);
        if (paragraph == null) {
            return ResponseEntity.notFound().build();
        }

        paragraph.setName(updatedParagraph.getName());
        paragraph.setDescription(updatedParagraph.getDescription());

        Paragraph savedParagraph = paragraphService.updateParagraph(paragraph);
        return ResponseEntity.ok(savedParagraph);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParagraph(@PathVariable Long id) {
        Paragraph paragraph = paragraphService.getParagraphById(id);
        if (paragraph == null) {
            return ResponseEntity.notFound().build();
        }

        Theme theme = paragraph.getTheme();
        if (theme != null) {
            theme.getParagraphList().remove(paragraph);
            themeService.saveTheme(theme);
        }

        paragraphService.deleteParagraph(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Paragraph>> searchParagraphs(@RequestParam("searchTerm") String searchTerm) {
        List<Paragraph> foundParagraphs = paragraphService.findParagraph(searchTerm);
        return ResponseEntity.ok(foundParagraphs);
    }
}
