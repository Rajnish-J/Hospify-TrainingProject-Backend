package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalAppointmentScheduling.Entity.AppointmentStatusVO;

@Repository
public interface AppointmentStatusRepo extends JpaRepository<AppointmentStatusVO, Integer> {
}
