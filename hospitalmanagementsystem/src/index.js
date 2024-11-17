import React from "react";
import ReactDOM from "react-dom/client";
import "bootstrap/dist/css/bootstrap.min.css";
import logoGif from "./assets/header/logo.gif";
import reportWebVitals from "./reportWebVitals";
import Header from "./header/header.jsx";
import { Col, Container, Row } from "react-bootstrap";
import "./index.css";
import {
  BrowserRouter as Router,
  Route,
  NavLink,
  Routes,
} from "react-router-dom";
import Admin from "./Admin/admin.jsx";
import InsertPatient from "./InsertPatients/insertpatient.jsx";
import FetchPatient from "./findBypatientId/findBypatientId.jsx";
import FetchAll from "./fetchall/fetchall.jsx";
import UpdatePatient from "./updatePatientDetails/updatePatientDetails.jsx";
import AddAppointments from "./associate/addAppointments.jsx";
import FetchByPhone from "./findbyphone/findbyphone.jsx";
import AppointmentDate from "./findapptDay/findapptDay.jsx";
import PatientName from "./findName/findName.jsx";
import PatientsBetTwoDates from "./betweenTwoDOBpat/betweenTwoDOBpat.jsx";
import Ascending from "./acending/acending.jsx";
import CommomDOB from "./findMostCommonDOB/findMostCommonDOB.jsx";
import MostAppointments from "./findPatientWithMostAppointments/findPatientWithMostAppointments.jsx";
import Count from "./findTotalPatientsCount/findTotalPatientsCount.jsx";

// import MainDataContext from

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    {/* Container tag */}
    <Container fluid className="headerContainer">
      {/* header Row */}
      <Row className="headerCont">
        {/* logo column */}
        <Col sm={1} xs={1} md={1} lg={1} className="logoCol">
          <img src={logoGif} alt="Animated Logo" className="gif-logo" />
        </Col>

        {/* header column: component composition */}
        <Col sm={11} xs={11} md={11} lg={11}>
          <Header />
        </Col>
      </Row>

      {/* Content Row */}
      <Row className="ContentRow">
        {/* ! menu column */}
        <Col sm={2} xs={2} md={2} lg={2} className="menuCol">
          {/* Router tags */}
          <Router>
            <div className="Routerstyles">
              {/* unordered lists */}
              <ul>
                <li>
                  <NavLink activeClassName="active" to="/InsertPatient">
                    Insert patient
                  </NavLink>
                </li>

                <li>
                  <NavLink activeClassName="active" to="/Patientdetails">
                    Fetch Details by Id
                  </NavLink>
                </li>

                <li>
                  <NavLink activeClassName="active" to="/fetchAll">
                    Fetch all patient Details
                  </NavLink>
                </li>

                <li>
                  <NavLink activeClassName="active" to="/updatePatient">
                    update patient Details
                  </NavLink>
                </li>

                <li>
                  <NavLink activeClassName="active" to="/addAppointment">
                    add appointment
                  </NavLink>
                </li>

                <li>
                  <NavLink activeClassName="active" to="/fetchByPhoneNumber">
                    Get Details by phone number
                  </NavLink>
                </li>

                <li>
                  <NavLink activeClassName="active" to="/getDetailsByDate">
                    Get patient details by appointment date
                  </NavLink>
                </li>

                <li>
                  <NavLink activeClassName="active" to="/fullname">
                    get patient full name
                  </NavLink>
                </li>

                <li>
                  <NavLink activeClassName="active" to="/patientDetailsByDOB">
                    get patients by two dates
                  </NavLink>
                </li>

                <li>
                  <NavLink activeClassName="active" to="/Ascending">
                    fetch ascending order
                  </NavLink>
                </li>

                <li>
                  <NavLink activeClassName="active" to="/MostDOB">
                    Patients Having most Date Of Birth
                  </NavLink>
                </li>

                <li>
                  <NavLink activeClassName="active" to="/mostAppointments">
                    Patient with Most appointments
                  </NavLink>
                </li>

                <li>
                  <NavLink activeClassName="active" to="/patientCount">
                    Total Patient Count
                  </NavLink>
                </li>
              </ul>
            </div>

            {/* Switch */}
            {/* <MainDataContext.Provider value = {this.state.name}/> */}
            <div>
              <Routes>
                {/* <Route path="/" element={<Admin />} /> */}
                <Route path="/InsertPatient" element={<InsertPatient />} />
                <Route path="/Patientdetails" element={<FetchPatient />} />
                <Route path="/fetchAll" element={<FetchAll />} />
                <Route path="/updatePatient" element={<UpdatePatient />} />
                <Route path="/addAppointment" element={<AddAppointments />} />
                <Route path="/fetchByPhoneNumber" element={<FetchByPhone />} />
                <Route path="/getDetailsByDate" element={<AppointmentDate />} />
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
            </div>
          </Router>
        </Col>

        {/* content column */}
        <Col sm={10} xs={10} md={10} lg={10}></Col>
      </Row>

      {/* Footer row */}
      <Row className="FooterRow">
        {/* Footer column */}
        <Col>
          <h1 className="FooterCol">
            Copyright @2024 Hospital Management. All rights reserved
          </h1>
        </Col>
      </Row>
    </Container>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
