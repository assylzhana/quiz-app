package assylzhan.project.quizapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_text", nullable = false, length = 1000)
    private String questionText;

    @ElementCollection
    @CollectionTable(name = "question_variants", joinColumns = @JoinColumn(name = "question_id"))
    @OrderColumn(name = "answer_order")
    @Column(name = "variant")
    private List<String> questionVariants;

    @Column(name = "correct_answer_order", nullable = false)
    private int correctAnswerOrder;
}
