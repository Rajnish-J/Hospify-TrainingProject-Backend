import React, { Component } from "react";
import { Button, Form, Container, Row, Col, Card, Alert } from "react-bootstrap";
import { useParams } from "react-router-dom";

class FindByPatientId extends Component {
  constructor(props) {
    super(props);
    this.state = {
      patientDetails: null,
      errorMessage: "",
      noResults: false,
    };
  }

  // Fetch patient data using patientId from URL
  componentDidMount() {
    const { patientId } = this.props.match.params;

    // Fetch data from API using the patientId
    this.fetchPatientDetails(patientId);
  }

  // Fetch patient details from the API
  fetchPatientDetails = (patientId) => {
    if (!patientId) {
      this.setState({ errorMessage: "Patient ID is required", noResults: false });
      return;
    }

    // Fetch patient data using the provided patientId
    fetch(`http://localhost:8080/patient/patientId/${patientId}`)
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

    return (
      <Container>
        <h1 className="text-center my-4">Patient Details</h1>

        <div className="mt-5">
          {patientDetails ? (
            <Card>
              <Card.Body>
                <Card.Title>Patient Details</Card.Title>
                <Card.Text>
                  <strong>Patient ID:</strong> {patientDetails.id} <br />
                  <strong>Name:</strong> {patientDetails.name} <br />
                  <strong>Age:</strong> {patientDetails.age} <br />
                  <strong>Gender:</strong> {patientDetails.gender} <br />
                  <strong>Condition:</strong> {patientDetails.condition} <br />
                </Card.Text>
              </Card.Body>
            </Card>
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

// Wrap component with useParams hook to fetch patientId from URL params
export default function FindByPatientIdWrapper(props) {
  const { patientId } = useParams();
  return <FindByPatientId {...props} patientId={patientId} />;
}
