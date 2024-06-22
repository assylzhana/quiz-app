CREATE TABLE IF NOT EXISTS courses (
                                       id bigserial not null,
                                       course_explanation varchar(1000) not null,
                                       course_name varchar(255) not null,
                                       PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS courses_theme_list (
                                                  course_id bigint not null,
                                                  theme_list_id bigint not null
);
create table IF NOT EXISTS paragraphs (
                                          id bigserial not null,
                                          description varchar(255),
                                          name varchar(255),
                                          quiz_time integer not null,
                                          theme_id bigint not null,
                                          primary key (id)
);

create table IF NOT EXISTS question_choices (
                                                question_id bigint not null,
                                                choice varchar(255)
);
create table IF NOT EXISTS question_correct_answers (
                                                        question_id bigint not null,
                                                        correct_answer varchar(255)
);
CREATE TABLE IF NOT EXISTS questions (
                                         id bigserial not null,
                                         question varchar(255),
                                         question_type varchar(255),
                                         paragraph_id bigint,
                                         PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS roles (
                                     id bigserial not null,
                                     name varchar(255),
                                     PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS themes (
                                      id bigserial not null,
                                      description varchar(255),
                                      name varchar(255),
                                      course_id bigint,
                                      PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS user_quiz_scores (
                                                id bigserial not null,
                                                score integer not null,
                                                paragraph_id bigint not null,
                                                user_id bigint not null,
                                                PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS users (
                                     id bigserial not null,
                                     email varchar(255) not null,
                                     password varchar(255),
                                     total_score integer not null,
                                     username varchar(255) not null,
                                     role_id bigint,
                                     PRIMARY KEY (id)
);

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


INSERT INTO roles (id, name)
VALUES
    (1,'ROLE_ADMIN'),
    (2,'ROLE_USER');

INSERT INTO users (id, email, password, total_score, username, role_id)
VALUES (1, 'kabibullaasylzhan@gmail.com', '$2a$10$LcToW3M54oTH6iWmRK5ZmOCY2vAw0wEk3O3XFpWnYp.EVMHoe7NFG', 0, 'assylzhana', 1);



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
