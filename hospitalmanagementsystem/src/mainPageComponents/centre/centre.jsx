import React, { Component } from "react";
import { Col, Container, Row, Button } from "react-bootstrap";
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import "../centre/centre.css";
import { UserContext } from "../../Login/login.jsx";

import Admin from "../Admin/admin.jsx";
import InsertPatient from "../../InsertPatients/insertpatient.jsx";
import FetchPatientDetails from "../../fetchPatientDetails/fetchPatientDetails.jsx";
import FetchAllAppointments from "../../fetchallAppointments/fetchallAppointments.jsx";
import UpdatePatientDetails from "../../updatePatientDetails/updatePatientDetails.jsx";
import AddAppointment from "../../AddAppointment/AddAppointment.jsx";
import DeleteAppointments from "../../DeleteAppointment/DeleteAppointment.jsx";
import DeletePatient from "../../DeleteAppointment/DeleteAppointment.jsx";
import UpdateAppointmentDetails from "../../UpdateAppointmentDetails/UpdateAppointmentDetails.jsx";

export default class centre extends Component {
  static contextType = UserContext;

  constructor(props) {
    super(props);
    this.state = {
      activeRoute: "",
    };
  }

  handleButtonClick = (route) => {
    this.setState({ activeRoute: route });
  };

  render() {
    const { activeRoute } = this.state;
    const patient = this.context;
    // const patientID = patientID?.patientID;
    return (
      <div>
        {/* Router for the menu part and content part */}
        <Router>
          {/* Container tag */}
          <Container fluid>
            {/* Single row for the menu and content parts */}
            <Row className="ContentRow">
              {/* first column: menu */}
              <Col sm={2} xs={2} md={2} lg={2} className="menuCol">
                {/* div: menu */}
                <div className="menuDiv">
                  {/* Insert new patient */}
                  <Link to="/InsertPatient">
                    <Button
                      variant={
                        activeRoute === "/InsertPatient" ? "primary" : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() => this.handleButtonClick("/InsertPatient")}
                    >
                      Add Patient
                    </Button>
                  </Link>

                  {/* fetch patient details by id */}
                  <Link to="/patientdetails">
                    <Button
                      variant={
                        activeRoute === "/patientdetails" ? "primary" : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() => this.handleButtonClick("/patientdetails")}
                    >
                      Fetch Patient Details
                    </Button>
                  </Link>

                  {/* fetching all patient details */}
                  <Link to="/fetchAllAppointments">
                    <Button
                      variant={
                        activeRoute === "/fetchAllAppointments"
                          ? "primary"
                          : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() =>
                        this.handleButtonClick("/fetchAllAppointments")
                      }
                    >
                      Fetch all Appointment Details
                    </Button>
                  </Link>

                  {/* updating patient details */}
                  <Link to="/UpdatePatientDetails">
                    <Button
                      variant={
                        activeRoute === "/UpdatePatientDetails"
                          ? "primary"
                          : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() =>
                        this.handleButtonClick("/UpdatePatientDetails")
                      }
                    >
                      update patient Details
                    </Button>
                  </Link>

                  {/* Adding the appointments for the patients */}
                  <Link to="/AddAppointments">
                    <Button
                      variant={
                        activeRoute === "/AddAppointments" ? "primary" : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() => this.handleButtonClick("/AddAppointments")}
                    >
                      Add Appointment
                    </Button>
                  </Link>

                  {/* updating appointment details */}
                  <Link to="/UpdateAppointmentDetails">
                    <Button
                      variant={
                        activeRoute === "/UpdateAppointmentDetails"
                          ? "primary"
                          : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() =>
                        this.handleButtonClick("/UpdateAppointmentDetails")
                      }
                    >
                      update Appointment Details
                    </Button>
                  </Link>

                  {/* delete appointment */}
                  <Link to="/DeleteAppointment">
                    <Button
                      variant={
                        activeRoute === "/DeleteAppointment"
                          ? "primary"
                          : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() =>
                        this.handleButtonClick("/DeleteAppointment")
                      }
                    >
                      Delete Appointment
                    </Button>
                  </Link>

                  {/* delete patient account */}
                  <Link to="/DeletePatient">
                    <Button
                      variant={
                        activeRoute === "/DeletePatient" ? "primary" : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() => this.handleButtonClick("/DeletePatient")}
                    >
                      Delete Patient Account
                    </Button>
                  </Link>
                </div>
              </Col>

              {/* second column: content showing column */}
              <Col sm={10} xs={10} md={10} lg={10} className="contentCol">
                {/* Routes to the respective pages or components or API's */}
                <Routes>
                  {/* <Route path="/" element={<Admin />} /> */}
                  <Route path="/InsertPatient" element={<InsertPatient />} />
                  <Route
                    path="/patientdetails"
                    element={<FetchPatientDetails />}
                  />
                  <Route
                    path="/fetchAllAppointments"
                    element={<FetchAllAppointments />}
                  />
                  <Route
                    path="/UpdatePatientDetails"
                    element={<UpdatePatientDetails />}
                  />
                  <Route path="/AddAppointments" element={<AddAppointment />} />
                  <Route
                    path="/DeleteAppointment"
                    element={<DeleteAppointments />}
                  />
                  <Route path="/DeletePatient" element={<DeletePatient />} />
                  <Route
                    path="/UpdateAppointmentDetails"
                    element={<UpdateAppointmentDetails />}
                  />
                </Routes>
              </Col>
            </Row>
          </Container>
        </Router>
      </div>
    );
  }
}
