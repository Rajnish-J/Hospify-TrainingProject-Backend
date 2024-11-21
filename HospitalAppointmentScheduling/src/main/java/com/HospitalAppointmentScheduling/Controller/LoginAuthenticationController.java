package com.HospitalAppointmentScheduling.Controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalAppointmentScheduling.Response.ResponseHandle;
import com.HospitalAppointmentScheduling.Service.PatientService;

@RestController
@RequestMapping("/loginPage")
public class LoginAuthenticationController {

	@Autowired
	private PatientService pservice;

	Logger log = Logger.getLogger(LoginAuthenticationController.class);

	@PostMapping("/patientLogin")
	public ResponseEntity<?> loginAuthentication(@RequestBody Map<String, String> loginData) {
		log.info("Patient Login method triggered in the controller layer");
		String patientEmail = loginData.get("patientEmail");
		String patientPassword = loginData.get("patientPassword");
		ResponseHandle res = pservice.patientAuthentication(patientEmail, patientPassword);
		if (res.getSucessMessage() != null && res.getPatient() != null) {
			log.info("patient account available in the database");
			return ResponseEntity.ok(res.getPatient());
		} else {
			log.error("patient account does not available in the database");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(res.getFailureMessage() != null ? res.getFailureMessage() : "Invalid credentials");
		}
	}
}
