// import React, { Component } from "react";
// import { Container, Form, Button, Card, Row, Col } from "react-bootstrap";
// import { UserContext } from "../Login/login.jsx";

// export default class AddAppointment extends Component {
//   static contextType = UserContext;

//   constructor(props) {
//     super(props);
//     this.state = {
//       appointmentDate: "",
//       reason: "",
//       selectedDoctor: "",
//       errorMessage: "",
//       successMessage: "",
//       doctors: [
//         { doctorId: 1, name: "Dr. Smith" },
//         { doctorId: 2, name: "Dr. Johnson" },
//         { doctorId: 3, name: "Dr. Brown" },
//         { doctorId: 4, name: "Dr. Taylor" },
//         { doctorId: 5, name: "Dr. Lee" },
//       ],
//     };
//   }

//   handleChange = (e) => {
//     const { name, value } = e.target;
//     this.setState({ [name]: value });
//   };

//   handleSubmit = (e) => {
//     e.preventDefault();

//     const { appointmentDate, reason, selectedDoctor } = this.state;
//     const patient = this.context;

//     if (!patient || !patient.patientId) {
//       this.setState({ errorMessage: "Patient not logged in." });
//       return;
//     }

//     if (!selectedDoctor) {
//       this.setState({ errorMessage: "Please select a doctor." });
//       return;
//     }

//     const requestBody = {
//       appointment: {
//         appointmentDate,
//         reason,
//       },
//       doctor: {
//         doctorId: parseInt(selectedDoctor, 10),
//       },
//       patient: {
//         patientId: patient.patientId,
//       },
//     };

//     fetch("http://localhost:8080/appointment/insertWithPatientID", {
//       method: "POST",
//       headers: { "Content-Type": "application/json" },
//       body: JSON.stringify(requestBody),
//     })
//       .then((response) => {
//         console.log(requestBody);
//         if (!response.ok) {
//           return response.json().then((data) => {
//             throw new Error(data.message || "Failed to book appointment.");
//           });
//         }
//         return response.json();
//       })
//       .then((data) => {
//         // Update the context with the new appointment
//         const updatedPatient = {
//           ...patient,
//           appointments: [...(patient.appointments || []), data],
//         };
//         this.setState({
//           successMessage: "Appointment booked successfully!",
//           errorMessage: "",
//         });

//         // Update the React Context
//         this.props.updatePatientContext(updatedPatient);
//       })
//       .catch((error) => {
//         this.setState({ errorMessage: error.message, successMessage: "" });
//       });
//   };

//   render() {
//     const {
//       appointmentDate,
//       reason,
//       selectedDoctor,
//       doctors,
//       errorMessage,
//       successMessage,
//     } = this.state;

//     return (
//       <Container
//         fluid
//         className="d-flex justify-content-center align-items-center"
//         style={{ minHeight: "100vh", backgroundColor: "#f8f9fa" }}
//       >
//         <Row className="w-100 justify-content-center">
//           <Col xs={12} md={6} lg={4}>
//             <Card className="shadow-lg p-4">
//               <Card.Body>
//                 <h3 className="text-center mb-4">Book Appointment</h3>
//                 {errorMessage && (
//                   <div className="text-danger mb-3 text-center">
//                     {errorMessage}
//                   </div>
//                 )}
//                 {successMessage && (
//                   <div className="text-success mb-3 text-center">
//                     {successMessage}
//                   </div>
//                 )}
//                 <Form onSubmit={this.handleSubmit}>
//                   <Form.Group controlId="appointmentDate" className="mb-3">
//                     <Form.Label>Appointment Date</Form.Label>
//                     <Form.Control
//                       type="date"
//                       name="appointmentDate"
//                       value={appointmentDate}
//                       onChange={this.handleChange}
//                       required
//                     />
//                   </Form.Group>
//                   <Form.Group controlId="reason" className="mb-3">
//                     <Form.Label>Reason</Form.Label>
//                     <Form.Control
//                       type="text"
//                       placeholder="Enter reason"
//                       name="reason"
//                       value={reason}
//                       onChange={this.handleChange}
//                       required
//                     />
//                   </Form.Group>
//                   <Form.Group controlId="doctor" className="mb-3">
//                     <Form.Label>Select Doctor</Form.Label>
//                     <Form.Control
//                       as="select"
//                       name="selectedDoctor"
//                       value={selectedDoctor}
//                       onChange={this.handleChange}
//                       required
//                     >
//                       <option value="">Select a doctor</option>
//                       {doctors.map((doctor) => (
//                         <option key={doctor.doctorId} value={doctor.doctorId}>
//                           {doctor.name}
//                         </option>
//                       ))}
//                     </Form.Control>
//                   </Form.Group>
//                   <Button variant="primary" type="submit" className="w-100">
//                     Book Appointment
//                   </Button>
//                 </Form>
//               </Card.Body>
//             </Card>
//           </Col>
//         </Row>
//       </Container>
//     );
//   }
// }

