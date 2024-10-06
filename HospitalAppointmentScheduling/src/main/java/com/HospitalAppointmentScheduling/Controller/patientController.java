package com.HospitalAppointmentScheduling.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

	// insert:
	@PostMapping("/insert")
	public ResponseEntity<?> insertPatient(@RequestBody patientDTO dto) {

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
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PhoneNumberException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (EmailException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PasswordException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (DateOfBirthException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// fetch by id:
	@GetMapping("patientId/{id}")
	public ResponseEntity<?> findBypatientId(@PathVariable("id") long id) {
		try {
			res = pservice.fetchById(id);
			return ResponseEntity.ok("Patient Details Fetched by ID:" + res.getPatient());
		} catch (IdException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// fetch all:
	@GetMapping("/fetchallPatient")
	public List<patientDTO> fetchall() {
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
		try {
			res = pservice.updatePatientDetails(id);
			return ResponseEntity.ok(mapToDTO(res.getPatient()));
		} catch (IdException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// association method
	@PostMapping("/associatePatientsWithAppointments")
	public ResponseEntity<?> associate(@RequestBody patientDTO dto) {
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
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PhoneNumberException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (EmailException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PasswordException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (AppointmentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (AppointmentBookingDateException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (DateOfBirthException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// find by patient phone number:
	@GetMapping("/fetchByPhoneNumber/{ph}")
	public ResponseEntity<?> findbyphone(@PathVariable("ph") String ph) {
		try {
			res = pservice.findbyphone(ph);
			return ResponseEntity.ok("Patient Details Fetched by ID:" + res.getPatient());
		} catch (PhoneNumberException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// fetch by day appointments:
	@GetMapping("/appointmentDate/{td}")
	public ResponseEntity<?> findapptDay(@PathVariable("td") LocalDate td) {
		try {
			res = pservice.findapptDay(td);
		} catch (AppointmentException e) {
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
		try {
			res = pservice.findAppointmentsByNumber(n);
		} catch (AppointmentException e) {
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
	public ResponseEntity<String> findName(@PathVariable("id") long n) {
		try {
			res = pservice.findName(n);
			return ResponseEntity
					.ok("First name: " + res.getPro().getFirstName() + " Second name: " + res.getPro().getLastName());
		} catch (IdException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// Appointment by between two days:
	@GetMapping("/patientDetailsAmongTwoDate/{sd}/{ld}")
	public ResponseEntity<?> betweenTwoDOBpat(@PathVariable("sd") LocalDate sd, @PathVariable("ld") LocalDate ld) {
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
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

		return ResponseEntity.ok("Patient Details Fetched by ID:" + res.getListpatient());

	}

	// ascending order:
	@GetMapping("/AscendingOrder")
	public ResponseEntity<?> acending() {
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
