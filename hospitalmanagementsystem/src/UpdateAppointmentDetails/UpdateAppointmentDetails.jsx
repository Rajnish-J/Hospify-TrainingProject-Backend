// import React, { Component } from "react";
// import { Container, Table, Button, Form, Card } from "react-bootstrap";
// import { UserContext } from "../Login/login.jsx"; // Import UserContext for accessing context data

// export default class AppointmentList extends Component {
//   constructor(props) {
//     super(props);
//     this.state = {
//       selectedAppointment: null, // Stores the appointment being updated
//       appointmentDate: "",
//       reason: "",
//       errorMessage: "",
//       successMessage: "",
//     };
//   }

//   static contextType = UserContext;

//   // Handles input changes in the form
//   handleChange = (e) => {
//     const { name, value } = e.target;
//     this.setState({ [name]: value });
//   };

//   // Handles the Update button click and sets the selected appointment
//   handleUpdateClick = (appointment) => {
//     this.setState({
//       selectedAppointment: appointment,
//       appointmentDate: appointment.appointmentDate,
//       reason: appointment.reason,
//       errorMessage: "",
//       successMessage: "",
//     });
//   };

//   // Handles the form submission to update the appointment
//   handleSubmit = (e) => {
//     e.preventDefault();

//     const { selectedAppointment, appointmentDate, reason } = this.state;

//     // Prepare request body
//     const updatedAppointment = { appointmentDate, reason };
//     console.log(updatedAppointment);

//     fetch(
//       `http://localhost:8080/appointment/updateAppointments/${selectedAppointment.appointmentID}`,
//       {
//         method: "PUT",
//         headers: {
//           "Content-Type": "application/json",
//         },
//         body: JSON.stringify(updatedAppointment),
//       }
//     )
//       .then((response) => {
//         if (!response.ok) {
//           throw new Error("Failed to update appointment");
//         }
//         return response.json();
//       })
//       .then(() => {
//         this.setState({
//           successMessage: "Appointment updated successfully",
//           errorMessage: "",
//         });

//         // Update context with new appointment data
//         const updatedAppointments = this.context.appointments.map(
//           (appointment) =>
//             appointment.appointmentID === selectedAppointment.appointmentID
//               ? { ...appointment, appointmentDate, reason }
//               : appointment
//         );
//         this.context.setAppointments(updatedAppointments); // Update context

//         // Reset selected appointment after success
//         this.setState({ selectedAppointment: null });
//       })
//       .catch((error) => {
//         this.setState({
//           errorMessage: error.message || "An error occurred",
//           successMessage: "",
//         });
//       });
//   };

//   render() {
//     const { appointments } = this.context; // Fetch appointments from context
//     const {
//       selectedAppointment,
//       appointmentDate,
//       reason,
//       errorMessage,
//       successMessage,
//     } = this.state;

//     return (
//       <Container className="mt-5">
//         <h3 className="text-center mb-4">Appointments</h3>

//         {/* Display success or error messages */}
//         {errorMessage && (
//           <div className="text-danger text-center mb-3">{errorMessage}</div>
//         )}
//         {successMessage && (
//           <div className="text-success text-center mb-3">{successMessage}</div>
//         )}

//         {/* Table for displaying appointments */}
//         <Table striped bordered hover>
//           <thead>
//             <tr>
//               <th>ID</th>
//               <th>Date</th>
//               <th>Reason</th>
//               <th>Actions</th>
//             </tr>
//           </thead>
//           <tbody>
//             {appointments.map((appointment) => (
//               <tr key={appointment.appointmentID}>
//                 <td>{appointment.appointmentID}</td>
//                 <td>{appointment.appointmentDate}</td>
//                 <td>{appointment.reason}</td>
//                 <td>
//                   <Button
//                     variant="primary"
//                     onClick={() => this.handleUpdateClick(appointment)}
//                   >
//                     Update
//                   </Button>
//                 </td>
//               </tr>
//             ))}
//           </tbody>
//         </Table>

