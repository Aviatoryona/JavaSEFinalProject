package dev.yonathaniel.model;

public class Teacher {
    private long id;
    private String course;
    private String name;
    private String staffNo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String toStringRow(){
        return "[id: " + this.getId() + ", name: " + this.getName() + ", StaffNo: " + this.getStaffNo() + ", course: " + this.getCourse() + "]";
    }

    @Override
    public String toString() {
        return "\nid:" + id +
                "\ncourse:'" + course +
                "\nStaff NO:'" + staffNo +
                "\nname:'" + name;
    }
}
