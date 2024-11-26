package com.HospitalAppointmentScheduling.BO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import com.HospitalAppointmentScheduling.DAO.PatientRepo;
import com.HospitalAppointmentScheduling.Entity.AppointmentsVO;
import com.HospitalAppointmentScheduling.Entity.PatientVO;

@Component
public class PatientBO {

	@Autowired
	PatientRepo patientRepo;

	// patient authentication method:
	public PatientVO patientAuthentication(String email, String password) {
		PatientVO vo = patientRepo.patientAuthentication(email, password);
		return vo;
	}

	// Insert method:
	public PatientVO insertPatientDetails(PatientVO vo) throws PatientException, PhoneNumberException, EmailException,
			PasswordException, DateOfBirthException, genderException {
		if (validatePatient(vo) && validateDOB(vo.getDob())) {
			patientRepo.save(vo);
			return vo;
		} else {
			throw new PatientException("Error in creating patient account..");
		}
	}

	// FindByID method:
	public PatientVO fetchByID(Long id) throws IdException {
		validateID(id);
		PatientVO ret = patientRepo.findById(id).get();
		return ret;

	}

	// fetchAll method:
	public List<PatientVO> fetchAll() {
		return patientRepo.findAll();
	}

	// update method:
	public PatientVO updatePatientDetails(PatientVO vo, int option) throws IdException, PatientException,
			PhoneNumberException, EmailException, PasswordException, genderException {
		if (validateID(vo.getPatientId())) {
			if (option == 1) {
				PatientVO vo1 = patientRepo.findById(vo.getPatientId()).get();
				validatePatient(vo1);
				vo1.setFirstName(vo.getFirstName());
				vo1 = patientRepo.save(vo1);
				return vo1;
			}
			if (option == 2) {
				PatientVO vo1 = patientRepo.findById(vo.getPatientId()).get();
				validatePatient(vo1);
				vo1.setLastName(vo.getLastName());
				vo1 = patientRepo.save(vo1);
				return vo1;
			}
			if (option == 3) {
				PatientVO vo1 = patientRepo.findById(vo.getPatientId()).get();
				validatePatient(vo1);
				vo1.setPatientPhone(vo.getPatientPhone());
				vo1 = patientRepo.save(vo1);
				return vo1;
			}
			if (option == 4) {
				PatientVO vo1 = patientRepo.findById(vo.getPatientId()).get();
				validatePatient(vo1);
				vo1.setDob(vo.getDob());
				vo1 = patientRepo.save(vo1);
				return vo1;
			}
			if (option == 5) {
				PatientVO vo1 = patientRepo.findById(vo.getPatientId()).get();
				validatePatient(vo1);
				vo1.setPatientEmail(vo.getPatientEmail());
				vo1 = patientRepo.save(vo1);
				return vo1;
			}
			if (option == 6) {
				PatientVO vo1 = patientRepo.findById(vo.getPatientId()).get();
				validatePatient(vo1);
				vo1.setPatientPassword(vo.getPatientPassword());
				vo1 = patientRepo.save(vo1);
				return vo1;
			}
		}
		return null;
	}

	// delete method:
	public String deletePatient(long id) throws IdException {
		String ret = "patient ID does not exists in the database give the valid ID";
		if (validateID(id)) {
			patientRepo.deleteById(id);
			ret = "patient deleted";
		}
		return ret;
	}

	// associate method
	public PatientVO associate(PatientVO vo)
			throws PatientException, PhoneNumberException, EmailException, PasswordException, AppointmentException,
			AppointmentBookingDateException, DateOfBirthException, ReasonException, genderException {
		List<AppointmentsVO> appts = vo.getAppointments();
		for (AppointmentsVO obj : appts) {
			LocalDate date = obj.getAppointmentDate();
			validateAppointmentBookingDate(date);
			String reason = obj.getReason();
			isValidReason(reason);
		}

		if (validatePatient(vo) && validateAppointmentCount(vo) && validateDOB(vo.getDob())) {
			PatientVO insert = patientRepo.save(vo);
			return insert;
		}
		return null;
	}

	// fetch patient by phone number:
	public PatientVO fetchbyPhoneNumber(String ph) throws PhoneNumberException {
		if (validatePhoneNumber(ph) && checkPhOnDB(ph)) {
			PatientVO vo = patientRepo.findByPhoneNumber(ph);
			return vo;
		}
		return null;
	}

	// fetch by day appointments:
	public List<PatientVO> fetchapptDay(LocalDate td) throws AppointmentException {
		List<PatientVO> list = patientRepo.findPatientsWithAppointmentsDay(td);
		if (list.size() <= 0) {
			throw new AppointmentException("ERROR: there is no appointments in that date");
		}
		return list;
	}

