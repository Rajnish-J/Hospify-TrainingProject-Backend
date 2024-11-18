import React, { Component, useState } from "react";
import { Col, Container, Row, Button } from "react-bootstrap";
import {
  BrowserRouter as Router,
  Route,
  NavLink,
  Link,
  Routes,
} from "react-router-dom";
import "../centre/centre.css";

import Admin from "../Admin/admin.jsx";
import InsertPatient from "../../InsertPatients/insertpatient.jsx";
import FetchPatient from "../../findBypatientId/findBypatientId.jsx";
import FetchAll from "../../fetchall/fetchall.jsx";
import UpdatePatient from "../../updatePatientDetails/updatePatientDetails.jsx";
import AddAppointments from "../../associate/addAppointments.jsx";
import FetchByPhone from "../../findbyphone/findbyphone.jsx";
import AppointmentDate from "../../findapptDay/findapptDay.jsx";
import PatientName from "../../findName/findName.jsx";
import PatientsBetTwoDates from "../../betweenTwoDOBpat/betweenTwoDOBpat.jsx";
import Ascending from "../../acending/acending.jsx";
import CommomDOB from "../../findMostCommonDOB/findMostCommonDOB.jsx";
import MostAppointments from "../../findPatientWithMostAppointments/findPatientWithMostAppointments.jsx";
import Count from "../../findTotalPatientsCount/findTotalPatientsCount.jsx";

export default class centre extends Component {
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
                  <Link to="/Patientdetails">
                    <Button
                      variant={
                        activeRoute === "/Patientdetails" ? "primary" : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() => this.handleButtonClick("/Patientdetails")}
                    >
                      Fetch Patient Details
                    </Button>
                  </Link>

                  {/* fetching all patient details */}
                  <Link to="/fetchAll">
                    <Button
                      variant={
                        activeRoute === "/fetchAll" ? "primary" : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() => this.handleButtonClick("/fetchAll")}
                    >
                      Fetch all patient Details
                    </Button>
                  </Link>

                  {/* updating patient details */}
                  <Link to="/updatePatient">
                    <Button
                      variant={
                        activeRoute === "/updatePatient" ? "primary" : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() => this.handleButtonClick("/updatePatient")}
                    >
                      update patient Details
                    </Button>
                  </Link>

                  {/* associate: adding one or more appointments */}
                  <Link to="/addAppointment">
                    <Button
                      variant={
                        activeRoute === "/addAppointment" ? "primary" : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() => this.handleButtonClick("/addAppointment")}
                    >
                      Add appointment
                    </Button>
                  </Link>

                  {/* fetching patient details  by phone number */}
                  <Link to="/fetchByPhoneNumber">
                    <Button
                      variant={
                        activeRoute === "/fetchByPhoneNumber"
                          ? "primary"
                          : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() =>
                        this.handleButtonClick("/fetchByPhoneNumber")
                      }
                    >
                      Get Details by phone number
                    </Button>
                  </Link>

                  {/* fetching the patient details by the appointment date */}
                  <Link to="/getDetailsByDate">
                    <Button
                      variant={
                        activeRoute === "/getDetailsByDate"
                          ? "primary"
                          : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() =>
                        this.handleButtonClick("/getDetailsByDate")
                      }
                    >
                      Get patient details by appointment date
                    </Button>
                  </Link>

                  {/* fetching the patient full name */}
                  <Link to="/fullname">
                    <Button
                      variant={
                        activeRoute === "/fullname" ? "primary" : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() => this.handleButtonClick("/fullname")}
                    >
                      get patient full name
                    </Button>
                  </Link>

                  {/* fetching patients details between two birthday dates */}
                  <Link to="/patientDetailsByDOB">
                    <Button
                      variant={
                        activeRoute === "/patientDetailsByDOB"
                          ? "primary"
                          : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() =>
                        this.handleButtonClick("/patientDetailsByDOB")
                      }
                    >
                      get patients Details between two birthdat dates
                    </Button>
                  </Link>

                  {/* fetching all the patient details in the ascending order */}
                  <Link to="/Ascending">
                    <Button
                      variant={
                        activeRoute === "/Ascending" ? "primary" : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() => this.handleButtonClick("/Ascending")}
                    >
                      Ascending
                    </Button>
                  </Link>

                  {/* patients details having more commom date of birthday's */}
                  <Link to="/MostDOB">
                    <Button
                      variant={activeRoute === "/MostDOB" ? "primary" : "light"}
                      className="w-100 mb-2 Button"
                      onClick={() => this.handleButtonClick("/MostDOB")}
                    >
                      Patients having most same DOB
                    </Button>
                  </Link>

                  {/* patient having more appointments */}
                  <Link to="/mostAppointments">
                    <Button
                      variant={
                        activeRoute === "/mostAppointments"
                          ? "primary"
                          : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() =>
                        this.handleButtonClick("/mostAppointments")
                      }
                    >
                      Patient with Most appointments
                    </Button>
                  </Link>

                  {/* total patients count */}
                  <Link to="/patientCount">
                    <Button
                      variant={
                        activeRoute === "/patientCount" ? "primary" : "light"
                      }
                      className="w-100 mb-2 Button"
                      onClick={() => this.handleButtonClick("/patientCount")}
                    >
                      Total Patient Count
                    </Button>
                  </Link>
                </div>
              </Col>

              {/* second column: content showing column */}
              <Col sm={10} xs={10} md={10} lg={10} className="contentCol">
                {/* Routes to the respective pages or components or API's */}
                <Routes>
                  <Route path="/" element={<Admin />} />
                  <Route path="/InsertPatient" element={<InsertPatient />} />
                  <Route path="/Patientdetails" element={<FetchPatient />} />
                  <Route path="/fetchAll" element={<FetchAll />} />
                  <Route path="/updatePatient" element={<UpdatePatient />} />
                  <Route path="/addAppointment" element={<AddAppointments />} />
                  <Route
                    path="/fetchByPhoneNumber"
                    element={<FetchByPhone />}
                  />
                  <Route
                    path="/getDetailsByDate"
                    element={<AppointmentDate />}
                  />
                  <Route path="/fullname" element={<PatientName />} />
                  <Route
                    path="/patientDetailsByDOB"
                    element={<PatientsBetTwoDates />}
                  />
                  <Route path="/Ascending" element={<Ascending />} />
                  <Route path="/MostDOB" element={<CommomDOB />} />
                  <Route
                    path="/mostAppointments"
                    element={<MostAppointments />}
                  />
                  <Route path="/patientCount" element={<Count />} />
                </Routes>
              </Col>
            </Row>
          </Container>
        </Router>
      </div>
    );
  }
}
