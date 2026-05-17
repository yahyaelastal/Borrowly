#!/bin/bash
# ===================================
# Borrowly - Start Script (Linux/Mac)
# ===================================
echo "🚀 Starting Borrowly..."
echo ""
echo "Building and starting all services with Docker Compose..."
echo ""

docker-compose up --build

echo ""
echo "Borrowly is running at http://localhost:8080"
