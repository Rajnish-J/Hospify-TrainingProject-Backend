import React, { Component } from "react";
import { Container, Card, Alert } from "react-bootstrap";
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
                </Card.Body>
              </Card>
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
