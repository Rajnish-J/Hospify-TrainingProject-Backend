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

  // * handles the input change 
  handleInputChange = (e) => {
    const value = e.target.value;
    if (!isNaN(value)) {
      this.setState({ patientID: value });
    }
  };

  // * handle fetching details
  handleFetch

  render() {
    return <div>
      {/*  */}
    </div>;
  }
}
