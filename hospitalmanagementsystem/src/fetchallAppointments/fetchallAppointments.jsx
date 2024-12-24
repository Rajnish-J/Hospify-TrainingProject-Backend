// ! after validations
import React, { Component } from "react";
import {
  Container,
  Row,
  Col,
  Table,
  Button,
  Card,
  Modal,
  Form,
} from "react-bootstrap";
import { UserContext } from "../Login/login.jsx";
import { useNavigate } from "react-router-dom";

import "../fetchallAppointments/fetchAllAppointments.css";

class FetchAll extends Component {
  static contextType = UserContext;

  constructor(props) {
    super(props);
    this.state = {
      appointments: [],
      error: null,
      exportFormat: "excel",
      showModal: false,
      modalMessage: "",
    };
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
      })
      .catch((error) => {
        this.setState({ appointments: [], error: error.message });
        this.showModal("Failed to fetch appointments: " + error.message);
      });
  };

  // Handle Export Logic
  handleExport = () => {
    const patId = this.context?.patientId;
    const { exportFormat } = this.state;

    fetch(
      `http://localhost:8080/export/AppointmentExport/${patId}?format=${exportFormat}`,
      {
        method: "GET",
      }
    )
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Error: ${response.status}`);
        }
        return response.blob(); // To handle file download
      })
      .then((blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement("a");
        a.href = url;

        const extension =
          exportFormat === "excel" ? "xlsx" : exportFormat.toLowerCase();
        a.download = `appointments.${extension}`;
        a.click();
        window.URL.revokeObjectURL(url);

        this.showModal(
          `Appointments exported successfully as ${exportFormat.toUpperCase()}!`
        );
      })
      .catch((error) => {
        this.showModal(`Export failed: ${error.message}`);
      });
  };

  // Modal Control
  showModal = (message) => {
    this.setState({ showModal: true, modalMessage: message });
  };

  closeModal = () => {
    this.setState({ showModal: false, modalMessage: "" });
  };

  componentDidMount() {
    this.handleFetchAll();
  }

  render() {
    const { appointments, error, showModal, modalMessage } = this.state;
    const { navigate } = this.props;

    return (
      <Container className="mt-5">
        <Row>
          <Col xs={12}>
            <h2 className="mb-4 title font">Your Appointments</h2>

            {/* Conditional rendering based on appointments */}
            {appointments.length === 0 && !error && (
              <Card className="mt-4 text-center">
                <Card.Body>
                  <Card.Title className="font">
                    No Appointments Found
                  </Card.Title>
                  <Button
                    className="font"
                    variant="primary"
                    onClick={() => navigate("/AddAppointments")}
                  >
                    Add Appointment
                  </Button>
                </Card.Body>
              </Card>
            )}

            {appointments.length > 0 && (
              <>
                <Table striped bordered hover responsive className="mt-4">
                  <thead className="font">
                    <tr>
                      <th>Appointment ID</th>
                      <th>Appointment Date</th>
                      <th>Reason</th>
                      <th>Doctor Name</th>
                    </tr>
                  </thead>
                  <tbody className="font">
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
                      </tr>
                    ))}
                  </tbody>
                </Table>

                {/* Export Section in Card */}
                <Card className="mt-4" style={{margin: '0 auto', maxWidth: '700px', width: '100%'}}>
                  <Card.Body>
                    <Row className="align-items-center">
                      <Col xs={12} md={6} style={{width: "300px"}}>
                        <Form.Group controlId="exportFormat">
                          <Form.Label className="font">
                            Select Export Format:
                          </Form.Label>
                          <Form.Control
                            as="select"
                            value={this.state.exportFormat}
                            onChange={(e) =>
                              this.setState({ exportFormat: e.target.value })
                            }
                            className="font"
                          >
                            <option value="pdf">PDF</option>
                            <option value="csv">CSV</option>
                            <option value="excel">Excel</option>
                          </Form.Control>
                        </Form.Group>
                      </Col>
                      <Col xs={12} md={6} className="text-md mt-3 mt-md-0">
                        <Button
                          className="font"
                          variant="success"
                          onClick={this.handleExport}
                          style={{ marginTop: "35px"}}
                        >
                          Export Appointments
                        </Button>
                      </Col>
                    </Row>
                  </Card.Body>
                </Card>
              </>
            )}
          </Col>
        </Row>

        {/* Modal for notifications */}
        <Modal show={showModal} onHide={this.closeModal}>
          <Modal.Header closeButton>
            <Modal.Title>Notification</Modal.Title>
          </Modal.Header>
          <Modal.Body>{modalMessage}</Modal.Body>
          <Modal.Footer>
            <Button variant="primary" onClick={this.closeModal}>
              Close
            </Button>
          </Modal.Footer>
        </Modal>
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
