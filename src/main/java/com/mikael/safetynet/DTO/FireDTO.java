package com.mikael.safetynet.DTO;

import java.util.List;
import java.util.Optional;

public class FireDTO {
    private Optional<Integer> stationNumber;
    private List<ResidentFireDTO> residents;

    public FireDTO() {}

    public FireDTO(Optional<Integer> stationNumber, List<ResidentFireDTO> residents) {
        this.stationNumber = stationNumber;
        this.residents = residents;
    }

    // getters / setters
    public Optional<Integer> getStationNumber() { return stationNumber; }
    public void setStationNumber(Integer stationNumber) { this.stationNumber = stationNumber.describeConstable(); }
    public List<ResidentFireDTO> getResidents() { return residents; }
    public void setResidents(List<ResidentFireDTO> residents) { this.residents = residents; }
}
