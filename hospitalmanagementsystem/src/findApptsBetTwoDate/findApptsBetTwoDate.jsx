import React, { Component } from "react";
import {
  Table,
  Card,
  Form,
  Button,
  Spinner,
  Container,
  Row,
  Col,
} from "react-bootstrap";
import { UserContext } from "../Login/login.jsx";

export default class findApptsBetTwoDate extends Component {
  static contextType = UserContext;
  constructor(props) {
    super(props);
    this.state = {
      startDate: "",
      endDate: "",
      appointments: [],
      error: "",
      loading: false,
    };
  }

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  fetchAppointments = () => {
    const { startDate, endDate } = this.state;
    const patId = this.context?.patientId;

    if (!startDate || !endDate) {
      this.setState({ error: "Please enter both start and end dates." });
      return;
    }

    const apiUrl = `http://localhost:8080/appointment/AppointmentDetailsAmongTwoDate/${startDate}/${endDate}/${patId}`;

    this.setState({ loading: true, error: "", appointments: [] });

    fetch(apiUrl)
      .then((response) => {
        if (!response.ok) {
          return response.text().then((errorMessage) => {
            throw new Error(errorMessage);
          });
        }
        return response.json();
      })
      .then((data) => {
        console.log(data);
        this.setState({ appointments: data, error: "" });
      })
      .catch((error) => {
        this.setState({ error: error.message });
      })
      .finally(() => {
        this.setState({ loading: false });
      });
  };

  render() {
    const { startDate, endDate, appointments, error, loading } = this.state;

    return (
      <Container className="font">
        <Row className="justify-content-md-center mt-4">
          <Col md={8}>
            <Card>
              <Card.Body>
                <Card.Title className="text-center mb-4 fw-bold fs-4">
                  Fetch Appointment Details
                </Card.Title>
                <Form>
                  <Form.Group controlId="startDate">
                    <Form.Label>Start Date</Form.Label>
                    <Form.Control
                      type="date"
                      name="startDate"
                      value={startDate}
                      onChange={this.handleInputChange}
                    />
                  </Form.Group>

                  <Form.Group controlId="endDate" className="mt-3">
                    <Form.Label>End Date</Form.Label>
                    <Form.Control
                      type="date"
                      name="endDate"
                      value={endDate}
                      onChange={this.handleInputChange}
                    />
                  </Form.Group>

                  <div className="d-flex justify-content-center">
                    <Button
                      variant="primary"
                      onClick={this.fetchAppointments}
                      className="px-5 mt-3"
                    >
                      {loading ? (
                        <Spinner animation="border" size="sm" />
                      ) : (
                        "Fetch Appointments"
                      )}
                    </Button>
                  </div>
                </Form>

                {error && (
                  <Card className="mt-4" bg="danger" text="white">
                    <Card.Body>{error}</Card.Body>
                  </Card>
                )}

                {appointments.length > 0 && (
                  <Table striped bordered hover className="mt-4">
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
                )}
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    );
  }
}
