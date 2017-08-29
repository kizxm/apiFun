import com.google.gson.Gson;
import dao.Sql2oCourseDao;
import dao.Sql2oSchoolDao;
import dao.Sql2oStudentDao;
import exceptions.ApiException;
import models.School;
import models.Student;
import models.Course;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        Sql2oCourseDao courseDao;
        Sql2oStudentDao studentDao;
        Sql2oSchoolDao schoolDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/API.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        courseDao = new Sql2oCourseDao(sql2o);
        schoolDao = new Sql2oSchoolDao(sql2o);
        studentDao = new Sql2oStudentDao(sql2o);
        conn = sql2o.open();

        ///-------------------------------------///

        ///..school CREATE..///
        post("/school/new", "application/json", (request, response) -> {
            School school = gson.fromJson(request.body(), School.class);
            schoolDao.add(school);
            response.status(201);
            return gson.toJson(school);
        });
        ///..school READ..///
        get("/school", "application/json", (request, response) -> {
            return gson.toJson(schoolDao.getAll());
        });
        get("/school/:typeId", "application/json", (request, response) -> {
            int schoolId = Integer.parseInt(request.params("locationId"));
            School school = schoolDao.findById(schoolId);
            if (school == null) {
                throw new ApiException(String.format("No type with the id '%d' found", schoolId), 404);
            }
            return gson.toJson(school);
        });
        ///..school UPDATE..///
        post("/school/:id/update", "application/json", (request, response) -> {
            int schoolId = Integer.parseInt(request.params("id"));
            School school = gson.fromJson(request.body(), School.class);
            schoolDao.update(schoolId, school.getSchoolType());
            response.status(201);
            return gson.toJson(school);
        });
        ///..school DELETE..///
        get("/school/:id/delete", "application/json", (request, response) -> {
            int schoolId = Integer.parseInt(request.params("id"));
            schoolDao.deleteByTypeId(schoolId);
            return schoolId;
        });
        get("/school/:id/delete/all", "application/json", (request, response) -> {
            schoolDao.deleteAllSchools();
            return schoolDao.getAll().size();
        });
        ///..student CREATE..///
        post("/students/new", "application/json", (request, response) -> {
            Student student = gson.fromJson(request.body(), Student.class);
            studentDao.add(student);
            response.status(201);
            return gson.toJson(student);
        });
        ///..student READ..///
        get("/students", "application/json", (request, response) -> {
            return gson.toJson(studentDao.getAll());
        });
        get("/students/:id", "application/json", (request, response) -> {
            int studentId = Integer.parseInt(request.params("id"));
            Student student = studentDao.findById(studentId);
            if (student == null){
                throw new ApiException(String.format("No student with the id '%d' found", studentId), 404);
            }
            return gson.toJson(student);
        });
        ///..student UPDATE..///
        post("/students/:id/update", "application/json", (request, response) -> {
            int studentId = Integer.parseInt(request.params("id"));
            Student student = gson.fromJson(request.body(), Student.class);
            studentDao.update(studentId, student.getName(), student.getAge(), student.getGender(), student.getSchoolGroup(),student.getGrade());
            response.status(201);
            return gson.toJson(student);
        });
        ///..student DELETE..///
        get("/students/:id/delete", "application/json", (request, response) -> {
            int studentId = Integer.parseInt(request.params("id"));
            studentDao.deleteById(studentId);
            return studentId;
        });
        get("/students/:id/delete/all", "application/json", (request, response) -> {
            studentDao.deleteAllStudents();
            return studentDao.getAll().size();
        });
        ///..course CREATE..///
        post("/courses/new", "application/json", (request, response) -> {
            Course course = gson.fromJson(request.body(), Course.class);
            courseDao.add(course);
            response.status(201);
            return gson.toJson(course);
        });
        ///..course READ..///
        get("/courses", "application/json", (request, response) -> {
            return gson.toJson(courseDao.getAll());
        });
        get("/courses/:id", "application/json", (request, response) -> {
            int courseId = Integer.parseInt(request.params("id"));
            Course course = courseDao.findById(courseId);
            if (course == null){
                throw new ApiException(String.format("No course with the id '%d' found", courseId), 404);
            }
            return gson.toJson(course);
        });
    }
}
