#!/usr/bin/env bash
set -euo pipefail

msg="${1:-}"
if [[ -z "$msg" ]]; then
  echo "Usage: bash scripts/push.sh \"commit message\""
  exit 2
fi

git status --porcelain=v1
git add -A
git commit -m "$msg" || {
  echo "Nothing to commit."
  exit 0
}
git push
