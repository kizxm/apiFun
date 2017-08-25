package models;

public class Course extends School {
    private String courseTitle;
    private String courseDescription;
    private int schoolId;
    private int id;

    public Course(String schoolType, String courseTitle, String courseDescription) {
        super(schoolType);
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
    }

    ///..GETTERS & SETTERS..///
    public String getCourseTitle() {
        return courseTitle;
    }
    public String getCourseDescription() {
        return courseDescription;
    }
    public int getSchoolId() {
        return schoolId;
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
        if (!super.equals(o)) return false;

        Course course = (Course) o;

        if (!courseTitle.equals(course.courseTitle)) return false;
        return courseDescription.equals(course.courseDescription);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + courseTitle.hashCode();
        result = 31 * result + courseDescription.hashCode();
        return result;
    }
    ///....///
}
