package dev.yonathaniel.model;

public class Result {
    private int id;
    private String studentRegNo;
    private int courseId;
    private double score;

    //
    private Student student;
    private Course course;

    public Result() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentRegNo() {
        return studentRegNo;
    }

    public void setStudentRegNo(String studentRegNo) {
        this.studentRegNo = studentRegNo;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String toStringRow() {
        return "[id: " + this.getId() + ", Student Name: " + this.getStudent().getName() + "," +
                " Course Title: " + this.getCourse().getTitle() + "," +
                " Score: " + this.getScore() + "]";
    }

    @Override
    public String toString() {
        return "\nid:" + this.getId() +
                "\nStudent Name:'" + this.getStudent().getName() +
                "\nCourse Title:'" + this.getCourse().getTitle() +
                "\nScore:'" + this.getScore();
    }
}
