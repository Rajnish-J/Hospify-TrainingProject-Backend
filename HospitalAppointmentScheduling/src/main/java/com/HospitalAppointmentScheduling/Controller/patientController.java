package com.HospitalAppointmentScheduling.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalAppointmentScheduling.CustomExceptions.AppointmentBookingDateException;
import com.HospitalAppointmentScheduling.CustomExceptions.AppointmentException;
import com.HospitalAppointmentScheduling.CustomExceptions.DateException;
import com.HospitalAppointmentScheduling.CustomExceptions.DateOfBirthException;
import com.HospitalAppointmentScheduling.CustomExceptions.EmailException;
import com.HospitalAppointmentScheduling.CustomExceptions.IdException;
import com.HospitalAppointmentScheduling.CustomExceptions.PasswordException;
import com.HospitalAppointmentScheduling.CustomExceptions.PhoneNumberException;
import com.HospitalAppointmentScheduling.CustomExceptions.patientException;
import com.HospitalAppointmentScheduling.DTO.patientDTO;
import com.HospitalAppointmentScheduling.Entity.appointmentsVO;
import com.HospitalAppointmentScheduling.Entity.patientVO;
import com.HospitalAppointmentScheduling.Response.ResponseHandle;
import com.HospitalAppointmentScheduling.Service.patientService;

@RestController
@RequestMapping("/patient")
public class patientController {

	@Autowired
	private patientService pservice;

	@Autowired
	private ResponseHandle res;

	Logger log = Logger.getLogger(patientController.class);

