package com.mikael.safetynet.controller;

import com.mikael.safetynet.DTO.*;
import com.mikael.safetynet.Service.AlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class AlertController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(AlertController.class);
    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/childAlert")
    public List<ChildAlertDTO> childAlert(@RequestParam("address") String address) throws IOException {
        logger.info("GET /childAlert - address={}", address);
        try {
            return alertService.getChildAlert(address);
        } catch (Exception e) {
            logger.error("GET /childAlert - erreur address={}", address, e);
            throw e;
        }
    }

    @GetMapping("/phoneAlert")
    public List<String> phoneAlert(@RequestParam("firestation") int stationNumber) throws IOException {
        logger.info("GET /phoneAlert - stationNumber={}", stationNumber);
        try {
            return alertService.getPhoneAlert(stationNumber);
        } catch (Exception e) {
            logger.error("GET /phoneAlert - erreur stationNumber={}", stationNumber, e);
            throw e;
        }
    }

    @GetMapping("/fire")
    public FireDTO fire(@RequestParam("address") String address) throws IOException {
        logger.info("GET /fire - address={}", address);
        try {
            return alertService.getFireByAddress(address);
        } catch (Exception e) {
            logger.error("GET /fire - erreur address={}", address, e);
            throw e;
        }
    }

    @GetMapping("/personInfo")
    public List<PersonInfoDTO> personInfo(@RequestParam("lastName") String lastName) throws IOException {
        logger.info("GET /personInfo - lastName={}", lastName);
        try {
            return alertService.getPersonInfoByLastName(lastName);
        } catch (Exception e) {
            logger.error("GET /personInfo - erreur lastName={}", lastName, e);
            throw e;
        }
    }

    @GetMapping("/flood/stations")
    public Map<String, List<FloodPersonDTO>> floodByStations(@RequestParam("stations") List<Integer> stations) throws IOException {
        logger.info("GET /flood/stations - stations={}", stations);
        try {
            return alertService.getFloodByStations(stations);
        } catch (Exception e) {
            logger.error("GET /flood/stations - erreur stations={}", stations, e);
            throw e;
        }

    }

    @GetMapping(value = "/firestation", params = "stationNumber")
    public PersonsByStationDTO personsByStation(@RequestParam("stationNumber") int stationNumber) throws IOException {
        logger.info("GET /firestation?stationNumber={}", stationNumber);
        try {
            return alertService.getPersonsByStation(stationNumber);
        } catch (Exception e) {
            logger.error("GET /firestation?stationNumber={} - erreur", stationNumber, e);
            throw e;
        }
    }

    @GetMapping("/communityEmail")
    public List<String> communityEmail(@RequestParam("city") String city) throws IOException {
        logger.info("GET /communityEmail - city={}", city);
        try {
            return alertService.getCommunityEmails(city);
        } catch (Exception e) {
            logger.error("GET /communityEmail - erreur city={}", city, e);
            throw e;
        }
    }
}
