package assylzhan.project.quizapp.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddCourse() throws Exception {
        String courseJson = "{\"name\": \"Course\", \"description\": \" description\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courseJson))
                .andExpect(status().isCreated());
    }
}
