package dev.yonathaniel.model;

public class Student{
    private long id;
    private String course;
    private String registrationNo;
    private String name;
    private String idNumber;

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

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String toStringRow(){
        return "[id: " + this.getId() + ", name: " + this.getName() + ", registrationNo: " + this.getRegistrationNo() + ", course: " + this.getCourse() + ", idNumber: " + this.getIdNumber() + "]";
    }

    @Override
    public String toString() {
        return "\nid:" + id +
                "\ncourse:'" + course + '\'' +
                "\nregistrationNo:'" + registrationNo + '\'' +
                "\nname:'" + name + '\'' +
                "\nidNumber:'" + idNumber + '\'';
    }
}
