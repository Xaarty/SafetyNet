package com.mikael.safetynet.controller;

import com.mikael.safetynet.DTO.*;
import com.mikael.safetynet.Service.AlertService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/childAlert")
    public List<ChildAlertDTO> childAlert(@RequestParam("address") String address) throws IOException {
        return alertService.getChildAlert(address);
    }

    @GetMapping("/phoneAlert")
    public List<String> phoneAlert(@RequestParam("firestation") int stationNumber) throws IOException {
        return alertService.getPhoneAlert(stationNumber);
    }

    @GetMapping("/fire")
    public FireDTO fire(@RequestParam("address") String address) throws IOException {
        return alertService.getFireByAddress(address);
    }

    @GetMapping("/personInfo")
    public List<PersonInfoDTO> personInfo(@RequestParam("lastName") String lastName) throws IOException {
        return alertService.getPersonInfoByLastName(lastName);
    }

    @GetMapping("/flood/stations")
    public Map<String, List<FloodPersonDTO>> floodByStations(@RequestParam("stations") List<Integer> stations) throws IOException {
        return alertService.getFloodByStations(stations);
    }

    @GetMapping(value = "/firestation", params = "stationNumber")
    public PersonsByStationDTO personsByStation(@RequestParam("stationNumber") int stationNumber) throws IOException {
        System.out.println("DEBUG: AlertController.personsByStation() called stationNumber=" + stationNumber);
        return alertService.getPersonsByStation(stationNumber);
    }

    @GetMapping("/communityEmail")
    public List<String> communityEmail(@RequestParam("city") String city) throws IOException {
        return alertService.getCommunityEmails(city);
    }
}
