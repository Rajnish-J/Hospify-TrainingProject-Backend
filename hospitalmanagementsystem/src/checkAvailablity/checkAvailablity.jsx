import React, { Component } from "react";
import { Form, Button, Card } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

class AppointmentChecker extends Component {
  constructor(props) {
    super(props);
    this.state = {
      date: "",
      message: "",
      messageType: "",
    };
  }

  handleInputChange = (event) => {
    this.setState({ date: event.target.value, message: "", messageType: "" });
  };

  handleSubmit = (event) => {
    event.preventDefault();
    const { date } = this.state;
    const { navigate } = this.props;

    // Validate if the selected date is a past date
    const selectedDate = new Date(date);
    const currentDate = new Date();

    // Set time to 00:00:00 for comparison
    selectedDate.setHours(0, 0, 0, 0);
    currentDate.setHours(0, 0, 0, 0);

    if (selectedDate < currentDate) {
      this.setState({
        message:
          "Appointments cannot be booked for past dates. Please select a future date.",
        messageType: "danger",
      });
      return; // Exit the function to prevent further execution
    }

    // Fetch API to get appointment count
    fetch(`http://localhost:8080/appointment/countOfAppointmentsByDate/${date}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((count) => {
        if (count < 5) {
          this.setState({
            message:
              "Appointments are available, you will be redirected to book appointment page.",
            messageType: "success",
          });

          // Redirect after 3 seconds
          setTimeout(() => {
            navigate("/AddAppointments");
          }, 1500);
        } else {
          this.setState({
            message:
              "All appointments are booked for this day already. Please select another convenient day for consulting doctors.",
            messageType: "danger",
          });
        }
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
        this.setState({
          message: "An error occurred while fetching the data.",
          messageType: "warning",
        });
      });
  };

  render() {
    const { date, message, messageType } = this.state;

    return (
      <div className="container mt-5 d-flex justify-content-center align-items-center font">
        <Card className="p-4 w-50 shadow-sm">
          <h2>Check Appointments Availability</h2>
          <Form onSubmit={this.handleSubmit}>
            <Form.Group controlId="dateInput" className="mb-3">
              <Form.Label>Enter Date</Form.Label>
              <Form.Control
                type="date"
                value={date}
                onChange={this.handleInputChange}
                required
              />
            </Form.Group>
            <Button variant="primary" type="submit">
              Check Availability
            </Button>
          </Form>

          {/* Conditional rendering of message */}
          {message && (
            <Card
              className={`mt-4 p-3 rounded-3 ${
                messageType === "success"
                  ? "bg-success text-white"
                  : messageType === "danger"
                  ? "bg-danger text-white"
                  : "bg-warning text-dark"
              }`}
              style={{ fontWeight: "bold" }}
            >
              {message}
            </Card>
          )}
        </Card>
      </div>
    );
  }
}

// Wrapper Component to Use Hooks in a Class Component
function AppointmentCheckerWithNavigate() {
  const navigate = useNavigate();
  return <AppointmentChecker navigate={navigate} />;
}

export default AppointmentCheckerWithNavigate;
