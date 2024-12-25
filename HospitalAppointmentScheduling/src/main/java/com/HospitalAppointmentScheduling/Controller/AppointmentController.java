package com.HospitalAppointmentScheduling.Controller;

//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.HospitalAppointmentScheduling.CustomExceptions.PatientException;
import com.HospitalAppointmentScheduling.CustomExceptions.PhoneNumberException;
import com.HospitalAppointmentScheduling.CustomExceptions.ReasonException;
import com.HospitalAppointmentScheduling.CustomExceptions.genderException;
import com.HospitalAppointmentScheduling.DTO.AppointmentDTO;
import com.HospitalAppointmentScheduling.DTO.AppointmentPatientDoctorAppointmentStatusDTO;
import com.HospitalAppointmentScheduling.Entity.AppointmentStatusVO;
import com.HospitalAppointmentScheduling.Entity.AppointmentsVO;
import com.HospitalAppointmentScheduling.Entity.DoctorVO;
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

//	Logger log = Logger.getLogger(AppointmentController.class);

	// insert appointments with patient ID
	@PostMapping("/insertWithPatientID")
	public ResponseEntity<?> insertAppointmentsWithPatientID(
			@RequestBody AppointmentPatientDoctorAppointmentStatusDTO dto) {
//		log.info("Appointment booking with patient ID method triggered in controller layer...");

		// converting DTO to entity
		DoctorVO dVO = new DoctorVO();
		dVO.setDoctorId(dto.getDoctor().getDoctorId());

		AppointmentStatusVO asVO = new AppointmentStatusVO();
		asVO.setStatusName("Pending");

		AppointmentsVO vo = new AppointmentsVO();
		vo.setAppointmentDate(dto.getAppointment().getAppointmentDate());
		vo.setDoctor(dVO);
		vo.setStatus(asVO);
		vo.setReason(dto.getAppointment().getReason());

		PatientVO vo1 = new PatientVO();
		vo1.setPatientId(dto.getPatient().getPatientId());
		vo.setPatient(vo1);

//		log.info("Assigned Patient ID to Appointment VO");

//		log.info("Attempting to insert appointment details with existing patient ID...");
		try {
			apptRes = aser.insertAppointmentsWithPatientID(vo);
		} catch (IdException e) {
//			log.error("Appointment ID is not present in the DataBase", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (EmailException e) {
//			log.error("email Exception caught valid", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PasswordException e) {
//			log.error("password format is not valid", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PhoneNumberException e) {
//			log.error("Phone number Exception caught", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (AppointmentException e) {
//			log.error("Appointment exception caught", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PatientException e) {
//			log.error("Patient Exception caught", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (DateOfBirthException e) {
//			log.error("Date of Birth exception caught", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (AppointmentBookingDateException e) {
//			log.error("Appointment Booking Date Exception caught", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (ReasonException e) {
//			log.error("Invalid Reason Exception caught", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		dto.getAppointment().setAppointmentID(vo.getAppointmentID());
		dto.getAppointment().setCreatedAt(vo.getCreatedAt());
		dto.getAppointment().setUpdatedAt(vo.getUpdatedAt());
		return ResponseEntity.ok("Appointments Details successfully saved: " + apptRes.getAppoVo().getAppointmentID());

	}

	// update method:
	@PutMapping("/updateAppointments/{id}")
	public ResponseEntity<?> updateAppointmentDetails(@RequestBody AppointmentDTO dto, @PathVariable long id) {
//		log.info("Appointment details update method triggerred");
		AppointmentsVO vo = new AppointmentsVO();

		vo.setAppointmentDate(dto.getAppointmentDate());
		vo.setReason(dto.getReason());

		try {
			apptRes = aser.update(vo, id);
			String pass = "Appointment ID: \" + apptRes.getAppoVo().getAppointmentID() + \" updated successfully.";
//			log.info(pass);
			return ResponseEntity.ok("Appointment ID: " + apptRes.getAppoVo().getAppointmentID() + " updated");
		} catch (IdException e) {
//			log.error("ID not found in the DataBase", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// delete method: to delete a appointment by ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAppointment(@PathVariable("id") Long id) {

		try {
			apptRes = aser.deleteAppointment(id);
			return ResponseEntity.ok(apptRes.getSucessMessage());
		} catch (IdException e) {
//			log.error("Patient does not exists in the database", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/fetchAppointmentsForPatientID/{id}")
	public ResponseEntity<?> findAllApptByPatientId(@PathVariable long id) {
//		log.info("Find All Appointments By Patient ID method triggered in the controller layer");
		try {
			apptRes = aser.findAllApptByPatientId(id);
			List<AppointmentsVO> list = apptRes.getList();
			if (list == null || list.isEmpty()) {
				return ResponseEntity.ok(new ArrayList<>());
			}
			List<AppointmentDTO> listd = new ArrayList<>();
			for (AppointmentsVO vo : list) {
				AppointmentDTO getDto = mapToDTO(vo);
				listd.add(getDto);
			}
			return ResponseEntity.ok(listd);
		} catch (IdException e) {
//			log.error("Patient does not exist in the database", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/countOfAppointmentsByDate/{date}")
	public ResponseEntity<?> countOfAppointmentsByDate(@PathVariable LocalDate date) {
//		log.info("Count of Appointments By particular method triggered in the controller layer");
		apptRes = aser.countOfAppointmentsByDate(date);
		return ResponseEntity.ok(apptRes.getApptsCount());
	}

	// Appointment by between two days:
	@GetMapping("/AppointmentDetailsAmongTwoDate/{sd}/{ld}/{id}")
	public ResponseEntity<?> betweenTwoDOBpat(@PathVariable("sd") LocalDate sd, @PathVariable("ld") LocalDate ld,
			@PathVariable("id") long id) {
//		log.info("Appointment details with the two dates...");
		try {
			String pass = "Fetching between start date: " + sd + " and end date: " + ld;
//			log.info(pass);
			apptRes = aser.findAppointmentsByPatientIdAndDateRange(sd, ld, id);
			List<AppointmentsVO> list = apptRes.getList();
			List<AppointmentDTO> listd = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				AppointmentsVO vo = list.get(i);
				AppointmentDTO getDto = mapToDTO(vo);
				listd.add(getDto);
//				log.info("Fetched appointment details between two dates successfully.");
			}
			return ResponseEntity.ok(listd);
		} catch (DateException e) {
//			log.error("Date Exception", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (IdException e) {
//			log.error("Id Exception", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (AppointmentException e) {
//			log.error("Appointment Exception", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// fetches all the appointments with respect to logged in patient id and given
	// date:
	@GetMapping("/AppointmentsOnDate/{ld}/{id}")
	public ResponseEntity<?> findAppointmentsByPatientIdAndDate(@PathVariable("ld") LocalDate ld,
			@PathVariable("id") long id) {
//		log.info("Appointments for the logged in patient on the particular date method triggered");
		try {
			apptRes = aser.findAppointmentsByPatientIdAndDate(ld, id);
			List<AppointmentsVO> list = apptRes.getList();
			List<AppointmentDTO> listd = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				AppointmentsVO vo = list.get(i);
				AppointmentDTO getDto = mapToDTO(vo);
				listd.add(getDto);
//				log.info("Fetched appointment details between two dates successfully.");
			}
			return ResponseEntity.ok(listd);
		} catch (IdException e) {
//			log.error("Id Exception", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (AppointmentException e) {
//			log.error("Appointment Exception", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// =======================================================================================//

	// unused API's
	// insert:
	@PostMapping("/insert")
	public ResponseEntity<?> insertAppointment(@RequestBody AppointmentPatientDoctorAppointmentStatusDTO dto) {
//		log.info("Appointment booking with patient details method triggered in controller layer...");

		// converting DTO to entity
		DoctorVO dVO = new DoctorVO();
		dVO.setDoctorId(dto.getDoctor().getDoctorId());

		AppointmentStatusVO asVO = new AppointmentStatusVO();
		asVO.setStatusName("Pending");

		AppointmentsVO vo = new AppointmentsVO();
		vo.setAppointmentDate(dto.getAppointment().getAppointmentDate());
		vo.setDoctor(dVO);
		vo.setStatus(asVO);
		vo.setReason(dto.getAppointment().getReason());

		PatientVO vo1 = new PatientVO();
		vo1.setFirstName(dto.getPatient().getFirstName());
		vo1.setLastName(dto.getPatient().getLastName());
		vo1.setPatientEmail(dto.getPatient().getPatientEmail());
		vo1.setPatientPassword(dto.getPatient().getPatientPassword());
		vo1.setPatientPhone(dto.getPatient().getPatientPhone());
		vo1.setDob(dto.getPatient().getDob());
		vo1.setGender(dto.getPatient().getGender());

		try {
			patRes = pser.insertPatientDetails(vo1);
		} catch (PatientException e) {
//			log.error("Patient Details details records not in the format", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PhoneNumberException e) {
//			log.error("Phone number format is wrong", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (EmailException e) {
//			log.error("email format is not valid", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PasswordException e) {
//			log.error("password format is not valid", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (DateOfBirthException e) {
//			log.error("Date of Birth format is not in the pattern", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (genderException e) {
//			log.error("Gender you entered is not valid", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

		vo.setPatient(patRes.getPatient());
//		log.info("Assigned Patient to Appointment VO.");

//		log.info("Attempting to insert appointment details...");
		try {
			apptRes = aser.insertAppointments(vo);
		} catch (IdException e) {
//			log.error("Appointment ID is not present in the DataBase", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (EmailException e) {
//			log.error("email Exception caught valid", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PasswordException e) {
//			log.error("password format is not valid", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PhoneNumberException e) {
//			log.error("Phone number Exception caught", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (AppointmentException e) {
//			log.error("Appointment exception caught", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PatientException e) {
//			log.error("Patient Exception caught", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (DateOfBirthException e) {
//			log.error("Date of Birth exception caught", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		dto.getAppointment().setAppointmentID(vo.getAppointmentID());
		dto.getAppointment().setCreatedAt(vo.getCreatedAt());
		dto.getAppointment().setUpdatedAt(vo.getUpdatedAt());
		return ResponseEntity.ok("Appointments Details successfully saved: " + apptRes.getAppoVo().getAppointmentID());
	}

	// fetch by ID:
	@GetMapping("appointmentID/{id}")
	public ResponseEntity<?> findByappointmentId(@PathVariable("id") long id) {
//		log.info("appointment details by their ID...");
		try {
			String pass = "Fetching appointment details by ID: " + id;
//			log.info(pass);
			apptRes = aser.fetchByID(id);
			return ResponseEntity.ok(mapToDTO(apptRes.getAppoVo()));
		} catch (IdException e) {
//			log.error("ID not found in the DateBase", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// fetch all:
	@GetMapping("/fetchallAppointments")
	public ResponseEntity<?> fetchall() {
//		log.info("appointment chooses fetch all the details option...");
		apptRes = aser.fetchAll();
		if (apptRes.getList().size() > 0) {
			List<AppointmentsVO> list = apptRes.getList();
			List<AppointmentDTO> listd = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				AppointmentsVO vo = list.get(i);
				AppointmentDTO getDto = mapToDTO(vo);
				listd.add(getDto);
			}
//			log.info("All appointment details fetched successfully.");
			return ResponseEntity.ok(listd);
		} else {
			String pass = "Error in fetching";
//			log.error(pass);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pass);
		}

	}

	// ascending order:
	@GetMapping("/AppointmentAscendingOrderByDate")
	public ResponseEntity<?> acendingDate() {
//		log.info("Appointments fetching all the details in ascending order...");
		try {
			apptRes = aser.acendingDate();
			List<AppointmentsVO> list = apptRes.getList();
			List<AppointmentDTO> listd = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				AppointmentsVO vo = list.get(i);
				AppointmentDTO getDto = mapToDTO(vo);
				listd.add(getDto);
			}
			return ResponseEntity.ok(listd);
		} catch (AppointmentException e) {
//			log.error("Id Exception", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// Vo to DTO method
	public static AppointmentDTO mapToDTO(AppointmentsVO vo) {
		AppointmentDTO dto = new AppointmentDTO();
		dto.setAppointmentDate(vo.getAppointmentDate());
		dto.setAppointmentID(vo.getAppointmentID());
		dto.setDoctorID(vo.getDoctor().getDoctorId());
		dto.setReason(vo.getReason());
		dto.setPatientID(vo.getPatient().getPatientId());
		dto.setCreatedAt(vo.getCreatedAt());
		dto.setUpdatedAt(vo.getUpdatedAt());

		DoctorVO docVO = new DoctorVO();
		docVO.setDoctorId(vo.getDoctor().getDoctorId());
		docVO.setFirstName(vo.getDoctor().getFirstName());
		docVO.setLastName(vo.getDoctor().getLastName());

		dto.setDoctor(docVO);

		return dto;
	}

}
