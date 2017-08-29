package dao;

import models.Course;
import models.Student;
import models.School;

import java.util.List;

public interface CourseDao {

    ///..CREATE..///
    void add (Course course);
    void addStudentToCourse (Course course, Student student);

    ///..READ..///
    Course findById(int id);
    List<Course> getAllTypesByCourse (int schoolId);
    List<Student> getAllStudentsByCourse (int studentId);
    List<Course> getAll();

    ///..UPDATE..///
    void update (int courseId, String courseTitle, String courseDescription);

    ///..DELETE..///
    void deleteById (int courseId);
    void deleteAllCourses();
}
