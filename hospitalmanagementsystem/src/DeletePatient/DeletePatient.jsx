// ! after validation
import React, { Component } from "react";
import { UserContext } from "../Login/login.jsx";
import { Button, Alert, Modal } from "react-bootstrap";
import "./DeletePatient.css";

class PatientDelete extends Component {
  static contextType = UserContext;

  constructor(props) {
    super(props);
    this.state = {
      appointments: [],
      error: null,
      successMessage: null,
      // track the deleting process
      isDeleting: false,
      // track for modal confirmation
      showModal: false,
    };
  }

  // Fetch appointments for the logged-in patient
  componentDidMount() {
    const patId = this.context?.patientId; // Get patientId from context

    if (patId) {
      fetch(
        `http://localhost:8080/appointment/fetchAppointmentsForPatientID/${patId}`
      )
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
      // No appointments, delete the patient directly in the database
      this.deletePatient();
    } else {
      let errorCount = 0;

      // creates a Loop for each appointment and delete it
      appointments.forEach((appointment) => {
        fetch(`http://localhost:8080/appointment/delete/${appointment.id}`, {
          method: "DELETE",
        })
          .then((response) => {
            if (!response.ok) {
              errorCount++;
              throw new Error(
                `Failed to delete appointment with ID: ${appointment.id}`
              );
            }
          })
          .catch((error) => {
            console.error("Error deleting appointment:", error);
          });
      });

      // After deleting all appointments, delete the patient account
      if (errorCount === 0) {
        this.deletePatient();
      }
    }
  };

  // Delete the patient's account
  deletePatient = () => {
    const { patientId } = this.context;

    if (!patientId) {
      this.setState({ error: "Patient ID is not found" });
      return;
    }

    // Start deleting process
    this.setState({ isDeleting: true });

    fetch(`http://localhost:8080/patient/delete/${patientId}`, {
      method: "DELETE",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to delete patient account.");
        }
        return response.text();
      })
      .then((data) => {
        this.setState({
          successMessage: "Your account has been successfully deleted",
          // Clear any error if deletion is successful
          error: null,
        });
        // Trigger logout after successful deletion
        this.handleLogout();
      })
      .catch((error) => {
        console.error("Error deleting patient:", error);
      })
      .finally(() => {
        // If the deletion completes, Trigger logout after successful deletion
        this.setState({ isDeleting: false });
      });
  };

  // Handle delete button click
  handleDeleteClick = () => {
    // Show the confirmation modal
    this.setState({ showModal: true });
  };

  // Handle logout after successful deletion
  handleLogout = () => {
    // Clear user session or any stored user data (localStorage, sessionStorage, etc.)
    localStorage.removeItem("patient");

    // After logout, redirect to Login page
    window.location.href = "/login"; // Or navigate to the login route
  };

  // Close the confirmation modal
  handleCloseModal = () => {
    this.setState({ showModal: false });
  };

  // Confirm deletion in modal
  handleConfirmDelete = () => {
    // close the modal
    this.setState({ showModal: false });
    // else proceeds
    this.deleteAppointments();
  };

  render() {
    const { error, successMessage, isDeleting, showModal } = this.state;

    return (
      <div
        className="font"
        style={{
          display: "flex",
          flexDirection: "column",
          justifyContent: "center",
          alignItems: "center",
          height: "80vh",
          color: "white",
        }}
      >
        <h1>Delete Your Account</h1>
        <p style={{ fontSize: "22px", color: "white" }}>
          If you delete your account, all your <br />
          appointments will also be deleted.
        </p>

        {error && <Alert variant="danger">{error}</Alert>}
        {successMessage && <Alert variant="success">{successMessage}</Alert>}

        <Button
          onClick={this.handleDeleteClick}
          className="delete-button"
          variant="danger"
          // Disable button during the deletion process
          disabled={isDeleting}
        >
          {isDeleting ? "Deleting..." : "Delete Account"}
        </Button>

        {/* Confirmation Modal */}
        <Modal show={showModal} onHide={this.handleCloseModal} centered>
          <Modal.Header closeButton>
            <Modal.Title>Confirm Account Deletion</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            Are you sure you want to delete your account? All your appointments
            will also be deleted.
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={this.handleCloseModal}>
              Cancel
            </Button>
            <Button
              variant="danger"
              onClick={this.handleConfirmDelete}
              disabled={isDeleting}
            >
              {isDeleting ? "Deleting..." : "Delete"}
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    );
  }
}

export default PatientDelete;
