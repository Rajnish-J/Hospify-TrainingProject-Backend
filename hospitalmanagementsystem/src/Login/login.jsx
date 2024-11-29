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

// import React, { Component, createContext } from "react";
// import { Container, Form, Button, Card, Row, Col } from "react-bootstrap";
// import Main from "../mainPageComponents/main.jsx";

// // Define the UserContext with proper structure
// export const UserContext = createContext({
//   patientId: null,
//   appointments: [],
//   setAppointments: () => {}, // Update appointments function
//   updatePatientContext: () => {}, // Update patient details in context
// });

// export default class PatLogin extends Component {
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

//     fetch("http://localhost:8080/loginPage/patientLogin", {
//       method: "POST",
//       headers: { "Content-Type": "application/json" },
//       body: JSON.stringify(requestBody),
//     })
//       .then((response) => response.json())
//       .then((data) => {
//         if (data && data.patientId) {
//           this.setState({ isLoggedIn: true, patient: data, errorMessage: "" });
//         } else {
//           this.setState({ errorMessage: "Invalid credentials" });
//         }
//       })
//       .catch((error) => {
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
//       return (
//         <UserContext.Provider
//           value={{
//             ...patient,
//             setAppointments: (appointments) =>
//               this.setState((prevState) => ({
//                 patient: { ...prevState.patient, appointments },
//               })),
//             updatePatientContext: (updatedPatient) =>
//               this.setState({ patient: updatedPatient }),
//           }}
//         >
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

// import React, { Component, createContext } from "react";
// import { Container, Row, Col, Form, Button, Card, Navbar, Nav } from "react-bootstrap";
// import { FaTimes } from "react-icons/fa";
// import Main from "../mainPageComponents/main.jsx";
// import "../Login/login.css"; // Add necessary CSS for blur effect and layout

// // Define the UserContext
// export const UserContext = createContext();

// export default class PatLogin extends Component {
//   constructor(props) {
//     super(props);
//     this.state = {
//       patient_email: "",
//       patient_password: "",
//       isLoggedIn: false,
//       patient: null,
//       showLogin: false,
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

//     fetch("http://localhost:8080/loginPage/patientLogin", {
//       method: "POST",
//       headers: { "Content-Type": "application/json" },
//       body: JSON.stringify(requestBody),
//     })
//       .then((response) => response.json())
//       .then((data) => {
//         if (data && data.patientId) {
//           this.setState({ isLoggedIn: true, patient: data, errorMessage: "" });
//         } else {
//           this.setState({ errorMessage: "Invalid credentials" });
//         }
//       })
//       .catch(() => {
//         this.setState({ errorMessage: "Login failed, please try again." });
//       });
//   };

//   toggleLogin = () => {
//     this.setState({ showLogin: !this.state.showLogin });
//   };

//   render() {
//     const { patient_email, patient_password, isLoggedIn, errorMessage, showLogin, patient } = this.state;

//     if (isLoggedIn) {
//       return (
//         <UserContext.Provider
//           value={{
//             ...patient,
//             setAppointments: (appointments) =>
//               this.setState((prevState) => ({
//                 patient: { ...prevState.patient, appointments },
//               })),
//           }}
//         >
//           <Main />
//         </UserContext.Provider>
//       );
//     }

//     return (
//       <div className={`login-page ${showLogin ? "blurred" : ""}`}>
//         {/* Navbar */}
//         <Navbar bg="dark" variant="dark" expand="lg">
//           <Container>
//             <Navbar.Brand>Hospital Management</Navbar.Brand>
//             <Navbar.Toggle aria-controls="basic-navbar-nav" />
//             <Navbar.Collapse id="basic-navbar-nav">
//               <Nav className="ms-auto">
//                 <Button variant="outline-light" onClick={this.toggleLogin}>
//                   Login
//                 </Button>
//               </Nav>
//             </Navbar.Collapse>
//           </Container>
//         </Navbar>

