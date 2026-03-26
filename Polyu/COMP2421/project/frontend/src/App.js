import React, { useState } from "react";
import FlipFlop from "./FlipFlop";

export default function App() {
  const [type, setType] = useState("sr");

  return (
    <div style={{ textAlign: "center" }}>
      <h1>Logic Circuit Simulator</h1>

      <select onChange={(e) => setType(e.target.value)}>
        <option value="sr">S-R</option>
        <option value="d">D</option>
        <option value="jk">JK</option>
        <option value="t">T</option>
      </select>

      <FlipFlop type={type} />
    </div>
  );
}