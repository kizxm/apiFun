package dao;

import models.Student;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oStudentDao implements StudentDao {
    private final Sql2o sql2o;
    public Sql2oStudentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Student student) {
        String query = "INSERT INTO students (name, age, gender, schoolGroup, grade) VALUES (:name, :age, :gender, :schoolGroup, :grade)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(query)
                    .bind(student)
                    .executeUpdate()
                    .getKey();
            student.setId(id);
        } catch (Sql2oException e) {
            System.out.println(e);
        }
    }
    @Override
    public Student findById(int id) {
        String query = "SELECT * FROM students WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(query)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Student.class);
        }
    }
    @Override
    public List<Student> getAll() {
        String query = "SELECT * from students";
        try(Connection con = sql2o.open()){
            return con.createQuery(query)
                    .executeAndFetch(Student.class);
        }
    }
    @Override
    public void update(int id, String name, int age, String gender, String schoolGroup, int grade){
        String query = "UPDATE students SET (name, age, gender, schoolGroup, grade) = (:name, :age, :gender, :schoolGroup, :grade) WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(query)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .addParameter("age", age)
                    .addParameter("gender", gender)
                    .addParameter("schoolGroup", schoolGroup)
                    .addParameter("grade", grade)
                    .executeUpdate();
        } catch (Sql2oException e) {
            System.out.println(e);
        }
    }
    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM students WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public void deleteAllStudents() {
        String sql = "DELETE from students";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
