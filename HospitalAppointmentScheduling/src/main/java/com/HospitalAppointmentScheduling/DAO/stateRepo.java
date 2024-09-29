package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalAppointmentScheduling.Entity.stateVO;

@Repository
public interface stateRepo extends JpaRepository<stateVO, Integer> {

}
