package dao;

import models.School;
import org.junit.After;
import org.junit.Before;
import org.sql2o.Sql2o;
import org.junit.Test;
import org.sql2o.Connection;

import static org.junit.Assert.*;

public class sql2OSchoolDaoTest {
    private Sql2oSchoolDao schoolDao;
    private Connection conn;
    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        schoolDao = new Sql2oSchoolDao(sql2o);
//        courseDao = new Sql2oCourseDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    public School setUpSchool(){
        return new School("Private-School");
    }

    @Test
    public void newSchoolReturnsId_True() throws Exception {
        School school = setUpSchool();
        int schoolId = school.getId();
        schoolDao.add(school);
        assertNotEquals(schoolId, school.getId());
    }



}