//         {/* Landing Page Content */}
//         <Container fluid className="landing-page-content text-center py-5">
//           <h1>Welcome to the Hospital Management System</h1>
//           <p>Manage patients, appointments, and more with ease.</p>
//           <Row>
//             <Col md={4}>
//               <Card className="mb-3">
//                 <Card.Body>
//                   <Card.Title>Efficient Management</Card.Title>
//                   <Card.Text>Streamline patient and staff operations.</Card.Text>
//                 </Card.Body>
//               </Card>
//             </Col>
//             <Col md={4}>
//               <Card className="mb-3">
//                 <Card.Body>
//                   <Card.Title>Secure Data</Card.Title>
//                   <Card.Text>Maintain privacy with robust security.</Card.Text>
//                 </Card.Body>
//               </Card>
//             </Col>
//             <Col md={4}>
//               <Card className="mb-3">
//                 <Card.Body>
//                   <Card.Title>Appointment Scheduling</Card.Title>
//                   <Card.Text>Organize appointments effectively.</Card.Text>
//                 </Card.Body>
//               </Card>
//             </Col>
//           </Row>
//         </Container>

//         {/* Login Form */}
//         {showLogin && (
//           <div className="login-overlay">
//             <div className="login-card">
//               {/* Close Button */}
//               <Button variant="secondary" className="close-btn" onClick={this.toggleLogin}>
//                 <FaTimes />
//               </Button>
//               <h3 className="text-center">Patient Login</h3>
//               {errorMessage && <div className="text-danger text-center">{errorMessage}</div>}
//               <Form onSubmit={this.handleSubmit}>
//                 <Form.Group className="mb-3">
//                   <Form.Label>Email</Form.Label>
//                   <Form.Control
//                     type="email"
//                     placeholder="Enter email"
//                     name="patient_email"
//                     value={patient_email}
//                     onChange={this.handleChange}
//                     required
//                   />
//                 </Form.Group>
//                 <Form.Group className="mb-3">
//                   <Form.Label>Password</Form.Label>
//                   <Form.Control
//                     type="password"
//                     placeholder="Enter password"
//                     name="patient_password"
//                     value={patient_password}
//                     onChange={this.handleChange}
//                     required
//                   />
//                 </Form.Group>
//                 <Button type="submit" variant="primary" className="w-100">
//                   Login
//                 </Button>
//               </Form>
//             </div>
//           </div>
//         )}
//       </div>
//     );
//   }
// }

// import React, { Component, createContext } from "react";
// import { Container, Row, Col, Form, Button, Card, Navbar, Nav } from "react-bootstrap";
// import { FaTimes } from "react-icons/fa";
// import Main from "../mainPageComponents/main.jsx";
// import "../Login/login.css"; // Add necessary CSS for blur effect and layout

// // Define the UserContext
// export const UserContext = createContext();

// export default class PatLogin extends Component {
//   constructor(props) {
//     super(props);
//     this.state = {
//       patient_email: "",
//       patient_password: "",
//       isLoggedIn: false,
//       patient: null,
//       showLogin: false,
//       showSignUp: false,
//       errorMessage: "",
//       patientData: {
//         firstName: "",
//         lastName: "",
//         patientEmail: "",
//         patientPassword: "",
//         patientPhone: "",
//         dob: "",
//         gender: "",
//       },
//       signUpErrors: {},
//       signUpResponse: "",
//     };
//   }

//   // Handle changes for Login inputs
//   handleLoginChange = (e) => {
//     const { name, value } = e.target;
//     this.setState({ [name]: value });
//   };

//   // Handle Login submission
//   handleLoginSubmit = (e) => {
//     e.preventDefault();
//     const { patient_email, patient_password } = this.state;

//     const requestBody = {
//       patientEmail: patient_email,
//       patientPassword: patient_password,
//     };

//     fetch("http://localhost:8080/loginPage/patientLogin", {
//       method: "POST",
//       headers: { "Content-Type": "application/json" },
//       body: JSON.stringify(requestBody),
//     })
//       .then((response) => response.json())
//       .then((data) => {
//         if (data && data.patientId) {
//           this.setState({ isLoggedIn: true, patient: data, errorMessage: "" });
//         } else {
//           this.setState({ errorMessage: "Invalid credentials" });
//         }
//       })
//       .catch(() => {
//         this.setState({ errorMessage: "Login failed, please try again." });
//       });
//   };

