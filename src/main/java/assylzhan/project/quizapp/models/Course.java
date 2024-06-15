package assylzhan.project.quizapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
    private String name;

    @Column(name = "course_explanation", nullable = false, length = 1000)
    private String explanation;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Theme> themeList ;
}