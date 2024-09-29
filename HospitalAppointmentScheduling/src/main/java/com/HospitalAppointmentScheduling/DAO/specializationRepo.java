package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalAppointmentScheduling.Entity.specializationVO;

@Repository
public interface specializationRepo extends JpaRepository<specializationVO, Integer> {

}
