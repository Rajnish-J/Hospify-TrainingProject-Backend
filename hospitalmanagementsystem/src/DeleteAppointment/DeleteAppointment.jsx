//  ! after validation
import React, { Component } from "react";
import {
  Container,
  Row,
  Col,
  Table,
  Button,
  Alert,
  Card,
} from "react-bootstrap";
import { UserContext } from "../Login/login.jsx";
import { useNavigate } from "react-router-dom";

class FetchAll extends Component {
  static contextType = UserContext;

  constructor(props) {
    super(props);
    this.state = {
      appointments: [],
      error: null,
    };
  }

  componentDidMount() {
    this.handleFetchAll();
  }

  // Fetch all appointments for the logged-in patient
  handleFetchAll = () => {
    const patId = this.context?.patientId;

    fetch(
      `http://localhost:8080/appointment/fetchAppointmentsForPatientID/${patId}`,
      {
        method: "GET",
        headers: { "Content-Type": "application/json" },
      }
    )
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Error: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        this.setState({ appointments: data, error: null });
        // Update React Context with fetched appointments
        this.context.setAppointments(data);
      })
      .catch((error) => {
        this.setState({ appointments: [], error: error.message });
      });
  };

  // Delete an appointment by ID
  handleDeleteAppointment = (appointmentID) => {
    fetch(`http://localhost:8080/appointment/delete/${appointmentID}`, {
      method: "DELETE",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Failed to delete appointment: ${response.status}`);
        }
        // Update state and context after successful deletion
        this.setState((prevState) => ({
          appointments: prevState.appointments.filter(
            (appointment) => appointment.appointmentID !== appointmentID
          ),
        }));

        // Update React Context
        this.context.setAppointments?.((prevAppointments) =>
          prevAppointments.filter(
            (appt) => appt.appointmentID !== appointmentID
          )
        );
      })
      .catch((error) => {
        this.setState({ error: error.message });
      });
  };

  render() {
    const { appointments, error } = this.state;

    return (
      <Container className="mt-5 font">
        <Row>
          <Col xs={12}>
            <h2 className="mb-4" style={{ color: "white" }}>
              Your Appointments
            </h2>

            <Button
              variant="primary"
              onClick={this.handleFetchAll}
              className="mb-4"
            >
              Refresh Appointments
            </Button>

            {error && (
              <Alert variant="danger" className="mt-4">
                {error}
              </Alert>
            )}

            {appointments.length === 0 && !error && (
              <Card className="mt-4 text-center">
                <Card.Body>
                  <Card.Title>No Appointments Found</Card.Title>
                </Card.Body>
              </Card>
            )}

            {appointments.length > 0 && (
              <Table striped bordered hover responsive className="mt-4">
                <thead className="table-head">
                  <tr>
                    <th>Appointment ID</th>
                    <th>Appointment Date</th>
                    <th>Reason</th>
                    <th>Doctor Name</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {appointments.map((appointment) => (
                    <tr key={appointment.appointmentID}>
                      <td>{appointment.appointmentID}</td>
                      <td>{appointment.appointmentDate}</td>
                      <td>{appointment.reason}</td>
                      <td>
                        {appointment.doctor
                          ? `${appointment.doctor.firstName} ${appointment.doctor.lastName}`
                          : "Not Assigned"}
                      </td>
                      <td>
                        <Button
                          variant="danger"
                          size="sm"
                          onClick={() =>
                            this.handleDeleteAppointment(
                              appointment.appointmentID
                            )
                          }
                        >
                          Delete
                        </Button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </Table>
            )}
          </Col>
        </Row>
      </Container>
    );
  }
}

// Wrapper Component to Use Hooks in a Class Component
function FetchAllWithNavigate() {
  const navigate = useNavigate();
  return <FetchAll navigate={navigate} />;
}

export default FetchAllWithNavigate;
