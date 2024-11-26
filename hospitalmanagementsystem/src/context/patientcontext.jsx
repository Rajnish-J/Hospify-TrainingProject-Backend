import React, { createContext, useContext, useState } from "react";

// Create the context
export const PatientContext = createContext();

// Provider Component
export const PatientProvider = ({ children }) => {
  const [patient, setPatient] = useState(null);

  return (
    <PatientContext.Provider value={{ patient, setPatient }}>
      {children}
    </PatientContext.Provider>
  );
};

// Custom hook for consuming the context
export const usePatientContext = () => useContext(PatientContext);
