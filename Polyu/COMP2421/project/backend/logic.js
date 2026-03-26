// Flip-Flop Logic Functions

function srFlipFlop(S, R, Q_prev) {
    if (S === 0 && R === 0) return Q_prev;
    if (S === 0 && R === 1) return 0;
    if (S === 1 && R === 0) return 1;
    return "INVALID";
}

function dFlipFlop(D) {
    return D;
}

function jkFlipFlop(J, K, Q_prev) {
    if (J === 0 && K === 0) return Q_prev;
    if (J === 0 && K === 1) return 0;
    if (J === 1 && K === 0) return 1;
    return Q_prev === 0 ? 1 : 0;
}

function tFlipFlop(T, Q_prev) {
    if (T === 0) return Q_prev;
    return Q_prev === 0 ? 1 : 0;
}

module.exports = {
    srFlipFlop,
    dFlipFlop,
    jkFlipFlop,
    tFlipFlop
};