//   // Handle changes for SignUp inputs
//   handleSignUpChange = (e) => {
//     const { name, value } = e.target;
//     this.setState((prevState) => ({
//       patientData: {
//         ...prevState.patientData,
//         [name]: value,
//       },
//       signUpErrors: {
//         ...prevState.signUpErrors,
//         [name]: "",
//       },
//     }));
//   };

//   // Handle SignUp submission
//   handleSignUpSubmit = (e) => {
//     e.preventDefault();
//     const { patientData } = this.state;
//     const newErrors = {};

//     // Validate required fields
//     Object.keys(patientData).forEach((key) => {
//       if (!patientData[key]) {
//         newErrors[key] = "This field is required";
//       }
//     });

//     if (Object.keys(newErrors).length > 0) {
//       this.setState({ signUpErrors: newErrors });
//     } else {
//       fetch("http://localhost:8080/patient/insert", {
//         method: "POST",
//         headers: { "Content-Type": "application/json" },
//         body: JSON.stringify(patientData),
//       })
//         .then((response) => {
//           if (!response.ok) {
//             throw new Error(`Error: ${response.status}`);
//           }
//           return response.text();
//         })
//         .then((data) => {
//           this.setState({
//             signUpResponse: data,
//             patientData: {
//               firstName: "",
//               lastName: "",
//               patientEmail: "",
//               patientPassword: "",
//               patientPhone: "",
//               dob: "",
//               gender: "",
//             },
//             showSignUp: false,
//           });
//         })
//         .catch((error) => {
//           this.setState({ signUpResponse: `Sign Up Failed: ${error.message}` });
//         });
//     }
//   };

//   toggleLogin = () => {
//     this.setState({ showLogin: !this.state.showLogin, showSignUp: false });
//   };

//   toggleSignUp = () => {
//     this.setState({ showSignUp: !this.state.showSignUp, showLogin: false });
//   };

//   render() {
//     const {
//       patient_email,
//       patient_password,
//       isLoggedIn,
//       errorMessage,
//       showLogin,
//       showSignUp,
//       patient,
//       patientData,
//       signUpErrors,
//       signUpResponse,
//     } = this.state;

//     if (isLoggedIn) {
//       return (
//         <UserContext.Provider
//           value={{
//             ...patient,
//             setAppointments: (appointments) =>
//               this.setState((prevState) => ({
//                 patient: { ...prevState.patient, appointments },
//               })),
//           }}
//         >
//           <Main />
//         </UserContext.Provider>
//       );
//     }

//     return (
//       <div className={`login-page ${showLogin || showSignUp ? "blurred" : ""}`}>
//         {/* Navbar */}
//         <Navbar bg="dark" variant="dark" expand="lg">
//           <Container>
//             <Navbar.Brand>Hospital Management</Navbar.Brand>
//             <Navbar.Toggle aria-controls="basic-navbar-nav" />
//             <Navbar.Collapse id="basic-navbar-nav">
//               <Nav className="ms-auto">
//                 <Button variant="outline-light" onClick={this.toggleLogin}>
//                   Login
//                 </Button>
//                 <Button variant="outline-light" onClick={this.toggleSignUp} className="ms-2">
//                   Sign Up
//                 </Button>
//               </Nav>
//             </Navbar.Collapse>
//           </Container>
//         </Navbar>

