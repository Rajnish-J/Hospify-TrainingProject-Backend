package com.HospitalAppointmentScheduling;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.HospitalAppointmentScheduling.CustomExceptions.patientException;
import com.HospitalAppointmentScheduling.Entity.patientVO;
import com.HospitalAppointmentScheduling.Response.ResponseHandle;
import com.HospitalAppointmentScheduling.Service.patientService;

@SpringBootApplication
@EnableJpaAuditing
//@ComponentScan(basePackages = { "com.HospitalAppointmentScheduling.DAO.*",
//		"com.HospitalAppointmentScheduling.Service.*" })
public class HospitalAppointmentSchedulingApplication {
	Scanner sc = new Scanner(System.in);

	@Autowired
	public patientService pService;

	@Autowired
	private ResponseHandle response;

	public static void main(String[] args) throws patientException {
		ApplicationContext ctx = SpringApplication.run(HospitalAppointmentSchedulingApplication.class, args);
		Scanner sc = new Scanner(System.in);

		HospitalAppointmentSchedulingApplication ref = ctx.getBean(HospitalAppointmentSchedulingApplication.class);
		boolean repeat = true;
		do {
			System.out.println("1. Save Patient\n2. FindByID\n3. FetchAllPatients\n4. Update Details\n5. Exit");
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
				System.out.println(ref.fetchByID(id));
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
				repeat = false;
				System.out.println("Thank you for Using the application");
				break;
			}
			case 6: {

			}
			}
		} while (repeat);

	}

	// insert method
	public void insert() throws patientException {
		Scanner sc = new Scanner(System.in);
		patientVO patient = new patientVO();

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

		response = pService.insertPatientDetails(patient);

		if (response.getId() > 0) {
			System.out.println("Your Generated Patient ID is: " + response.getId());
		} else {
			System.err.println("Failed");
		}
	}

	// fetch by ID:
	public patientVO fetchByID(long id) throws patientException {
		response = pService.fetchById(id);
		return response.getPatient();
	}

	// fetch all method:
	public void fetchAll() {
		response = pService.fetchAll();
		List<patientVO> patientlist = response.getListpatient();
		for (patientVO obj : patientlist) {
			System.out.println(obj);
		}
	}

	// update method:
	public void update(long id) throws patientException {
		response = pService.updatePatientDetails(id);
		if (response.getSucessmessage() != null) {
			System.out.println(response.getSucessmessage());
		}
	}

}
