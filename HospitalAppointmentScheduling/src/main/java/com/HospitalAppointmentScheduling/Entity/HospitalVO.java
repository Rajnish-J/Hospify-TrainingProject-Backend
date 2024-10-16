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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "hospital")
@EntityListeners(AuditingEntityListener.class)
public class HospitalVO {

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
	@ManyToOne
	@JoinColumn(name = "city_id", nullable = false)
	private CityVO city;

	// Many-to-One relationship with State
	@ManyToOne
	@JoinColumn(name = "state_id", nullable = false)
	private StateVO state;

	// Many-to-One relationship with Country
	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false)
	private CountryVO country;

	// One-to-Many relationship with Doctor
	@OneToMany(mappedBy = "hospital")
	private List<DoctorVO> doctors;

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

	public CityVO getCity() {
		return city;
	}

	public void setCity(CityVO city) {
		this.city = city;
	}

	public StateVO getState() {
		return state;
	}

	public void setState(StateVO state) {
		this.state = state;
	}

	public CountryVO getCountry() {
		return country;
	}

	public void setCountry(CountryVO country) {
		this.country = country;
	}

	public List<DoctorVO> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<DoctorVO> doctors) {
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
