package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HospitalAppointmentScheduling.Entity.patientVO;

public interface patientRepo extends JpaRepository<patientVO, Long> {

}
