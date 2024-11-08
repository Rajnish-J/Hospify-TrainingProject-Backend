package com.HospitalAppointmentScheduling.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalAppointmentScheduling.DTO.PatientDTO;
import com.HospitalAppointmentScheduling.Entity.PatientVO;
import com.HospitalAppointmentScheduling.Service.ExportService;
import com.HospitalAppointmentScheduling.Service.PatientService;

@RestController
@RequestMapping("/export")
public class ExportController {

	@Autowired
	private PatientService pSer;

	@Autowired
	private ExportService eSer;

	@GetMapping("/PatientExport")
	public ResponseEntity<?> exportPatientDetails(@RequestParam String format) throws IOException {
		List<PatientVO> patient = pSer.fetchAll().getListPatient();
		List<PatientDTO> patientDTO = new ArrayList();
		for (PatientVO details : patient) {
			PatientDTO mealdetail = mapToDTO(details);
			patientDTO.add(mealdetail);
		}

		ByteArrayInputStream fileInputStream;
		String fileName;

		switch (format.toLowerCase()) {
		case "csv":
			fileInputStream = eSer.exportToCSV(patientDTO);
			fileName = "patient-details.csv";
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
					.contentType(MediaType.parseMediaType("text/csv")).body(new InputStreamResource(fileInputStream));

		case "excel":
			fileInputStream = eSer.exportToExcel(patientDTO);
			fileName = "patient-details.xlsx";
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
					.contentType(MediaType
							.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
					.body(new InputStreamResource(fileInputStream));

		case "pdf":
			fileInputStream = eSer.exportToPDF(patientDTO);
			fileName = "patient-details.pdf";
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
					.contentType(MediaType.parseMediaType("application/pdf"))
					.body(new InputStreamResource(fileInputStream));

		default:
			return ResponseEntity.badRequest().body("Invalid format. Supported formats: CSV, Excel, PDF");
		}
	}

	// own method => converts entity to DTO:
	public static PatientDTO mapToDTO(PatientVO vo) {

		PatientDTO dto = new PatientDTO();
		dto.setFirstName(vo.getFirstName());
		dto.setLastName(vo.getLastName());
		dto.setPatientEmail(vo.getPatientEmail());
		dto.setPatientPassword(vo.getPatientPassword());
		dto.setPatientPhone(vo.getPatientPhone());
		dto.setDob(vo.getDob());
		dto.setUpdatedAt(vo.getUpdatedAt());
		dto.setCreatedAt(vo.getCreatedAt());
		dto.setPatientId(vo.getPatientId());

		return dto;

	}
}
