package com.mikael.safetynet.Service;

import com.mikael.safetynet.DTO.*;
import com.mikael.safetynet.model.FireStation;
import com.mikael.safetynet.model.MedicalRecord;
import com.mikael.safetynet.model.Person;
import com.mikael.safetynet.repository.FireStationRespository;
import com.mikael.safetynet.repository.MedicalRecordRepository;
import com.mikael.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlertService {

    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final FireStationRespository fireStationRespository;

    private static final DateTimeFormatter BIRTHDATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public AlertService(PersonRepository personRepository,
                        MedicalRecordRepository medicalRecordRepository,
                        FireStationRespository fireStationRespository) {
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.fireStationRespository = fireStationRespository;
    }

    public int getAge(String birthdate) {

        LocalDate bd = LocalDate.parse(birthdate, BIRTHDATE_FORMATTER);
        LocalDate now = LocalDate.now();

        Period period = Period.between(bd, now);
        return period.getYears();

    }

    public PersonsByStationDTO getPersonsByStation(int stationNumber) throws IOException {

        List<String> addresses = fireStationRespository.getAll().stream()
                .filter(FireStation -> {
                    String station = FireStation.getStation();
                    return Integer.parseInt(station) == stationNumber;
                })
                .map(FireStation::getAddress)
                .toList();

        List<Person> persons = personRepository.getAll().stream()
                .filter(Person -> Person.getAddress() != null && addresses.contains(Person.getAddress()))
                .toList();

        List<PersonCoveredDTO> dtoList = new ArrayList<>();
        int adultCount = 0;
        int childCount = 0;

        for (Person person : persons) {
            int age = -1;

            MedicalRecord mr = medicalRecordRepository.getByName(person.getFirstName(), person.getLastName());
            if (mr != null && mr.getBirthdate() != null) {
                age = getAge(mr.getBirthdate());
            }

                if (age >= 0 && age <= 18) {
                    childCount++;
                } else {
                    adultCount++;
                }

                PersonCoveredDTO dto = new PersonCoveredDTO(
                        person.getFirstName(),
                        person.getLastName(),
                        person.getAddress(),
                        person.getPhone(),
                        age
                );
                dtoList.add(dto);
            }

            return new PersonsByStationDTO(dtoList, adultCount, childCount);
        }

    public List<ChildAlertDTO> getChildAlert(String address) throws IOException {

        List<Person> residents = personRepository.getAll().stream()
                .filter(person -> address.equals(person.getAddress()))
                .toList();

        Map<String, Integer> ageByKey = new HashMap<>();
        for (Person person : residents) {
            MedicalRecord medicalRecord = medicalRecordRepository.getByName(person.getFirstName(), person.getLastName());
                int age = getAge(medicalRecord.getBirthdate());
                    ageByKey.put(key(person), age);
        }

        List<Person> children = residents.stream()
                .filter(person -> ageByKey.get(key(person)) <= 18)
                .toList();

        List<ChildAlertDTO> result = new ArrayList<>();
        for (Person child : children) {
            int age = ageByKey.get(key(child));
            List<HouseMemberDTO> others = residents.stream()
                    .filter(person ->
                            !(person.getFirstName().equals(child.getFirstName()) && person.getLastName().equals(child.getLastName())))
                    .map(person -> new HouseMemberDTO(person.getFirstName(), person.getLastName()))
                    .collect(Collectors.toList());
            result.add(new ChildAlertDTO(child.getFirstName(), child.getLastName(), age, others));
        }

        return result;
    }

    private String key(Person person) {
        return person.getFirstName().trim().toLowerCase() + "|" + person.getLastName().trim().toLowerCase();
    }

    public List<String> getPhoneAlert(int stationNumber) throws IOException {

        List<String> addresses = fireStationRespository.getAll().stream()
                .filter(fireStation -> {
                        return Integer.parseInt(fireStation.getStation()) == stationNumber;
                })
                .map(FireStation::getAddress)
                .toList();

        return personRepository.getAll().stream()
                .filter(person -> person.getAddress() != null && addresses.contains(person.getAddress()))
                .map(Person::getPhone)
                .map(String::trim)
                .distinct()
                .collect(Collectors.toList());
    }

    public FireDTO getFireByAddress(String address) throws IOException {

        // Trouver la station qui dessert cette adresse
        Optional<Integer> stationNumber = fireStationRespository.getAll().stream()
                .filter(fireStation -> address.equals(fireStation.getAddress()))
                .map(fireStation -> {
                        return Integer.parseInt(fireStation.getStation());
                })
                .findFirst();

        // Récupérer les résidents de l'adresse
        List<Person> residents = personRepository.getAll().stream()
                .filter(person -> address.equals(person.getAddress()))
                .toList();

        List<ResidentFireDTO> residentDTOs = new ArrayList<>();

        for (Person person : residents) {
            int age = -1;
            List<String> medications = Collections.emptyList();
            List<String> allergies = Collections.emptyList();

                MedicalRecord medicalRecord = medicalRecordRepository.getByName(person.getFirstName(), person.getLastName());


                    age = getAge(medicalRecord.getBirthdate());
                    medications = medicalRecord.getMedications();
                    allergies   = medicalRecord.getAllergies();

            ResidentFireDTO dto = new ResidentFireDTO(
                    person.getFirstName(),
                    person.getLastName(),
                    person.getPhone(),
                    age,
                    medications,
                    allergies
            );
            residentDTOs.add(dto);
        }

        return new FireDTO(stationNumber, residentDTOs);
    }

    public List<PersonInfoDTO> getPersonInfoByLastName(String lastName) throws IOException {

        // Recherche case-insensitive des personnes
        List<Person> persons = personRepository.getAll().stream()
                .filter(person ->  person.getLastName().equalsIgnoreCase(lastName))
                .toList();

        List<PersonInfoDTO> result = new ArrayList<>();

        for (Person person : persons) {
            int age = -1;
            List<String> medications = Collections.emptyList();
            List<String> allergies = Collections.emptyList();


            MedicalRecord medicalRecord = medicalRecordRepository.getByName(person.getFirstName(), person.getLastName());

                age = getAge(medicalRecord.getBirthdate());
                medications = medicalRecord.getMedications();
                allergies   = medicalRecord.getAllergies();

            PersonInfoDTO dto = new PersonInfoDTO(
                    person.getFirstName(),
                    person.getLastName(),
                    person.getAddress(),
                    age,
                    person.getEmail(),
                    medications,
                    allergies
            );

            result.add(dto);
        }

        return result;
    }

    public Map<String, List<FloodPersonDTO>> getFloodByStations(List<Integer> stations) throws IOException {

        Set<String> addresses = fireStationRespository.getAll().stream()
                .filter(fireStation -> stations.contains(Integer.parseInt(fireStation.getStation())))
                .map(FireStation::getAddress)
                .collect(Collectors.toSet());

        List<Person> persons = personRepository.getAll().stream()
                .filter(person -> addresses.contains(person.getAddress()))
                .toList();

        Map<String, List<FloodPersonDTO>> result = new HashMap<>();

        for (Person person : persons) {
            int age = -1;
            List<String> medications = java.util.Collections.emptyList();
            List<String> allergies = java.util.Collections.emptyList();

            MedicalRecord medicalRecord = medicalRecordRepository.getByName(person.getFirstName(), person.getLastName());

            if (medicalRecord != null) {
                if (medicalRecord.getBirthdate() != null) {
                    age = getAge(medicalRecord.getBirthdate());
                }
                if (medicalRecord.getMedications() != null) {
                    medications = medicalRecord.getMedications();
                }
                if (medicalRecord.getAllergies() != null) {
                    allergies = medicalRecord.getAllergies();
                }
            }

            FloodPersonDTO dto = new FloodPersonDTO(
                    person.getFirstName(),
                    person.getLastName(),
                    person.getPhone(),
                    age,
                    medications,
                    allergies
            );

            result.computeIfAbsent(person.getAddress(), s -> new ArrayList<>()).add(dto);
        }

        return result;
    }

    public List<String> getCommunityEmails(String city) throws IOException {
        String target = city.trim().toLowerCase(Locale.ROOT);

        return personRepository.getAll().stream()
                .filter(person -> person.getCity().trim().toLowerCase(Locale.ROOT).equals(target))
                .map(Person::getEmail)
                .filter(Objects::nonNull)
                .map(String::trim)
                .distinct()
                .toList();
    }
}