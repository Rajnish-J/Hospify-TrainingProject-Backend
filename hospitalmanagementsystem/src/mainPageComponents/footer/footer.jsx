import React, { Component } from "react";
import { Col, Container, Row } from "react-bootstrap";
import "../footer/footer.css";

export default class footer extends Component {
  render() {
    return (
      <div>
        <Container fluid className="text-light">
          <Row className="FooterRow d-flex justify-content-center align-items-center">
            <Col xs={12} md={6} lg={4} className="text-center">
              <h5 className="FooterCol mb-0 font">
                Copyright © 2024 Hospital Management. All rights reserved.
              </h5>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}