//         {/* Landing Page Content */}
//         <Container fluid className="landing-page-content text-center py-5">
//           <h1>Welcome to the Hospital Management System</h1>
//           <p>Manage patients, appointments, and more with ease.</p>
//           <Row>
//             <Col md={4}>
//               <Card className="mb-3">
//                 <Card.Body>
//                   <Card.Title>Efficient Management</Card.Title>
//                   <Card.Text>Streamline patient and staff operations.</Card.Text>
//                 </Card.Body>
//               </Card>
//             </Col>
//             <Col md={4}>
//               <Card className="mb-3">
//                 <Card.Body>
//                   <Card.Title>Secure Data</Card.Title>
//                   <Card.Text>Maintain privacy with robust security.</Card.Text>
//                 </Card.Body>
//               </Card>
//             </Col>
//             <Col md={4}>
//               <Card className="mb-3">
//                 <Card.Body>
//                   <Card.Title>Appointment Scheduling</Card.Title>
//                   <Card.Text>Organize appointments effectively.</Card.Text>
//                 </Card.Body>
//               </Card>
//             </Col>
//           </Row>
//         </Container>

//         {/* Login Form */}
//         {showLogin && (
//           <div className="login-overlay">
//             <div className="login-card">
//               <Button variant="secondary" className="close-btn" onClick={this.toggleLogin}>
//                 <FaTimes />
//               </Button>
//               <h3 className="text-center">Patient Login</h3>
//               {errorMessage && <div className="text-danger text-center">{errorMessage}</div>}
//               <Form onSubmit={this.handleLoginSubmit}>
//                 <Form.Group className="mb-3">
//                   <Form.Label>Email</Form.Label>
//                   <Form.Control
//                     type="email"
//                     placeholder="Enter email"
//                     name="patient_email"
//                     value={patient_email}
//                     onChange={this.handleLoginChange}
//                     required
//                   />
//                 </Form.Group>
//                 <Form.Group className="mb-3">
//                   <Form.Label>Password</Form.Label>
//                   <Form.Control
//                     type="password"
//                     placeholder="Enter password"
//                     name="patient_password"
//                     value={patient_password}
//                     onChange={this.handleLoginChange}
//                     required
//                   />
//                 </Form.Group>
//                 <Button type="submit" variant="primary" className="w-100">
//                   Login
//                 </Button>
//               </Form>
//             </div>
//           </div>
//         )}

