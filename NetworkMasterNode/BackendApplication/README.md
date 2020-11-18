# BackendApplication

## Overview

This application serves as a facade between the User Application and the Mesh Network. It handles all accessing and mutation of information among the network and hides the complexity of these operations behind a simple REST API, on which the User Application can call.

## Deployment

#### For Production

Deploying this application to a Raspberry Pi is handled by the start up scripts in the `NetworkMasterNode` directory of this repository. See `NetworkMasterNode/README.md` for production deployment instructions.

#### For Development

To run this application on your local machine, do `./gradlew bootRun` and access the API endpoints at `http://localhost:8080`.

If you're going to be adding to or modifying this application, I recommend using IntelliJ. Just import this directory as a Gradle project and select the `BackendApplication` build configuration, and you will be able to run and debug using the IntelliJ UI.

# Tests

You can run all the unit tests by doing `./gradlew test`.

However, it's much more convenient to run all the unit tests with an IDE. We used IntelliJ.

