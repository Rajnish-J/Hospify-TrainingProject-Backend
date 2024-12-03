import React, { Component } from "react";
import "./header.css";
import { Navbar, Nav, Container, Button } from "react-bootstrap";
import { UserContext } from "../../Login/login.jsx";

export default class Header extends Component {
  static contextType = UserContext;

  handleLogout = () => {
    // Clear user session or any stored user data (localStorage, sessionStorage, etc.)
    localStorage.removeItem("patient");

    // After logout, redirect to LoginSelector page or Login page
    window.location.href = "/login"; // Or navigate to the login route
  };

  render() {
    const { firstName } = this.context || {};

    return (
      <>
        <Container fluid style={{ backgroundColor: "#3e5c76" }}>
          <Navbar expand="lg">
            <Container fluid className="d-flex align-items-center">
              {/* Hospital Management Brand Name */}
              <Navbar.Brand href="#home" className="navItem hover">
                Hospify
              </Navbar.Brand>

              {/* Home and about contents */}
              <Nav className="me-auto">
                <Nav.Link href="#home" className="subHeading font">
                  Home
                </Nav.Link>
                <Nav.Link href="#about" className="subHeading font">
                  About
                </Nav.Link>
              </Nav>

              {/* Username and logout button on the right and used display flex */}
              <div className="d-flex align-items-center">
                {/* Display user's first name */}
                {firstName && (
                  <h4 className="userName font">{`Hi, ${firstName}`}</h4>
                )}

                {/* Logout Button */}
                <Button
                  variant="danger"
                  className="logoutbtn font"
                  onClick={this.handleLogout}
                >
                  Logout
                </Button>
              </div>
            </Container>
          </Navbar>
        </Container>
      </>
    );
  }
}
