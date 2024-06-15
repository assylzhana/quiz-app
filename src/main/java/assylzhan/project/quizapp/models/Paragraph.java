package assylzhan.project.quizapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paragraphs")
@Getter
@Setter
public class Paragraph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = false)
    @JsonBackReference
    private Theme theme;


    @OneToMany(mappedBy = "paragraph", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Question> questionLists = new ArrayList<>();
}