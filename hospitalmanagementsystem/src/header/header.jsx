import React, { Component } from "react";
import './header.css';
import { Navbar, Nav, Container, NavDropdown } from "react-bootstrap";

export default class header extends Component {
  render() {
    return (
      <div>
        <Navbar expand="lg" className="bg-body-tertiary">
          <Container fluid>
            <Navbar.Brand href="#home" className = "navItem">Hospital Management</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="me-auto">
                <Nav.Link href="#home">Home</Nav.Link>
                <Nav.Link href="#about">About</Nav.Link>
                <Nav.Link href="#services">Services</Nav.Link>
                <Nav.Link href="#contact">Contact</Nav.Link>
                <NavDropdown title="Departments" id="basic-nav-dropdown">
                  <NavDropdown.Item href="#cardiology">
                    Cardiology
                  </NavDropdown.Item>
                  <NavDropdown.Item href="#orthopedics">
                    Orthopedics
                  </NavDropdown.Item>
                  <NavDropdown.Item href="#pediatrics">
                    Pediatrics
                  </NavDropdown.Item>
                  <NavDropdown.Divider />
                  <NavDropdown.Item href="#emergency">
                    Emergency
                  </NavDropdown.Item>
                </NavDropdown>
              </Nav>
            </Navbar.Collapse>
          </Container>
        </Navbar>
      </div>
    );
  }
}