//         {/* Form to update the selected appointment */}
//         {selectedAppointment && (
//           <Card className="mt-4">
//             <Card.Body>
//               <h5 className="text-center">Update Appointment</h5>
//               <Form onSubmit={this.handleSubmit}>
//                 <Form.Group controlId="appointmentDate" className="mb-3">
//                   <Form.Label>Appointment Date</Form.Label>
//                   <Form.Control
//                     type="date"
//                     name="appointmentDate"
//                     value={appointmentDate}
//                     onChange={this.handleChange}
//                     required
//                   />
//                 </Form.Group>
//                 <Form.Group controlId="reason" className="mb-3">
//                   <Form.Label>Reason</Form.Label>
//                   <Form.Control
//                     type="text"
//                     name="reason"
//                     value={reason}
//                     onChange={this.handleChange}
//                     required
//                     placeholder="Enter reason"
//                   />
//                 </Form.Group>
//                 <Button variant="success" type="submit" className="w-100">
//                   Save Changes
//                 </Button>
//               </Form>
//             </Card.Body>
//           </Card>
//         )}
//       </Container>
//     );
//   }
// }

// 

import React, { Component } from "react";
import { Container, Table, Button, Form, Card } from "react-bootstrap";
import { UserContext } from "../Login/login.jsx"; // Import UserContext for accessing context data

export default class AppointmentList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      selectedAppointment: null, // Stores the appointment being updated
      appointmentDate: "",
      reason: "",
      errorMessage: "",
      successMessage: "",
    };
  }

  static contextType = UserContext;

  // Handles input changes in the form
  handleChange = (e) => {
    const { name, value } = e.target;
    this.setState({ [name]: value });
  };

  // Handles the Update button click and sets the selected appointment
  handleUpdateClick = (appointment) => {
    this.setState({
      selectedAppointment: appointment,
      appointmentDate: appointment.appointmentDate,
      reason: appointment.reason,
      errorMessage: "",
      successMessage: "",
    });
  };

  // Handles the form submission to update the appointment
  handleSubmit = (e) => {
    e.preventDefault();

    const { selectedAppointment, appointmentDate, reason } = this.state;

    // Prepare request body
    const updatedAppointment = { appointmentDate, reason };

    fetch(
      `http://localhost:8080/appointment/updateAppointments/${selectedAppointment.appointmentID}`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(updatedAppointment),
      }
    )
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to update appointment");
        }
        return response.text(); // Expect plain text response
      })
      .then((message) => {
        this.setState({
          successMessage: message, // Display the plain text success message
          errorMessage: "",
        });

        // Update context with new appointment data
        const updatedAppointments = this.context.appointments.map(
          (appointment) =>
            appointment.appointmentID === selectedAppointment.appointmentID
              ? { ...appointment, appointmentDate, reason }
              : appointment
        );
        this.context.setAppointments(updatedAppointments); // Update context

        // Reset selected appointment after success
        this.setState({ selectedAppointment: null });
      })
      .catch((error) => {
        this.setState({
          errorMessage: error.message || "An error occurred",
          successMessage: "",
        });
      });
  };

  render() {
    const { appointments } = this.context; // Fetch appointments from context
    const {
      selectedAppointment,
      appointmentDate,
      reason,
      errorMessage,
      successMessage,
    } = this.state;

    return (
      <Container className="mt-5">
        <h3 className="text-center mb-4">Appointments</h3>

        {/* Display success or error messages */}
        {errorMessage && (
          <div className="text-danger text-center mb-3">{errorMessage}</div>
        )}
        {successMessage && (
          <div className="text-success text-center mb-3">{successMessage}</div>
        )}

        {/* Conditional rendering for appointments */}
        {appointments.length === 0 ? (
          <Card className="mt-4 text-center">
            <Card.Body>
              <Card.Title>No Appointments Found</Card.Title>
            </Card.Body>
          </Card>
        ) : (
          <>
            {/* Table for displaying appointments */}
            <Table striped bordered hover>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Date</th>
                  <th>Reason</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {appointments.map((appointment) => (
                  <tr key={appointment.appointmentID}>
                    <td>{appointment.appointmentID}</td>
                    <td>{appointment.appointmentDate}</td>
                    <td>{appointment.reason}</td>
                    <td>
                      <Button
                        variant="primary"
                        onClick={() => this.handleUpdateClick(appointment)}
                      >
                        Update
                      </Button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>

            {/* Form to update the selected appointment */}
            {selectedAppointment && (
              <Card className="mt-4">
                <Card.Body>
                  <h5 className="text-center">Update Appointment</h5>
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
                        name="reason"
                        value={reason}
                        onChange={this.handleChange}
                        required
                        placeholder="Enter reason"
                      />
                    </Form.Group>
                    <Button variant="success" type="submit" className="w-100">
                      Save Changes
                    </Button>
                  </Form>
                </Card.Body>
              </Card>
            )}
          </>
        )}
      </Container>
    );
  }
}