//         {/* Sign Up Form */}
//         {showSignUp && (
//           <div className="login-overlay">
//             <div className="login-card">
//               <Button variant="secondary" className="close-btn" onClick={this.toggleSignUp}>
//                 <FaTimes />
//               </Button>
//               <h3 className="text-center">Sign Up</h3>
//               {signUpResponse && (
//                 <div className={signUpResponse.includes("Failed") ? "text-danger" : "text-success"}>
//                   {signUpResponse}
//                 </div>
//               )}
//               <Form onSubmit={this.handleSignUpSubmit}>
//                 <Row className="mb-3">
//                   <Col md={6}>
//                     <Form.Group>
//                       <Form.Label>First Name</Form.Label>
//                       <Form.Control
//                         type="text"
//                         name="firstName"
//                         value={patientData.firstName}
//                         onChange={this.handleSignUpChange}
//                         placeholder="Enter first name"
//                       />
//                       {signUpErrors.firstName && (
//                         <Form.Text className="text-danger">{signUpErrors.firstName}</Form.Text>
//                       )}
//                     </Form.Group>
//                   </Col>
//                   <Col md={6}>
//                     <Form.Group>
//                       <Form.Label>Last Name</Form.Label>
//                       <Form.Control
//                         type="text"
//                         name="lastName"
//                         value={patientData.lastName}
//                         onChange={this.handleSignUpChange}
//                         placeholder="Enter last name"
//                       />
//                       {signUpErrors.lastName && (
//                         <Form.Text className="text-danger">{signUpErrors.lastName}</Form.Text>
//                       )}
//                     </Form.Group>
//                   </Col>
//                 </Row>
//                 <Form.Group className="mb-3">
//                   <Form.Label>Email</Form.Label>
//                   <Form.Control
//                     type="email"
//                     name="patientEmail"
//                     value={patientData.patientEmail}
//                     onChange={this.handleSignUpChange}
//                     placeholder="Enter email"
//                   />
//                   {signUpErrors.patientEmail && (
//                     <Form.Text className="text-danger">{signUpErrors.patientEmail}</Form.Text>
//                   )}
//                 </Form.Group>
//                 <Form.Group className="mb-3">
//                   <Form.Label>Password</Form.Label>
//                   <Form.Control
//                     type="password"
//                     name="patientPassword"
//                     value={patientData.patientPassword}
//                     onChange={this.handleSignUpChange}
//                     placeholder="Enter password"
//                   />
//                   {signUpErrors.patientPassword && (
//                     <Form.Text className="text-danger">{signUpErrors.patientPassword}</Form.Text>
//                   )}
//                 </Form.Group>
//                 <Row className="mb-3">
//                   <Col md={6}>
//                     <Form.Group>
//                       <Form.Label>Phone</Form.Label>
//                       <Form.Control
//                         type="text"
//                         name="patientPhone"
//                         value={patientData.patientPhone}
//                         onChange={this.handleSignUpChange}
//                         placeholder="Enter phone number"
//                       />
//                       {signUpErrors.patientPhone && (
//                         <Form.Text className="text-danger">{signUpErrors.patientPhone}</Form.Text>
//                       )}
//                     </Form.Group>
//                   </Col>
//                   <Col md={6}>
//                     <Form.Group>
//                       <Form.Label>Date of Birth</Form.Label>
//                       <Form.Control
//                         type="date"
//                         name="dob"
//                         value={patientData.dob}
//                         onChange={this.handleSignUpChange}
//                       />
//                       {signUpErrors.dob && (
//                         <Form.Text className="text-danger">{signUpErrors.dob}</Form.Text>
//                       )}
//                     </Form.Group>
//                   </Col>
//                 </Row>
//                 <Form.Group className="mb-3">
//                   <Form.Label>Gender</Form.Label>
//                   <Form.Control
//                     as="select"
//                     name="gender"
//                     value={patientData.gender}
//                     onChange={this.handleSignUpChange}
//                   >
//                     <option value="">Select gender</option>
//                     <option value="Male">Male</option>
//                     <option value="Female">Female</option>
//                     <option value="Other">Other</option>
//                   </Form.Control>
//                   {signUpErrors.gender && (
//                     <Form.Text className="text-danger">{signUpErrors.gender}</Form.Text>
//                   )}
//                 </Form.Group>
//                 <Button type="submit" variant="primary" className="w-100">
//                   Sign Up
//                 </Button>
//               </Form>
//             </div>
//           </div>
//         )}
//       </div>
//     );
//   }
// }

import React, { Component, createContext } from "react";
import {
  Container,
  Row,
  Col,
  Form,
  Button,
  Card,
  Navbar,
  Nav,
} from "react-bootstrap";
import { FaTimes } from "react-icons/fa";
import Main from "../mainPageComponents/main.jsx";
import "../Login/login.css";

// Define the UserContext
export const UserContext = createContext();

export default class PatLogin extends Component {
  constructor(props) {
    super(props);
    this.state = {
      patient_email: "",
      patient_password: "",
      isLoggedIn: false,
      patient: null,
      showLogin: false,
      showSignUp: false,
      errorMessage: "",
      patientData: {
        firstName: "",
        lastName: "",
        patientEmail: "",
        patientPassword: "",
        patientPhone: "",
        dob: "",
        gender: "",
      },
      signUpErrors: {},
      signUpResponse: "",
    };
  }

  // Handle changes for Login inputs
  handleLoginChange = (e) => {
    const { name, value } = e.target;
    this.setState({ [name]: value });
  };

  // Handle Login submission
  handleLoginSubmit = (e) => {
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
      .catch(() => {
        this.setState({ errorMessage: "Login failed, please try again." });
      });
  };

  // Handle changes for SignUp inputs
  handleSignUpChange = (e) => {
    const { name, value } = e.target;
    this.setState((prevState) => ({
      patientData: {
        ...prevState.patientData,
        [name]: value,
      },
      signUpErrors: {
        ...prevState.signUpErrors,
        [name]: "",
      },
    }));
  };

