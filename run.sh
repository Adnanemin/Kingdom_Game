#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"

OUT_DIR="out/production/Kingdom_Game"

echo "Cleaning old build..."
rm -rf out

echo "Compiling..."
mkdir -p "$OUT_DIR"
find src -name "*.java" -print0 | xargs -0 javac -d "$OUT_DIR"

echo "Starting game..."
java -cp "$OUT_DIR" game.GameEngine
