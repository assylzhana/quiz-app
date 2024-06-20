package assylzhan.project.quizapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @Column(unique = true)
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = false)
    @JsonIgnore
    private Theme theme;

    @OneToMany(mappedBy = "paragraph", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> questionLists = new ArrayList<>();

    private int quizTime = 15;
}