import React, { Component } from "react";
import { Container, Form, Button, Card, Row, Col } from "react-bootstrap";
import { UserContext } from "../Login/login.jsx";

export default class AddAppointment extends Component {
  static contextType = UserContext;

  constructor(props) {
    super(props);
    this.state = {
      appointmentDate: "",
      reason: "",
      selectedDoctor: "",
      errorMessage: "",
      successMessage: "",
      doctors: [
        { doctorId: 1, name: "Dr. Smith" },
        { doctorId: 2, name: "Dr. Johnson" },
        { doctorId: 3, name: "Dr. Brown" },
        { doctorId: 4, name: "Dr. Taylor" },
        { doctorId: 5, name: "Dr. Lee" },
      ],
    };
  }

  handleChange = (e) => {
    const { name, value } = e.target;
    this.setState({ [name]: value });
  };

  handleSubmit = (e) => {
    e.preventDefault();

    const { appointmentDate, reason, selectedDoctor } = this.state;
    const patient = this.context;

    if (!patient || !patient.patientId) {
      this.setState({ errorMessage: "Patient not logged in." });
      return;
    }

    if (!selectedDoctor) {
      this.setState({ errorMessage: "Please select a doctor." });
      return;
    }

    const requestBody = {
      appointment: {
        appointmentDate,
        reason,
      },
      doctor: {
        doctorId: parseInt(selectedDoctor, 10),
      },
      patient: {
        patientId: patient.patientId,
      },
    };

    fetch("http://localhost:8080/appointment/insertWithPatientID", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(requestBody),
    })
      .then((response) => response.text()) // Expecting plain text response
      .then((data) => {
        if (data.startsWith("Appointments Details successfully saved")) {
          // Parse the string and get appointment details from the response
          const successMessage = data;
          this.setState({
            successMessage,
            errorMessage: "",
          });

          // Update the context with the new appointment
          const updatedPatient = {
            ...patient,
            appointments: [
              ...(patient.appointments || []),
              {
                appointmentDate,
                reason,
                doctorId: selectedDoctor,
              },
            ],
          };

          this.context.updatePatientContext(updatedPatient);

          this.setState({
            appointmentDate: "",
            reason: "",
            selectedDoctor: "",
          });
        } else {
          this.setState({
            errorMessage: "Failed to book appointment.",
            successMessage: "",
          });
        }
      })
      .catch((error) => {
        this.setState({
          errorMessage: "Error booking appointment",
          successMessage: "",
        });
      });
  };

  render() {
    const {
      appointmentDate,
      reason,
      selectedDoctor,
      doctors,
      errorMessage,
      successMessage,
    } = this.state;

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
                <h3 className="text-center mb-4">Book Appointment</h3>
                {errorMessage && (
                  <div className="text-danger mb-3 text-center">
                    {errorMessage}
                  </div>
                )}
                {successMessage && (
                  <div className="text-success mb-3 text-center">
                    {successMessage}
                  </div>
                )}
                <Form onSubmit={this.handleSubmit}>
                  <Form.Group controlId="appointmentDate" className="mb-3">
                    <Form.Label>Appointment Date</Form.Label>
                    <Form.Control
                      type="date"
                      name="appointmentDate"
                      value={appointmentDate}
                      onChange={this.handleChange}
                      required
                    />
                  </Form.Group>
                  <Form.Group controlId="reason" className="mb-3">
                    <Form.Label>Reason</Form.Label>
                    <Form.Control
                      type="text"
                      placeholder="Enter reason"
                      name="reason"
                      value={reason}
                      onChange={this.handleChange}
                      required
                    />
                  </Form.Group>
                  <Form.Group controlId="doctor" className="mb-3">
                    <Form.Label>Select Doctor</Form.Label>
                    <Form.Control
                      as="select"
                      name="selectedDoctor"
                      value={selectedDoctor}
                      onChange={this.handleChange}
                      required
                    >
                      <option value="">Select a doctor</option>
                      {doctors.map((doctor) => (
                        <option key={doctor.doctorId} value={doctor.doctorId}>
                          {doctor.name}
                        </option>
                      ))}
                    </Form.Control>
                  </Form.Group>
                  <Button variant="primary" type="submit" className="w-100">
                    Book Appointment
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