  // Handle SignUp submission
  handleSignUpSubmit = (e) => {
    e.preventDefault();
    const { patientData } = this.state;
    const newErrors = {};

    // Validate required fields
    Object.keys(patientData).forEach((key) => {
      if (!patientData[key]) {
        newErrors[key] = "This field is required";
      }
    });

    if (Object.keys(newErrors).length > 0) {
      this.setState({ signUpErrors: newErrors });
    } else {
      fetch("http://localhost:8080/patient/insert", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(patientData),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
          }
          return response.text();
        })
        .then((data) => {
          this.setState({
            signUpResponse: data,
            patientData: {
              firstName: "",
              lastName: "",
              patientEmail: "",
              patientPassword: "",
              patientPhone: "",
              dob: "",
              gender: "",
            },
            showSignUp: false,
          });
        })
        .catch((error) => {
          this.setState({ signUpResponse: `Sign Up Failed: ${error.message}` });
        });
    }
  };

  toggleLogin = () => {
    this.setState({ showLogin: !this.state.showLogin, showSignUp: false });
  };

  toggleSignUp = () => {
    this.setState({ showSignUp: !this.state.showSignUp, showLogin: false });
  };

  render() {
    const {
      patient_email,
      patient_password,
      isLoggedIn,
      errorMessage,
      showLogin,
      showSignUp,
      patient,
      patientData,
      signUpErrors,
      signUpResponse,
    } = this.state;

    if (isLoggedIn) {
      return (
        <UserContext.Provider
          value={{
            ...patient,
            updatePatientContext: (updatedData) =>
              this.setState((prevState) => ({
                patient: { ...prevState.patient, ...updatedData },
              })),
            setAppointments: (appointments) =>
              this.setState((prevState) => ({
                patient: { ...prevState.patient, appointments },
              })), 
          }}
        >
          <Main />
        </UserContext.Provider>
      );
    }

    return (
      <div className={`login-page ${showLogin || showSignUp ? "blurred" : ""}`}>
        {/* Navbar */}
        <Navbar bg="dark" variant="dark" expand="lg">
          <Container>
            <Navbar.Brand>Hospital Management</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="ms-auto">
                <Button variant="outline-light" onClick={this.toggleLogin}>
                  Login
                </Button>
                <Button
                  variant="outline-light"
                  onClick={this.toggleSignUp}
                  className="ms-2"
                >
                  Sign Up
                </Button>
              </Nav>
            </Navbar.Collapse>
          </Container>
        </Navbar>

        {/* Landing Page Content */}
        <Container fluid className="landing-page-content text-center py-5">
          <h1>Welcome to the Hospital Management System</h1>
          <p>Manage patients, appointments, and more with ease.</p>
          <Row>
            <Col md={4}>
              <Card className="mb-3">
                <Card.Body>
                  <Card.Title>Efficient Management</Card.Title>
                  <Card.Text>
                    Streamline patient and staff operations.
                  </Card.Text>
                </Card.Body>
              </Card>
            </Col>
            <Col md={4}>
              <Card className="mb-3">
                <Card.Body>
                  <Card.Title>Secure Data</Card.Title>
                  <Card.Text>Maintain privacy with robust security.</Card.Text>
                </Card.Body>
              </Card>
            </Col>
            <Col md={4}>
              <Card className="mb-3">
                <Card.Body>
                  <Card.Title>Appointment Scheduling</Card.Title>
                  <Card.Text>Organize appointments effectively.</Card.Text>
                </Card.Body>
              </Card>
            </Col>
          </Row>
        </Container>

        {/* Login Form */}
        {showLogin && (
          <div className="login-overlay">
            <div className="login-card">
              <Button
                variant="secondary"
                className="close-btn"
                onClick={this.toggleLogin}
              >
                <FaTimes />
              </Button>
              <h3 className="text-center">Patient Login</h3>
              {errorMessage && (
                <div className="text-danger text-center">{errorMessage}</div>
              )}
              <Form onSubmit={this.handleLoginSubmit}>
                <Form.Group className="mb-3">
                  <Form.Label>Email</Form.Label>
                  <Form.Control
                    type="email"
                    placeholder="Enter email"
                    name="patient_email"
                    value={patient_email}
                    onChange={this.handleLoginChange}
                    required
                  />
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    placeholder="Enter password"
                    name="patient_password"
                    value={patient_password}
                    onChange={this.handleLoginChange}
                    required
                  />
                </Form.Group>
                <Button type="submit" variant="primary" className="w-100">
                  Login
                </Button>
              </Form>
            </div>
          </div>
        )}

        {/* Sign Up Form */}
        {showSignUp && (
          <div className="login-overlay">
            <div className="login-card">
              <Button
                variant="secondary"
                className="close-btn"
                onClick={this.toggleSignUp}
              >
                <FaTimes />
              </Button>
              <h3 className="text-center">Sign Up</h3>
              {signUpResponse && (
                <div
                  className={
                    signUpResponse.includes("Failed")
                      ? "text-danger"
                      : "text-success"
                  }
                >
                  {signUpResponse}
                </div>
              )}
              <Form onSubmit={this.handleSignUpSubmit}>
                <Row className="mb-3">
                  <Col md={6}>
                    <Form.Group>
                      <Form.Label>First Name</Form.Label>
                      <Form.Control
                        type="text"
                        name="firstName"
                        value={patientData.firstName}
                        onChange={this.handleSignUpChange}
                        placeholder="Enter first name"
                      />
                      {signUpErrors.firstName && (
                        <Form.Text className="text-danger">
                          {signUpErrors.firstName}
                        </Form.Text>
                      )}
                    </Form.Group>
                  </Col>
                  <Col md={6}>
                    <Form.Group>
                      <Form.Label>Last Name</Form.Label>
                      <Form.Control
                        type="text"
                        name="lastName"
                        value={patientData.lastName}
                        onChange={this.handleSignUpChange}
                        placeholder="Enter last name"
                      />
                      {signUpErrors.lastName && (
                        <Form.Text className="text-danger">
                          {signUpErrors.lastName}
                        </Form.Text>
                      )}
                    </Form.Group>
                  </Col>
                </Row>
                <Form.Group className="mb-3">
                  <Form.Label>Email</Form.Label>
                  <Form.Control
                    type="email"
                    name="patientEmail"
                    value={patientData.patientEmail}
                    onChange={this.handleSignUpChange}
                    placeholder="Enter email"
                  />
                  {signUpErrors.patientEmail && (
                    <Form.Text className="text-danger">
                      {signUpErrors.patientEmail}
                    </Form.Text>
                  )}
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    name="patientPassword"
                    value={patientData.patientPassword}
                    onChange={this.handleSignUpChange}
                    placeholder="Enter password"
                  />
                  {signUpErrors.patientPassword && (
                    <Form.Text className="text-danger">
                      {signUpErrors.patientPassword}
                    </Form.Text>
                  )}
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label>Phone Number</Form.Label>
                  <Form.Control
                    type="text"
                    name="patientPhone"
                    value={patientData.patientPhone}
                    onChange={this.handleSignUpChange}
                    placeholder="Enter phone number"
                  />
                  {signUpErrors.patientPhone && (
                    <Form.Text className="text-danger">
                      {signUpErrors.patientPhone}
                    </Form.Text>
                  )}
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label>Date of Birth</Form.Label>
                  <Form.Control
                    type="date"
                    name="dob"
                    value={patientData.dob}
                    onChange={this.handleSignUpChange}
                  />
                  {signUpErrors.dob && (
                    <Form.Text className="text-danger">
                      {signUpErrors.dob}
                    </Form.Text>
                  )}
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label>Gender</Form.Label>
                  <Form.Select
                    name="gender"
                    value={patientData.gender}
                    onChange={this.handleSignUpChange}
                  >
                    <option value="">Select Gender</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                  </Form.Select>
                  {signUpErrors.gender && (
                    <Form.Text className="text-danger">
                      {signUpErrors.gender}
                    </Form.Text>
                  )}
                </Form.Group>
                <Button type="submit" variant="primary" className="w-100">
                  Sign Up
                </Button>
              </Form>
            </div>
          </div>
        )}
      </div>
    );
  }
}
