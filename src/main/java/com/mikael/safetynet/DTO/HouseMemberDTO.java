package com.mikael.safetynet.DTO;

public class HouseMemberDTO {

    private String firstName;
    private String lastName;

    public HouseMemberDTO() {}

    public HouseMemberDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // getters / setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

}
