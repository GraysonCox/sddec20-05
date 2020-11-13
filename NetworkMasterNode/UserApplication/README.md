# UserApplication

## Overview

This is the UI application that allows the user to view the information being shared among the mesh network. Right now, it's just two pages: a home page, which lists all the nodes in the network, and a node-detail page, where the user can view and interact with a selected node. It's primitive currently, but it has a clean architecture and is ready to be expanded. Thanks and go State.

## Deployment

### For Production

Deploying this application to a Raspberry Pi is handled by the start up scripts in the `NetworkMasterNode` directory of this repository. Go to the README in that directory for production deployment instructions.

### For Development/Testing

There are two options for running this application locally.

To run `UserApplication` along with the actual `BackendApplication`, do `npm run start` and navigate to `http://localhost:4200/` in your browser. This will proxy all API requests, so the `BackendApplication` must also be running on the same machine.

To run `UserApplication` by itself without having to start `BackendApplication`, do `npm run start-with-mock-backend` and navigate to `http://localhost:4200/` in your browser. This instruction uses `json-server` to make a fake REST server so that the `BackendApplication` won't be needed. The mock data used by this server is located in `mock-backend-application-data.json`.
