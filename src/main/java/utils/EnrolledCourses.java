package utils;

public class EnrolledCourses {
    private int course_id;
    private String email;
    private String course_name;

    public EnrolledCourses(String email) {
        this.email = email;
    }

    public EnrolledCourses() {

    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}
