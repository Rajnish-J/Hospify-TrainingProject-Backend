package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalAppointmentScheduling.Entity.StateVO;

@Repository
public interface StateRepo extends JpaRepository<StateVO, Integer> {

}
