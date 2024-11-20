package com.HospitalAppointmentScheduling;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.HospitalAppointmentScheduling.Clinet.PatientClient;
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
import com.HospitalAppointmentScheduling.DAO.DoctorDetailsProjection;
import com.HospitalAppointmentScheduling.DTO.PatientDTO;
import com.HospitalAppointmentScheduling.Entity.AppointmentStatusVO;
import com.HospitalAppointmentScheduling.Entity.AppointmentsVO;
import com.HospitalAppointmentScheduling.Entity.DoctorVO;
import com.HospitalAppointmentScheduling.Entity.PatientVO;
import com.HospitalAppointmentScheduling.Response.ResponseHandle;
import com.HospitalAppointmentScheduling.Response.ResponseHandleAppointments;
import com.HospitalAppointmentScheduling.Response.ResponseHandleDoctor;
import com.HospitalAppointmentScheduling.Service.AppointmentsService;
import com.HospitalAppointmentScheduling.Service.DoctorService;
import com.HospitalAppointmentScheduling.Service.PatientService;

@SpringBootApplication
@EnableJpaAuditing
public class HospitalAppointmentSchedulingApplication {

	@Autowired
	public PatientService pService;

	@Autowired
	private AppointmentsService aService;

	@Autowired
	private ResponseHandle response;

	@Autowired
	private ResponseHandleAppointments resAppt;

	@Autowired
	private DoctorService dser;

	@Autowired
	private ResponseHandleDoctor docRes;

	@Autowired
	private PatientClient patClient;

