import React, { Component } from "react";
import { Container, Card, Alert, Table } from "react-bootstrap";
import { UserContext } from "../Login/login.jsx";

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
    const { patientDetails, errorMessage, noResults } = this.state;
    console.log(patientDetails);

    return (
      <Container>
        <h1 className="text-center my-4">Patient Details</h1>

        <div className="mt-5">
          {patientDetails ? (
            <>
              <Card className="mb-4">
                <Card.Body>
                  <Card.Title>Patient Details</Card.Title>
                  <Card.Text>
                    <strong>Patient ID:</strong> {patientDetails.patientId}{" "}
                    <br />
                    <strong>Name:</strong> {patientDetails.firstName}{" "}
                    {patientDetails.lastName} <br />
                    <strong>Date of Birth:</strong> {patientDetails.dob} <br />
                    <strong>Phone:</strong> {patientDetails.patientPhone} <br />
                    <strong>Email:</strong> {patientDetails.patientEmail} <br />
                    <strong>Gender:</strong> {patientDetails.gender} <br />
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
                        <th>Appointment ID</th>
                        <th>Appointment Date</th>
                        <th>Reason</th>
                        <th>Doctor ID</th>
                      </tr>
                    </thead>
                    <tbody>
                      {patientDetails.appointments.map((appointment) => (
                        <tr key={appointment.appointmentID}>
                          <td>{appointment.appointmentID}</td>
                          <td>{appointment.appointmentDate}</td>
                          <td>{appointment.reason}</td>
                          <td>{appointment.doctorID || "Not Assigned"}</td>
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
