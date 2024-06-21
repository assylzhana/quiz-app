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