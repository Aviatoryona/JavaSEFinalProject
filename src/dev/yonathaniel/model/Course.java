package dev.yonathaniel.model;

public class Course {
    String title;
    double courseHours; //ie 45hrs, 35hrs
    int levelOfStudy; //ie 1,2,3,4...
    int id;
    Teacher teacher;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCourseHours() {
        return courseHours;
    }

    public void setCourseHours(double courseHours) {
        this.courseHours = courseHours;
    }

    public int getLevelOfStudy() {
        return levelOfStudy;
    }

    public void setLevelOfStudy(int levelOfStudy) {
        this.levelOfStudy = levelOfStudy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String toStringRow() {
        return "[id: " + this.getId() + ", Title: " + this.getTitle() + ", Hours: " + this.getCourseHours() + ", Level: " + this.getLevelOfStudy() + ", Teacher: " + this.getTeacher().getName() + "]";
    }

    @Override
    public String toString() {
        return "\nid:" + this.getId() +
                "\nTitle:'" + this.getTeacher() +
                "\nHours:'" + this.getCourseHours() +
                "\nLevel:'" + this.getLevelOfStudy() +
                "\nTeacher:'" + this.getTeacher().getName();
    }
}
