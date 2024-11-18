import React, { Component } from "react";
import { Container, Form, Row, Col, Button } from "react-bootstrap";

export default class InsertPatient extends Component {
  constructor(props) {
    super(props);
    this.state = {
      patientData: {
        firstName: "",
        lastName: "",
        patientPhone: "",
        dob: "",
        patientEmail: "",
        patientPassword: "",
      },
      errors: {
        firstName: "",
        lastName: "",
        patientPhone: "",
        dob: "",
        patientEmail: "",
        patientPassword: "",
      },
    };

    // Bind methods
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  // Handle input changes
  handleChange(e) {
    const { name, value } = e.target;
    const { patientData, errors } = this.state;

    // Validate phone number, email, and date of birth
    if (name === "patientPhone" && isNaN(value)) {
      this.setState({
        errors: {
          ...errors,
          patientPhone: "Phone number must contain only digits",
        },
      });
      return;
    }

    if (name === "patientEmail" && !/\S+@\S+\.\S+/.test(value)) {
      this.setState({
        errors: {
          ...errors,
          patientEmail: "Invalid email format",
        },
      });
      return;
    }

    if (name === "dob" && !/^\d{4}-\d{2}-\d{2}$/.test(value)) {
      this.setState({
        errors: {
          ...errors,
          dob: "Date must be in the format YYYY-MM-DD",
        },
      });
      return;
    }

    this.setState({
      errors: {
        ...errors,
        [name]: "", // Clear specific error
      },
      patientData: {
        ...patientData,
        [name]: value,
      },
    });
  }

  // Handle form submission
  handleSubmit(e) {
    e.preventDefault();
    const { patientData, errors } = this.state;
    const newErrors = {};

    // Validate required fields
    Object.keys(patientData).forEach((key) => {
      if (!patientData[key]) {
        newErrors[key] = "This field is required";
      }
    });

    if (Object.keys(newErrors).length > 0) {
      this.setState({ errors: newErrors });
    } else {
      console.log("Form submitted successfully:", patientData);
      // Call API or service to handle the patient data
    }
  }

  render() {
    const { patientData, errors } = this.state;

    return (
      <Container fluid className="p-4 bg-light">
        <h3 className="mb-4">Insert Patient Details</h3>
        <Form onSubmit={this.handleSubmit}>
          <Row className="mb-3">
            <Col xs={12} md={6}>
              <Form.Group controlId="firstName">
                <Form.Label>First Name</Form.Label>
                <Form.Control
                  type="text"
                  name="firstName"
                  value={patientData.firstName}
                  onChange={this.handleChange}
                  placeholder="Enter First Name"
                />
                {errors.firstName && (
                  <Form.Text className="text-danger">
                    {errors.firstName}
                  </Form.Text>
                )}
              </Form.Group>
            </Col>

            <Col xs={12} md={6}>
              <Form.Group controlId="lastName">
                <Form.Label>Last Name</Form.Label>
                <Form.Control
                  type="text"
                  name="lastName"
                  value={patientData.lastName}
                  onChange={this.handleChange}
                  placeholder="Enter Last Name"
                />
                {errors.lastName && (
                  <Form.Text className="text-danger">
                    {errors.lastName}
                  </Form.Text>
                )}
              </Form.Group>
            </Col>
          </Row>

          <Row className="mb-3">
            <Col xs={12} md={6}>
              <Form.Group controlId="patientPhone">
                <Form.Label>Phone Number</Form.Label>
                <Form.Control
                  type="text"
                  name="patientPhone"
                  value={patientData.patientPhone}
                  onChange={this.handleChange}
                  placeholder="Enter Phone Number"
                />
                {errors.patientPhone && (
                  <Form.Text className="text-danger">
                    {errors.patientPhone}
                  </Form.Text>
                )}
              </Form.Group>
            </Col>

            <Col xs={12} md={6}>
              <Form.Group controlId="dob">
                <Form.Label>Date of Birth (YYYY-MM-DD)</Form.Label>
                <Form.Control
                  type="text"
                  name="dob"
                  value={patientData.dob}
                  onChange={this.handleChange}
                  placeholder="Enter Date of Birth"
                />
                {errors.dob && (
                  <Form.Text className="text-danger">{errors.dob}</Form.Text>
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
                  value={patientData.patientEmail}
                  onChange={this.handleChange}
                  placeholder="Enter Email Address"
                />
                {errors.patientEmail && (
                  <Form.Text className="text-danger">
                    {errors.patientEmail}
                  </Form.Text>
                )}
              </Form.Group>
            </Col>

            <Col xs={12} md={6}>
              <Form.Group controlId="patientPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  type="password"
                  name="patientPassword"
                  value={patientData.patientPassword}
                  onChange={this.handleChange}
                  placeholder="Enter Password"
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
