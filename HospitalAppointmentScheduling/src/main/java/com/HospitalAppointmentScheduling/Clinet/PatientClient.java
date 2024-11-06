package com.HospitalAppointmentScheduling.Clinet;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.HospitalAppointmentScheduling.DTO.PatientDTO;

@Component
public class PatientClient {

	private RestTemplate restTemplate;
	private String baseUrl = "http://localhost:8080/patient";

	// Constructor dependency
	public PatientClient() {
		this.restTemplate = new RestTemplate();
	}

	// insert patient details through client:
	public String insertPatientThroughClient(PatientDTO patient) {
		ResponseEntity<String> ret = restTemplate.postForEntity(baseUrl + "/insert", patient, String.class);
		return ret.getBody();
	}

	// fetch all the patient details through client
	public List<PatientDTO> fetchAllPatientsThroughClient() {
		ResponseEntity<List<PatientDTO>> ret = restTemplate.exchange(baseUrl + "/fetchallPatient",
				org.springframework.http.HttpMethod.GET, null, new ParameterizedTypeReference<List<PatientDTO>>() {
				});

		return ret.getBody();
	}
}
