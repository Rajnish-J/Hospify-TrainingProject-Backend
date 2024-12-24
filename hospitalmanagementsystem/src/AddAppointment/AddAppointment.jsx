// ! after validations
import React, { Component } from "react";
import { Container, Form, Button, Card, Row, Col } from "react-bootstrap";
import { UserContext } from "../Login/login.jsx";
import "./AddAppointment.css";

export default class AddAppointment extends Component {
  static contextType = UserContext;

  constructor(props) {
    super(props);
    this.state = {
      appointmentDate: "",
      reason: "",
      selectedDoctor: "",
      errorMessage: "",
      successMessage: "",
      doctors: [
        { doctorId: 1, name: "Dr. P. Raghu - General" },
        { doctorId: 2, name: "Dr. R. K. K. Sharma - Dentist" },
        { doctorId: 3, name: "Dr. K. M. Cherian - Cardio" },
        { doctorId: 4, name: "Dr. M. S. V. K. Murthy - Neurology" },
        { doctorId: 5, name: "Dr. T. R. Ramachandran - Pediatrics" },
      ],
      formErrors: {
        appointmentDate: "",
        reason: "",
        selectedDoctor: "",
      },
    };
  }

  handleChange = (e) => {
    const { name, value } = e.target;
    this.setState({ [name]: value });
  };

  validateInputs = () => {
    const { appointmentDate, reason, selectedDoctor } = this.state;
    let formErrors = { appointmentDate: "", reason: "", selectedDoctor: "" };

    // Check if appointment date is not in the past
    if (!appointmentDate) {
      formErrors.appointmentDate = "Appointment date is required.";
    } else {
      const selectedDate = new Date(appointmentDate);
      const currentDate = new Date();
      // set current date on the runtime for the validation
      currentDate.setHours(0, 0, 0, 0);
      if (selectedDate < currentDate) {
        formErrors.appointmentDate = "Appointment date cannot be in the past.";
      }
    }

    // Check reason length
    if (!reason) {
      formErrors.reason = "Reason is required.";
    } else if (reason.length > 50) {
      formErrors.reason = "Reason must be less than 50 characters.";
    }

    // Check if doctor is selected
    if (!selectedDoctor) {
      formErrors.selectedDoctor = "Please select a doctor.";
    }

    this.setState({ formErrors });
    return !Object.values(formErrors).some((error) => error !== "");
  };

  handleSubmit = (e) => {
    e.preventDefault();

    const { appointmentDate, reason, selectedDoctor } = this.state;
    const patient = this.context;

    // Validate input fields
    if (!this.validateInputs()) {
      return;
    }

    // Check the number of appointments for the selected date
    const apiUrl = `http://localhost:8080/appointment/countOfAppointmentsByDate/${appointmentDate}`;
    fetch(apiUrl)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to fetch appointment count.");
        }
        return response.json();
      })
      .then((count) => {
        if (count >= 5) {
          this.setState({
            errorMessage:
              "All appointments are booked for this day already. Please select another convenient day for consulting doctors.",
            successMessage: "",
          });
        } else {
          // If count is less than or equal to 5, proceed with booking the appointment
          const requestBody = {
            appointment: {
              appointmentDate,
              reason,
            },
            doctor: {
              doctorId: parseInt(selectedDoctor, 10),
            },
            patient: {
              patientId: patient.patientId,
            },
          };

          fetch("http://localhost:8080/appointment/insertWithPatientID", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(requestBody),
          })
            .then((response) => response.text())
            .then((data) => {
              if (data.startsWith("Appointments Details successfully saved")) {
                const successMessage = data;
                this.setState({
                  successMessage,
                  errorMessage: "",
                });

                // Update the context with the new appointment
                const updatedPatient = {
                  ...patient,
                  appointments: [
                    ...(patient.appointments || []),
                    {
                      appointmentDate,
                      reason,
                      doctorId: selectedDoctor,
                    },
                  ],
                };

                this.context.updatePatientContext(updatedPatient);

                // Reset the form fields
                this.setState({
                  appointmentDate: "",
                  reason: "",
                  selectedDoctor: "",
                });
              } else {
                this.setState({
                  errorMessage: "Failed to book appointment.",
                  successMessage: "",
                });
              }
            })
            .catch(() => {
              this.setState({
                errorMessage: "Error booking appointment.",
                successMessage: "",
              });
            });
        }
      })
      .catch(() => {
        this.setState({
          errorMessage: "Error checking appointment availability.",
          successMessage: "",
        });
      });
  };

  render() {
    const {
      appointmentDate,
      reason,
      selectedDoctor,
      doctors,
      errorMessage,
      successMessage,
      formErrors,
    } = this.state;

    return (
      <Container
        fluid
        className="d-flex justify-content-center align-items-center"
        style={{ minHeight: "90vh" }}
      >
        <Row className="w-100 justify-content-center">
          <Col xs={12} md={6} lg={4}>
            <Card className="shadow-lg p-4">
              <Card.Body>
                <h3 className="text-center mb-4 font">Book Appointment</h3>
                {errorMessage && (
                  <div className="text-danger mb-3 text-center">
                    {errorMessage}
                  </div>
                )}
                {successMessage && (
                  <div className="text-success mb-3 text-center">
                    {successMessage}
                  </div>
                )}
                <Form onSubmit={this.handleSubmit}>
                  <Form.Group controlId="appointmentDate" className="mb-3">
                    <Form.Label className="font">Appointment Date</Form.Label>
                    <Form.Control
                      type="date"
                      name="appointmentDate"
                      value={appointmentDate}
                      onChange={this.handleChange}
                      isInvalid={!!formErrors.appointmentDate}
                    />
                    <Form.Control.Feedback type="invalid">
                      {formErrors.appointmentDate}
                    </Form.Control.Feedback>
                  </Form.Group>
                  <Form.Group controlId="reason" className="mb-3">
                    <Form.Label className="font">Reason</Form.Label>
                    <Form.Control
                      type="text"
                      placeholder="Enter reason"
                      name="reason"
                      value={reason}
                      onChange={this.handleChange}
                      isInvalid={!!formErrors.reason}
                    />
                    <Form.Control.Feedback type="invalid">
                      {formErrors.reason}
                    </Form.Control.Feedback>
                  </Form.Group>
                  <Form.Group controlId="doctor" className="mb-3">
                    <Form.Label className="font">Select Doctor</Form.Label>
                    <Form.Control
                      as="select"
                      name="selectedDoctor"
                      value={selectedDoctor}
                      onChange={this.handleChange}
                      isInvalid={!!formErrors.selectedDoctor}
                    >
                      <option value="">Select a doctor</option>
                      {doctors.map((doctor) => (
                        <option key={doctor.doctorId} value={doctor.doctorId}>
                          {doctor.name}
                        </option>
                      ))}
                    </Form.Control>
                    <Form.Control.Feedback type="invalid">
                      {formErrors.selectedDoctor}
                    </Form.Control.Feedback>
                  </Form.Group>
                  <Button variant="primary" type="submit" className="w-100 font">
                    Book Appointment
                  </Button>
                </Form>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    );
  }
}
