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

export default class fetchall extends Component {
  constructor(props) {
    super(props);

    this.state = {
      patients: [],
      error: null,
    };
  }

  // * hardcoded patient details for checking purpose
  allPatients = [
    {
      id: 1,
      name: "Rajnish",
      age: 22,
      dob: "07-10-2002",
      email: "rajnish@gmail.com",
      phone: 9176960600,
      gender: "Male",
      condition: "Eye check up",
    },
    {
      id: 2,
      name: "srimathi",
      age: 22,
      dob: "15-11-2002",
      email: "sri@gmail.com",
      phone: 9962577559,
      gender: "Female",
      condition: "back pain",
    },
    {
      id: 3,
      name: "sabari",
      age: 25,
      dob: "17-12-2002",
      email: "sabari@gmail.com",
      phone: 9569874123,
      gender: "Male",
      condition: "Diabetes",
    },
    {
      id: 4,
      name: "sanjay",
      age: 35,
      dob: "21-08-2002",
      email: "san@gmail.com",
      phone: 8521463975,
      gender: "Male",
      condition: "Asthma",
    },
    {
      id: 5,
      name: "Raghul",
      age: 29,
      dob: "12-08-2002",
      email: "ragul@gmail.com",
      phone: 6325894107,
      gender: "Male",
      condition: "procrastination",
    },
  ];

  // * handling method : fetching all the patients...
  handleFetchAll = () => {
    if (this.allPatients.length > 0) {
      this.setState({ patients: this.allPatients, error: null });
    } else {
      this.setState({ patients: null, error: "No patient records found" });
    }
  };

  render() {
    const { patients, error } = this.state;
    return (
      <div>
        {/* Container tag */}
        <Container className="mt-5">
          {/* consits single row */}
          <Row>
            {/* consits single column */}
            <Col xs={12}>
              {/* shows patient records text in the page */}
              <h2 className="mb-4"> Patient records</h2>

              {/* button : if we click this button fetches all the patient details */}
              <Button variant="primary" onClick={this.handleFetchAll}>
                Fetch All Patients
              </Button>

              {/* if any error pops up it shows in the webApp */}
              {error && (
                <Alert variant="danger" className="mt-3">
                  {error}
                </Alert>
              )}

              {/* if the patient details available it shows the details in the table format */}
              {patients.length > 0 && (
                <Table striped bordered hover responsive className="mt-3">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Name</th>
                      <th>Age</th>
                      <th>Date of birth</th>
                      <th>email</th>
                      <th>Phone</th>
                      <th>Gender</th>
                      <th>Condition</th>
                    </tr>
                  </thead>
                  <tbody>
                    {patients.map((patient) => (
                      <tr key={patient.id}>
                        <td>{patient.id}</td>
                        <td>{patient.name}</td>
                        <td>{patient.age}</td>
                        <td>{patient.dob}</td>
                        <td>{patient.email}</td>
                        <td>{patient.phone}</td>
                        <td>{patient.gender}</td>
                        <td>{patient.condition}</td>
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
