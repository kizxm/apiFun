package models;

public class School {
    private String schoolType;
    private int id;

    public School(String schoolType){
        this.schoolType = schoolType;
    }


    ///..GETTERS & SETTERS..///
    public String getSchoolType() {
        return schoolType;
    }
    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    ///....///

    ///..OVERRIDES..///
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        School school = (School) o;

        if (id != school.id) return false;
        return schoolType.equals(school.schoolType);
    }

    @Override
    public int hashCode() {
        int result = schoolType.hashCode();
        result = 31 * result + id;
        return result;
    }
    ///....///
}
