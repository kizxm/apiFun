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
    ///-------------------------------------///
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
    public Course setUpCourse2() {
        return new Course("Independent", "Sewing Class", "Basics of sewing");
    }
    ///-------------------------------------///

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
    @Test
    public void returnAllCourses_True() throws Exception {
        Course course = setUpCourse();
        courseDao.add(course);
        courseDao.add(new Course("Public", "Humanities","Study of human culture."));
        assertEquals(2, courseDao.getAll().size());
    }
    @Test
    public void updatingCourseUpdatesTitle_True() throws Exception {
        Course course = setUpCourse();
        courseDao.add(course);
        courseDao.update(course.getCourseId(), "Science", "Basic science class.");
        Course updatedCourse = courseDao.findById(course.getCourseId());
        assertEquals("Science", updatedCourse.getCourseTitle());
    }
    @Test
    public void updatingCourseUpdatesDescription_True() throws Exception {
        Course course = setUpCourse();
        courseDao.add(course);
        courseDao.update(course.getCourseId(), "Science", "Basic science class.");
        Course updatedCourse = courseDao.findById(course.getCourseId());
        assertEquals("Basic science class.", updatedCourse.getCourseDescription());
    }
    @Test
    public void noCoursesReturns_0() throws Exception {
        assertEquals(0, courseDao.getAll().size());
    }
    @Test
    public void deleteByIdDeletes_True() throws Exception {
        Course course = setUpCourse();
        Course course2 = setUpCourse2();
        courseDao.add(course);
        courseDao.add(course2);
        courseDao.deleteById(course.getCourseId());
        assertEquals(1, courseDao.getAll().size());
    }
    @Test
    public void idReturnsCorrectly_True() throws Exception {
        Course course = setUpCourse();
        int origId = course.getSchoolId();
        courseDao.add(course);
        assertEquals(origId, courseDao.findById(course.getCourseId()).getSchoolId());
    }
    @Test
    public void deleteAllCoursesDeletes_True() throws Exception {
        Course course = setUpCourse();
        Course course2 = setUpCourse2();
        courseDao.add(course);
        courseDao.add(course2);
        int totalSize = courseDao.getAll().size();
        courseDao.deleteAllCourses();
        assertTrue(totalSize > 0 && totalSize > courseDao.getAll().size());
    }
}