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

import com.HospitalAppointmentScheduling.CustomExceptions.AppointmentBookingDateException;
import com.HospitalAppointmentScheduling.CustomExceptions.AppointmentException;
import com.HospitalAppointmentScheduling.CustomExceptions.DateException;
import com.HospitalAppointmentScheduling.CustomExceptions.DateOfBirthException;
import com.HospitalAppointmentScheduling.CustomExceptions.EmailException;
import com.HospitalAppointmentScheduling.CustomExceptions.IdException;
import com.HospitalAppointmentScheduling.CustomExceptions.PasswordException;
import com.HospitalAppointmentScheduling.CustomExceptions.PatientException;
import com.HospitalAppointmentScheduling.CustomExceptions.PhoneNumberException;
import com.HospitalAppointmentScheduling.Entity.AppointmentsVO;
import com.HospitalAppointmentScheduling.Entity.PatientVO;
import com.HospitalAppointmentScheduling.Response.ResponseHandle;
import com.HospitalAppointmentScheduling.Service.PatientService;

@SpringBootApplication
@EnableJpaAuditing
public class HospitalAppointmentSchedulingApplication {
	@Autowired
	public PatientService pService;

	@Autowired
	private ResponseHandle response;

	static Logger log = Logger.getLogger(HospitalAppointmentSchedulingApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(HospitalAppointmentSchedulingApplication.class, args);
		Scanner sc = new Scanner(System.in);

		HospitalAppointmentSchedulingApplication ref = ctx.getBean(HospitalAppointmentSchedulingApplication.class);

		PropertyConfigurator.configure(
				"C:\\Users\\Lenovo\\OneDrive\\Desktop\\GIT\\Hospital-Appointment-Scheduling\\HospitalAppointmentScheduling\\src\\main\\java\\log4j\\log4j.properities");
		log.info(" Application Started Started..");

		boolean repeat = true;
		log.info("Patient chooses create account option...");
		do {
			System.out.println("1. Save Patient\n2. FindByID\n3. FetchAllPatients\n4. Update Details\n5. Associate\n6. "
					+ "Fetch patient by phone number\n7. fetch appointments by the date\n8. Fetch appointments having more than number\n9. "
					+ "Find first and last name by patient ID\n10. Fetch all the patient details among the two date\n11. Get the patients in "
					+ "Ascending order\n12. exit");
			System.out.print("Enter the option: ");
			int option = sc.nextInt();
			switch (option) {
			case 1: {
				ref.insert();
				break;
			}
			case 2: {
				System.out.print("Enter the patient ID: ");
				long id = sc.nextLong();
				ref.fetchByID(id);
				break;
			}
			case 3: {
				ref.fetchAll();
				break;
			}
			case 4: {
				System.out.print("Enter the patient ID: ");
				int id = sc.nextInt();
				ref.update(id);
				break;
			}
			case 5: {
				ref.AssociatePatientwithAppointment();
				break;
			}
			case 6: {
				System.out.print("Enter the patient phone number to fetch patient Details: ");
				String ph = sc.next();
				ref.fetchbyPhone(ph);
				break;
			}
			case 7: {
				ref.fetchapptday();
				break;
			}
			case 8: {
				long n = sc.nextLong();
				ref.fetchAppointmentbynumber(n);
				break;
			}
			case 9: {
				System.out.println("Enter the patient ID: ");
				long n = sc.nextLong();
				ref.findName(n);
				break;
			}
			case 10: {
				System.out.print("Enter the Start Date in the format (YYYY-MM-DD): ");
				String start_date = sc.next();
				System.out.print("Enter the End Date in the format (YYYY-MM-DD): ");
				String end_date = sc.next();
				DateTimeFormatter format_appt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate started_date = LocalDate.parse(start_date, format_appt);
				LocalDate ended_date = LocalDate.parse(end_date, format_appt);

				ref.betweenTwoDOBpat(started_date, ended_date);
				break;

			}
			case 11: {
				ref.ascending();
				break;

			}
			case 12: {
				log.info("patient chooses to EXIT the application...");
				repeat = false;
				System.out.println("Thank you for Using the application");
				break;
			}
			default: {
				System.out.println("Enter the correct option");
			}
			}
		} while (repeat);
	}

