package dao;
import models.Course;
import models.School;

import java.sql.Connection;
import java.util.List;


public interface SchoolDao {

    ///..CREATE..///
    void add(School school);
    void addCourseToType(School school, Course course);

    ///..READ..///
    List<Course> getAllCoursesForTypes(int schoolId);
    List<School> getAll();
    School findById(int typeId);

    ///..UPDATE..///
    void update(int typeId, String newSchoolType);

    ///..DELETE..///
    void deleteByTypeId(int typeId);
    void deleteAllSchools();
}
