#!/bin/bash

# Creating necessary directories
mkdir -p ./output/bin
mkdir -p ./src/main/resources/config

# Checking for Scala
if hash scala 2>/dev/null; then
  echo "Scala is installed"
else
  echo "Scala is not installed. Please install Scala."
fi

# Checking for JDK
if hash javac 2>/dev/null; then
  echo "JDK is installed"
else
  echo "JDK is not installed. Please install JDK."
fi

# Checking for sbt
if hash sbt 2>/dev/null; then
  echo "sbt is installed"
else
  echo "sbt is not installed. Please install sbt."
fi

# JavaFX Module Path Setup
echo "Please make sure to set the JavaFX module path in build.sbt to the correct location on your system."

# Install dependencies and build the project
sbt update
sbt compile

echo "Setup completed successfully!"
