package assylzhan.project.quizapp.services;

import assylzhan.project.quizapp.models.Theme;
import assylzhan.project.quizapp.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;


    public Theme addTheme(Theme theme) {
        return themeRepository.save(theme);
    }
}
