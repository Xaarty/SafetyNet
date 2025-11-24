package com.mikael.safetynet.DTO;

import java.util.List;

public class PersonsByStationDTO {
    private List<PersonCoveredDTO> persons;
    private int adultCount;
    private int childCount;

    public PersonsByStationDTO() {}
    public PersonsByStationDTO(List<PersonCoveredDTO> persons, int adultCount, int childCount) {
        this.persons = persons; this.adultCount = adultCount; this.childCount = childCount;
    }
    // getters / setters
    public List<PersonCoveredDTO> getPersons() { return persons; }
    public void setPersons(List<PersonCoveredDTO> persons) { this.persons = persons; }
    public int getAdultCount() { return adultCount; }
    public void setAdultCount(int adultCount) { this.adultCount = adultCount; }
    public int getChildCount() { return childCount; }
    public void setChildCount(int childCount) { this.childCount = childCount; }
}