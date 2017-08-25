package dao;

import models.School;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

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
}
