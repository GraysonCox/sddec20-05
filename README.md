# An Advanced Networking Outreach Activity For Kids

## Overview

This repository contains all of our project's code. The directories at the root of this repository are as follows.

- NodeCommon
    - This is for startup scripts and code that is used by every type of node.
- CameraNode
    - This is for startup scripts and code that is used by Video Mesh Nodes.
- NetworkMasterNode
    - This is for startup scripts and code that is used by the Network Master Node.

## Deploying Software to a Node

The high-level steps for deploying this software to a Raspberry Pi are as follows. You will have to refer to the associated README.md files for complete instructions.

1. Put the project repository on the Raspberry Pi.
1. Follow the node setup instructions in `NodeCommon/README.md`.
1. If the node is meant to be anything other than a basic Relay Mesh Node, then follow the deployment instructions for the intended node type.
    - Network Master Node deployment instructions are located in `NetworkMasterNode/README.md`.
    - Video Mesh Node deployment instructions are located in `CameraNode/README.md`.