SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS school (
    typeId int PRIMARY KEY auto_increment,
    schoolType VARCHAR,
);

CREATE TABLE IF NOT EXISTS students (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    age INTEGER,
    gender VARCHAR,
    schoolGroup VARCHAR,
    grade INTEGER
);

CREATE TABLE IF NOT EXISTS courses (
    courseId int PRIMARY KEY auto_increment,
    courseTitle VARCHAR,
    courseDescription VARCHAR,
    schoolId INTEGER
);

CREATE TABLE IF NOT EXISTS school_students (
    id int PRIMARY KEY auto_increment,
    schoolId INTEGER,
    studentId INTEGER,
    courseId INTEGER
);