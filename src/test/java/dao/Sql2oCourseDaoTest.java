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
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        sql2o = new Sql2o(connectionString, "", "");
        courseDao = new Sql2oCourseDao(sql2o);
        studentDao = new Sql2oStudentDao(sql2o);
        conn = sql2o.open();
    }
    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    public Course setUpCourse() {
        return new Course("Private", "Math Class", "Simple math class");
    }

    @Test
    public void addCourseWithIdReturn_True() throws Exception {
        Course course = setUpCourse();
        courseDao.add(course);
        assertEquals(1, course.getCourseId());
        assertEquals("Simple math class", course.getCourseDescription());
        assertEquals("Math Class", course.getCourseTitle());
        assertEquals("Private", course.getSchoolType());
    }
    @Test
    public void findCourseByIdReturn_True() throws Exception {
        Course course = setUpCourse();
        courseDao.add(course);
        Course foundCourse = courseDao.findById(course.getCourseId());
        assertEquals(course, foundCourse);
    }
}