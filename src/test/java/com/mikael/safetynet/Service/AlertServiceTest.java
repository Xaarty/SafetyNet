package com.mikael.safetynet.Service;

import com.mikael.safetynet.DTO.*;
import com.mikael.safetynet.model.FireStation;
import com.mikael.safetynet.model.MedicalRecord;
import com.mikael.safetynet.model.Person;
import com.mikael.safetynet.repository.FireStationRespository;
import com.mikael.safetynet.repository.MedicalRecordRepository;
import com.mikael.safetynet.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlertServiceTest {

    @Test
    public void getAge() {
        AlertService service = new AlertService(null, null, null);
        int age = service.getAge("01/01/1997");
        assertTrue(age > 27);
    }

    @Test
    public void getCommunityEmails() throws IOException {
        PersonRepository repository = mock(PersonRepository.class);
        Person person = new Person();
        person.setEmail("jaboyd@email.com"); person.setCity("Culver");
        when(repository.getAll()).thenReturn(List.of(person));

        AlertService service = new AlertService(repository, null, null);
        List<String> result = service.getCommunityEmails("Culver");

        assertEquals(List.of("jaboyd@email.com"), result);
    }

    @Test
    public void getPhoneAlert() throws IOException {
        PersonRepository personRepository = mock(PersonRepository.class);
        FireStationRespository fireStationRespository = mock(FireStationRespository.class);


        Person person = new Person();
        person.setAddress("1509 Culver St"); person.setPhone("841-874-6512");
        FireStation fireStation = new FireStation();
        fireStation.setAddress("1509 Culver St"); fireStation.setStation("3");


        when(fireStationRespository.getAll()).thenReturn(List.of(fireStation));
        when(personRepository.getAll()).thenReturn(List.of(person));


        AlertService service = new AlertService(personRepository, null, fireStationRespository);
        List<String> phones = service.getPhoneAlert(3);
        assertEquals(List.of("841-874-6512"), phones);
    }

    @Test
    public void getPersonsByStation() throws IOException {
        PersonRepository personRepository = mock(PersonRepository.class);
        MedicalRecordRepository medicalRecordRepository = mock(MedicalRecordRepository.class);
        FireStationRespository fireStationRespository = mock(FireStationRespository.class);


        Person person = new Person();
        person.setAddress("1509 Culver St"); person.setFirstName("John"); person.setLastName("Boyd");
        FireStation fireStation = new FireStation(); fireStation.setAddress("1509 Culver St"); fireStation.setStation("3");
        MedicalRecord medicalRecord = new MedicalRecord(); medicalRecord.setBirthdate("03/06/1984");


        when(fireStationRespository.getAll()).thenReturn(List.of(fireStation));
        when(personRepository.getAll()).thenReturn(List.of(person));
        when(medicalRecordRepository.getByName("John", "Boyd")).thenReturn(medicalRecord);


        AlertService service = new AlertService(personRepository, medicalRecordRepository, fireStationRespository);
        PersonsByStationDTO dto = service.getPersonsByStation(3);


        assertEquals(1, dto.getChildCount() + dto.getAdultCount());
    }

    @Test
    public void getChildAlert() throws IOException {
        PersonRepository personRepository = mock(PersonRepository.class);
        MedicalRecordRepository medicalRecordRepository = mock(MedicalRecordRepository.class);


        Person person = new Person(); person.setAddress("1509 Culver St"); person.setFirstName("Tenley"); person.setLastName("Boyd");
        MedicalRecord medicalRecord = new MedicalRecord(); medicalRecord.setBirthdate("02/18/2012");


        when(personRepository.getAll()).thenReturn(List.of(person));
        when(medicalRecordRepository.getByName("Tenley", "Boyd")).thenReturn(medicalRecord);


        AlertService service = new AlertService(personRepository, medicalRecordRepository, null);
        List<ChildAlertDTO> result = service.getChildAlert("1509 Culver St");


        assertEquals(1, result.size());
        assertEquals("Tenley", result.getFirst().getFirstName());
    }

    @Test
    public void getPersonInfoByLastName() throws IOException {
        PersonRepository personRepository = mock(PersonRepository.class);
        MedicalRecordRepository medicalRecordRepository = mock(MedicalRecordRepository.class);


        Person person = new Person();
        person.setFirstName("John"); person.setLastName("Boyd");
        person.setAddress("1509 Culver St"); person.setEmail("jaboyd@email.com");


        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBirthdate("03/06/1984");


        when(personRepository.getAll()).thenReturn(List.of(person));
        when(medicalRecordRepository.getByName("John", "Boyd")).thenReturn(medicalRecord);


        AlertService service = new AlertService(personRepository, medicalRecordRepository, null);
        List<PersonInfoDTO> infos = service.getPersonInfoByLastName("Boyd");
        assertEquals(1, infos.size());
    }

    @Test
    void getFireByAddress() throws IOException {
        PersonRepository personRepository = mock(PersonRepository.class);
        MedicalRecordRepository medicalRepository = mock(MedicalRecordRepository.class);
        FireStationRespository fireStationRepository = mock(FireStationRespository.class);

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setPhone("841-874-6512");
        person.setAddress("1509 Culver St");

        MedicalRecord medical = new MedicalRecord();
        medical.setBirthdate("03/06/1984");
        medical.setMedications(List.of("medicament"));
        medical.setAllergies(List.of("allergy"));

        FireStation station = new FireStation();
        station.setAddress("1509 Culver St");
        station.setStation("3");

        when(fireStationRepository.getAll()).thenReturn(List.of(station));
        when(personRepository.getAll()).thenReturn(List.of(person));
        when(medicalRepository.getByName("John", "Boyd")).thenReturn(medical);

        AlertService service = new AlertService(personRepository, medicalRepository, fireStationRepository);
        FireDTO result = service.getFireByAddress("1509 Culver St");

        assertTrue(result.getStationNumber().isPresent());
        assertEquals(1, result.getResidents().size());
        assertEquals("John", result.getResidents().getFirst().getFirstName());
    }

    @Test
    void getFloodByStations() throws IOException {
        PersonRepository personRepository = mock(PersonRepository.class);
        MedicalRecordRepository medicalRepository = mock(MedicalRecordRepository.class);
        FireStationRespository fireStationRepository = mock(FireStationRespository.class);

        FireStation station = new FireStation();
        station.setStation("3");
        station.setAddress("1509 Culver St");

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setPhone("841-874-6512");
        person.setAddress("1509 Culver St");

        MedicalRecord medical = new MedicalRecord();
        medical.setBirthdate("01/01/1990");
        medical.setMedications(List.of("medicament"));
        medical.setAllergies(List.of("allergy"));

        when(fireStationRepository.getAll()).thenReturn(List.of(station));
        when(personRepository.getAll()).thenReturn(List.of(person));
        when(medicalRepository.getByName("John", "Boyd")).thenReturn(medical);

        AlertService service = new AlertService(personRepository, medicalRepository, fireStationRepository);
        Map<String, List<FloodPersonDTO>> result = service.getFloodByStations(List.of(3));

        assertTrue(((java.util.Map<?, ?>) result).containsKey("1509 Culver St"));
        assertEquals(1, result.get("1509 Culver St").size());
        assertEquals("John", result.get("1509 Culver St").getFirst().getFirstName());
    }
}
