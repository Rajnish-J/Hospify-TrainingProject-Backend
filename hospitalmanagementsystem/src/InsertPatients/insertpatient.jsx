import React, { Component } from "react";
import { Row, Col, Container, Form, Button, Card } from "react-bootstrap";
import "../InsertPatients/insertpatient.css";

export default class InsertPatient extends Component {
  constructor(props) {
    super(props);

    this.state = {
      patientData: {
        firstName: "",
        lastName: "",
        patientEmail: "",
        patientPassword: "",
        patientPhone: "",
        dob: "",
        gender: "",
      },
      errors: {
        firstName: "",
        lastName: "",
        patientEmail: "",
        patientPassword: "",
        patientPhone: "",
        dob: "",
        gender: "",
      },
      responseMessage: "",
      showNotification: false,
      isSuccess: false, // To distinguish between success and error
    };
  }

  handleChange = (e) => {
    const { name, value } = e.target;

    this.setState((prevState) => ({
      patientData: {
        ...prevState.patientData,
        [name]: value,
      },
      errors: {
        ...prevState.errors,
        [name]: "",
      },
    }));
  };

  handleSubmit = (e) => {
    e.preventDefault();
    const newErrors = {};

    Object.keys(this.state.patientData).forEach((key) => {
      if (!this.state.patientData[key]) {
        newErrors[key] = "This field is required";
      }
    });

    if (Object.keys(newErrors).length > 0) {
      this.setState({ errors: newErrors });
    } else {
      const payload = { ...this.state.patientData };

      fetch("http://localhost:8080/patient/insert", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
      })
        .then(async (response) => {
          if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || `Error: ${response.status}`);
          }
          return response.text(); // Backend sends a string response
        })
        .then((data) => {
          this.setState({
            responseMessage: data, // Success message from backend
            showNotification: true,
            isSuccess: true, // Indicates success
            patientData: {
              firstName: "",
              lastName: "",
              patientEmail: "",
              patientPassword: "",
              patientPhone: "",
              dob: "",
              gender: "",
            },
          });

          // Hide the notification after 5 seconds
          setTimeout(() => {
            this.setState({ showNotification: false });
          }, 5000);
        })
        .catch((error) => {
          this.setState({
            responseMessage: `Failed to create patient details: ${error.message}`,
            showNotification: true,
            isSuccess: false, // Indicates an error
          });

          // Hide the notification after 5 seconds
          setTimeout(() => {
            this.setState({ showNotification: false });
          }, 5000);
        });
    }
  };

  renderNotificationCard() {
    const { responseMessage, isSuccess } = this.state;

    return (
      <Card
        className={`mb-4 ${isSuccess ? "border-success" : "border-danger"}`}
        style={{ borderWidth: "2px" }}
      >
        <Card.Body>
          <Card.Title className={isSuccess ? "text-success" : "text-danger"}>
            {isSuccess ? "Success" : "Error"}
          </Card.Title>
          <Card.Text>{responseMessage}</Card.Text>
        </Card.Body>
      </Card>
    );
  }

  render() {
    const { patientData, errors, showNotification } = this.state;

    return (
      <Container fluid className="p-4 bg-light">
        {showNotification && this.renderNotificationCard()}

        <h3 className="mb-4 text-center">Insert Patient Details</h3>
        <Form onSubmit={this.handleSubmit}>
          <Row className="mb-3">
            <Col xs={12} md={6}>
              <Form.Group controlId="firstName">
                <Form.Label>First Name</Form.Label>
                <Form.Control
                  type="text"
                  name="firstName"
                  value={patientData.firstName || ""}
                  onChange={this.handleChange}
                  placeholder="First Name"
                />
                {errors.firstName && (
                  <Form.Text className="text-danger">{errors.firstName}</Form.Text>
                )}
              </Form.Group>
            </Col>
            <Col xs={12} md={6}>
              <Form.Group controlId="lastName">
                <Form.Label>Last Name</Form.Label>
                <Form.Control
                  type="text"
                  name="lastName"
                  value={patientData.lastName || ""}
                  onChange={this.handleChange}
                  placeholder="Last Name"
                />
                {errors.lastName && (
                  <Form.Text className="text-danger">{errors.lastName}</Form.Text>
                )}
              </Form.Group>
            </Col>
          </Row>

          <Row className="mb-3">
            <Col xs={12} md={6}>
              <Form.Group controlId="patientEmail">
                <Form.Label>Email</Form.Label>
                <Form.Control
                  type="email"
                  name="patientEmail"
                  value={patientData.patientEmail || ""}
                  onChange={this.handleChange}
                  placeholder="Email"
                />
                {errors.patientEmail && (
                  <Form.Text className="text-danger">
                    {errors.patientEmail}
                  </Form.Text>
                )}
              </Form.Group>
            </Col>

            <Col xs={12} md={6}>
              <Form.Group controlId="patientPhone">
                <Form.Label>Phone Number</Form.Label>
                <Form.Control
                  type="text"
                  name="patientPhone"
                  value={patientData.patientPhone || ""}
                  onChange={this.handleChange}
                  placeholder="Phone Number"
                />
                {errors.patientPhone && (
                  <Form.Text className="text-danger">
                    {errors.patientPhone}
                  </Form.Text>
                )}
              </Form.Group>
            </Col>
          </Row>

          <Row className="mb-3">
            <Col xs={12} md={6}>
              <Form.Group controlId="dob">
                <Form.Label>Date of Birth</Form.Label>
                <Form.Control
                  type="date"
                  name="dob"
                  value={patientData.dob || ""}
                  onChange={this.handleChange}
                />
                {errors.dob && (
                  <Form.Text className="text-danger">{errors.dob}</Form.Text>
                )}
              </Form.Group>
            </Col>

            <Col xs={12} md={6}>
              <Form.Group controlId="gender">
                <Form.Label>Gender</Form.Label>
                <Form.Control
                  as="select"
                  name="gender"
                  value={patientData.gender || ""}
                  onChange={this.handleChange}
                >
                  <option value="">Select Gender</option>
                  <option value="Male">Male</option>
                  <option value="Female">Female</option>
                  <option value="Other">Other</option>
                </Form.Control>
                {errors.gender && (
                  <Form.Text className="text-danger">{errors.gender}</Form.Text>
                )}
              </Form.Group>
            </Col>
          </Row>

          <Row className="mb-3">
            <Col xs={12} md={6}>
              <Form.Group controlId="patientPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  type="password"
                  name="patientPassword"
                  value={patientData.patientPassword || ""}
                  onChange={this.handleChange}
                  placeholder="Password"
                />
                {errors.patientPassword && (
                  <Form.Text className="text-danger">
                    {errors.patientPassword}
                  </Form.Text>
                )}
              </Form.Group>
            </Col>
          </Row>

          <Button variant="primary" type="submit">
            Submit
          </Button>
        </Form>
      </Container>
    );
  }
}
