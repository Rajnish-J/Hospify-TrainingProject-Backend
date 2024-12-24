// ! after validation

import React, { Component } from "react";
import { Container, Form, Button, Card, Row, Col } from "react-bootstrap";
import { UserContext } from "../../src/Login/login.jsx";
import "./updatePatientDetails.css";

export default class UpdatePatientDetails extends Component {
  static contextType = UserContext;

  constructor(props) {
    super(props);
    this.state = {
      updatedFields: {},
      validationErrors: {},
      errorMessage: "",
      successMessage: "",
    };
  }

  handleChange = (e) => {
    const { name, value } = e.target;
    this.setState((prevState) => ({
      updatedFields: { ...prevState.updatedFields, [name]: value },
      validationErrors: { ...prevState.validationErrors, [name]: "" },
    }));
  };

  validateInputs = () => {
    const { updatedFields } = this.state;
    const errors = {};

    const { firstName, lastName, dob, patientPhone, patientEmail } =
      updatedFields;

    const nameRegex = /^[A-Za-z]+$/; // Updated regex to disallow spaces

    if (
      firstName &&
      (firstName.trim().length < 2 ||
        firstName.length > 50 ||
        !nameRegex.test(firstName) ||
        /\s/.test(firstName)) // Check for spaces in between
    ) {
      errors.firstName =
        "First name must be between 2 and 50 characters, contain no numbers, special characters, or spaces.";
    }

    if (
      lastName &&
      (lastName.trim().length < 2 ||
        lastName.length > 50 ||
        !nameRegex.test(lastName) ||
        /\s/.test(lastName)) // Check for spaces in between
    ) {
      errors.lastName =
        "Last name must be between 2 and 50 characters, contain no numbers, special characters, or spaces.";
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

    if (patientPhone) {
      if (!/^\d{10}$/.test(patientPhone)) {
        errors.patientPhone = "Phone number must be exactly 10 digits.";
      } else if (!/^[6-9]/.test(patientPhone)) {
        errors.patientPhone = "Phone number must start with 6, 7, 8, or 9.";
      }
    }

    if (patientEmail && !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(patientEmail)) {
      errors.patientEmail = "Please provide a valid email address.";
    }

    this.setState({ validationErrors: errors });
    return Object.keys(errors).length === 0;
  };

  handleSubmit = (e) => {
    e.preventDefault();

    // Validate inputs
    if (!this.validateInputs()) {
      return;
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
        updatePatientContext(data);
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
      <Container fluid className="d-flex" style={{ minHeight: "90vh" }}>
        <Row
          className="w-100 justify-content-center align-items-start"
          style={{ marginTop: "8rem" }}
        >
          <Col xs={12} md={6} lg={4}>
            <Card className="font shadow-lg p-4">
              <Card.Body>
                <h3 className="text-center mb-4">Update Your Details</h3>
                {errorMessage && (
                  <div className="text-danger mb-3 text-center font">
                    {errorMessage}
                  </div>
                )}
                {successMessage && (
                  <div className="text-success mb-3 text-center font">
                    {successMessage}
                  </div>
                )}
                <Form onSubmit={this.handleSubmit} noValidate>
                  {[
                    {
                      label: "First Name",
                      name: "firstName",
                      type: "text",
                      placeholder: firstName || "Enter first name",
                    },
                    {
                      label: "Last Name",
                      name: "lastName",
                      type: "text",
                      placeholder: lastName || "Enter last name",
                    },
                    {
                      label: "Date of Birth",
                      name: "dob",
                      type: "date",
                      placeholder: dob,
                    },
                    {
                      label: "Phone",
                      name: "patientPhone",
                      type: "text",
                      placeholder: patientPhone || "Enter phone number",
                    },
                    {
                      label: "Email",
                      name: "patientEmail",
                      type: "email",
                      placeholder: patientEmail || "Enter email",
                    },
                  ].map((field, index) => (
                    <Form.Group
                      key={index}
                      controlId={field.name}
                      className="mb-3 "
                    >
                      <div className="d-flex justify-content-start align-items-center">
                        <Form.Label
                          className="me-3 mb-0"
                          style={{ minWidth: "100px" }}
                        >
                          {field.label}:
                        </Form.Label>
                        <Form.Control
                          type={field.type}
                          placeholder={field.placeholder}
                          name={field.name}
                          value={updatedFields[field.name] || ""}
                          onChange={this.handleChange}
                          isInvalid={!!validationErrors[field.name]}
                        />
                      </div>
                      <p style={{ color: "red" }}>
                        {validationErrors[field.name]}
                      </p>
                    </Form.Group>
                  ))}

                  <Form.Group controlId="gender" className="mb-3">
                    <div className="d-flex align-items-center">
                      <Form.Label
                        className="me-3 mb-0"
                        style={{ minWidth: "100px" }}
                      >
                        Gender:
                      </Form.Label>
                      <Form.Select
                        name="gender"
                        value={updatedFields.gender || gender}
                        onChange={this.handleChange}
                      >
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                      </Form.Select>
                    </div>
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
