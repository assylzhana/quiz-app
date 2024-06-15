package assylzhan.project.quizapp.repositories;

import assylzhan.project.quizapp.models.Course;
import assylzhan.project.quizapp.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
    @Query("SELECT t FROM Theme t JOIN Course c JOIN c.themeList ctl WHERE t.name = :name AND c.id = :courseId")
    Theme findByNameAndCourse(@Param("name") String name, @Param("courseId") Long courseId);

    List<Theme> findAllByName(String searchTerm);

    @Query(value = "SELECT t.* FROM themes t " +
            "JOIN courses_theme_list ctl ON t.id = ctl.theme_list_id " +
            "JOIN courses c ON ctl.course_id = c.id " +
            "WHERE t.name = :themeName AND c.course_name = :courseName",
            nativeQuery = true)
    Theme findByNameCourse(@Param("themeName") String themeName, @Param("courseName") String courseName);

    List<Theme> findByParagraphListId(Long paragraphId);
}


