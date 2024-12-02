import React, { Component } from "react";
import Header from "./header/header.jsx";
import Centre from "./centre/centre.jsx";
import Footer from "./footer/footer.jsx";

export default class main extends Component {
  render() {
    return (
      <>
        <Header />
        <Centre />
        <Footer />
      </>
    );
  }
}
