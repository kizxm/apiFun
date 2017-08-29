package models;

public class Student extends School{
    private String name;
    private int age;
    private String gender;
    private String schoolGroup;
    private int grade;
    private int id;

    public Student(String schoolType, String name, int age, String gender, String schoolGroup, int grade) {
        super(schoolType);
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.schoolGroup = schoolGroup;
        this.grade = grade;
    }

    ///..GETTERS & SETTERS..///

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getGender() {
        return gender;
    }
    public String getSchoolGroup() {
        return schoolGroup;
    }
    public int getGrade() {
        return grade;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }

    public void setSchoolGroup(String schoolGroup) {
        this.schoolGroup = schoolGroup;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
    ///....///

    ///..OVERRIDES..///

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (age != student.age) return false;
        if (grade != student.grade) return false;
        if (!name.equals(student.name)) return false;
        if (!gender.equals(student.gender)) return false;
        return schoolGroup.equals(student.schoolGroup);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + age;
        result = 31 * result + gender.hashCode();
        result = 31 * result + schoolGroup.hashCode();
        return result;
    }
    ///....///
}
