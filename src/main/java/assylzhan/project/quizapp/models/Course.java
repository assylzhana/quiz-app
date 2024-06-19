package assylzhan.project.quizapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "course_name", nullable = false, unique = true)
    @NotBlank
    private String name;

    @Column(name = "course_explanation", nullable = false, length = 1000)
    @NotBlank
    private String explanation;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Theme> themeList ;
}