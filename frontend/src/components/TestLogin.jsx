import axios from "axios";
import React from "react";

function TestLogin() {
  const handleTest = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/auth/login",
        { email: "batu25@gmail.com", password: "carlsberg" } // Postman’de çalışan bilgiler
      );
      console.log("Response data:", response.data); // token, expiresIn, createdAt burada görünür
    } catch (err) {
      console.error("Error:", err);
    }
  };

  return <button onClick={handleTest}>Test Login</button>;
}

export default TestLogin;
