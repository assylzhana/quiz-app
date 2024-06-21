ALTER TABLE users
    ADD CONSTRAINT UK_r43af9ap4edm43mmtq01oddj6 UNIQUE (username);

ALTER TABLE courses_theme_list
    ADD CONSTRAINT FKruud1ro89f8l6gdr6if1eobl5
        FOREIGN KEY (theme_list_id)
            REFERENCES themes;


ALTER TABLE courses_theme_list
    ADD CONSTRAINT FKma88ro44xm2efa0cjioju8u2a
        FOREIGN KEY (course_id)
            REFERENCES courses;


ALTER TABLE paragraphs
    ADD CONSTRAINT FK3llknptjdbnhikv0whtw37sqa
        FOREIGN KEY (theme_id)
            REFERENCES themes;

ALTER TABLE question_choices
    ADD CONSTRAINT FK77biojwg2xd8kc8a2odnx3ld4
        FOREIGN KEY (question_id)
            REFERENCES questions;

ALTER TABLE question_correct_answers
    ADD CONSTRAINT FK20d694wynxtnrdqn8d6g1brcp
        FOREIGN KEY (question_id)
            REFERENCES questions;

ALTER TABLE questions
    ADD CONSTRAINT FKp0wu29kkp3teu5la7j7u6p4x9
        FOREIGN KEY (paragraph_id)
            REFERENCES paragraphs;

ALTER TABLE themes
    ADD CONSTRAINT FK2epacbt8yl366kwle5396860n
        FOREIGN KEY (course_id)
            REFERENCES courses;

ALTER TABLE user_quiz_scores
    ADD CONSTRAINT FKj97n12ifko5feif1jn985k0ln
        FOREIGN KEY (paragraph_id)
            REFERENCES paragraphs;


ALTER TABLE user_quiz_scores
    ADD CONSTRAINT FKec65ylgj6cnyggf661vtpvpsf
        FOREIGN KEY (user_id)
            REFERENCES users;

ALTER TABLE users
    ADD CONSTRAINT FKp56c1712k691lhsyewcssf40f
        FOREIGN KEY (role_id)
            REFERENCES roles;

ALTER TABLE courses
    DROP CONSTRAINT IF EXISTS UK_e6rl70tjjnfr7cpqd6koh0abk;
