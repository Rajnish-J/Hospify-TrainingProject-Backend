import React, { Component } from "react";
import {
  Container,
  Row,
  Col,
  Table,
  Card,
  Button,
  Alert,
} from "react-bootstrap";

export default class FetchAll extends Component {
  constructor(props) {
    super(props);

    this.state = {
      patients: [],
      error: null,
    };
  }

  handleFetchAll = () => {
    fetch("http://localhost:8080/patient/fetchallPatient", {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Error: $(response.status)`);
        }
        return response.json();
      })
      .then((data) => {
        this.setState({ patients: data, error: null });
      })
      .catch((error) => {
        this.setState({ patients: [], error: error.message });
      });
  };

  render() {
    const { patients, error } = this.state;
    return (
      <div>
        <Container className="" mt-5>
          <Row>
            <Col xs={12}>
              <h2 className="mb-4">Patient Records</h2>

              <Button variant="primary" onClick={this.handleFetchAll}>
                Fetch All Patients
              </Button>

              {error && (
                <Alert variant="danger" className="mt-5">
                  {error}
                </Alert>
              )}

              {patients.length > 0 && (
                <Table striped bordered hover responsive className="mt-3">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Name</th>
                      <th>Age</th>
                      <th>Date Of Birth</th>
                      <th>Email</th>
                      <th>Phone</th>
                      <th>Gender</th>
                    </tr>
                  </thead>
                  <tbody>
                    {patients.map((patient) => (
                      <tr key={patient.patientID}>
                        <td>{patient.patientID}</td>
                        <td>
                          {patient.firstName} {patient.lastName}
                        </td>
                        <td>
                          {new Date().getFullYear() -
                            new Date(patient.dob).getFullYear()}
                        </td>
                        <td>{patient.dob}</td>
                        <td>{patient.patientEmail}</td>
                        <td>{patient.patientPhone}</td>
                        <td>{patient.gender}</td>
                        {/* <td>
                          Placeholder for condition
                          -
                        </td> */}
                      </tr>
                    ))}
                  </tbody>
                </Table>
              )}
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}
