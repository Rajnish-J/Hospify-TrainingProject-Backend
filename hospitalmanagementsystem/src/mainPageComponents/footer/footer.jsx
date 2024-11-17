import React, { Component } from "react";
import { Col, Container, Row } from "react-bootstrap";
import "../footer/footer.css";

export default class footer extends Component {
  render() {
    return (
      <div>
        <Container fluid>
          {/* Footer row */}
          <Row className="FooterRow">
            {/* Footer column */}
            <Col>
              <h1 className="FooterCol">
                Copyright @2024 Hospital Management. All rights reserved
              </h1>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}
