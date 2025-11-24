package com.mikael.safetynet.DTO;

import java.util.List;

public class ChildAlertDTO {

    private String firstName;
    private String lastName;
    private int age;
    private List<HouseMemberDTO> otherMembers;

    public ChildAlertDTO() {}

    public ChildAlertDTO(String firstName, String lastName, int age, List<HouseMemberDTO> otherMembers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.otherMembers = otherMembers;
    }

    // getters / setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public List<HouseMemberDTO> getOtherMembers() { return otherMembers; }
    public void setOtherMembers(List<HouseMemberDTO> otherMembers) { this.otherMembers = otherMembers; }

}
