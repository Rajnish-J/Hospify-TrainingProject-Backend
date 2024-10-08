package com.HospitalAppointmentScheduling.Controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalAppointmentScheduling.CustomExceptions.AppointmentException;
import com.HospitalAppointmentScheduling.CustomExceptions.DateOfBirthException;
import com.HospitalAppointmentScheduling.CustomExceptions.EmailException;
import com.HospitalAppointmentScheduling.CustomExceptions.IdException;
import com.HospitalAppointmentScheduling.CustomExceptions.PasswordException;
import com.HospitalAppointmentScheduling.CustomExceptions.PatientException;
import com.HospitalAppointmentScheduling.CustomExceptions.PhoneNumberException;
import com.HospitalAppointmentScheduling.DTO.AppointmentDTO;
import com.HospitalAppointmentScheduling.DTO.PatientDTO;
import com.HospitalAppointmentScheduling.Entity.AppointmentsVO;
import com.HospitalAppointmentScheduling.Entity.PatientVO;
import com.HospitalAppointmentScheduling.Response.ResponseHandle;
import com.HospitalAppointmentScheduling.Response.ResponseHandleAppointments;
import com.HospitalAppointmentScheduling.Service.AppointmentsService;
import com.HospitalAppointmentScheduling.Service.PatientService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentsService aser;

	@Autowired
	private PatientService pser;

	@Autowired
	private ResponseHandleAppointments apptRes;

	@Autowired
	private ResponseHandle patRes;

	Logger log = Logger.getLogger(AppointmentController.class);

	// insert:
	@PostMapping("/insert")
	public ResponseEntity<?> insertAppointment(@RequestBody AppointmentDTO dto, @RequestBody PatientDTO dto1) {
		log.info("Patient chooses create account option...");

		// converting DTO to entity
		AppointmentsVO vo = new AppointmentsVO();
		vo.setAppointmentDate(dto.getAppointmentDate());
		vo.setDoctorId(dto.getDoctorID());
		vo.setReason(dto.getReason());

		PatientVO vo1 = new PatientVO();
		vo1.setFirstName(dto1.getFirstName());
		vo1.setLastName(dto1.getLastName());
		vo1.setPatientEmail(dto1.getPatientEmail());
		vo1.setPatientPassword(dto1.getPatientPassword());
		vo1.setPatientPhone(dto1.getPatientPhone());
		vo1.setDob(dto1.getDob());

		try {
			patRes = pser.insertPatientDetails(vo1);
		} catch (PatientException e) {
			log.error("Patient Details details records not in the format", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PhoneNumberException e) {
			log.error("Phone number format is wrong", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (EmailException e) {
			log.error("email format is not valid", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PasswordException e) {
			log.error("password format is not valid", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (DateOfBirthException e) {
			log.error("Date of Birth format is not in the pattern", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

		vo.setPatient(patRes.getPatient());

		try {
			apptRes = aser.insertAppointments(vo);
			dto.setAppointmentID(vo.getAppointmentID());
			dto.setCreatedAt(vo.getCreatedAt());
			dto.setUpdatedAt(vo.getUpdatedAt());
			return ResponseEntity
					.ok("Appointments Details successfully saved: " + apptRes.getAppoVo().getAppointmentID());
		} catch (IdException e) {
			log.error("Appointment ID is not present in the DataBase", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (EmailException e) {
			log.error("email format is not valid", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PasswordException e) {
			log.error("password format is not valid", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PhoneNumberException e) {
			log.error("Date of Birth format is not in the pattern", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (AppointmentException e) {
			log.error("Appointment exception caught", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	public static AppointmentDTO mapToDTO(AppointmentsVO vo) {
		AppointmentDTO dto = new AppointmentDTO();
		dto.setAppointmentDate(vo.getAppointmentDate());
		dto.setAppointmentID(vo.getAppointmentID());
		dto.setDoctorID(vo.getDoctorId());
		dto.setPatient(vo.getPatient());
		dto.setReason(vo.getReason());
		dto.setCreatedAt(vo.getCreatedAt());
		dto.setUpdatedAt(vo.getUpdatedAt());

		return dto;
	}

}