	static Logger log = Logger.getLogger(HospitalAppointmentSchedulingApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(HospitalAppointmentSchedulingApplication.class, args);
		Scanner sc = new Scanner(System.in);

		HospitalAppointmentSchedulingApplication ref = ctx.getBean(HospitalAppointmentSchedulingApplication.class);

		PropertyConfigurator.configure(
				"C:\\Users\\Lenovo\\OneDrive\\Desktop\\GIT\\Hospital-Appointment-Scheduling\\HospitalAppointmentScheduling\\src\\main\\java\\log4j\\log4j.properities");
		log.info(" Application Started Started..");

		System.out.println("Welcome to Appoinment management Application...");

		System.out.println(
				"You are having three Log In chances if you don't LogIn success then you can LogIn tomorrow only...");

		int tryLogIn = 3;
		while (tryLogIn > 0) {
			System.out.print("Enter the registered patient emailID: ");
			String email = sc.next();

			System.out.print("Enter the registered patient Password: ");
			String password = sc.next();

			ResponseHandle refAuth = ref.pService.patientAuthentication(email, password);
			if (refAuth.getSucessMessage() != null) {
				tryLogIn = 0;
				System.out.println("Your LogIn Success...");
				boolean mainRepeat = true;
				do {
					System.out.println("1. Patient menu\n2. Appointments menu\n3. Exit");
					System.out.print("Enter the option: ");
					int mainOption = sc.nextInt();

					switch (mainOption) {
					case 1: {
						boolean patientRepeat = true;
						do {
							System.out.println(
									"1. Save Patient\n2. FindByID\n3. FetchAllPatients\n4. Update Details\n5. Associate\n6. "
											+ "Fetch patient by phone number\n7. fetch appointments by the date\n8. "
											+ "Find first and last name by patient ID\n9. Fetch all the patient details among the two date"
											+ "\n10. Get the patients in "
											+ "Ascending order\n11. Insert patient throught client\n12. Get all patients through "
											+ "client\n13. find most birthday among patients\n14. patients having most appointments"
											+ "\n15. Total number of patients in the DateBase\n16. exit");
							System.out.print("Enter the option: ");
							int patientOption = sc.nextInt();
							switch (patientOption) {
							case 1: {
								ref.insertPatient();
								break;
							}
							case 2: {
								System.out.print("Enter the patient ID: ");
								long id = sc.nextLong();
								ref.fetchByIDPatient(id);
								break;
							}
							case 3: {
								ref.fetchAllPatients();
								break;
							}
							case 4: {
								System.out.print("Enter the patient ID: ");
								int id = sc.nextInt();
								ref.updatePatient(id);
								break;
							}
							case 5: {
								long id = ref.associatePatientWithAppointment();
								System.out.println("Patient Details Fetched by ID:" + id);
								break;
							}
							case 6: {
								System.out.print("Enter the patient phone number to fetch patient Details: ");
								String ph = sc.next();
								ref.fetchbyPatientPhone(ph);
								break;
							}
							case 7: {
								ref.fetchapptday();
								break;
							}
							case 8: {
								System.out.println("Enter the patient ID: ");
								long id = sc.nextLong();
								ref.findPatientName(id);
								break;
							}
							case 9: {
								System.out.print("Enter the Start Date in the format (YYYY-MM-DD): ");
								String startDate = sc.next();
								System.out.print("Enter the End Date in the format (YYYY-MM-DD): ");
								String endDate = sc.next();
								DateTimeFormatter formatAppt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
								LocalDate startedDate = LocalDate.parse(startDate, formatAppt);
								LocalDate endedDate = LocalDate.parse(endDate, formatAppt);

								ref.betweenTwoDOBpat(startedDate, endedDate);
								break;

							}
							case 10: {
								ref.ascendingPatient();
								break;

							}
							case 11: {
								ref.insertPatientThroughClient();
								break;
							}
							case 12: {
								ref.fetchAllPatientsThroughClient();
								break;
							}
							case 13: {
								ref.findMostCommonDOB();
								break;
							}
							case 14: {
								ref.findPatientWithMostAppointments();
								break;
							}
							case 15: {
								ref.findTotalPatientsCount();
								break;
							}
							case 16: {
								log.info("patient chooses to EXIT the application...");
								patientRepeat = false;
								System.out.println("Thank you for Using patient page returning to main page");
								break;
							}
							default: {
								System.out.println("Enter the correct option");
							}
							}
						} while (patientRepeat);
						break;
					}
					case 2: {
						boolean appointmentRepeat = true;
						do {
							System.out.println(
									"1. Add appointments with creating patient account\n2. Add appointments with already registered patient ID\n3. "
											+ "Fetch appointments by ID\n4. fetch all appointments\n5. Update appointmens status\n6. Fetch appointments in two dates"
											+ "\n7. Fetch appointments in ascending order" + "\n8. EXIT");
							System.out.print("Enter the option: ");
							int appointmentOption = sc.nextInt();
							switch (appointmentOption) {
							case 1: {
								ref.insertApptWithPatientAcc();
								break;
							}
							case 2: {
								ref.insertAppointmentsWithPatientID();
								break;
							}
							case 3: {
								System.out.print("Enter the Appointment ID: ");
								long id = sc.nextLong();
								ref.fetchByIDAppointment(id);
								break;
							}
							case 4: {
								ref.fetchAllAppointments();
								break;
							}
							case 5: {
								System.out.print("Enter the Appointment ID: ");
								long id = sc.nextLong();
								ref.updateAppointment(id);
								break;
							}
							case 6: {
								System.out.print("Enter the Start Date in the format (YYYY-MM-DD): ");
								String startDate = sc.next();
								System.out.print("Enter the End Date in the format (YYYY-MM-DD): ");
								String endDate = sc.next();
								DateTimeFormatter formatAppt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
								LocalDate startedDate = LocalDate.parse(startDate, formatAppt);
								LocalDate endedDate = LocalDate.parse(endDate, formatAppt);

								ref.fetchApptBetweenTwoDates(startedDate, endedDate);
								break;
							}
							case 7: {
								ref.ascendingAppointmentsDate();
								break;
							}
							case 8: {
								System.out
										.println("Thank you for using Appointment booking page, retuning to main page");
								appointmentRepeat = false;
								break;
							}
							}
						} while (appointmentRepeat);
						break;
					}
					case 3: {
						System.out.println("Thank you for using Hospital Management System application");
						break;
					}
					default: {
						System.out.println("Enter the correct option");
					}
					}
				} while (mainRepeat);
			} else {
				tryLogIn--;
				if (tryLogIn > 0) {
					System.out.println(refAuth.getFailureMessage() + "you are having " + tryLogIn + " attempts");
				}
				if (tryLogIn == 0) {
					System.out.println(
							"Your all chances are finished, here after you can only logIn on tomorrow, Thanks for using Hospital Management System application");
				}
			}
		}

	}
	// ------------------------------------------------------------------------------------------------------------------------------//

