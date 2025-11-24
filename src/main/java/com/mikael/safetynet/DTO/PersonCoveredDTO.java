package com.mikael.safetynet.DTO;

public class PersonCoveredDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private int age;

    public PersonCoveredDTO() {}
    public PersonCoveredDTO(String firstName, String lastName, String address, String phone, int age) {
        this.firstName = firstName; this.lastName = lastName; this.address = address; this.phone = phone; this.age = age;
    }
    // getters / setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}