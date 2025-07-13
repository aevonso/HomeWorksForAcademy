package ioStream.task5.models;

import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private String lastname;
    private String firstname;
    private int age;
    private String department;

    public Employee(String lastname, String firstname, int age, String department) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.department = department;
    }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return lastname + " " + firstname + ", возраст: " + age + ", отдел: " + department;
    }
}