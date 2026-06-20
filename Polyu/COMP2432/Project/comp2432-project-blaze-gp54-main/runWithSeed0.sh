#!/bin/bash
set -euo pipefail

ts="$(date +%Y%m%d_%H%M%S)"
mkdir -p ./log
cargo run -- --seed=0 2>&1 | tee "./log/${ts}.log"