	// fetch first name and last name:
	public PatientProjection findname(long n) throws IdException {
		PatientProjection po = null;
		if (validateID(n)) {
			po = patientRepo.findNameOfPatientById(n);
		}
		return po;

	}

	// Fetching all the patient details having the DOB between two days:
	public List<PatientVO> betweenTwoDOBpat(LocalDate sd, LocalDate ld) throws DateException {
		List<PatientVO> list = patientRepo.fetchBetweenDOBpat(sd, ld);
		if (sd.isAfter(ld)) {
			throw new DateException("start date could be before the end date");
		}
		return list;
	}

	// ascending order:
	public List<PatientVO> ascending() throws AppointmentException {
		List<PatientVO> list = patientRepo.fetchAscending();
		if (!(list.size() > 0)) {
			throw new AppointmentException("ERROR: There is no Records in the DataBase");
		}
		return list;
	}

	// find Most Common DOB:
	public List<LocalDate> findMostCommonDOB() {
		return patientRepo.findMostCommonDOB();
	}

	// findPatientWithMostAppointments
	public List<PatientVO> findPatientWithMostAppointments() {
		List<PatientVO> che = patientRepo.findPatientWithMostAppointments();
		System.out.println(che);
		return patientRepo.findPatientWithMostAppointments();
	}

	// findTotalPatientsCount
	public Long findTotalPatientsCount() {
		return patientRepo.findTotalPatientsCount();
	}

	// validation methods:

	public boolean validateGender(String gender) throws genderException {
		if (!(gender.equals("male")) && !(gender.equals("female")) && !(gender.equals("others"))) {
			throw new genderException("ERROR: give valid gender");
		}
		if (gender.isEmpty() || gender == null) {
			throw new genderException("gender could not be empty");
		}
		return true;
	}

	public boolean validatePhoneNumber(String phoneNumber) throws PhoneNumberException {
		if (phoneNumber == null || phoneNumber.length() != 10) {
			throw new PhoneNumberException("ERROR: The phone number lenght is atleast 10");
		}

		char firstChar = phoneNumber.charAt(0);
		if (firstChar != '9' && firstChar != '8' && firstChar != '7' && firstChar != '6') {
			throw new PhoneNumberException("ERROR: Phone number must start with 9, 8, 7, or 6.");
		}

		for (char c : phoneNumber.toCharArray()) {
			if (!Character.isDigit(c)) {
				throw new PhoneNumberException("ERROR: Phone number can only contain digits.");
			}
		}
		return true;
	}

	// checks for the phone number that exists in the database
	public boolean checkPhOnDB(String phoneNumber) throws PhoneNumberException {
		List<String> checkPhone = patientRepo.fetchPatientPhoneNumber();
		boolean contains = false;
		for (String obj : checkPhone) {
			if (phoneNumber.equals(obj)) {
				contains = true;
				break;
			}
		}
		if (!contains) {
			throw new PhoneNumberException("ERROR: patient Phone number not exist in the database");
		}
		return true;

	}

	// validation for email
	public boolean validateEmail(String email) throws EmailException {
		if (email == null || email.isEmpty()) {
			throw new EmailException("ERROR: Email field could not be empty");
		}
		int atCount = 0;
		for (char c : email.toCharArray()) {
			if (c == '@') {
				atCount++;
				if (atCount == 1) {
					break;
				}
			}
		}
		if (atCount == 0) {
			throw new EmailException("ERROR: Email should contain atleast one " + "@" + " charactre in it");
		}

		if (email.contains("..")) {
			throw new EmailException("ERROR: Email cannot contain consecutive dots.");
		}
		return atCount == 1;
	}

	// validations for password
	public boolean validatePassword(String password) throws PatientException, PasswordException {

		if (password == null) {
			throw new PasswordException("ERROR: password field could not be empty");
		} else if (password.length() < 8 || password.length() > 12) {
			throw new PasswordException("ERROR: password length in between 8 to 12");
		}

		boolean hasUppercase = false;
		boolean hasLowercase = false;
		boolean hasDigit = false;
		boolean hasSpecial = false;

		for (char c : password.toCharArray()) {
			if (Character.isUpperCase(c)) {
				hasUppercase = true;
			} else if (Character.isLowerCase(c)) {
				hasLowercase = true;
			} else if (Character.isDigit(c)) {
				hasDigit = true;
			} else {
				hasSpecial = true;
			}
		}
		if (!(hasUppercase)) {
			throw new PasswordException("ERROR: password could have atleast one Uppercase letter");
		}
		if (!(hasLowercase)) {
			throw new PasswordException("ERROR: password could have atleast one Lowercase letter");
		}
		if (!(hasDigit)) {
			throw new PasswordException("ERROR: password could have atleast one Digit");
		}
		if (!(hasSpecial)) {
			throw new PasswordException("ERROR: password could have atleast one Special character");
		}

		return hasUppercase && hasLowercase && hasDigit && hasSpecial;
	}

