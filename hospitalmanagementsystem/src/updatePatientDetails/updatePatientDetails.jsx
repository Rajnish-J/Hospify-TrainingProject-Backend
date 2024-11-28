// import React, { Component } from "react";
// import { Container, Form, Button, Card, Row, Col } from "react-bootstrap";
// import { UserContext } from "../Login/login.jsx"; // Importing the UserContext

// export default class UpdatePatientDetails extends Component {
//   static contextType = UserContext; // Access the UserContext directly in class component

//   constructor(props) {
//     super(props);
//     this.state = {
//       updatedFields: {}, // Stores only the fields that are modified
//       errorMessage: "",
//       successMessage: "",
//     };
//   }

//   handleChange = (e) => {
//     const { name, value } = e.target;
//     this.setState((prevState) => ({
//       updatedFields: { ...prevState.updatedFields, [name]: value },
//     }));
//   };

//   handleSubmit = (e) => {
//     e.preventDefault();

//     const { updatedFields } = this.state;
//     const patientContext = this.context;

//     if (!patientContext) {
//       this.setState({ errorMessage: "Patient data is not available." });
//       return;
//     }

//     const { patientId, updatePatientContext } = patientContext;

//     // Merge updated fields with context data to form the complete payload
//     const updatedPatient = {
//       ...patientContext,
//       ...updatedFields,
//     };

//     console.log("Updated Patient Data:", updatedPatient);
//     console.log("Patient ID:", patientId);

//     fetch(`http://localhost:8080/patient/update/${patientId}`, {
//       method: "PUT",
//       headers: { "Content-Type": "application/json" },
//       body: JSON.stringify(updatedPatient),
//     })
//       .then((response) => {
//         if (!response.ok) {
//           return response.json().then((data) => {
//             throw new Error(data.message || "Update failed");
//           });
//         }
//         return response.json();
//       })
//       .then((data) => {
//         console.log(data);
//         this.setState({
//           successMessage: "Details updated successfully!",
//           errorMessage: "",
//         });
//         updatePatientContext(data); // Update the context with new patient details
//       })
//       .catch((error) => {
//         this.setState({
//           errorMessage: error.message,
//           successMessage: "",
//         });
//       });
//   };

//   render() {
//     const { updatedFields, errorMessage, successMessage } = this.state;
//     const patientContext = this.context;

//     if (!patientContext) {
//       return <p>Loading patient data...</p>;
//     }

//     const { firstName, lastName, dob, patientPhone, patientEmail, gender } =
//       patientContext;

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
//                 <h3 className="text-center mb-4">Update Details</h3>
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
//                   <Form.Group controlId="firstName" className="mb-3">
//                     <Form.Label>First Name</Form.Label>
//                     <Form.Control
//                       type="text"
//                       placeholder={firstName}
//                       name="firstName"
//                       value={updatedFields.firstName || ""}
//                       onChange={this.handleChange}
//                     />
//                   </Form.Group>
//                   <Form.Group controlId="lastName" className="mb-3">
//                     <Form.Label>Last Name</Form.Label>
//                     <Form.Control
//                       type="text"
//                       placeholder={lastName}
//                       name="lastName"
//                       value={updatedFields.lastName || ""}
//                       onChange={this.handleChange}
//                     />
//                   </Form.Group>
//                   <Form.Group controlId="dob" className="mb-3">
//                     <Form.Label>Date of Birth</Form.Label>
//                     <Form.Control
//                       type="date"
//                       placeholder={dob}
//                       name="dob"
//                       value={updatedFields.dob || ""}
//                       onChange={this.handleChange}
//                     />
//                   </Form.Group>
//                   <Form.Group controlId="patientPhone" className="mb-3">
//                     <Form.Label>Phone</Form.Label>
//                     <Form.Control
//                       type="text"
//                       placeholder={patientPhone}
//                       name="patientPhone"
//                       value={updatedFields.patientPhone || ""}
//                       onChange={this.handleChange}
//                     />
//                   </Form.Group>
//                   <Form.Group controlId="patientEmail" className="mb-3">
//                     <Form.Label>Email</Form.Label>
//                     <Form.Control
//                       type="email"
//                       placeholder={patientEmail}
//                       name="patientEmail"
//                       value={updatedFields.patientEmail || ""}
//                       onChange={this.handleChange}
//                     />
//                   </Form.Group>
//                   <Form.Group controlId="gender" className="mb-3">
//                     <Form.Label>Gender</Form.Label>
//                     <Form.Select
//                       name="gender"
//                       value={updatedFields.gender || gender}
//                       onChange={this.handleChange}
//                     >
//                       <option value="Male">Male</option>
//                       <option value="Female">Female</option>
//                       <option value="Other">Other</option>
//                     </Form.Select>
//                   </Form.Group>
//                   <Button variant="primary" type="submit" className="w-100">
//                     Update
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
import { UserContext } from "../../src/Login/login.jsx"; // Import UserContext

