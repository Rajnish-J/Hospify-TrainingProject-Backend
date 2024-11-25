import Main from "../src/mainPageComponents/main.jsx";
import { Routes, Route } from 'react-router-dom';
import LandingPage from "../src/LandingPage/landingPage.jsx";
import Login from "./Login/login.jsx";

function App() {
  return (
    <div>
      {/* <Login /> */}
      {/* <Main /> */}
      <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/login" element={<Login />} />
    </Routes>
    </div>
  );
}

export default App;
