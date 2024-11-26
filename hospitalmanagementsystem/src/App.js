import Main from "../src/mainPageComponents/main.jsx";
import LandingPage from "../src/LandingPage/landingPage.jsx";
import Login from "./Login/login.jsx";

function App() {
  return (
    <div>
      {/* straightly redirects to the login page */}
      <Login />
      {/* straightly redirects to the menu page */}
      {/* <Main /> */}

      {/* actual work flow */}
      {/* <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="/login" element={<Login />} />
      </Routes> */}
    </div>
  );
}

export default App;
