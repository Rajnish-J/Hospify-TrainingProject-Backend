import React, { Component } from "react";
import {
  Card,
  Form,
  Button,
  Table,
  Alert,
  Container,
  Row,
  Col,
} from "react-bootstrap";
import { UserContext } from "../Login/login.jsx";

export default class findApptsByDate extends Component {
  static contextType = UserContext;

  constructor(props) {
    super(props);
    this.state = {
      date: "",
      appointments: [],
      error: null,
    };
  }

  // Handle input change
  handleInputChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  };

  // Fetch appointments on button click
  fetchAppointments = () => {
    const { date } = this.state;
    const patId = this.context?.patientId;

    if (!date || !patId) {
      this.setState({
        error: "Please select a date to fetch appointments.",
        appointments: [],
      });
      return;
    }

    fetch(
      `http://localhost:8080/appointment/AppointmentsOnDate/${date}/${patId}`
    )
      .then((response) => {
        if (!response.ok) {
          return response.text().then((text) => {
            throw new Error(text);
          });
        }
        return response.json();
      })
      .then((data) => {
        this.setState({ appointments: data, error: null });
      })
      .catch((err) => {
        this.setState({ error: err.message, appointments: [] });
      });
  };

  render() {
    const { date, appointments, error } = this.state;

    return (
      <Container className="mt-5 font">
        <Row className="justify-content-center">
          <Col md={10} lg={8}>
            {/* Form Card */}
            <Card className="shadow-lg mb-4 border-0">
              <Card.Body>
                <Card.Title className="text-center mb-4 fw-bold fs-4">
                  Fetch Appointments
                </Card.Title>
                <Form>
                  <Form.Group className="mb-3">
                    <Form.Label className="fw-semibold">Select Date</Form.Label>
                    <Form.Control
                      type="date"
                      name="date"
                      value={date}
                      onChange={this.handleInputChange}
                    />
                  </Form.Group>
                  <div className="d-flex justify-content-center">
                    <Button
                      variant="primary"
                      onClick={this.fetchAppointments}
                      className="px-5"
                    >
                      Fetch Appointments
                    </Button>
                  </div>
                </Form>
              </Card.Body>
            </Card>

            {/* Error Alert */}
            {error && (
              <Alert variant="danger" className="shadow-sm text-center">
                {error}
              </Alert>
            )}

            {/* Appointments Table */}
            {appointments.length > 0 && (
              <Card className="shadow-lg border-0 mt-4">
                <Card.Body>
                  <Card.Title className="text-center mb-4 fw-bold fs-4">
                    Appointments List
                  </Card.Title>
                  <Table
                    striped
                    bordered
                    hover
                    responsive
                    className="text-center"
                  >
                    <thead>
                      <tr>
                        <th>Appointment ID</th>
                        <th>Appointment Date</th>
                        <th>Reason</th>
                        <th>Doctor Name</th>
                      </tr>
                    </thead>
                    <tbody>
                      {appointments.map((appointment, index) => (
                        <tr key={index}>
                          <td>{appointment.appointmentID}</td>
                          <td>{appointment.appointmentDate}</td>
                          <td>{appointment.reason}</td>
                          <td>
                            {appointment.doctor &&
                            appointment.doctor.firstName &&
                            appointment.doctor.lastName
                              ? `${appointment.doctor.firstName.trim()} ${appointment.doctor.lastName.trim()}`
                              : "Not Assigned"}
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </Table>
                </Card.Body>
              </Card>
            )}
          </Col>
        </Row>
      </Container>
    );
  }
}
