package com.HospitalAppointmentScheduling.Entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "hospital")
@EntityListeners(AuditingEntityListener.class)
public class hospitalVO {

	@Id
	@Column(name = "hospital_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hospitalId;

	@Column(name = "hospital_name", nullable = false)
	private String hospitalName;

	@Column(name = "hospital_phone", nullable = false, unique = true)
	private String hospitalPhone;

	@Column(name = "hospital_email", nullable = false, unique = true)
	private String hospitalEmail;

	@CreatedDate
	@Column(name = "createdAt", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "updatedAt", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;

	// mapping:
	// Many-to-One relationship with City
	@OneToOne
	@JoinColumn(name = "city_id", nullable = false)
	private cityVO city;

	// Many-to-One relationship with State
	@OneToOne
	@JoinColumn(name = "state_id", nullable = false)
	private stateVO state;

	// Many-to-One relationship with Country
	@OneToOne
	@JoinColumn(name = "country_id", nullable = false)
	private countryVO country;

	// One-to-Many relationship with Doctor
	@OneToMany(mappedBy = "hospital")
	private List<doctorVO> doctors;

	// Getters and Setters methods:
	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalPhone() {
		return hospitalPhone;
	}

	public void setHospitalPhone(String hospitalPhone) {
		this.hospitalPhone = hospitalPhone;
	}

	public String getHospitalEmail() {
		return hospitalEmail;
	}

	public void setHospitalEmail(String hospitalEmail) {
		this.hospitalEmail = hospitalEmail;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public cityVO getCity() {
		return city;
	}

	public void setCity(cityVO city) {
		this.city = city;
	}

	public stateVO getState() {
		return state;
	}

	public void setState(stateVO state) {
		this.state = state;
	}

	public countryVO getCountry() {
		return country;
	}

	public void setCountry(countryVO country) {
		this.country = country;
	}

	public List<doctorVO> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<doctorVO> doctors) {
		this.doctors = doctors;
	}

	@Override
	public String toString() {
		return "hospitalVO [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", hospitalPhone="
				+ hospitalPhone + ", hospitalEmail=" + hospitalEmail + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", city=" + city + ", state=" + state + ", country=" + country + ", doctors=" + doctors
				+ "]";
	}

}
