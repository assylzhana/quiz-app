package assylzhan.project.quizapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {
    private String username;
    private String email;
    private String password;
}
