package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HospitalAppointmentScheduling.Entity.specializationVO;

public interface specializationRepo extends JpaRepository<specializationVO, Integer> {

}