export default class UpdatePatientDetails extends Component {
  static contextType = UserContext;

  constructor(props) {
    super(props);
    this.state = {
      updatedFields: {},
      errorMessage: "",
      successMessage: "",
    };
  }

  handleChange = (e) => {
    const { name, value } = e.target;
    this.setState((prevState) => ({
      updatedFields: { ...prevState.updatedFields, [name]: value },
    }));
  };

  handleSubmit = (e) => {
    e.preventDefault();
    const { updatedFields } = this.state;
    const patientContext = this.context;

    if (!patientContext || !patientContext.patientId) {
      this.setState({ errorMessage: "Patient data is not available." });
      return;
    }

    const { patientId, updatePatientContext } = patientContext;

    const updatedPatient = {
      ...patientContext,
      ...updatedFields,
    };

    fetch(`http://localhost:8080/patient/update/${patientId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(updatedPatient),
    })
      .then((response) => {
        if (!response.ok) {
          return response.json().then((data) => {
            throw new Error(data.message || "Update failed");
          });
        }
        return response.json();
      })
      .then((data) => {
        this.setState({
          successMessage: "Details updated successfully!",
          errorMessage: "",
        });
        updatePatientContext(data);
      })
      .catch((error) => {
        this.setState({
          errorMessage: error.message,
          successMessage: "",
        });
      });
  };

  render() {
    const { updatedFields, errorMessage, successMessage } = this.state;
    const patientContext = this.context;

    if (!patientContext) {
      return <p>Loading patient data...</p>;
    }

    const { firstName, lastName, dob, patientPhone, patientEmail, gender } =
      patientContext;

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
                <h3 className="text-center mb-4">Update Details</h3>
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
                  <Form.Group controlId="firstName" className="mb-3">
                    <Form.Label>First Name</Form.Label>
                    <Form.Control
                      type="text"
                      placeholder={firstName}
                      name="firstName"
                      value={updatedFields.firstName || ""}
                      onChange={this.handleChange}
                    />
                  </Form.Group>
                  <Form.Group controlId="lastName" className="mb-3">
                    <Form.Label>Last Name</Form.Label>
                    <Form.Control
                      type="text"
                      placeholder={lastName}
                      name="lastName"
                      value={updatedFields.lastName || ""}
                      onChange={this.handleChange}
                    />
                  </Form.Group>
                  <Form.Group controlId="dob" className="mb-3">
                    <Form.Label>Date of Birth</Form.Label>
                    <Form.Control
                      type="date"
                      placeholder={dob}
                      name="dob"
                      value={updatedFields.dob || ""}
                      onChange={this.handleChange}
                    />
                  </Form.Group>
                  <Form.Group controlId="patientPhone" className="mb-3">
                    <Form.Label>Phone</Form.Label>
                    <Form.Control
                      type="text"
                      placeholder={patientPhone}
                      name="patientPhone"
                      value={updatedFields.patientPhone || ""}
                      onChange={this.handleChange}
                    />
                  </Form.Group>
                  <Form.Group controlId="patientEmail" className="mb-3">
                    <Form.Label>Email</Form.Label>
                    <Form.Control
                      type="email"
                      placeholder={patientEmail}
                      name="patientEmail"
                      value={updatedFields.patientEmail || ""}
                      onChange={this.handleChange}
                    />
                  </Form.Group>
                  <Form.Group controlId="gender" className="mb-3">
                    <Form.Label>Gender</Form.Label>
                    <Form.Select
                      name="gender"
                      value={updatedFields.gender || gender}
                      onChange={this.handleChange}
                    >
                      <option value="Male">Male</option>
                      <option value="Female">Female</option>
                      <option value="Other">Other</option>
                    </Form.Select>
                  </Form.Group>
                  <Button variant="primary" type="submit" className="w-100">
                    Update
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
