import React, { Component } from "react";
import { Container, Form, Button, Card, Row, Col } from "react-bootstrap";
import Main from "../mainPageComponents/main.jsx";

export default class patlogin extends Component {
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

    console.log("Request Body:", requestBody);

    fetch("http://localhost:8080/loginPage/patientLogin", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(requestBody),
    })
      .then((response) => {
        console.log("Response status:", response.status);
        return response.json().then((data) => {
          console.log("Response data:", data);
          if (!response.ok) {
            throw new Error(`Login failed: ${data.message || response.status}`);
          }
          return data;
        });
      })
      .then((data) => {
        if (data && data.patientId) {
          this.setState({ isLoggedIn: true, patient: data, errorMessage: "" });
        } else {
          this.setState({ errorMessage: "Invalid credentials" });
        }
      })
      .catch((error) => {
        console.error("Error during login:", error);
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
      console.log(patient);

      return <Main patient={patient} />;
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

// import React, { Component } from "react";
// import { Container, Form, Button, Card, Row, Col } from "react-bootstrap";
// import Main from "../mainPageComponents/main.jsx";

// export default class LoginSelector extends Component {
//   constructor(props) {
//     super(props);
//     this.state = {
//       email: "",
//       password: "",
//       loginType: "Patient", // Default login type
//       isLoggedIn: false,
//       user: null,
//       errorMessage: "",
//     };
//   }

//   handleLoginTypeSelect = (type) => {
//     this.setState({
//       loginType: type,
//       email: "",
//       password: "",
//       errorMessage: "",
//     });
//   };

//   handleChange = (e) => {
//     const { name, value } = e.target;
//     this.setState({ [name]: value });
//   };

//   handleSubmit = (e) => {
//     e.preventDefault();
//     const { email, password, loginType } = this.state;

//     const requestBody = {
//       patientEmail: loginType === "Patient" ? email : undefined,
//       patientPassword: loginType === "Patient" ? password : undefined,
//       adminEmail: loginType === "Admin" ? email : undefined,
//       adminPassword: loginType === "Admin" ? password : undefined,
//     };

//     const url =
//       loginType === "Admin"
//         ? "http://localhost:8080/admin/adminLogin"
//         : "http://localhost:8080/loginPage/patientLogin";

//     fetch(url, {
//       method: "POST",
//       headers: { "Content-Type": "application/json" },
//       body: JSON.stringify(requestBody),
//     })
//       .then((response) => {
//         if (!response.ok) {
//           return response.json().then((data) => {
//             throw new Error(data.message || "Login failed");
//           });
//         }
//         return response.json();
//       })
//       .then((data) => {
//         if (
//           (loginType === "Patient" && data.patientId) ||
//           (loginType === "Admin" && data.adminId)
//         ) {
//           this.setState({ isLoggedIn: true, user: data, errorMessage: "" });
//         } else {
//           this.setState({ errorMessage: "Invalid credentials" });
//         }
//       })
//       .catch((error) => {
//         console.error("Error during login:", error);
//         this.setState({ errorMessage: "Login failed, please try again." });
//       });
//   };

//   render() {
//     const { email, password, isLoggedIn, errorMessage, user, loginType } =
//       this.state;

//     if (isLoggedIn) {
//       return <Main user={user} />;
//     }

//     return (
//       <Container
//         fluid
//         className="d-flex justify-content-center align-items-center"
//         style={{
//           minHeight: "100vh",
//           background: "linear-gradient(135deg, #f0f8ff, #87ceeb)",
//         }}
//       >
//         <Row className="w-100 justify-content-center">
//           <Col xs={12} md={6} lg={4}>
//             <Card
//               className="shadow-lg p-4 rounded-4"
//               style={{ borderRadius: "1rem" }}
//             >
//               <Card.Body>
//                 <Row className="text-center mb-4">
//                   <Col>
//                     <Button
//                       variant={
//                         loginType === "Admin" ? "primary" : "outline-primary"
//                       }
//                       onClick={() => this.handleLoginTypeSelect("Admin")}
//                       className="w-100 py-2 mb-2"
//                       style={{ fontWeight: "bold", borderRadius: "1rem" }}
//                     >
//                       Admin Login
//                     </Button>
//                   </Col>
//                   <Col>
//                     <Button
//                       variant={
//                         loginType === "Patient" ? "primary" : "outline-primary"
//                       }
//                       onClick={() => this.handleLoginTypeSelect("Patient")}
//                       className="w-100 py-2 mb-2"
//                       style={{ fontWeight: "bold", borderRadius: "1rem" }}
//                     >
//                       Patient Login
//                     </Button>
//                   </Col>
//                 </Row>
//                 <h3 className="text-center mb-4" style={{ color: "#1976d2" }}>
//                   {loginType} Login
//                 </h3>
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
//                       name="email"
//                       value={email}
//                       onChange={this.handleChange}
//                       required
//                       className="rounded-pill"
//                       style={{ padding: "0.75rem", borderColor: "#1976d2" }}
//                     />
//                   </Form.Group>
//                   <Form.Group controlId="password" className="mb-3">
//                     <Form.Label>Password</Form.Label>
//                     <Form.Control
//                       type="password"
//                       placeholder="Enter password"
//                       name="password"
//                       value={password}
//                       onChange={this.handleChange}
//                       required
//                       className="rounded-pill"
//                       style={{ padding: "0.75rem", borderColor: "#1976d2" }}
//                     />
//                   </Form.Group>
//                   <Button
//                     variant="primary"
//                     type="submit"
//                     className="w-100 rounded-pill"
//                     style={{ padding: "0.75rem" }}
//                   >
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
