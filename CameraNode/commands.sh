#!/bin/bash

/opt/janus/bin/janus -F /opt/janus/etc/janus &
ffmpeg -f v4l2 \
	-input_format yuyv422 \
	-video_size hvga \
	-framerate 15 \
	-i /dev/video0 \
	-c:v h264_omx \
	-profile:v baseline \
	-b:v 320k \
	-bf 0 \
	-flags:v +global_header \
	-bsf:v "dump_extra=freq=keyframe" \
	-max_delay 0 \
	-an \
	-f rtp rtp://127.0.0.1:8004/ &
/bin/bash