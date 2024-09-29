package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalAppointmentScheduling.Entity.cityVO;

@Repository
public interface cityRepo extends JpaRepository<cityVO, Integer> {

}
