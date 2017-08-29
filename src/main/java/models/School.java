package models;

public class School {
    private String schoolType;
    private int typeId;

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
    public int getTypeId() { return typeId; }
    public void setTypeId(int id) {
        this.typeId = id;
    }
    ///....///

    ///..OVERRIDES..///
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        School school = (School) o;

        return schoolType.equals(school.schoolType);
    }
    @Override
    public int hashCode() {
        int result = schoolType.hashCode();
        return result;
    }
    ///....///
}
