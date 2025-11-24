package com.mikael.safetynet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikael.safetynet.model.Person;
import com.mikael.safetynet.util.DataReaderUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class SafetyNetApplication {

	public static void main(String[] args) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		DataReaderUtil reader = new DataReaderUtil(objectMapper);

		reader.getAllPersons()
						.forEach(System.out::println);

		reader.getAllFireStations()
				.forEach(System.out::println);

		reader.getAllMedicalRecords()
				.forEach(System.out::println);

		SpringApplication.run(SafetyNetApplication.class, args);
	}
}