	// insert method
	public void insert() {
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
		}

		if (response.getPatient().getPatientId() > 0) {
			System.out.println("Your Generated Patient ID is: " + response.getPatient().getPatientId());

		} else {
			System.out.println("Failed");
		}
	}

	// fetch by ID:
	public void fetchByID(long id) {
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

	// fetch all method:
	public void fetchAll() {
		response = pService.fetchAll();
		List<PatientVO> patientlist = response.getListPatient();
		for (PatientVO obj : patientlist) {
			System.out.println(obj);
		}
	}

	// update method:
	public void update(long id) {
		try {
			response = pService.updatePatientDetails(id);
			if (response.getSucessMessage() != null) {
				System.out.println(response.getSucessMessage() + response.getId());
			}
		} catch (IdException e) {
			System.err.println(e.getMessage());
		}
	}

	// associate method:
	public void AssociatePatientwithAppointment() {

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

		List<AppointmentsVO> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			AppointmentsVO appt = new AppointmentsVO();

			System.out.print("Enter the Date of Appointment in the format (YYYY-MM-DD): ");
			String appt_date = sc.next();
			DateTimeFormatter format_appt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate appnt_date = LocalDate.parse(appt_date, format_appt);
			appt.setAppointmentDate(appnt_date);

			sc.nextLine();
			System.out.println("Enter the reason: ");
			appt.setReason(sc.nextLine());

			System.out.println("Enter the doctor ID: ");
			appt.setDoctorId(sc.nextLong());

			appt.setPatient(patient);
			list.add(appt);
		}

		patient.setAppointments(list);
		try {
			response = pService.associate(patient);
		} catch (PatientException e) {
			System.err.println(e.getMessage());
		} catch (PhoneNumberException e) {
			System.err.println(e.getMessage());
		} catch (EmailException e) {
			System.err.println(e.getMessage());
		} catch (PasswordException e) {
			System.err.println(e.getMessage());
		} catch (AppointmentException e) {
			System.err.println(e.getMessage());
		} catch (AppointmentBookingDateException e) {
			System.err.println(e.getMessage());
		} catch (DateOfBirthException e) {
			System.err.println(e.getMessage());
		}

		long id = response.getId();

		if (id > 0) {
			System.out.println(response.getSucessMessage());
		} else {
			System.out.println(response.getFailureMessage());
		}

	}

	// fetch by phone number
	public void fetchbyPhone(String ph) {
		try {
			response = pService.findbyphone(ph);
			System.out.println(response.getPatient());
		} catch (PhoneNumberException e) {

			System.err.println(e.getMessage());
		}

	}

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
	}

	// fetch by more appointments
	public void fetchAppointmentbynumber(long n) {
		try {
			response = pService.findAppointmentsByNumber(n);
			if (response.getSucessMessage() != null) {
				System.out.println("The users have more than appointments by given: ");
				System.out.println(response.getListPatient());
			} else {
				System.out.println(response.getFailureMessage());
			}
		} catch (AppointmentException e) {
			System.err.println(e.getMessage());
		}

	}

	// fetch first name and last name:
	public void findName(long id) {
		try {
			response = pService.findName(id);
			System.out.println("First name: " + response.getPro().getFirstName() + " Second name: "
					+ response.getPro().getLastName());
		} catch (IdException e) {
			System.err.println(e.getMessage());
		}

	}

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

	// ascending order:
	public void ascending() {
		try {
			response = pService.acending();
		} catch (AppointmentException e) {
			System.err.println(e.getMessage());
		}
		for (PatientVO obj : response.getListPatient()) {
			System.out.println(obj);
		}
	}

}
