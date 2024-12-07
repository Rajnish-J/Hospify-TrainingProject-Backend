//package com.HospitalAppointmentScheduling.Controller;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.HospitalAppointmentScheduling.DTO.PatientDTO;
//import com.HospitalAppointmentScheduling.Entity.PatientVO;
//import com.HospitalAppointmentScheduling.Service.ExportService;
//import com.HospitalAppointmentScheduling.Service.PatientService;
//
//@RestController
//@RequestMapping("/export")
//public class ExportController {
//
//	@Autowired
//	private PatientService pSer;
//
//	@Autowired
//	private ExportService eSer;
//
//	@GetMapping("/PatientExport")
//	public ResponseEntity<?> exportPatientDetails(@RequestParam String format) throws IOException {
//		List<PatientVO> patient = pSer.fetchAll().getListPatient();
//		List<PatientDTO> patientDTO = new ArrayList();
//		for (PatientVO details : patient) {
//			PatientDTO mealdetail = mapToDTO(details);
//			patientDTO.add(mealdetail);
//		}
//
//		ByteArrayInputStream fileInputStream;
//		String fileName;
//
//		switch (format.toLowerCase()) {
//		case "csv":
//			fileInputStream = eSer.exportToCSV(patientDTO);
//			fileName = "patient-details.csv";
//			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
//					.contentType(MediaType.parseMediaType("text/csv")).body(new InputStreamResource(fileInputStream));
//
//		case "excel":
//			fileInputStream = eSer.exportToExcel(patientDTO);
//			fileName = "patient-details.xlsx";
//			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
//					.contentType(MediaType
//							.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
//					.body(new InputStreamResource(fileInputStream));
//
//		case "pdf":
//			fileInputStream = eSer.exportToPDF(patientDTO);
//			fileName = "patient-details.pdf";
//			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
//					.contentType(MediaType.parseMediaType("application/pdf"))
//					.body(new InputStreamResource(fileInputStream));
//
//		default:
//			return ResponseEntity.badRequest().body("Invalid format. Supported formats: CSV, Excel, PDF");
//		}
//	}
//
//	// own method => converts entity to DTO:
//	public static PatientDTO mapToDTO(PatientVO vo) {
//
//		PatientDTO dto = new PatientDTO();
//		dto.setFirstName(vo.getFirstName());
//		dto.setLastName(vo.getLastName());
//		dto.setPatientEmail(vo.getPatientEmail());
//		dto.setPatientPassword(vo.getPatientPassword());
//		dto.setPatientPhone(vo.getPatientPhone());
//		dto.setDob(vo.getDob());
//		dto.setUpdatedAt(vo.getUpdatedAt());
//		dto.setCreatedAt(vo.getCreatedAt());
//		dto.setPatientId(vo.getPatientId());
//
//		return dto;
//
//	}
//}

package com.HospitalAppointmentScheduling.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalAppointmentScheduling.CustomExceptions.IdException;
import com.HospitalAppointmentScheduling.DTO.AppointmentDTO;
import com.HospitalAppointmentScheduling.Entity.AppointmentsVO;
import com.HospitalAppointmentScheduling.Entity.DoctorVO;
import com.HospitalAppointmentScheduling.Service.AppointmentsService;
import com.HospitalAppointmentScheduling.Service.ExportService;

@RestController
@RequestMapping("/export")
public class ExportController {

	@Autowired
	private AppointmentsService aService;

	@Autowired
	private ExportService exportService;

	@GetMapping("/AppointmentExport/{id}")
	public ResponseEntity<?> exportAppointmentsForPatient(@PathVariable long id, @RequestParam String format)
			throws IOException {
		try {
			// Fetch appointments for the given patient ID
			List<AppointmentsVO> appointments = aService.findAllApptByPatientId(id).getList();
			if (appointments == null || appointments.isEmpty()) {
				return ResponseEntity.ok("No appointments found for the given patient ID.");
			}

			// Convert AppointmentsVO to DTO (if needed)
			List<AppointmentDTO> appointmentDTOs = new ArrayList<>();
			for (AppointmentsVO vo : appointments) {
				appointmentDTOs.add(mapToDTO(vo));
			}

			// Export the data in the specified format
			ByteArrayInputStream fileInputStream;
			String fileName;

			switch (format.toLowerCase()) {
			case "csv":
				fileInputStream = exportService.exportToCSV(appointmentDTOs);
				fileName = "appointment-details.csv";
				return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
						.contentType(MediaType.parseMediaType("text/csv"))
						.body(new InputStreamResource(fileInputStream));

			case "excel":
				fileInputStream = exportService.exportToExcel(appointmentDTOs);
				fileName = "appointment-details.xlsx";
				return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
						.contentType(MediaType
								.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
						.body(new InputStreamResource(fileInputStream));

			case "pdf":
				fileInputStream = exportService.exportToPDF(appointmentDTOs);
				fileName = "appointment-details.pdf";
				return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
						.contentType(MediaType.parseMediaType("application/pdf"))
						.body(new InputStreamResource(fileInputStream));

			default:
				return ResponseEntity.badRequest().body("Invalid format. Supported formats: CSV, Excel, PDF");
			}
		} catch (IdException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
	}

	// Utility method to map AppointmentsVO to AppointmentDTO
	private AppointmentDTO mapToDTO(AppointmentsVO vo) {
		AppointmentDTO dto = new AppointmentDTO();
		dto.setAppointmentDate(vo.getAppointmentDate());
		dto.setAppointmentID(vo.getAppointmentID());
		dto.setDoctorID(vo.getDoctor().getDoctorId());
		dto.setReason(vo.getReason());

		DoctorVO docVO = new DoctorVO();
		docVO.setDoctorId(vo.getDoctor().getDoctorId());
		docVO.setFirstName(vo.getDoctor().getFirstName());
		docVO.setLastName(vo.getDoctor().getLastName());

		dto.setDoctor(docVO);

		return dto;
	}
}
