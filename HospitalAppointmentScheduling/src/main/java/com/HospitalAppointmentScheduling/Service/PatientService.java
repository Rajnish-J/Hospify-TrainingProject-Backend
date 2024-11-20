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
import com.HospitalAppointmentScheduling.CustomExceptions.ReasonException;
import com.HospitalAppointmentScheduling.CustomExceptions.genderException;
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

	// patient authentication method:
	public ResponseHandle patientAuthentication(String email, String password) {
		PatientVO vo = patientBO.patientAuthentication(email, password);
		if (vo != null) {
			response.setSucessMessage("Patient ID available in the db");
			response.setPatient(vo);
		} else {
			response.setFailureMessage("patient don't have any account in the database");
		}
		return response;
	}

	// insert method
	@Transactional
	public ResponseHandle insertPatientDetails(PatientVO vo) throws PatientException, PhoneNumberException,
			EmailException, PasswordException, DateOfBirthException, genderException {
		log.info("Insert method triggered...");
		PatientVO flag = patientBO.insertPatientDetails(vo);
		log.info("insertPatientDetails called in BO layer");
		if (flag != null) {
			String pass = "Patient details successfully inserted for Patient ID: " + flag.getPatientId();
			log.info(pass);
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
		log.info("fetchByID called in BO layer");
		if (vo != null) {
			String pass = "Successfully fetched patient details for Patient ID: " + vo.getPatientId();
			log.info(pass);
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
		log.info("fetchAll called in BO layer");
		if (list.size() > 0) {
			String pass = "Successfully fetched all patient details. Total records: " + list.size();
			log.info(pass);
			response.setListPatient(list);
			response.setSucessMessage("Fetching all the patiend details is successfully executed");
		} else {
			response.setFailureMessage("Error in fetching...");
		}

		return response;
	}

	// update method
	@Transactional
	public ResponseHandle updatePatientDetails(long id) throws IdException, PatientException, PhoneNumberException,
			EmailException, PasswordException, genderException {
		log.info("Update method triggered...");
		PatientVO flag = patientBO.updatePatientDetails(id);
		log.info("updatePatientDetails called in BO layer");
		if (flag != null) {
			String pass = "Successfully updated patient details for Patient ID: " + id;
			log.info(pass);
			response.setSucessMessage("updated the patient details successfully for the patient ID: ");
			response.setPatient(flag);
		} else {
			response.setFailureMessage("error in updating patient details");
		}

		return response;
	}

	// Associate method:
	@Transactional(rollbackOn = { PatientException.class, PhoneNumberException.class, EmailException.class,
			PasswordException.class, AppointmentException.class, AppointmentBookingDateException.class,
			DateOfBirthException.class })
	public ResponseHandle associate(PatientVO vo)
			throws PatientException, PhoneNumberException, EmailException, PasswordException, AppointmentException,
			AppointmentBookingDateException, DateOfBirthException, ReasonException, genderException {
		log.info("Asscoiate method triggered...");
		PatientVO inserted = patientBO.associate(vo);
		log.info("associate method called in BO layer");
		if (inserted != null) {
			String pass = "Appointment successfully added for Patient ID: " + inserted.getPatientId();
			log.info(pass);
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
		log.info("fetchByPhoneNumber called in BO layer");
		if (vo != null) {
			String pass = "Successfully fetched patient details for Phone Number: " + ph;
			log.info(pass);
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
		log.info("fetchapptDay called in BO layer");
		if (list.size() > 0) {
			String pass = "Successfully fetched appointments for Date: " + td + ". Total appointments: " + list.size();
			log.info(pass);
			response.setListPatient(list);
			response.setSucessMessage("fetching the appoinment details within the day is successfully executed");
		} else {
			response.setFailureMessage("There is no appointments on the day...");
		}
		return response;
	}

	// fetch first name and last name:
	public ResponseHandle findName(long n) throws IdException {
		log.info("Fetching patient name by ID method triggered...");
		PatientProjection p = patientBO.findname(n);
		log.info("findname called in BO layer");
		if (p != null) {
			String pass = "Successfully fetched patient name for Patient ID: " + n;
			log.info(pass);
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
		log.info("betweenTwoDOBpat called in BO layer");
		if (list.size() > 0) {
			String pass = "Successfully fetched patient details between Dates: " + sd + " and " + ld
					+ ". Total records: " + response.getListPatient().size();
			log.info(pass);
			response.setListPatient(list);
			response.setSucessMessage(
					"fetching patient details between the two dates with respect to DOB is successfully executed");
		} else {
			response.setFailureMessage("Error in fetching...");
		}
		return response;
	}

	// ascending order:
	public ResponseHandle ascending() throws AppointmentException {
		log.info("Fetching the patient details in ascending order method triggered...");
		List<PatientVO> list = patientBO.ascending();
		log.info("ascending called in BO layer");
		if (list.size() > 0) {
			String pass = "Successfully fetched patient details in ascending order. Total records: " + list.size();
			log.info(pass);
			response.setListPatient(list);
			response.setSucessMessage("fetching the paitent details in ascending order is successfully executed");
		} else {
			response.setFailureMessage("Error in fetching...");
		}
		return response;
	}

	// find Most Common DOB
	public ResponseHandle findMostCommonDOB() {
		log.info("Fetching the patient phone number recently added method triggered...");
		List<LocalDate> ld = patientBO.findMostCommonDOB();
		log.info("find Latest PhoneNumber Entry Triggerred in the BO layer");
		if (ld != null) {
			String pass = "Successfully fetched commom date of births: " + ld;
			log.info(pass);
			response.setSucessMessage(
					"fetching the paitent phone number who recently created is successfully executed");
			response.setListOfDates(ld);
		} else {
			response.setFailureMessage("Error in fetching...");
		}
		return response;
	}

	// find Patient With Most Appointments
	public ResponseHandle findPatientWithMostAppointments() {
		log.info("Fetching the patient having more appointments method triggered");
		List<PatientVO> list = patientBO.findPatientWithMostAppointments();
		log.info("Fetching the patient with most appointments method triggered in the BO layer");
		if (list.size() > 0) {
			String pass = "successfully fetched the appointments" + list;
			log.info(pass);
			response.setSucessMessage("Patients with more appointments fetched successfully");
			response.setListPatient(list);
		} else {
			response.setFailureMessage("error in fetching the details");
		}
		return response;
	}

	// find Total Patients Count
	public ResponseHandle findTotalPatientsCount() {
		log.info("Fetching total no of records present in the database method triggered");
		long count = patientBO.findTotalPatientsCount();
		log.info("Fetching the total number of records in the patient method triggered in the BO layer");
		if (count > 0) {
			String pass = "successfully fetched the total number of records in the patient table" + count;
			log.info(pass);
			response.setSucessMessage("total number of records fetched in the patient table");
			response.setId(count);
		} else {
			response.setFailureMessage("error in fetching the details");
		}
		return response;
	}
}