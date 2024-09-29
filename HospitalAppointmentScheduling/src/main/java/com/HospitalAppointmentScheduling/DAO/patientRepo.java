package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalAppointmentScheduling.Entity.patientVO;

@Repository
public interface patientRepo extends JpaRepository<patientVO, Long> {

}
