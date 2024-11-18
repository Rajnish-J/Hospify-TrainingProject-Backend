import React, { Component } from "react";
import "./header.css";
import { Navbar, Nav, Container, NavDropdown } from "react-bootstrap";
import logoGif from "../../assets/header/logo.gif";
import { Col, Row } from "react-bootstrap";
import "../header/header.css";

export default class header extends Component {
  render() {
    return (
      <div>
        <Container fluid>
          {/* header Row */}
          <Row className="headerCont">
            {/* logo column */}
            <Col sm={2} xs={3} md={1} lg={1} className="logoCol">
              <img src={logoGif} alt="Animated Logo" className="gif-logo" />
            </Col>

            {/* header column: */}
            <Col sm={11} xs={11} md={11} lg={11}>
              <Navbar expand="lg" className="bg-body-tertiary">
                <Container fluid>
                  <Navbar.Brand href="#home" className="navItem">
                    Hospital Management
                  </Navbar.Brand>
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
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}
