package assylzhan.project.quizapp.controllers;

import assylzhan.project.quizapp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paragraph")
public class ParagraphController {

    @Autowired
    private CourseService courseService;


}
