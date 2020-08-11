package dev.yonathaniel.model;

public class Result {
    private int id;
    private String studentRegNo;
    private int courseId;
    private double score;

    public Result(int id, String studentRegNo, int courseId, double score) {
        this.setId(id);
        this.setStudentRegNo(studentRegNo);
        this.setCourseId(courseId);
        this.setScore(score);
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
}
