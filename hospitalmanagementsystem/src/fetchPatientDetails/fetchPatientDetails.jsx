import React, { Component } from "react";
import { Container, Card, Alert, Table } from "react-bootstrap";
import { UserContext } from "../Login/login.jsx";

import "./fetchPatientDetails.css";

export default class FindByPatientId extends Component {
  static contextType = UserContext;

  constructor(props) {
    super(props);
    this.state = {
      patientDetails: null,
      errorMessage: "",
      noResults: false,
    };
  }

  componentDidMount() {
    this.fetchPatientDetails();
  }

  // Fetch patient details from the API
  fetchPatientDetails = () => {
    const patId = this.context?.patientId;

    // Fetch patient data using the provided patientId
    fetch(`http://localhost:8080/patient/patientId/${patId}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Patient not found");
        }
        return response.json();
      })
      .then((data) => {
        this.setState({
          patientDetails: data,
          errorMessage: "",
          noResults: false,
        });
      })
      .catch((error) => {
        console.error("Error fetching patient data", error);
        this.setState({
          patientDetails: null,
          errorMessage: "",
          noResults: true,
        });
      });
  };

  render() {
    const { patientDetails, noResults } = this.state;

    return (
      <Container>
        <h1 className="text-center my-4 title font">Patient Details</h1>

        <div className="mt-5">
          {patientDetails ? (
            <>
              <Card className="mb-4">
                <Card.Body>
                  <Card.Title>
                    <h2 className="font">Patient Details</h2>
                  </Card.Title>
                  <Card.Text>
                    <strong className="font">Patient ID: </strong>{" "}
                    <span className="font">{patientDetails.patientId}</span>{" "}
                    <br />
                    <strong className="font">Name: </strong>{" "}
                    <span className="font">
                      {patientDetails.firstName} {patientDetails.lastName}
                    </span>{" "}
                    <br />
                    <strong className="font">Date of Birth:</strong>{" "}
                    <span className="font">{patientDetails.dob}</span> <br />
                    <strong className="font">Phone:</strong>{" "}
                    <span className="font">{patientDetails.patientPhone}</span>{" "}
                    <br />
                    <strong className="font">Email:</strong>{" "}
                    <span className="font">{patientDetails.patientEmail}</span>{" "}
                    <br />
                    <strong className="font">Gender:</strong>{" "}
                    <span className="font">{patientDetails.gender}</span> <br />
                  </Card.Text>
                  {patientDetails.appointments &&
                    patientDetails.appointments.length === 0 && (
                      <Alert variant="info" className="mt-3">
                        No appointments available.
                      </Alert>
                    )}
                </Card.Body>
              </Card>

              {patientDetails.appointments &&
                patientDetails.appointments.length > 0 && (
                  <Table striped bordered hover>
                    <thead>
                      <tr>
                        <th className="font">Appointment ID</th>
                        <th className="font">Appointment Date</th>
                        <th className="font">Reason</th>
                        <th className="font">Doctor Name</th>
                      </tr>
                    </thead>
                    <tbody>
                      {patientDetails.appointments.map((appointment) => (
                        <tr key={appointment.appointmentID}>
                          <td className="font">{appointment.appointmentID}</td>
                          <td className="font">
                            {appointment.appointmentDate}
                          </td>
                          <td className="font">{appointment.reason}</td>
                          <td className="font">
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
            </>
          ) : noResults ? (
            <Alert variant="danger" className="mt-3">
              No Patient Details Available
            </Alert>
          ) : null}
        </div>
      </Container>
    );
  }
}
