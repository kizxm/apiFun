package dao;

import models.Student;

import java.util.List;

public interface StudentDao {

    ///..CREATE..///
    void add (Student student);

    ///..READ..///
    Student findById(int id);
    List<Student> getAll();

    ///..UPDATE..///
    void update (int id, String name, int age, String gender, String schoolGroup, int grade);

    ///..DELETE..///
    void deleteById(int id);
    void deleteAllStudents();
}
