package com.HospitalAppointmentScheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.HospitalAppointmentScheduling.Service.patientService;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages = { "com.HospitalAppointmentScheduling.DAO" })
public class HospitalAppointmentSchedulingApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(HospitalAppointmentSchedulingApplication.class, args);

		patientService pService = ctx.getBean(patientService.class);
	}

}
