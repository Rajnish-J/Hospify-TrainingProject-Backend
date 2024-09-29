package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HospitalAppointmentScheduling.Entity.cityVO;

public interface cityRepo extends JpaRepository<cityVO, Integer> {

}