	// insert method
	public void insertPatient() {
		Scanner sc = new Scanner(System.in);
		PatientVO patient = new PatientVO();

		System.out.print("Enter the First Name: ");
		patient.setFirstName(sc.next());

		System.out.print("Enter the Last Name: ");
		patient.setLastName(sc.next());

		System.out.print("Enter the phone number: ");
		patient.setPatientPhone(sc.next());

		System.out.print("Enter the Date of birth in the format (YYYY-MM-DD): ");
		String date = sc.next();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dob = LocalDate.parse(date, format);
		patient.setDob(dob);

		System.out.print("Enter the Email: ");
		patient.setPatientEmail(sc.next());

		System.out.print("Enter the Password: ");
		patient.setPatientPassword(sc.next());

		System.out.println("Enter the gender: ");
		patient.setGender(sc.next());

		try {
			response = pService.insertPatientDetails(patient);
		} catch (PatientException e) {
			System.err.println(e.getMessage() + ", hence your patient could not registered");
		} catch (PhoneNumberException e) {
			System.err.println(e.getMessage() + ", hence your patient could not registered");
		} catch (EmailException e) {
			System.err.println(e.getMessage() + ", hence your patient could not registered");
		} catch (PasswordException e) {
			System.err.println(e.getMessage() + ", hence your patient could not registered");
		} catch (DateOfBirthException e) {
			System.err.println(e.getMessage() + ", hence your patient could not registered");
		} catch (genderException e) {
			System.err.println(e.getMessage() + ", hence your patient could not registered");
		}

		if (response.getPatient().getPatientId() > 0) {
			System.out.println("Your Generated Patient ID is: " + response.getPatient().getPatientId());

		} else {
			System.out.println("Failed");
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// fetch by ID:
	public void fetchByIDPatient(long id) {
		try {
			response = pService.fetchById(id);
			if (response.getSucessMessage() != null) {
				System.out.println(response.getSucessMessage() + response.getId());
				System.out.println(response.getPatient());
			}
		} catch (IdException e) {
			System.out.println(e.getMessage());
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// fetch all method:
	public void fetchAllPatients() {
		response = pService.fetchAll();
		List<PatientVO> patientlist = response.getListPatient();
		for (PatientVO obj : patientlist) {
			System.out.println(obj);
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// update method:
	public void updatePatient(long id) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.print("1. First Name\n2. Last Name\n3. Phone number\n4. Date of Birth\n5. Email\n6. Password");
			System.out.println();
			System.out.print("Enter the number to choose the field to change: ");
			int upt = sc.nextInt();
			switch (upt) {
			case 1: {
				System.out.print("Enter the new First Name: ");
				PatientVO vo = new PatientVO();
				vo.setPatientId(id);
				vo.setFirstName(sc.next());
				response = pService.updatePatientDetails(vo, 1);
				if (response.getSucessMessage() != null) {
					System.out.println(response.getSucessMessage() + response.getId());
				}
				break;
			}
			case 2: {
				System.out.print("Enter the new Last Name: ");
				PatientVO vo = new PatientVO();
				vo.setPatientId(id);
				vo.setLastName(sc.next());
				response = pService.updatePatientDetails(vo, 2);
				if (response.getSucessMessage() != null) {
					System.out.println(response.getSucessMessage() + response.getId());
				}
				break;
			}
			case 3: {
				System.out.print("Enter the new Phone number: ");
				PatientVO vo = new PatientVO();
				vo.setPatientId(id);
				vo.setPatientPhone(sc.next());
				response = pService.updatePatientDetails(vo, 3);
				if (response.getSucessMessage() != null) {
					System.out.println(response.getSucessMessage() + response.getId());
				}
				break;
			}
			case 4: {
				System.out.print("Enter the new Date of Birth in the format (YYYY-MM-DD): ");
				PatientVO vo = new PatientVO();
				vo.setPatientId(id);
				String date = sc.next();
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dob = LocalDate.parse(date, format);
				vo.setDob(dob);
				response = pService.updatePatientDetails(vo, 4);
				if (response.getSucessMessage() != null) {
					System.out.println(response.getSucessMessage() + response.getId());
				}
				break;
			}
			case 5: {
				System.out.print("Enter the new Email: ");
				PatientVO vo = new PatientVO();
				vo.setPatientId(id);
				vo.setPatientEmail(sc.next());
				response = pService.updatePatientDetails(vo, 5);
				if (response.getSucessMessage() != null) {
					System.out.println(response.getSucessMessage() + response.getId());
				}
				break;
			}
			case 6: {
				System.out.print("Enter the new Password: ");
				PatientVO vo = new PatientVO();
				vo.setPatientId(id);
				vo.setPatientPassword(sc.next());
				response = pService.updatePatientDetails(vo, 6);
				if (response.getSucessMessage() != null) {
					System.out.println(response.getSucessMessage() + response.getId());
				}
				break;
			}
			default: {
				System.out.println("Enter the correct number to update");
			}

			}

		} catch (IdException e) {
			System.err.println(e.getMessage());
		} catch (PatientException e) {
			System.err.println(e.getMessage());
		} catch (PhoneNumberException e) {
			System.err.println(e.getMessage());
		} catch (EmailException e) {
			System.err.println(e.getMessage());
		} catch (PasswordException e) {
			System.err.println(e.getMessage());
		} catch (genderException e) {
			System.err.println(e.getMessage());
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// associate method:
	public long associatePatientWithAppointment() {

		Scanner sc = new Scanner(System.in);
		PatientVO patient = new PatientVO();

		System.out.print("Enter the First Name: ");
		patient.setFirstName(sc.next());

		System.out.print("Enter the Last Name: ");
		patient.setLastName(sc.next());

		System.out.print("Enter the phone number: ");
		patient.setPatientPhone(sc.next());

		System.out.print("Enter the Date of birth in the format (YYYY-MM-DD): ");
		String date = sc.next();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dob = LocalDate.parse(date, format);
		patient.setDob(dob);

		System.out.print("Enter the Email: ");
		patient.setPatientEmail(sc.next());

		System.out.print("Enter the Password: ");
		patient.setPatientPassword(sc.next());

		System.out.println("Enter Number of Appointments Details :");
		int n = sc.nextInt();

		resAppt = dser.fetch();
		for (DoctorDetailsProjection obj : resAppt.getListSpecialiaztion()) {
			System.out.println(obj.getDoctorId() + ". " + obj.getFirstName() + " " + obj.getLastName() + ": "
					+ obj.getSpecialtyName());
		}

		List<AppointmentsVO> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			AppointmentsVO appt = new AppointmentsVO();

			System.out.print("Enter the Date of Appointment in the format (YYYY-MM-DD): ");
			String apptDate = sc.next();
			DateTimeFormatter formatAppt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate appntDate = LocalDate.parse(apptDate, formatAppt);
			appt.setAppointmentDate(appntDate);

			sc.nextLine();
			System.out.println("Enter the reason: ");
			appt.setReason(sc.nextLine());

			System.out.println("Enter the doctor ID: ");
			DoctorVO dVO = new DoctorVO();
			dVO.setDoctorId(sc.nextLong());
			appt.setDoctor(dVO);

			AppointmentStatusVO asVO = new AppointmentStatusVO();
			asVO.setStatusName("Pending");
			appt.setStatus(asVO);

			appt.setPatient(patient);
			list.add(appt);
		}

		patient.setAppointments(list);
		try {
			response = pService.associate(patient);
		} catch (PatientException e) {
			System.err.println(e.getMessage() + ", hence your patient could not registered");
		} catch (PhoneNumberException e) {
			System.err.println(e.getMessage() + ", hence your patient could not registered");
		} catch (EmailException e) {
			System.err.println(e.getMessage() + ", hence your patient could not registered");
		} catch (PasswordException e) {
			System.err.println(e.getMessage() + ", hence your patient could not registered");
		} catch (AppointmentException e) {
			System.err.println(e.getMessage() + ", hence your patient could not registered");
		} catch (AppointmentBookingDateException e) {
			System.err.println(e.getMessage() + ", hence your patient could not registered");
		} catch (DateOfBirthException e) {
			System.err.println(e.getMessage() + ", hence your patient could not registered");
		} catch (ReasonException e) {
			System.err.println(e.getMessage() + ", hence your patient could not registered");
		} catch (genderException e) {
			System.err.println(e.getMessage());
		}

		long id = response.getId();

		if (id > 0) {
			System.out.println(response.getSucessMessage());
		} else {
			System.out.println(response.getFailureMessage());
		}

		return id;
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// fetch by phone number
	public void fetchbyPatientPhone(String ph) {
		try {
			response = pService.findbyphone(ph);
			System.out.println(response.getPatient());
		} catch (PhoneNumberException e) {

			System.err.println(e.getMessage());
		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// fetch by day appointments
	public void fetchapptday() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the date in the format (YYYY-MM-DD): ");
		String date = sc.next();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate day = LocalDate.parse(date, format);

		try {
			response = pService.findapptDay(day);
		} catch (AppointmentException e) {
			System.err.println(e.getMessage());
		}
		if (response.getSucessMessage() != null) {
			System.out.println(response.getListPatient());
		}
		sc.close();
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// fetch first name and last name:
	public void findPatientName(long id) {
		try {
			response = pService.findName(id);
			System.out.println("First name: " + response.getPro().getFirstName() + " Second name: "
					+ response.getPro().getLastName());
		} catch (IdException e) {
			System.err.println(e.getMessage());
		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// Appointment by between two days:
	public void betweenTwoDOBpat(LocalDate sd, LocalDate ld) {
		try {
			response = pService.betweenTwoDOBpat(sd, ld);
		} catch (DateException e) {
			System.err.println(e.getMessage());
		}
		for (PatientVO obj : response.getListPatient()) {
			System.out.println(obj);
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// insert patient through client:
	public void insertPatientThroughClient() {
		PatientDTO patient = new PatientDTO();
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the First Name: ");
		patient.setFirstName(sc.next());

		System.out.print("Enter the Last Name: ");
		patient.setLastName(sc.next());

		System.out.print("Enter the phone number: ");
		patient.setPatientPhone(sc.next());

		System.out.print("Enter the Date of birth in the format (YYYY-MM-DD): ");
		String date = sc.next();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dob = LocalDate.parse(date, format);
		patient.setDob(dob);

		System.out.print("Enter the Email: ");
		patient.setPatientEmail(sc.next());

		System.out.print("Enter the Password: ");
		patient.setPatientPassword(sc.next());

		String msg = patClient.insertPatientThroughClient(patient);
		System.out.println(msg);
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// get all patients through client:
	public void fetchAllPatientsThroughClient() {
		List<PatientDTO> list = patClient.fetchAllPatientsThroughClient();
		if (list.size() > 0) {
			for (PatientDTO obj : list) {
				System.out.println(obj);
			}
		} else {
			System.out.println("there is no records in the database");
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// find most birthday among patients
	public void findMostCommonDOB() {
		response = pService.findMostCommonDOB();
		for (LocalDate obj : response.getListOfDates()) {
			System.out.println(obj);
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// patients having most appointments
	public void findPatientWithMostAppointments() {
		response = pService.findPatientWithMostAppointments();
		for (PatientVO obj : response.getListPatient()) {
			System.out.println(obj);
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// Total number of patients in the DateBase
	public void findTotalPatientsCount() {
		response = pService.findTotalPatientsCount();
		System.out.println(response.getId());
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// ascending order:
	public void ascendingPatient() {
		try {
			response = pService.ascending();
		} catch (AppointmentException e) {
			System.err.println(e.getMessage());
		}
		for (PatientVO obj : response.getListPatient()) {
			System.out.println(obj);
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// insert appointment with creating patient account
	public void insertApptWithPatientAcc() {
		Scanner sc = new Scanner(System.in);

		AppointmentsVO avo = new AppointmentsVO();

		System.out.print("Enter the appointment date in the format (YYYY-MM-DD): ");
		String date = sc.next();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate aDate = LocalDate.parse(date, format);
		avo.setAppointmentDate(aDate);

		sc.nextLine();

		System.out.print("Enter the reason: ");
		avo.setReason(sc.nextLine());

		resAppt = dser.fetch();
		for (DoctorDetailsProjection obj : resAppt.getListSpecialiaztion()) {
			System.out.println(obj.getDoctorId() + ". " + obj.getFirstName() + " " + obj.getLastName() + ": "
					+ obj.getSpecialtyName());
		}
		System.out.print("Enter the doctor ID: ");
		DoctorVO dVO = new DoctorVO();
		dVO.setDoctorId(sc.nextLong());
		avo.setDoctor(dVO);
		sc.nextLine();

		AppointmentStatusVO asVO = new AppointmentStatusVO();
		asVO.setStatusName("Pending");
		avo.setStatus(asVO);

		PatientVO patient = new PatientVO();
		System.out.print("Enter the First Name: ");
		patient.setFirstName(sc.next());

		System.out.print("Enter the Last Name: ");
		patient.setLastName(sc.next());

		System.out.print("Enter the phone number: ");
		patient.setPatientPhone(sc.next());

		System.out.print("Enter the Date of birth in the format (YYYY-MM-DD): ");
		String datePat = sc.next();
		DateTimeFormatter formatPat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dob = LocalDate.parse(datePat, formatPat);
		patient.setDob(dob);

		System.out.print("Enter the Email: ");
		patient.setPatientEmail(sc.next());

		System.out.print("Enter the Password: ");
		patient.setPatientPassword(sc.next());

		System.out.println("Enter the gender: ");
		patient.setGender(sc.next());

		try {
			response = pService.insertPatientDetails(patient);
		} catch (PatientException e) {
			System.err.println(e.getMessage());
		} catch (PhoneNumberException e) {
			System.err.println(e.getMessage());
		} catch (EmailException e) {
			System.err.println(e.getMessage());
		} catch (PasswordException e) {
			System.err.println(e.getMessage());
		} catch (DateOfBirthException e) {
			System.err.println(e.getMessage());
		} catch (genderException e) {
			System.err.println(e.getMessage());
		}

		avo.setPatient(response.getPatient());

		try {
			resAppt = aService.insertAppointments(avo);
		} catch (IdException e) {
			System.out.println(e.getMessage());
		} catch (EmailException e) {
			System.out.println(e.getMessage());
		} catch (PasswordException e) {
			System.out.println(e.getMessage());
		} catch (PhoneNumberException e) {
			System.out.println(e.getMessage());
		} catch (AppointmentException e) {
			System.out.println(e.getMessage());
		} catch (PatientException e) {
			System.out.println(e.getMessage());
		} catch (DateOfBirthException e) {
			System.out.println(e.getMessage());
		}

		if (resAppt.getAppoVo().getAppointmentID() > 0) {
			System.out.println("Your Generated Appointment ID is: " + resAppt.getAppoVo().getAppointmentID());

		} else {
			System.out.println("Failed");
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	public void insertAppointmentsWithPatientID() {
		Scanner sc = new Scanner(System.in);

		AppointmentsVO avo = new AppointmentsVO();

		System.out.print("Enter the appointment date in the format (YYYY-MM-DD): ");
		String date = sc.next();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate aDate = LocalDate.parse(date, format);
		avo.setAppointmentDate(aDate);

		sc.nextLine();

		System.out.print("Enter the reason: ");
		sc.nextLine();
		avo.setReason(sc.nextLine());

		resAppt = dser.fetch();
		for (DoctorDetailsProjection obj : resAppt.getListSpecialiaztion()) {
			System.out.println(obj.getDoctorId() + ". " + obj.getFirstName() + " " + obj.getLastName() + ": "
					+ obj.getSpecialtyName());
		}

		System.out.print("Enter the doctor ID respect to the specilization: ");
		DoctorVO dVO = new DoctorVO();
		dVO.setDoctorId(sc.nextLong());
		avo.setDoctor(dVO);
		sc.nextLine();

		AppointmentStatusVO asVO = new AppointmentStatusVO();
		asVO.setStatusName("Pending");
		avo.setStatus(asVO);

		PatientVO patient = new PatientVO();
		System.out.print("Enter the already registerd Patient ID:");
		patient.setPatientId(sc.nextLong());
		avo.setPatient(patient);

		try {
			resAppt = aService.insertAppointmentsWithPatientID(avo);
		} catch (IdException e) {
			System.out.println(e.getMessage());
		} catch (EmailException e) {
			System.out.println(e.getMessage());
		} catch (PasswordException e) {
			System.out.println(e.getMessage());
		} catch (PhoneNumberException e) {
			System.out.println(e.getMessage());
		} catch (AppointmentException e) {
			System.out.println(e.getMessage());
		} catch (PatientException e) {
			System.out.println(e.getMessage());
		} catch (DateOfBirthException e) {
			System.out.println(e.getMessage());
		} catch (AppointmentBookingDateException e) {
			System.out.println(e.getMessage());
		} catch (ReasonException e) {
			System.out.println(e.getMessage());
		}

		if (resAppt.getAppoVo().getAppointmentID() > 0) {
			System.out.println("Your Generated Appointment ID is: " + resAppt.getAppoVo().getAppointmentID());

		} else {
			System.out.println("Failed");
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// fetch by ID:
	public void fetchByIDAppointment(long id) {
		try {
			resAppt = aService.fetchByID(id);
			if (response.getSucessMessage() != null) {
				System.out.println(resAppt.getSucessMessage() + resAppt.getAppoVo().getAppointmentID());
				System.out.println(resAppt.getAppoVo());
			}
		} catch (IdException e) {
			System.out.println(e.getMessage());
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// fetch all method:
	public void fetchAllAppointments() {
		resAppt = aService.fetchAll();
		List<AppointmentsVO> appointmentlist = resAppt.getList();
		for (AppointmentsVO obj : appointmentlist) {
			System.out.println(obj);
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// update method:
	public void updateAppointment(long id) {
		try {
			resAppt = aService.update(id);
			if (resAppt.getSucessMessage() != null) {
				System.out.println(resAppt.getSucessMessage() + resAppt.getAppoVo().getAppointmentID());
			}
		} catch (IdException e) {
			System.err.println(e.getMessage());
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	public void fetchApptBetweenTwoDates(LocalDate sd, LocalDate ld) {

		try {
			resAppt = aService.fetchApptBetweenTwoDates(sd, ld);
			if (resAppt.getSucessMessage() != null) {
				for (AppointmentsVO obj : resAppt.getList()) {
					System.out.println(obj);
				}
			}
		} catch (DateException e) {
			System.err.println(e.getMessage());
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

	// ascending order:
	public void ascendingAppointmentsDate() {
		try {
			resAppt = aService.acendingDate();
		} catch (AppointmentException e) {
			System.err.println(e.getMessage());
		}
		for (AppointmentsVO obj : resAppt.getList()) {
			System.out.println(obj);
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------//

}