	// checks the ID Checking
	public boolean validateID(Long id) throws IdException {
		List<Long> pID = patientRepo.fetchPatientId();

		boolean contains = false;
		for (Long obj : pID) {
			if (obj == id) {
				contains = true;
				break;
			}
		}
		if (!contains) {
			throw new IdException("ERROR: patient ID not exist in the database");
		}
		if (id == null) {
			throw new IdException("ERROR: patient Id field could not be null");
		} else if (id <= 0) {
			throw new IdException("ERROR: patient ID could not be negative or zero");
		}

		return id != null && id > 0;
	}

	// checks if the list of appointments could be greater than zero
	public boolean validateAppointmentCount(PatientVO vo) throws AppointmentException {
		if (vo.getAppointments().size() <= 0) {
			throw new AppointmentException("ERROR: Appointments could not be zero..");
		}
		return vo.getAppointments().size() > 0;
	}

	// checks for the new appointments could not be in the past
	public boolean validateAppointmentBookingDate(LocalDate ld) throws AppointmentBookingDateException {

		LocalDate today = LocalDate.now();

		if (!(ld.isAfter(today) || ld.isEqual(today))) {
			throw new AppointmentBookingDateException("Appointment booking date could not be in the past");
		}
		return true;

	}

	// checks for the DOB could not be in the future
	public boolean validateDOB(LocalDate ld) throws DateOfBirthException {
		LocalDate today = LocalDate.now();

		if (ld == null) {
			throw new DateOfBirthException("Date of birth could not be empty..");
		}

		if (ld.isAfter(today)) {
			throw new DateOfBirthException("date of birth could not be in the future");
		}
		if (today.minusYears(18).isBefore(ld)) {
			throw new DateOfBirthException("ERROR: Patient must be at least 18 years old.");
		}
		return true;
	}

	public boolean validateCombinedName(String firstName, String lastName) throws PatientException {
		String combinedName = firstName + " " + lastName;

		if (combinedName.length() > 50) {
			throw new PatientException("ERROR: Combined first and last name cannot exceed 50 characters.");
		}

		for (char c : combinedName.toCharArray()) {
			if (!Character.isAlphabetic(c) && !Character.isSpaceChar(c)) {
				throw new PatientException("ERROR: Combined first and last name contains invalid characters.");
			}
		}

		if (combinedName.contains("  ")) {
			throw new PatientException("ERROR: Combined first and last name cannot contain consecutive spaces.");
		}

		return true;
	}

	public boolean validateFirstName(String firstName) throws PatientException {
		if (firstName == null || firstName.isEmpty()) {
			throw new PatientException("ERROR: First name cannot be empty.");
		}

		if (firstName.length() < 2) {
			throw new PatientException("ERROR: First name must be at least 2 characters long.");
		}

		for (char c : firstName.toCharArray()) {
			if (!Character.isAlphabetic(c)) {
				throw new PatientException("ERROR: First name can only contain alphabetic characters.");
			}
		}

		if (firstName.trim().length() != firstName.length()) {
			throw new PatientException("ERROR: First name cannot have leading or trailing spaces.");
		}

		return true;
	}

	public boolean validateLastName(String lastName) throws PatientException {
		if (lastName == null || lastName.isEmpty()) {
			throw new PatientException("ERROR: Last name cannot be empty.");
		}

		if (lastName.length() < 2) {
			throw new PatientException("ERROR: Last name must be at least 2 characters long.");
		}

		for (char c : lastName.toCharArray()) {
			if (!Character.isAlphabetic(c)) {
				throw new PatientException("ERROR: Last name can only contain alphabetic characters.");
			}
		}

		if (lastName.equalsIgnoreCase("N/A") || lastName.equalsIgnoreCase("Unknown")) {
			throw new PatientException("ERROR: Last name cannot be 'N/A' or 'Unknown'.");
		}

		return true;
	}

	private boolean isValidReason(String reason) throws ReasonException {
		boolean flag = true;
		int reasonLength = reason.length();
		if (reasonLength > 31) {
			throw new ReasonException("Reason is too long, hence your patient could not registered");
		}
		if (reasonLength <= 0) {
			throw new ReasonException("Reason is too short, hence your patient could not registered");
		}
		return flag;
	}

	// Main validation method to validate a patient object:
	public boolean validatePatient(PatientVO vo)
			throws PatientException, PhoneNumberException, EmailException, PasswordException, genderException {
		return validatePhoneNumber(vo.getPatientPhone()) && validateEmail(vo.getPatientEmail())
				&& validatePassword(vo.getPatientPassword()) && validateFirstName(vo.getFirstName())
				&& validateLastName(vo.getLastName()) && validateCombinedName(vo.getFirstName(), vo.getLastName())
				&& validateGender(vo.getGender().toLowerCase());

	}
}
