import React, { Component } from "react";
import { UserContext } from "../Login/login.jsx"; // Assuming this is the context where logged-in user info is stored
import { Button, Alert } from "react-bootstrap";

class PatientDelete extends Component {
  static contextType = UserContext;

  constructor(props) {
    super(props);
    this.state = {
      appointments: [],
      error: null,
      successMessage: null,
      isDeleting: false, // Track if the deletion is in progress
    };
  }

  // Fetch appointments for the logged-in patient
  componentDidMount() {
    const patId = this.context?.patientId; // Get patientId from context

    if (patId) {
      fetch(`http://localhost:8080/appointment/fetchAppointmentsForPatientID/${patId}`)
        .then((response) => response.json())
        .then((data) => {
          this.setState({ appointments: data });
        })
        .catch((error) => {
          this.setState({ error: error.message });
        });
    }
  }

  // Delete appointments for the logged-in patient
  deleteAppointments = () => {
    const { appointments } = this.state;

    if (!appointments || appointments.length === 0) {
      // No appointments, delete the patient directly
      this.deletePatient();
    } else {
      // Loop through each appointment and delete it
      appointments.forEach((appointment) => {
        fetch(`http://localhost:8080/appointment/delete/${appointment.id}`, {
          method: "DELETE",
        })
          .then((response) => response.text()) // Get the raw response text (because it could be empty)
          .then((data) => {
            if (data !== "") {
              throw new Error(`Failed to delete appointment: ${data}`);
            }
          })
          .catch((error) => {
            console.error("Error deleting appointment:", error);
            this.setState({ error: error.message });
          });
      });

      // After deleting all appointments, delete the patient account
      this.deletePatient();
    }
  };

  // Delete the patient's account
  deletePatient = () => {
    const { patientId } = this.context;

    if (!patientId) {
      this.setState({ error: "Patient ID is not found" });
      return;
    }

    this.setState({ isDeleting: true }); // Start deleting process

    fetch(`http://localhost:8080/patient/delete/${patientId}`, {
      method: "DELETE",
    })
      .then((response) => response.text()) // Get the raw response text (it could be "Deleted successfully" or an error message)
      .then((data) => {
        if (data === "Deleted successfully") {
          this.setState({ successMessage: "Your account has been successfully deleted" });
          this.handleLogout(); // Trigger logout after successful deletion
        } else if (data.includes("ERROR:")) {
          throw new Error(data); // Handle errors like "ERROR: patient ID not exist"
        }
      })
      .catch((error) => {
        console.error("Error deleting patient:", error);
        this.setState({ error: error.message });
      })
      .finally(() => {
        this.setState({ isDeleting: false }); // Stop deleting process
      });
  };

  // Handle delete button click
  handleDeleteClick = () => {
    const confirmDelete = window.confirm(
      "Are you sure you want to delete your account? All your appointments will also be deleted."
    );

    if (confirmDelete) {
      this.deleteAppointments();
    }
  };

  // Handle logout after successful deletion
  handleLogout = () => {
    // Clear user session or any stored user data (localStorage, sessionStorage, etc.)
    localStorage.removeItem("patient");

    // After logout, redirect to Login page
    window.location.href = "/login"; // Or navigate to the login route
  };

  render() {
    const { appointments, error, successMessage, isDeleting } = this.state;

    return (
      <div className="patient-delete-card">
        <h3>Delete Patient Account</h3>
        <p>If you delete your account, all your appointments will also be deleted.</p>

        {error && <Alert variant="danger">{error}</Alert>}
        {successMessage && <Alert variant="success">{successMessage}</Alert>}

        <Button
          onClick={this.handleDeleteClick}
          className="delete-button"
          variant="danger"
          disabled={isDeleting} // Disable button during the deletion process
        >
          {isDeleting ? "Deleting..." : "Delete Account"}
        </Button>
      </div>
    );
  }
}

export default PatientDelete;
