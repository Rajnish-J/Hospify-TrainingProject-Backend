package com.HospitalAppointmentScheduling.Service;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalAppointmentScheduling.BO.PatientBO;
import com.HospitalAppointmentScheduling.CustomExceptions.AppointmentBookingDateException;
import com.HospitalAppointmentScheduling.CustomExceptions.AppointmentException;
import com.HospitalAppointmentScheduling.CustomExceptions.DateException;
import com.HospitalAppointmentScheduling.CustomExceptions.DateOfBirthException;
import com.HospitalAppointmentScheduling.CustomExceptions.EmailException;
import com.HospitalAppointmentScheduling.CustomExceptions.IdException;
import com.HospitalAppointmentScheduling.CustomExceptions.PasswordException;
import com.HospitalAppointmentScheduling.CustomExceptions.PatientException;
import com.HospitalAppointmentScheduling.CustomExceptions.PhoneNumberException;
import com.HospitalAppointmentScheduling.DAO.PatientProjection;
import com.HospitalAppointmentScheduling.Entity.PatientVO;
import com.HospitalAppointmentScheduling.Response.ResponseHandle;

import jakarta.transaction.Transactional;

@Service
public class PatientService {
	@Autowired
	private PatientBO patientBO;

	@Autowired
	private ResponseHandle response;

	Logger log = Logger.getLogger(PatientService.class);

	// insert method
	@Transactional
	public ResponseHandle insertPatientDetails(PatientVO vo)
			throws PatientException, PhoneNumberException, EmailException, PasswordException, DateOfBirthException {
		log.info("Insert method triggered...");
		PatientVO flag = patientBO.insertPatientDetails(vo);
		if (flag != null) {
			response.setSucessMessage("patient Details added successfully");
			response.setPatient(flag);
		} else {
			response.setFailureMessage("Error in fetching...");
		}
		return response;
	}

	// find by Id method:
	@Transactional
	public ResponseHandle fetchById(long id) throws IdException {
		log.info("Fetch by ID method triggered...");
		PatientVO vo = patientBO.fetchByID(id);
		if (vo != null) {
			response.setPatient(vo);
			response.setId(vo.getPatientId());
			response.setSucessMessage("patient details fetched by Patient ID: ");
		} else {
			response.setFailureMessage("Error in fetching...");
		}
		return response;
	}

	// fetch all method:
	@Transactional
	public ResponseHandle fetchAll() {
		log.info("fetch all method triggered...");
		List<PatientVO> list = patientBO.fetchAll();
		if (list.size() > 0) {
			response.setListPatient(list);
			response.setSucessMessage("Fetching all the patiend details is successfully executed");
		} else {
			response.setFailureMessage("Error in fetching...");
		}

		return response;
	}

	// update method
	@Transactional
	public ResponseHandle updatePatientDetails(long id) throws IdException {
		log.info("Update method triggered...");
		PatientVO flag = patientBO.updatePatientDetails(id);
		if (flag != null) {
			response.setSucessMessage("updated the patient details successfully for the patient ID: ");
			response.setPatient(flag);
		} else {
			response.setFailureMessage("error in updating patient details");
		}

		return response;
	}

	// Associate method:
	@Transactional
	public ResponseHandle associate(PatientVO vo) throws PatientException, PhoneNumberException, EmailException,
			PasswordException, AppointmentException, AppointmentBookingDateException, DateOfBirthException {
		log.info("Asscoiate method triggered...");
		PatientVO inserted = patientBO.associate(vo);

		if (inserted != null) {
			response.setSucessMessage("Appointment added successfully");
			response.setPatient(inserted);
			response.setId(inserted.getPatientId());
		} else {
			response.setFailureMessage("Failed to add data");
		}

		return response;
	}

	// find by patient phone number:
	public ResponseHandle findbyphone(String ph) throws PhoneNumberException {
		log.info("Fetch patient details by phone number method triggered...");
		PatientVO vo = patientBO.fetchbyPhoneNumber(ph);
		if (vo != null) {
			response.setPatient(vo);
			response.setSucessMessage("patient details fetched by phone number");
		} else {
			response.setFailureMessage("Error in fetching...");
		}
		return response;

	}

	// fetch by day appointments:
	public ResponseHandle findapptDay(LocalDate td) throws AppointmentException {
		log.info("Find the appointments by the Date method triggered...");
		List<PatientVO> list = patientBO.fetchapptDay(td);
		if (list.size() > 0) {
			response.setListPatient(list);
			response.setSucessMessage("fetching the appoinment details within the day is successfully executed");
		} else {
			response.setFailureMessage("There is no appointments on the day...");
		}
		return response;
	}

	// fetch by more appointments
	public ResponseHandle findAppointmentsByNumber(long n) throws AppointmentException {
		log.info("Find the patients having more appointments compared to given number method triggered...");
		List<PatientVO> list = patientBO.fetchappointByNumber(n);
		if (list.size() > 0) {
			response.setListPatient(list);
			response.setSucessMessage("fetching the appoinment details with the patient ID is successfully executed");
		} else {
			response.setFailureMessage("There are no patients having more than the number you provided...");
		}
		return response;
	}

	// fetch first name and last name:
	public ResponseHandle findName(long n) throws IdException {
		log.info("Fetching patient name by ID method triggered...");
		PatientProjection p = patientBO.findname(n);
		if (p != null) {
			response.setPro(p);
			response.setSucessMessage("fetching patient first name and last name is successfully executed");
		} else {
			response.setFailureMessage("Error in fetching...");
		}
		return response;
	}

	// Appointment by between two days:
	public ResponseHandle betweenTwoDOBpat(LocalDate sd, LocalDate ld) throws DateException {
		log.info(
				"Fetching the patient details by two date who all are having Date Of Birth between the two dates method triggered...");
		List<PatientVO> list = patientBO.betweenTwoDOBpat(sd, ld);
		if (list.size() > 0) {
			response.setListPatient(list);
			response.setSucessMessage(
					"fetching patient details between the two dates with respect to DOB is successfully executed");
		} else {
			response.setFailureMessage("Error in fetching...");
		}
		return response;
	}

	// ascending order:
	public ResponseHandle acending() throws AppointmentException {
		log.info("Fetching the patient details in ascending order method triggered...");
		List<PatientVO> list = patientBO.ascending();
		if (list.size() > 0) {
			response.setListPatient(list);
			response.setSucessMessage("fetching the paitent details in ascending order is successfully executed");
		} else {
			response.setFailureMessage("Error in fetching...");
		}
		return response;
	}
}
