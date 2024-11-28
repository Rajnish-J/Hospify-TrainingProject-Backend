// import React, { Component, createContext } from "react";
// import { Container, Form, Button, Card, Row, Col } from "react-bootstrap";
// import Main from "../mainPageComponents/main.jsx";

// // exports the user context
// export const UserContext = createContext({
//   patientId: null,
//   appointments: [],
//   setAppointments: () => {}, // Update function for appointments
// });

// export default class patlogin extends Component {
//   constructor(props) {
//     super(props);

//     this.state = {
//       patient_email: "",
//       patient_password: "",
//       isLoggedIn: false,
//       patient: null,
//       errorMessage: "",
//     };
//   }

//   handleChange = (e) => {
//     const { name, value } = e.target;
//     this.setState({ [name]: value });
//   };

//   handleSubmit = (e) => {
//     e.preventDefault();
//     const { patient_email, patient_password } = this.state;

//     const requestBody = {
//       patientEmail: patient_email,
//       patientPassword: patient_password,
//     };

//     // ! log checking for the request body
//     // console.log("Request Body:", requestBody);

//     fetch("http://localhost:8080/loginPage/patientLogin", {
//       method: "POST",
//       headers: { "Content-Type": "application/json" },
//       body: JSON.stringify(requestBody),
//     })
//       .then((response) => {
//         console.log("Response status:", response.status);
//         return response.json().then((data) => {
//           console.log("Response data:", data);
//           if (!response.ok) {
//             throw new Error(`Login failed: ${data.message || response.status}`);
//           }
//           return data;
//         });
//       })
//       .then((data) => {
//         if (data && data.patientId) {
//           this.setState({ isLoggedIn: true, patient: data, errorMessage: "" });
//         } else {
//           this.setState({ errorMessage: "Invalid credentials" });
//         }
//       })
//       .catch((error) => {
//         // console.error("Error during login:", error);
//         this.setState({ errorMessage: "Login failed, please try again." });
//       });
//   };

//   render() {
//     const {
//       patient_email,
//       patient_password,
//       isLoggedIn,
//       errorMessage,
//       patient,
//     } = this.state;

//     if (isLoggedIn) {
//       // ! log for checking the patient object
//       // console.log(patient);

//       return (
//         <UserContext.Provider value={patient}>
//           <Main />
//         </UserContext.Provider>
//       );
//     }

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
//                 <h3 className="text-center mb-4">User Login</h3>
//                 {errorMessage && (
//                   <div className="text-danger mt-3 text-center">
//                     {errorMessage}
//                   </div>
//                 )}
//                 <Form onSubmit={this.handleSubmit}>
//                   <Form.Group controlId="email" className="mb-3">
//                     <Form.Label>Email</Form.Label>
//                     <Form.Control
//                       type="email"
//                       placeholder="Enter email"
//                       name="patient_email"
//                       value={patient_email}
//                       onChange={this.handleChange}
//                       required
//                     />
//                   </Form.Group>
//                   <Form.Group controlId="password" className="mb-3">
//                     <Form.Label>Password</Form.Label>
//                     <Form.Control
//                       type="password"
//                       placeholder="Enter password"
//                       name="patient_password"
//                       value={patient_password}
//                       onChange={this.handleChange}
//                       required
//                     />
//                   </Form.Group>
//                   <Button variant="primary" type="submit" className="w-100">
//                     Login
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

import React, { Component, createContext } from "react";
import { Container, Form, Button, Card, Row, Col } from "react-bootstrap";
import Main from "../mainPageComponents/main.jsx";

// Define the UserContext with proper structure
export const UserContext = createContext({
  patientId: null,
  appointments: [],
  setAppointments: () => {}, // Update appointments function
  updatePatientContext: () => {}, // Update patient details in context
});

export default class PatLogin extends Component {
  constructor(props) {
    super(props);

    this.state = {
      patient_email: "",
      patient_password: "",
      isLoggedIn: false,
      patient: null,
      errorMessage: "",
    };
  }

  handleChange = (e) => {
    const { name, value } = e.target;
    this.setState({ [name]: value });
  };

  handleSubmit = (e) => {
    e.preventDefault();
    const { patient_email, patient_password } = this.state;

    const requestBody = {
      patientEmail: patient_email,
      patientPassword: patient_password,
    };

    fetch("http://localhost:8080/loginPage/patientLogin", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(requestBody),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data && data.patientId) {
          this.setState({ isLoggedIn: true, patient: data, errorMessage: "" });
        } else {
          this.setState({ errorMessage: "Invalid credentials" });
        }
      })
      .catch((error) => {
        this.setState({ errorMessage: "Login failed, please try again." });
      });
  };

  render() {
    const {
      patient_email,
      patient_password,
      isLoggedIn,
      errorMessage,
      patient,
    } = this.state;

    if (isLoggedIn) {
      return (
        <UserContext.Provider
          value={{
            ...patient,
            setAppointments: (appointments) =>
              this.setState((prevState) => ({
                patient: { ...prevState.patient, appointments },
              })),
            updatePatientContext: (updatedPatient) =>
              this.setState({ patient: updatedPatient }),
          }}
        >
          <Main />
        </UserContext.Provider>
      );
    }

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
                <h3 className="text-center mb-4">User Login</h3>
                {errorMessage && (
                  <div className="text-danger mt-3 text-center">
                    {errorMessage}
                  </div>
                )}
                <Form onSubmit={this.handleSubmit}>
                  <Form.Group controlId="email" className="mb-3">
                    <Form.Label>Email</Form.Label>
                    <Form.Control
                      type="email"
                      placeholder="Enter email"
                      name="patient_email"
                      value={patient_email}
                      onChange={this.handleChange}
                      required
                    />
                  </Form.Group>
                  <Form.Group controlId="password" className="mb-3">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                      type="password"
                      placeholder="Enter password"
                      name="patient_password"
                      value={patient_password}
                      onChange={this.handleChange}
                      required
                    />
                  </Form.Group>
                  <Button variant="primary" type="submit" className="w-100">
                    Login
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

