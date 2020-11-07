#!/bin/sh
docker run -it --network=host --device=/dev/vchiq --device=/dev/video0 camera_node
