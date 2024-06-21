INSERT INTO roles (id, name)
VALUES
    (1,'ROLE_ADMIN'),
    (2,'ROLE_USER');

INSERT INTO users (id, email, password, total_score, username, role_id)
VALUES (1, 'kabibullaasylzhan@gmail.com', '$2a$10$LcToW3M54oTH6iWmRK5ZmOCY2vAw0wEk3O3XFpWnYp.EVMHoe7NFG', 0, 'assylzhana', 1);

INSERT INTO users (id, email, password, total_score, username, role_id)
VALUES (2, 'asylzhan@gmail.com', '$2a$10$LcToW3M54oTH6iWmRK5ZmOCY2vAw0wEk3O3XFpWnYp.EVMHoe7NFG', 0, 'assyl', 2);


INSERT INTO courses (id, course_explanation, course_name)
VALUES (1, 'Welcome to exciting world of Math', 'Math');

INSERT INTO themes (id, name, description, course_id)
VALUES (1, 'Trigonometry', 'Study about sine, cosine, tan, cotan and etc',  1);

INSERT INTO courses_theme_list (course_id, theme_list_id)
VALUES (1, 1);

INSERT INTO paragraphs (id, description, name, quiz_time, theme_id)
VALUES (1, 'sine a trigonometric function' , 'Sine', 15, 1);

INSERT INTO questions (id, question, question_type, paragraph_id)
VALUES (1,'What is sine?' , 'True/false',1);

INSERT INTO question_choices (question_id, choice)
VALUES (1, 'trigonometric function');

INSERT INTO question_choices (question_id, choice)
VALUES (1, 'nothing');

INSERT INTO question_correct_answers (question_id, correct_answer)
VALUES (1, 'trigonometric function');

INSERT INTO user_quiz_scores (id, score, paragraph_id, user_id)
VALUES (1,100, 1,2);