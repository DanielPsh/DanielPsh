const express = require("express");
const cors = require("cors");

const {
    srFlipFlop,
    dFlipFlop,
    jkFlipFlop,
    tFlipFlop
} = require("./logic");

const app = express();
app.use(cors());
app.use(express.json());

app.post("/api/sr", (req, res) => {
    const { S, R, Q } = req.body;
    res.json({ Q: srFlipFlop(S, R, Q) });
});

app.post("/api/d", (req, res) => {
    const { D } = req.body;
    res.json({ Q: dFlipFlop(D) });
});

app.post("/api/jk", (req, res) => {
    const { J, K, Q } = req.body;
    res.json({ Q: jkFlipFlop(J, K, Q) });
});

app.post("/api/t", (req, res) => {
    const { T, Q } = req.body;
    res.json({ Q: tFlipFlop(T, Q) });
});

app.listen(5000, () => {
    console.log("Server running on http://localhost:5000");
});