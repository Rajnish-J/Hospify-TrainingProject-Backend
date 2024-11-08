package com.HospitalAppointmentScheduling.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.HospitalAppointmentScheduling.DTO.PatientDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class ExportService {

	// Export to PDF
	public ByteArrayInputStream exportToPDF(List<PatientDTO> patient) {
		Document docs = new Document();
		ByteArrayOutputStream docsOutput = new ByteArrayOutputStream();

		try {
			PdfWriter.getInstance(docs, docsOutput);
			docs.open();
			docs.add(new Paragraph("Patient Report"));
			for (PatientDTO obj : patient) {
				docs.add(new Paragraph(obj.toString()));
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			docs.close();
		}
		return new ByteArrayInputStream(docsOutput.toByteArray());
	}

	// Export to CSV
	public ByteArrayInputStream exportToCSV(List<PatientDTO> patient) {

		StringBuilder csv = new StringBuilder("");
		for (PatientDTO obj : patient) {
			csv.append(obj.getPatientId()).append(", ").append(obj.getFirstName()).append(", ")
					.append(obj.getLastName()).append(", ").append(obj.getDob()).append(", ")
					.append(obj.getPatientPhone()).append(", ").append(obj.getPatientEmail()).append(", ")
					.append(obj.getPatientPassword()).append(", ").append(obj.getCreatedAt()).append(", ")
					.append(obj.getUpdatedAt()).append(", ").append(obj.getAppointments()).append("\n");
		}

		return new ByteArrayInputStream(csv.toString().getBytes());
	}

	// Export to Excel
	public ByteArrayInputStream exportToExcel(List<PatientDTO> patient) throws IOException {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			Sheet sheet = workbook.createSheet("Meal Details");

			// Header code
			Row headerRow = sheet.createRow(0);
			String[] headers = { "Patient ID", "First Name", "Last Name", "Date of Birth", "Phone number", "Email",
					"Password", "Date Created", "Last Updated", "appointments" };
			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
			}
			// Data rows code
			int rowIdx = 1;
			for (PatientDTO obj : patient) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(obj.getPatientId());
				row.createCell(1).setCellValue(obj.getFirstName());
				row.createCell(2).setCellValue(obj.getLastName());
				row.createCell(3).setCellValue(obj.getDob().toString());
				row.createCell(4).setCellValue(obj.getPatientPhone());
				row.createCell(5).setCellValue(obj.getPatientEmail());
				row.createCell(6).setCellValue(obj.getPatientPassword());
				row.createCell(7).setCellValue(obj.getCreatedAt());
				row.createCell(8).setCellValue(obj.getUpdatedAt());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

}
