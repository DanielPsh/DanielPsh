import React, { useState } from "react";

export default function FlipFlop({ type }) {
  const [inputs, setInputs] = useState({
    S: 0, R: 0, D: 0, J: 0, K: 0, T: 0
  });

  const [Q, setQ] = useState(0);

  const toggle = (key) => {
    setInputs({ ...inputs, [key]: inputs[key] ^ 1 });
  };

  const run = async () => {
    let url = `http://localhost:5000/api/${type}`;
    let body = { Q };

    if (type === "sr") body = { S: inputs.S, R: inputs.R, Q };
    if (type === "d") body = { D: inputs.D };
    if (type === "jk") body = { J: inputs.J, K: inputs.K, Q };
    if (type === "t") body = { T: inputs.T, Q };

    const res = await fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body)
    });

    const data = await res.json();
    setQ(data.Q);
  };

  return (
    <div style={{ marginTop: "20px" }}>
      <h2>{type.toUpperCase()} Flip-Flop</h2>

      {type === "sr" && (
        <>
          <button onClick={() => toggle("S")}>S: {inputs.S}</button>
          <button onClick={() => toggle("R")}>R: {inputs.R}</button>
        </>
      )}

      {type === "d" && (
        <button onClick={() => toggle("D")}>D: {inputs.D}</button>
      )}

      {type === "jk" && (
        <>
          <button onClick={() => toggle("J")}>J: {inputs.J}</button>
          <button onClick={() => toggle("K")}>K: {inputs.K}</button>
        </>
      )}

      {type === "t" && (
        <button onClick={() => toggle("T")}>T: {inputs.T}</button>
      )}

      <br /><br />
      <button onClick={run}>Clock Pulse ▶</button>

      <h3>Output Q: {Q}</h3>
    </div>
  );
}