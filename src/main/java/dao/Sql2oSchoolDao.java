package dao;

import models.Course;
import models.School;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oSchoolDao implements SchoolDao {
    private final Sql2o sql2o;

    public Sql2oSchoolDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(School school) {
        String query = "INSERT INTO school(schoolType) VALUES (:schoolType)";
       try (Connection con = sql2o.open()) {
           int id = (int) con.createQuery(query)
                   .bind(school)
                   .executeUpdate()
                   .getKey();
           school.setId(id);
       }catch (Sql2oException ex) {
           System.out.println(ex);
       }
    }
    @Override
    public void addCourseToType(School school, Course course) {
        String query = "INSERT INTO school_students(schoolId, courseId) VALUES (:schoolId, :courseId)";
        try(Connection con = sql2o.open()) {
            con.createQuery(query)
                    .addParameter("schoolId", school.getId())
                    .addParameter("courseId", course.getId())
                    .executeUpdate();
        } catch (Sql2oException e){
            System.out.println(e);
        }
    }
    @Override
    public List<Course> getAllCoursesForTypes(int schoolId) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT courseId FROM school_students WHERE schoolId = :schoolId";
        try(Connection con = sql2o.open()) {
            List<Integer> allCourseIds = con.createQuery(query)
                    .addParameter("schoolId", schoolId)
                    .executeAndFetch(Integer.class);
            for (Integer courseId : allCourseIds) {
                String query2 = "SELECT * from course WHERE id = courseId";
                courses.add(
                        con.createQuery(query2)
                                .addParameter("courseId", courseId)
                                .executeAndFetchFirst(Course.class));
            }
        }catch(Sql2oException e){
            System.out.println(e);
            }
            return courses;
        }
    }
