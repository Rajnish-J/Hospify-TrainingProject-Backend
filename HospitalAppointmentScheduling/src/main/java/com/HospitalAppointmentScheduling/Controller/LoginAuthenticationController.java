package com.HospitalAppointmentScheduling.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalAppointmentScheduling.DTO.AppointmentDTO;
import com.HospitalAppointmentScheduling.DTO.PatientDTO;
import com.HospitalAppointmentScheduling.Entity.AppointmentsVO;
import com.HospitalAppointmentScheduling.Entity.PatientVO;
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
			return ResponseEntity.ok(mapToDTO(res.getPatient()));
		} else {
			log.error("patient account does not available in the database");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(res.getFailureMessage() != null ? res.getFailureMessage() : "Invalid credentials");
		}
	}

	// own method: converts entity to DTO
	public static PatientDTO mapToDTO(PatientVO patientVO) {
		// Map PatientVO to PatientDTO
		PatientDTO dto = new PatientDTO();
		dto.setPatientId(patientVO.getPatientId());
		dto.setFirstName(patientVO.getFirstName());
		dto.setLastName(patientVO.getLastName());
		dto.setDob(patientVO.getDob());
		dto.setPatientPhone(patientVO.getPatientPhone());
		dto.setPatientEmail(patientVO.getPatientEmail());
		dto.setPatientPassword(patientVO.getPatientPassword());
		dto.setGender(patientVO.getGender());
		dto.setCreatedAt(patientVO.getCreatedAt());
		dto.setUpdatedAt(patientVO.getUpdatedAt());

		// Map Appointments
		List<AppointmentDTO> appointmentDTOs = new ArrayList<>();
		if (patientVO.getAppointments() != null) {
			for (AppointmentsVO appointment : patientVO.getAppointments()) {
				AppointmentDTO appointmentDTO = new AppointmentDTO();
				appointmentDTO.setAppointmentID(appointment.getAppointmentID());
				appointmentDTO.setAppointmentDate(appointment.getAppointmentDate());
				appointmentDTO.setReason(appointment.getReason());
				appointmentDTO.setCreatedAt(appointment.getCreatedAt());
				appointmentDTO.setUpdatedAt(appointment.getUpdatedAt());

				appointmentDTOs.add(appointmentDTO);
			}
		}
		dto.setAppointments(appointmentDTOs);

		return dto;
	}
}
