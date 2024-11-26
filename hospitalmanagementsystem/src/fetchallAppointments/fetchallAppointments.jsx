// import React, { Component } from "react";
// import { Container, Row, Col, Table, Button, Alert } from "react-bootstrap";
// import { UserContext } from "../Login/login.jsx";

// export default class FetchAll extends Component {
//   static contextType = UserContext;
//   constructor(props) {
//     super(props);

//     this.state = {
//       appointments: [],
//       error: null,
//     };
//   }

//   handleFetchAll = () => {

//     const patId = this.context?.patientId;

//     fetch(`http://localhost:8080/appointment/fetchAppointmentsForPatientID/${patId}`, {
//       method: "GET",
//       headers: { "Content-Type": "application/json" },
//     })
//       .then((response) => {
//         if (!response.ok) {
//           throw new Error(`Error: $(response.status)`);
//         }
//         return response.json();
//       })
//       .then((data) => {
//         this.setState({ appointments: data, error: null });
//       })
//       .catch((error) => {
//         this.setState({ appointments: [], error: error.message });
//       });
//   };

//   render() {
//     const { appointments, error } = this.state;
//     return (
//       <div>
//         <Container className="" mt-5>
//           <Row>
//             <Col xs={12}>
//               <h2 className="mb-4">Patient Records</h2>

//               <Button variant="primary" onClick={this.handleFetchAll}>
//                 Fetch All Patients
//               </Button>

//               {error && (
//                 <Alert variant="danger" className="mt-5">
//                   {error}
//                 </Alert>
//               )}

//               {appointments.length > 0 && (
//                 <Table striped bordered hover responsive className="mt-3">
//                   <thead>
//                     <tr>
//                       <th>ID</th>
//                       <th>Name</th>
//                       <th>Age</th>
//                       <th>Date Of Birth</th>
//                       <th>Email</th>
//                       <th>Phone</th>
//                       <th>Gender</th>
//                     </tr>
//                   </thead>
//                   <tbody>
//                     {appointments.map((patient) => (
//                       <tr key={patient.patientID}>
//                         <td>{patient.patientID}</td>
//                         <td>
//                           {patient.firstName} {patient.lastName}
//                         </td>
//                         <td>
//                           {new Date().getFullYear() -
//                             new Date(patient.dob).getFullYear()}
//                         </td>
//                         <td>{patient.dob}</td>
//                         <td>{patient.patientEmail}</td>
//                         <td>{patient.patientPhone}</td>
//                         <td>{patient.gender}</td>
//                         {/* <td>
//                           Placeholder for condition
//                           -
//                         </td> */}
//                       </tr>
//                     ))}
//                   </tbody>
//                 </Table>
//               )}
//             </Col>
//           </Row>
//         </Container>
//       </div>
//     );
//   }
// }


import React, { Component } from "react";
import { Container, Row, Col, Table, Button, Alert, Card } from "react-bootstrap";
import { UserContext } from "../Login/login.jsx";
import { useNavigate } from "react-router-dom";

class FetchAll extends Component {
  static contextType = UserContext;

  constructor(props) {
    super(props);
    this.state = {
      appointments: [],
      error: null,
    };
  }

  handleFetchAll = () => {
    const patId = this.context?.patientId;

    fetch(`http://localhost:8080/appointment/fetchAppointmentsForPatientID/${patId}`, {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Error: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        this.setState({ appointments: data, error: null });
      })
      .catch((error) => {
        this.setState({ appointments: [], error: error.message });
      });
  };

  render() {
    const { appointments, error } = this.state;
    const { navigate } = this.props;

    return (
      <Container className="mt-5">
        <Row>
          <Col xs={12}>
            <h2 className="mb-4">Patient Appointments</h2>
            <Button variant="primary" onClick={this.handleFetchAll}>
              Fetch Appointments
            </Button>

            {error && (
              <Alert variant="danger" className="mt-4">
                {error}
              </Alert>
            )}

            {appointments.length === 0 && !error && (
              <Card className="mt-4 text-center">
                <Card.Body>
                  <Card.Title>No Appointments Found</Card.Title>
                  <Button variant="primary" onClick={() => navigate("/AddAppointments")}>
                    Add Appointment
                  </Button>
                </Card.Body>
              </Card>
            )}

            {appointments.length > 0 && (
              <Table striped bordered hover responsive className="mt-4">
                <thead>
                  <tr>
                    <th>Appointment ID</th>
                    <th>Appointment Date</th>
                    <th>Reason</th>
                    <th>Doctor ID</th>
                  </tr>
                </thead>
                <tbody>
                  {appointments.map((appointment) => (
                    <tr key={appointment.appointmentID}>
                      <td>{appointment.appointmentID}</td>
                      <td>{appointment.appointmentDate}</td>
                      <td>{appointment.reason}</td>
                      <td>{appointment.doctorID}</td>
                    </tr>
                  ))}
                </tbody>
              </Table>
            )}
          </Col>
        </Row>
      </Container>
    );
  }
}

// Wrapper Component to Use Hooks in a Class Component
function FetchAllWithNavigate() {
  const navigate = useNavigate();
  return <FetchAll navigate={navigate} />;
}

export default FetchAllWithNavigate;