	// insert:
	@PostMapping("/insert")
	public ResponseEntity<?> insertPatient(@RequestBody patientDTO dto) {
		log.info("Patient chooses create account option...");

		// converting DTO to entity
		patientVO vo = new patientVO();
		vo.setFirstName(dto.getFirstName());
		vo.setLastName(dto.getLastName());
		vo.setPatientEmail(dto.getPatientEmail());
		vo.setPatientPassword(dto.getPatientPassword());
		vo.setPatientPhone(dto.getPatientPhone());
		vo.setDob(dto.getDob());

		try {
			res = pservice.insertPatientDetails(vo);
			dto.setUpdatedAt(res.getPatient().getUpdatedAt());
			dto.setCreatedAt(res.getPatient().getCreatedAt());
			dto.setPatientId(res.getPatient().getPatientId());
			return ResponseEntity.ok("Patient Details successfully saved: " + res.getPatient().getPatientId());
		} catch (patientException e) {
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

	}

	// fetch by id:
	@GetMapping("patientId/{id}")
	public ResponseEntity<?> findBypatientId(@PathVariable("id") long id) {
		log.info("patient chooses fetch Details by their ID...");
		try {
			res = pservice.fetchById(id);
			return ResponseEntity.ok("Patient Details Fetched by ID:" + res.getPatient());
		} catch (IdException e) {
			log.error("ID not found in the DateBase", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// fetch all:
	@GetMapping("/fetchallPatient")
	public List<patientDTO> fetchall() {
		log.info("patient chooses fetch all the details option...");
		res = pservice.fetchAll();
		List<patientVO> list = res.getListpatient();
		List<patientDTO> listd = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			patientVO vo = list.get(i);
			patientDTO getDto = mapToDTO(vo);
			listd.add(getDto);
		}

		return listd;

	}

	// update method
	@PutMapping("/update/{id}")
	public ResponseEntity<?> fetchpatientDetails(@PathVariable long id) {
		log.info("patient chooses Update their information by their ID...");
		try {
			res = pservice.updatePatientDetails(id);
			return ResponseEntity.ok(mapToDTO(res.getPatient()));
		} catch (IdException e) {
			log.error("ID not found in the DataBase", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// association method
	@PostMapping("/associatePatientsWithAppointments")
	public ResponseEntity<?> associate(@RequestBody patientDTO dto) {
		log.info("patient chooses to create account with booking appointments...");
		patientVO vo = new patientVO();
		vo.setFirstName(dto.getFirstName());
		vo.setLastName(dto.getLastName());
		vo.setPatientEmail(dto.getPatientEmail());
		vo.setPatientPassword(dto.getPatientPassword());
		vo.setPatientPhone(dto.getPatientPhone());
		vo.setDob(dto.getDob());

		List<appointmentsVO> list = new ArrayList<>();
		for (appointmentsVO obj : dto.getAppointments()) {
			appointmentsVO avo = new appointmentsVO();
			avo.setAppointmentDate(obj.getAppointmentDate());
			avo.setReason(obj.getReason());
			avo.setDoctorId(obj.getDoctorId());
			System.out.println(avo);
			avo.setPatient(vo);
			list.add(avo);
		}
		vo.setAppointments(list);
		try {
			res = pservice.associate(vo);
			return ResponseEntity.ok("Patient Details and Appointments added successfully" + res.getId());
		} catch (patientException e) {
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
		} catch (AppointmentException e) {
			log.error("book atleast one appointments", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (AppointmentBookingDateException e) {
			log.error("Appointment booking date could not be in the Past", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (DateOfBirthException e) {
			log.error("date of birth could not be in the Future", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// find by patient phone number:
	@GetMapping("/fetchByPhoneNumber/{ph}")
	public ResponseEntity<?> findbyphone(@PathVariable("ph") String ph) {
		log.info("patient chooses fetching their details by their phone number...");
		try {
			res = pservice.findbyphone(ph);
			return ResponseEntity.ok("Patient Details Fetched by ID:" + res.getPatient());
		} catch (PhoneNumberException e) {
			log.error("phone number exception caught", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// fetch by day appointments:
	@GetMapping("/appointmentDate/{td}")
	public ResponseEntity<?> findapptDay(@PathVariable("td") LocalDate td) {
		log.info("patient chooses to fetch the appointment details by the day...");
		try {
			res = pservice.findapptDay(td);
		} catch (AppointmentException e) {
			log.error("Appointment exception caught", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		List<patientVO> list = res.getListpatient();
		List<patientDTO> listd = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			patientVO vo = list.get(i);
			patientDTO getDto = mapToDTO(vo);
			listd.add(getDto);
		}

		return ResponseEntity.ok(listd);
	}

	// fetch by more appointments
	@GetMapping("/findAppointmentsById/{id}")
	public ResponseEntity<?> findAppointmentsByNumber(@PathVariable("id") long n) {
		System.out.println("Enter the number that fetches patient having more appoinments than the number: ");
		try {
			res = pservice.findAppointmentsByNumber(n);
		} catch (AppointmentException e) {
			log.error("Appointment Exception", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		List<patientVO> list = res.getListpatient();
		List<patientDTO> listd = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			patientVO vo = list.get(i);
			patientDTO getDto = mapToDTO(vo);
			listd.add(getDto);
		}

		return ResponseEntity.ok("Patient Details Fetched by ID:" + res.getPatient());
	}

	// fetch first name and last name:
	@GetMapping("/findFirstandLastNamebyPatientId/{id}")
	public ResponseEntity<?> findName(@PathVariable("id") long n) {
		log.info("patient chooses Find their First and Last names in the records...");
		try {
			res = pservice.findName(n);
			return ResponseEntity
					.ok("First name: " + res.getPro().getFirstName() + " Second name: " + res.getPro().getLastName());
		} catch (IdException e) {
			log.error("Id Exception", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// Appointment by between two days:
	@GetMapping("/patientDetailsAmongTwoDate/{sd}/{ld}")
	public ResponseEntity<?> betweenTwoDOBpat(@PathVariable("sd") LocalDate sd, @PathVariable("ld") LocalDate ld) {
		log.info("patient chooses ferching the patient details with the two dates...");
		try {
			res = pservice.betweenTwoDOBpat(sd, ld);
			List<patientVO> list = res.getListpatient();
			List<patientDTO> listd = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				patientVO vo = list.get(i);
				patientDTO getDto = mapToDTO(vo);
				listd.add(getDto);
			}
		} catch (DateException e) {
			log.error("Id Exception", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

		return ResponseEntity.ok("Patient Details Fetched by ID:" + res.getListpatient());

	}

	// ascending order:
	@GetMapping("/AscendingOrder")
	public ResponseEntity<?> acending() {
		log.info("patient chooses fetching all the patient details in ascending order...");
		try {
			res = pservice.acending();
			List<patientVO> list = res.getListpatient();
			List<patientDTO> listd = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				patientVO vo = list.get(i);
				patientDTO getDto = mapToDTO(vo);
				listd.add(getDto);
			}
		} catch (AppointmentException e) {
			log.error("Id Exception", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

		return ResponseEntity.ok("Patient Details Fetched by ID:" + res.getListpatient());

	}

	// own method => converts entity to DTO:
	public static patientDTO mapToDTO(patientVO vo) {

		patientDTO dto = new patientDTO();
		dto.setFirstName(vo.getFirstName());
		dto.setLastName(vo.getLastName());
		dto.setPatientEmail(vo.getPatientEmail());
		dto.setPatientPassword(vo.getPatientPassword());
		dto.setPatientPhone(vo.getPatientPhone());
		dto.setDob(vo.getDob());
		dto.setUpdatedAt(vo.getUpdatedAt());
		dto.setCreatedAt(vo.getCreatedAt());
		dto.setPatientId(vo.getPatientId());

		return dto;

	}
}
