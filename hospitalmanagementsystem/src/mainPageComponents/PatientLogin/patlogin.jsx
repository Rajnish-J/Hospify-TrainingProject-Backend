import React, { Component } from "react";

export default class patLogin extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      error: "",
    };
  }

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  handleSubmit = async (event) => {
    event.preventDefault();
    const { username, password } = this.state;

    try {
      const response = await fetch(
        "http://localhost:8080/loginPage/patientLogin",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            patientEmail: username,
            patientPassword: password,
          }),
        }
      );

      if (response.ok) {
        const data = await response.json();
        console.log("Login successful:", data);
        // Handle successful login
      } else {
        const errorData = await response.json();
        this.setState({ error: errorData.message || "Login failed" });
      }
    } catch (error) {
      this.setState({ error: "Network error, please try again later." });
    }
  };

  render() {
    const { username, password, error } = this.state;

    return (
      <div className="login-container">
        <h2>Login</h2>
        {error && <p style={{ color: "red" }}>{error}</p>}
        <form onSubmit={this.handleSubmit}>
          <div>
            <label>Username:</label>
            <input
              type="text"
              name="username"
              value={username}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div>
            <label>Password:</label>
            <input
              type="password"
              name="password"
              value={password}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <button type="submit" name="login" onChange={this.handleSubmit}>
            Login
          </button>
        </form>
      </div>
    );
  }
}
