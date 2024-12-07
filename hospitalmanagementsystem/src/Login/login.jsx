// ! after validation
import React, { Component, createContext } from "react";
import {
  Container,
  Row,
  Col,
  Form,
  Button,
  Navbar,
  Nav,
} from "react-bootstrap";
import { FaTimes } from "react-icons/fa";
import Main from "../mainPageComponents/main.jsx";
import "../Login/login.css";

// usercontext object
export const UserContext = createContext();

export default class PatLogin extends Component {
  constructor(props) {
    super(props);
    this.state = {
      patient_email: "",
      patient_password: "",
      isLoggedIn: false,
      patient: null,
      showLogin: false,
      showSignUp: false,
      errorMessage: "",
      patientData: {
        firstName: "",
        lastName: "",
        patientEmail: "",
        patientPassword: "",
        patientPhone: "",
        dob: "",
        gender: "",
      },
      signUpErrors: {},
      signUpResponse: "",
    };
  }

  // Handle changes for Login inputs
  handleLoginChange = (e) => {
    const { name, value } = e.target;
    this.setState({ [name]: value });
  };

  // Handle Login submission
  handleLoginSubmit = (e) => {
    e.preventDefault();
    const { patient_email, patient_password } = this.state;

    const requestBody = {
      patientEmail: patient_email,
      patientPassword: patient_password,
    };

    fetch("http://localhost:8080/loginPage/patientLogin", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(requestBody),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data && data.patientId) {
          this.setState({ isLoggedIn: true, patient: data, errorMessage: "" });
        } else {
          this.setState({ errorMessage: "Invalid credentials" });
        }
      })
      .catch(() => {
        this.setState({ errorMessage: "Login failed, please try again." });
      });
  };

  // Handle changes for SignUp inputs
  handleSignUpChange = (e) => {
    const { name, value } = e.target;
    this.setState((prevState) => ({
      patientData: {
        ...prevState.patientData,
        [name]: value,
      },
      signUpErrors: {
        ...prevState.signUpErrors,
        [name]: "",
      },
    }));
  };

  handleSignUpSubmit = (e) => {
    e.preventDefault();
    const { patientData } = this.state;
    const newErrors = {};

    // Validate required fields (First Name, Last Name, Email, etc.)
    Object.keys(patientData).forEach((key) => {
      if (!patientData[key] && key !== "gender" && key !== "dob") {
        newErrors[key] = "This field is required";
      }
    });

    // Validate First Name (alphabetic characters only, minimum 2 characters)
    if (
      patientData.firstName &&
      (!/^[A-Za-z]+$/.test(patientData.firstName) ||
        patientData.firstName.length < 2)
    ) {
      newErrors.firstName =
        "First name must be at least 2 characters and contain only letters.";
    }

    // Validate Last Name (alphabetic characters only, minimum 2 characters)
    if (
      patientData.lastName &&
      (!/^[A-Za-z]+$/.test(patientData.lastName) ||
        patientData.lastName.length < 2)
    ) {
      newErrors.lastName =
        "Last name must be at least 2 characters and contain only letters.";
    }

    // Validate Email (basic email format)
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const gmailPattern = /@gmail\.com$/;

    if (
      patientData.patientEmail &&
      !emailPattern.test(patientData.patientEmail)
    ) {
      newErrors.patientEmail = "Please enter a valid email address.";
    } else if (
      patientData.patientEmail &&
      !gmailPattern.test(patientData.patientEmail)
    ) {
      newErrors.patientEmail = "Email must contain @gmail.com.";
    }

    // Validate Phone Number (basic phone number format, adjust regex as needed)
    const phonePattern = /^[6-9][0-9]{9}$/;

    if (
      patientData.patientPhone &&
      !phonePattern.test(patientData.patientPhone)
    ) {
      newErrors.patientPhone =
        "Please enter a valid phone number (10 digits) starting with 6, 7, 8, or 9.";
    }

    // Validate Date of Birth (at least 18 years old)
    if (!patientData.dob) {
      newErrors.dob = "Date of Birth is mandatory.";
    } else {
      const today = new Date();
      const dob = new Date(patientData.dob);
      const age = today.getFullYear() - dob.getFullYear();
      const month = today.getMonth() - dob.getMonth();

      if (age < 18 || (age === 18 && month < 0)) {
        newErrors.dob = "You must be at least 18 years old.";
      }
      if (age > 120) {
        newErrors.dob = "Age should not greater than 120 years old";
      }
    }

    // Validate Password (At least 1 small letter, 1 capital letter, 1 number, and 1 special character)
    const passwordPattern =
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
    if (
      patientData.patientPassword &&
      !passwordPattern.test(patientData.patientPassword)
    ) {
      newErrors.patientPassword =
        "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character.";
    }

    // Validate Gender (must be selected)
    if (!patientData.gender) {
      newErrors.gender = "Please select a gender.";
    }

    // If there are any validation errors, display them
    if (Object.keys(newErrors).length > 0) {
      this.setState({ signUpErrors: newErrors });
    } else {
      fetch("http://localhost:8080/patient/insert", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(patientData),
      })
        .then((response) => response.text())
        .then((data) => {
          if (data.includes("Patient created successfully")) {
            this.setState({
              signUpResponse: data,
              signUpErrors: {},
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
          } else {
            this.setState({
              signUpResponse: data,
              signUpErrors: {},
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
          }
        })
        .catch((error) => {
          // If an error occurs outside of the response
          this.setState({
            signUpResponse: `Sign Up Failed: ${error.message}`,
            signUpErrors: {},
          });
        });
    }
  };

  toggleLogin = () => {
    this.setState({ showLogin: !this.state.showLogin, showSignUp: false });
  };

  toggleSignUp = () => {
    this.setState({ showSignUp: !this.state.showSignUp, showLogin: false });
  };

  render() {
    const {
      patient_email,
      patient_password,
      isLoggedIn,
      errorMessage,
      showLogin,
      showSignUp,
      patient,
      patientData,
      signUpErrors,
      signUpResponse,
    } = this.state;

    if (isLoggedIn) {
      return (
        <UserContext.Provider
          value={{
            ...patient,
            updatePatientContext: (updatedData) =>
              this.setState((prevState) => ({
                patient: { ...prevState.patient, ...updatedData },
              })),
            setAppointments: (appointments) =>
              this.setState((prevState) => ({
                patient: { ...prevState.patient, appointments },
              })),
          }}
        >
          <Main />
        </UserContext.Provider>
      );
    }

    return (
      <div>
        <div
          className={`login-page ${showLogin || showSignUp ? "blurred" : ""}`}
        >
          <div className="landing-background"></div>

          {/* navbar */}
          <Navbar variant="dark" expand="lg" className="nav">
            <Container>
              <div className="d-flex justify-content-between align-items-center">
                <Navbar.Brand
                  className="hospify"
                  style={{ fontSize: "2.5rem" }}
                >
                  Hospify
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
              </div>

              <Nav className="mx-auto">
                <button className="nav-button">Home</button>

                <button className="nav-button">Products</button>

                <button className="nav-button">Pricing</button>
              </Nav>

              <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="ms-auto">
                  <Button
                    variant="outline-danger"
                    style={{
                      marginRight: "15px",
                      border: "none",
                      fontSize: "1.3rem",
                      fontFamily: "Josefinasans",
                      padding: "10px 30px",
                    }}
                    onClick={this.toggleSignUp}
                    className="ms-2"
                  >
                    Sign Up
                  </Button>

                  <Button
                    style={{
                      fontSize: "1.3rem",
                      padding: "10px 30px",
                      fontFamily: "Josefinasans",
                    }}
                    variant="danger"
                    onClick={this.toggleLogin}
                  >
                    Login
                  </Button>
                </Nav>
              </Navbar.Collapse>
            </Container>
          </Navbar>

          {/* home page content */}
          <Container className="landing-main-content">
            <h3 className="welcome-text">Welcome</h3>
            <h1 className="landing-header">
              A Great Place To <br /> Receive Care
            </h1>
            <p className="landing-paragraph">
              Overcome any hurdle or any other person
            </p>
            <Button
              variant="danger"
              style={{
                fontSize: "1.5rem",
                padding: "10px 30px",
                fontFamily: "Josefinasans",
              }}
              onClick={this.toggleLogin}
            >
              Login
            </Button>
          </Container>

          {/* Login Form */}
          {showLogin && (
            <div className="login-overlay">
              <div className="login-card">
                <Button
                  variant="secondary"
                  className="close-btn"
                  onClick={this.toggleLogin}
                >
                  <FaTimes />
                </Button>
                <h3 className="text-center font">Patient Login</h3>
                {errorMessage && (
                  <div className="text-danger text-center">{errorMessage}</div>
                )}
                <Form onSubmit={this.handleLoginSubmit}>
                  <Form.Group className="mb-3">
                    <Form.Label className="font">Email</Form.Label>
                    <Form.Control
                      type="email"
                      placeholder="Enter email"
                      name="patient_email"
                      value={patient_email}
                      onChange={this.handleLoginChange}
                      required
                    />
                  </Form.Group>
                  <Form.Group className="mb-3">
                    <Form.Label className="font">Password</Form.Label>
                    <Form.Control
                      type="password"
                      placeholder="Enter password"
                      name="patient_password"
                      value={patient_password}
                      onChange={this.handleLoginChange}
                      required
                    />
                  </Form.Group>
                  <Button
                    type="submit"
                    variant="primary"
                    className="w-100 font"
                  >
                    Login
                  </Button>
                </Form>
              </div>
            </div>
          )}

          {/* Sign Up Form */}
          {showSignUp && (
            <div className="login-overlay">
              <div className="login-card">
                <Button
                  variant="secondary"
                  className="close-btn"
                  onClick={this.toggleSignUp}
                >
                  <FaTimes />
                </Button>
                <h3 className="text-center font">Sign Up</h3>
                {signUpResponse && (
                  <div
                    className={
                      signUpResponse.includes("already registered") ||
                      signUpResponse.includes("Error")
                        ? "text-danger"
                        : "text-success"
                    }
                  >
                    {signUpResponse}
                  </div>
                )}

                <Form onSubmit={this.handleSignUpSubmit}>
                  {/* First Name */}
                  <Row className="mb-3">
                    <Col md={6}>
                      <Form.Group>
                        <Form.Label className="font">First Name</Form.Label>
                        <Form.Control
                          type="text"
                          name="firstName"
                          value={patientData.firstName}
                          onChange={this.handleSignUpChange}
                          placeholder="Enter first name"
                          isInvalid={!!signUpErrors.firstName}
                        />
                        <Form.Control.Feedback type="invalid">
                          {signUpErrors.firstName}
                        </Form.Control.Feedback>
                      </Form.Group>
                    </Col>

                    <Col md={6}>
                      <Form.Group>
                        <Form.Label className="font">Last Name</Form.Label>
                        <Form.Control
                          type="text"
                          name="lastName"
                          value={patientData.lastName}
                          onChange={this.handleSignUpChange}
                          placeholder="Enter last name"
                          isInvalid={!!signUpErrors.lastName}
                        />
                        <Form.Control.Feedback type="invalid">
                          {signUpErrors.lastName}
                        </Form.Control.Feedback>
                      </Form.Group>
                    </Col>
                  </Row>

                  {/* Email */}
                  <Form.Group className="mb-3">
                    <Form.Label className="font">Email</Form.Label>
                    <Form.Control
                      type="email"
                      name="patientEmail"
                      value={patientData.patientEmail}
                      onChange={this.handleSignUpChange}
                      placeholder="Enter email"
                      isInvalid={!!signUpErrors.patientEmail}
                    />
                    <Form.Control.Feedback type="invalid">
                      {signUpErrors.patientEmail}
                    </Form.Control.Feedback>
                  </Form.Group>

                  {/* Password */}
                  <Form.Group className="mb-3">
                    <Form.Label className="font">Password</Form.Label>
                    <Form.Control
                      type="password"
                      name="patientPassword"
                      value={patientData.patientPassword}
                      onChange={this.handleSignUpChange}
                      placeholder="Enter password"
                      isInvalid={!!signUpErrors.patientPassword}
                    />
                    <Form.Control.Feedback type="invalid">
                      {signUpErrors.patientPassword}
                    </Form.Control.Feedback>
                  </Form.Group>

                  {/* Phone Number */}
                  <Form.Group className="mb-3">
                    <Form.Label className="font">Phone Number</Form.Label>
                    <Form.Control
                      type="text"
                      name="patientPhone"
                      value={patientData.patientPhone}
                      onChange={this.handleSignUpChange}
                      placeholder="Enter phone number"
                      isInvalid={!!signUpErrors.patientPhone}
                    />
                    <Form.Control.Feedback type="invalid">
                      {signUpErrors.patientPhone}
                    </Form.Control.Feedback>
                  </Form.Group>

                  {/* Date of Birth */}
                  <Form.Group className="mb-3">
                    <Form.Label className="font">Date of Birth</Form.Label>
                    <Form.Control
                      className="font"
                      type="date"
                      name="dob"
                      value={patientData.dob}
                      onChange={this.handleSignUpChange}
                      isInvalid={!!signUpErrors.dob}
                    />
                    <Form.Control.Feedback type="invalid">
                      {signUpErrors.dob}
                    </Form.Control.Feedback>
                  </Form.Group>

                  {/* Gender */}
                  <Form.Group className="mb-3">
                    <Form.Label className="font">Gender</Form.Label>
                    <Form.Select
                      className="font"
                      name="gender"
                      value={patientData.gender}
                      onChange={this.handleSignUpChange}
                      isInvalid={!!signUpErrors.gender}
                    >
                      <option value="">Select Gender</option>
                      <option value="Male">Male</option>
                      <option value="Female">Female</option>
                      <option value="Other">Other</option>
                    </Form.Select>
                    <Form.Control.Feedback type="invalid">
                      {signUpErrors.gender}
                    </Form.Control.Feedback>
                  </Form.Group>

                  {/* Submit Button */}
                  <Button
                    type="submit"
                    variant="primary"
                    className="w-100 font"
                  >
                    Sign Up
                  </Button>
                </Form>
              </div>
            </div>
          )}
        </div>
      </div>
    );
  }
}
