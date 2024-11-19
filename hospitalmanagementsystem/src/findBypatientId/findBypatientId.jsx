import React, { Component } from "react";
import {
  Container,
  Row,
  Col,
  Form,
  Button,
  Card,
  Alert,
} from "react-bootstrap";

export default class findBypatientId extends Component {
  constructor(props) {
    super(props);

    // initilizing the required feild
    this.state = {
      patientID: "",
      patientDetails: null,
      error: null,
    };
  }

  // Hardcoded patient details
  patients = {
    1: { id: 1, name: "Rajnish", age: 22, gender: "Male", condition: "Flu" },
    2: { id: 2, name: "sabari", age: 28, gender: "male", condition: "Allergy" },
    3: { id: 3, name: "sam", age: 25, gender: "Male", condition: "Diabetes" },
  };

  // * handles the input change
  handleInput = (e) => {
    this.setState({
      patientID: e.target.value,
      error: null,
      patientDetails: null,
    });
  };

  // * handle fetching details
  handleFetch = () => {
    const { patientID } = this.state;

    if (!patientID) {
      this.setState({
        error: "please enter the valid patient ID",
        patientDetails: null,
      });
      return;
    }

    const patientDetails = this.patients[Number(patientID)];
    if (patientDetails) {
      this.setState({ patientDetails, error: null });
    } else {
      this.setState({ patientDetails: null, error: "patient not found" });
    }
  };

  render() {
    const { patientID, patientDetails, error } = this.state;

    return (
      <div>
        {/* container tag */}
        <Container className="mt-5">
          {/* row : represents card and form tags */}
          <Row>
            {/* column */}
            <Col xs={12} md={6} className="mx-auto">
              {/* instead of putting in the div tag I used to show the form in the card */}
              <Card>
                {/* card heading */}
                <Card.Body>
                  <Card.Title className="mb-4">
                    Fetch patient detials
                  </Card.Title>

                  {/* form tag : fetch the patient ID */}
                  <Form.Group className="mb-3">
                    {/* form lable */}
                    <Form.Label>Enter patient ID</Form.Label>

                    {/* defining the form */}
                    <Form.Control
                      type="number"
                      value={patientID}
                      onChange={this.handleInput}
                      placeholder="Enter patient ID"
                    />
                  </Form.Group>

                  {/* Button tag */}
                  <Button variant="primary" onClick={this.handleFetch}>
                    Fetch
                  </Button>

                  {/* if any error exists it shows in the content part */}
                  {error && (
                    <Alert variant="danger" className="mt-3">
                      {error}
                    </Alert>
                  )}

                  {/* if the patientDetails object available respective code will execute */}
                  {patientDetails && (
                    <Card className="mt-3">
                      <Card.Body>
                        <Card.Title>Patient Details</Card.Title>
                        <Card.Text>
                          <strong>ID:</strong> {patientDetails.id} <br />
                          <strong>Name:</strong> {patientDetails.name} <br />
                          <strong>Age:</strong> {patientDetails.age} <br />
                          <strong>Gender:</strong> {patientDetails.gender}
                          <br />
                          <strong>Condition:</strong> {patientDetails.condition}
                        </Card.Text>
                      </Card.Body>
                    </Card>
                  )}
                </Card.Body>
              </Card>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}
