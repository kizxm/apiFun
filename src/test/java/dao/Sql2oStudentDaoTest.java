package dao;

import models.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oStudentDaoTest {
    private Sql2o sql2o;
    private Sql2oStudentDao studentDao;
    private Connection conn;
    ///-------------------------------------///
    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        sql2o = new Sql2o(connectionString, "", "");
        studentDao = new Sql2oStudentDao(sql2o);
        conn = sql2o.open();
    }
    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    public Student setUpStudent(){
        return new Student("Religious", "Bob", 15, "non-binary", "Highschool", 10);
    }
    ///-------------------------------------///

    @Test
    public void StudentInstantiates_True() throws Exception {
        Student student = setUpStudent();
        assertEquals(true, student instanceof Student);
    }
    @Test
    public void newStudentIdReturns_True() throws Exception {
        Student student = setUpStudent();
        studentDao.add(student);
        assertEquals(1, student.getId());
        assertEquals("Bob", student.getName());
        assertEquals(15, student.getAge());
        assertEquals("non-binary", student.getGender());
        assertEquals("Highschool", student.getSchoolGroup());
        assertEquals(10, student.getGrade());
    }
    @Test
    public void allStudentSizeReturns_2() throws Exception {
        Student student = setUpStudent();
        studentDao.add(student);
        studentDao.add(new Student("Private", "Henry", 14, "male", "Highschool", 9));
        assertEquals(2, studentDao.getAll().size());
    }
    @Test
    public void studentIdReturns_True() throws Exception {
        Student student = setUpStudent();
        studentDao.add(student);
        Student findStudent = studentDao.findById(student.getId());
        assertEquals(student, findStudent);
    }

}