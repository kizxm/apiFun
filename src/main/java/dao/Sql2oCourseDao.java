package dao;

import models.Course;
import models.Student;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;

public class Sql2oCourseDao implements CourseDao{
    private final Sql2o sql2o;
    public Sql2oCourseDao(Sql2o sql2o) {this.sql2o = sql2o;}

    @Override
    public void add(Course course) {
        String query = "INSERT INTO courses (courseTitle, courseDescription, schoolId) VALUES (:courseTitle, :courseDescription, :SchoolId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(query)
                    .bind(course)
                    .executeUpdate()
                    .getKey();
            course.setCourseId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public void addStudentToCourse(Course course, Student student) {
        String query = "INSERT INT school_students(schoolId, studentId) VALUES (:schoolId, :studentId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(query)
                    .addParameter("schoolId", course.getCourseId())
                    .addParameter("studentId", student.getId())
                    .executeUpdate();
        } catch (Sql2oException e) {
            System.out.println(e);
        }
    }
    @Override
    public Course findById(int courseId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM courses WHERE courseId = :courseId")
                    .addParameter("courseId", courseId)
                    .executeAndFetchFirst(Course.class);
        }
    }
    @Override
    public List<Course> getAllTypesByCourse(int schoolId){
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM courses WHERE schoolId = :schoolId")
                    .addParameter("schoolId", schoolId)
                    .executeAndFetch(Course.class);
        }
    }
    @Override
    public List<Student> getAllStudentsByCourse(int schoolId){
        List<Student> students = new ArrayList<>();
        String query = "SELECT studentId FROM school_students WHERE schoolId = :schoolId";
        try (Connection con = sql2o.open()) {
            List<Integer> allStudentIds = con.createQuery(query)
                    .addParameter("schoolId", schoolId)
                    .executeAndFetch(Integer.class);
            for (Integer studentId : allStudentIds) {
                String query2 = "SELECT * from students WHERE id = :studentId";
                students.add(
                        con.createQuery(query2)
                                .addParameter("studentId", studentId)
                                .executeAndFetchFirst(Student.class));
            }
        }catch(Sql2oException e) {
            System.out.println(e);
        }
        return students;
    }
    @Override
    public List<Course> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM courses")
                    .executeAndFetch(Course.class);
        }
    }
    @Override
    public void update (int courseId, String courseTitle, String courseDescription) {
        String query = "UPDATE courses SET (courseTitle, courseDescription) = (:courseTitle, :courseDescription) WHERE courseId = :courseId";
        try(Connection con = sql2o.open()){
            con.createQuery(query)
                    .addParameter("courseId", courseId)
                    .addParameter("courseTitle", courseTitle)
                    .addParameter("courseDescription", courseDescription)
                    .executeUpdate();
        } catch (Sql2oException e){
            System.out.println(e);
        }
    }
    @Override
    public void deleteById (int courseId) {
        String query = "DELETE FROM courses WHERE courseId = :courseId";
        try(Connection con = sql2o.open()){
            con.createQuery(query)
                    .addParameter("courseId", courseId)
                    .executeUpdate();
        } catch (Sql2oException e){
            System.out.println(e);
        }
    }
    @Override
    public void deleteAllCourses() {
        String sql = "DELETE from courses";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}
