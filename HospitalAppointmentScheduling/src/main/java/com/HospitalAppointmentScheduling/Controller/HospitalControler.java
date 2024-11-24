package com.HospitalAppointmentScheduling.Controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalAppointmentScheduling.DTO.CityDTO;
import com.HospitalAppointmentScheduling.DTO.CountryDTO;
import com.HospitalAppointmentScheduling.DTO.DoctorDTO;
import com.HospitalAppointmentScheduling.DTO.HospitalDTO;
import com.HospitalAppointmentScheduling.DTO.StateDTO;
import com.HospitalAppointmentScheduling.Entity.HospitalVO;
import com.HospitalAppointmentScheduling.Response.ResponseHandleHospital;
import com.HospitalAppointmentScheduling.Service.HospitalService;

@RestController
@RequestMapping("/admin")
public class HospitalControler {

	@Autowired
	private HospitalService serHos;

	Logger log = Logger.getLogger(HospitalControler.class);

	@PostMapping("/adminLogin")
	public ResponseEntity<?> loginAdmin(@RequestBody Map<String, String> admin) {
		log.info("Patient Login method triggered in the controller layer");
		String hosEmail = admin.get("hospitalEmail");
		String hospitalPhone = admin.get("hospitalPhone");

		ResponseHandleHospital res = serHos.hospitalAuthentication(hosEmail, hospitalPhone);

		if (res.getSucessMessage() != null && res.getHospital() != null) {
			log.info("patient account available in the database");
			return ResponseEntity.ok(mapToDTO(res.getHospital()));
		} else {
			log.error("patient account does not available in the database");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(res.getFailureMessage() != null ? res.getFailureMessage() : "Invalid credentials");
		}
	}

	public static HospitalDTO mapToDTO(HospitalVO hospitalVO) {
		HospitalDTO dto = new HospitalDTO();
		dto.setHospitalId(hospitalVO.getHospitalId());
		dto.setHospitalName(hospitalVO.getHospitalName());
		dto.setHospitalPhone(hospitalVO.getHospitalPhone());
		dto.setHospitalEmail(hospitalVO.getHospitalEmail());
		dto.setCreatedAt(hospitalVO.getCreatedAt());
		dto.setUpdatedAt(hospitalVO.getUpdatedAt());

		// Map City
		if (hospitalVO.getCity() != null) {
			CityDTO cityDTO = new CityDTO();
			cityDTO.setCityId(hospitalVO.getCity().getCityId());
			cityDTO.setCity(hospitalVO.getCity().getCity());
			dto.setCity(cityDTO);
		}

		// Map State
		if (hospitalVO.getState() != null) {
			StateDTO stateDTO = new StateDTO();
			stateDTO.setStateId(hospitalVO.getState().getStateId());
			stateDTO.setState(hospitalVO.getState().getState());
			dto.setState(stateDTO);
		}

		// Map Country
		if (hospitalVO.getCountry() != null) {
			CountryDTO countryDTO = new CountryDTO();
			countryDTO.setCountryId(hospitalVO.getCountry().getCountryId());
			countryDTO.setCountry(hospitalVO.getCountry().getCountry());
			dto.setCountry(countryDTO);
		}

		// Map Doctors
		if (hospitalVO.getDoctors() != null) {
			List<DoctorDTO> doctorDTOs = hospitalVO.getDoctors().stream().map(doctor -> {
				DoctorDTO doctorDTO = new DoctorDTO();
				doctorDTO.setDoctorId(doctor.getDoctorId());
				doctorDTO.setFirstName(doctor.getFirstName());
				doctorDTO.setLastName(doctor.getLastName());
				doctorDTO.setDoctorPassword(doctor.getDoctorPassword());
				doctorDTO.setDoctorPhone(doctor.getDoctorPhone());
				doctorDTO.setDoctorEmail(doctor.getDoctorEmail());
				doctorDTO.setHospitalId(doctor.getHospital().getHospitalId());

				// Map Specialization
				if (doctor.getSpecialization() != null) {
					doctorDTO.setSpecilizationId(doctor.getSpecialization().getSpecializationId());
				}

				return doctorDTO;
			}).toList();
			dto.setDoctors(doctorDTOs);
		}

		return dto;
	}

}
