package dao;

import models.Course;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oCourseDaoTest {
    private Sql2o sql2o;
    private Sql2oCourseDao courseDao;
    private Sql2oStudentDao studentDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        sql2o = new Sql2o(connectionString, "", "");
        courseDao = new Sql2oCourseDao(sql2o);
        studentDao = new Sql2oStudentDao(sql2o);
        con = sql2o.open();
    }
    @After
    public void tearDown() throws Exception {
        con.close();
    }
    public Course setUpCourse() {
        return new Course("Private", "Math Class", "Simple math class");
    }

    @Test
    public void addCourseWithId() throws Exception {
        Course course = setUpCourse();
        courseDao.add(course);
        assertEquals(1, course.getCourseId());
        assertEquals("Private", course.getSchoolType());
        assertEquals("Math Class", course.getCourseTitle());
        assertEquals("Simple math class", course.getCourseDescription());
    }
}