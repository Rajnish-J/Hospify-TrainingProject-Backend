import React, { Component } from "react";
import { Container, Form, Button, Card, Row, Col } from "react-bootstrap";
import Main from "../mainPageComponents/main.jsx";

export default class patlogin extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      Password: "",
      isLoggedIn: false,
      user: null,
      errorMessage: "",
    };
  }

  handleChange = (e) => {
    const { name, value } = e.target;
    this.setState({ [name]: value });
  };

  handleSubmit = (e) => {
    e.preventDefault();
    const { email, Password } = this.state; // Match case sensitivity
    const requestBody = { email, password: Password }; // Correct payload key

    fetch("localhost:8080/loginPage/patientLogin", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(requestBody),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Login failed"); // Keep this for non-200 responses
        }
        return response.json();
      })
      .then((data) => {
        if (data && data.userId) {
          this.setState({ isLoggedIn: true, user: data });
        } else {
          this.setState({ errorMessage: "Invalid credentials" });
        }
      })
      .catch((error) => {
        console.error("Error during login:", error);
        this.setState({ errorMessage: "Login failed, please try again." });
      });
  };

  render() {
    const { email, password, isLoggedIn, errorMessage, user } = this.state;

    // If logged in, render the Main component
    if (isLoggedIn) {
      console.log(user);
      return <Main user={user} />; // Render Main component after login
    }

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
                <h3 className="text-center mb-4">User Login</h3>
                {errorMessage && (
                  <div className="text-danger mt-3 text-center">
                    {errorMessage}
                  </div>
                )}
                <Form onSubmit={this.handleSubmit}>
                  <Form.Group controlId="email" className="mb-3">
                    <Form.Label>Email</Form.Label>
                    <Form.Control
                      type="email"
                      placeholder="Enter email"
                      name="email"
                      value={email}
                      onChange={this.handleChange}
                      required
                    />
                  </Form.Group>
                  <Form.Group controlId="password" className="mb-3">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                      type="password"
                      placeholder="Enter password"
                      name="password"
                      value={password}
                      onChange={this.handleChange}
                      required
                    />
                  </Form.Group>
                  <Button variant="primary" type="submit" className="w-100">
                    Login
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
