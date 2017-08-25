package dao;
import models.Course;
import models.School;

import java.util.List;


public interface SchoolDao {

    ///..CREATE..///
    void add(School school);
    void addCourseToType(School school, Course course);
    ///..READ..///
    List<Course> getAllCoursesForTypes(int schoolId);
    ///..UPDATE..///
    ///..DELETE..///

}
