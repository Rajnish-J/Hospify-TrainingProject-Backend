// ! after validation

import React, { Component } from "react";
import { Container, Form, Button, Card, Row, Col } from "react-bootstrap";
import { UserContext } from "../../src/Login/login.jsx";

export default class UpdatePatientDetails extends Component {
  static contextType = UserContext;

  constructor(props) {
    super(props);
    this.state = {
      updatedFields: {},
      validationErrors: {}, // Object to store validation errors
      errorMessage: "",
      successMessage: "",
    };
  }

  handleChange = (e) => {
    const { name, value } = e.target;
    this.setState((prevState) => ({
      updatedFields: { ...prevState.updatedFields, [name]: value },
      validationErrors: { ...prevState.validationErrors, [name]: "" }, // Reset field-specific error
    }));
  };

  validateInputs = () => {
    const { updatedFields } = this.state;
    const errors = {};

    const { firstName, lastName, dob, patientPhone, patientEmail } =
      updatedFields;

    if (firstName && (firstName.trim().length < 2 || firstName.length > 50)) {
      errors.firstName = "First name must be between 2 and 50 characters.";
    }

    if (lastName && (lastName.trim().length < 2 || lastName.length > 50)) {
      errors.lastName = "Last name must be between 2 and 50 characters.";
    }

    if (dob) {
      const dobDate = new Date(dob);
      const today = new Date();
      const age = today.getFullYear() - dobDate.getFullYear();
      const isBirthdayPassed =
        today.getMonth() > dobDate.getMonth() ||
        (today.getMonth() === dobDate.getMonth() &&
          today.getDate() >= dobDate.getDate());
      if (age < 18 || (age === 18 && !isBirthdayPassed)) {
        errors.dob = "Patient must be at least 18 years old.";
      }
    }

    if (patientPhone && !/^\d{10}$/.test(patientPhone)) {
      errors.patientPhone = "Phone number must be exactly 10 digits.";
    }

    if (patientEmail && !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(patientEmail)) {
      errors.patientEmail = "Please provide a valid email address.";
    }

    this.setState({ validationErrors: errors });
    return Object.keys(errors).length === 0; // Return true if no errors
  };

  handleSubmit = (e) => {
    e.preventDefault();

    // Validate inputs
    if (!this.validateInputs()) {
      return; // Stop submission if validation fails
    }

    const { updatedFields } = this.state;
    const patientContext = this.context;

    if (!patientContext || !patientContext.patientId) {
      this.setState({ errorMessage: "Patient data is not available." });
      return;
    }

    const { patientId, updatePatientContext } = patientContext;

    const updatedPatient = {
      ...patientContext,
      ...updatedFields,
    };

    fetch(`http://localhost:8080/patient/update/${patientId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(updatedPatient),
    })
      .then((response) => {
        if (!response.ok) {
          return response.json().then((data) => {
            throw new Error(data.message || "Update failed");
          });
        }
        return response.json();
      })
      .then((data) => {
        this.setState({
          successMessage: "Details updated successfully!",
          errorMessage: "",
        });
        updatePatientContext(data); // Update context with the new patient details
      })
      .catch((error) => {
        this.setState({
          errorMessage: error.message,
          successMessage: "",
        });
      });
  };

  render() {
    const { updatedFields, validationErrors, errorMessage, successMessage } =
      this.state;
    const patientContext = this.context;

    if (!patientContext) {
      return <p>Loading patient data...</p>;
    }

    const { firstName, lastName, dob, patientPhone, patientEmail, gender } =
      patientContext;

    return (
      <Container
        fluid
        className="d-flex justify-content-center align-items-center"
        style={{ minHeight: "100vh", backgroundColor: "#f8f9fa" }}
      >
        <Row className="w-100 justify-content-center">
          <Col xs={12} md={6} lg={4}>
            <Card className="shadow-lg p-4">
              <Card.Body>
                <h3 className="text-center mb-4">Update Details</h3>
                {errorMessage && (
                  <div className="text-danger mb-3 text-center">
                    {errorMessage}
                  </div>
                )}
                {successMessage && (
                  <div className="text-success mb-3 text-center">
                    {successMessage}
                  </div>
                )}
                <Form onSubmit={this.handleSubmit} noValidate>
                  <Form.Group controlId="firstName" className="mb-3">
                    <Form.Label>First Name</Form.Label>
                    <Form.Control
                      type="text"
                      placeholder={firstName || "Enter first name"}
                      name="firstName"
                      value={updatedFields.firstName || ""}
                      onChange={this.handleChange}
                      isInvalid={!!validationErrors.firstName}
                    />
                    <Form.Control.Feedback type="invalid">
                      {validationErrors.firstName}
                    </Form.Control.Feedback>
                  </Form.Group>

                  <Form.Group controlId="lastName" className="mb-3">
                    <Form.Label>Last Name</Form.Label>
                    <Form.Control
                      type="text"
                      placeholder={lastName || "Enter last name"}
                      name="lastName"
                      value={updatedFields.lastName || ""}
                      onChange={this.handleChange}
                      isInvalid={!!validationErrors.lastName}
                    />
                    <Form.Control.Feedback type="invalid">
                      {validationErrors.lastName}
                    </Form.Control.Feedback>
                  </Form.Group>

                  <Form.Group controlId="dob" className="mb-3">
                    <Form.Label>Date of Birth</Form.Label>
                    <Form.Control
                      type="date"
                      placeholder={dob}
                      name="dob"
                      value={updatedFields.dob || ""}
                      onChange={this.handleChange}
                      isInvalid={!!validationErrors.dob}
                    />
                    <Form.Control.Feedback type="invalid">
                      {validationErrors.dob}
                    </Form.Control.Feedback>
                  </Form.Group>

                  <Form.Group controlId="patientPhone" className="mb-3">
                    <Form.Label>Phone</Form.Label>
                    <Form.Control
                      type="text"
                      placeholder={patientPhone || "Enter phone number"}
                      name="patientPhone"
                      value={updatedFields.patientPhone || ""}
                      onChange={this.handleChange}
                      isInvalid={!!validationErrors.patientPhone}
                    />
                    <Form.Control.Feedback type="invalid">
                      {validationErrors.patientPhone}
                    </Form.Control.Feedback>
                  </Form.Group>

                  <Form.Group controlId="patientEmail" className="mb-3">
                    <Form.Label>Email</Form.Label>
                    <Form.Control
                      type="email"
                      placeholder={patientEmail || "Enter email"}
                      name="patientEmail"
                      value={updatedFields.patientEmail || ""}
                      onChange={this.handleChange}
                      isInvalid={!!validationErrors.patientEmail}
                    />
                    <Form.Control.Feedback type="invalid">
                      {validationErrors.patientEmail}
                    </Form.Control.Feedback>
                  </Form.Group>

                  <Form.Group controlId="gender" className="mb-3">
                    <Form.Label>Gender</Form.Label>
                    <Form.Select
                      name="gender"
                      value={updatedFields.gender || gender}
                      onChange={this.handleChange}
                    >
                      <option value="Male">Male</option>
                      <option value="Female">Female</option>
                      <option value="Other">Other</option>
                    </Form.Select>
                  </Form.Group>

                  <Button variant="primary" type="submit" className="w-100">
                    Update
                  </Button>
                </Form>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    );
  }
}
