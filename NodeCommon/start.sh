#!/bin/bash

#setup of mesh network on startup

#absolute path to network_setup.py, may be different
python /home/pi/network_setup.py

#start hostapd
service hostapd start

#python3 /home/pi/node-api.py &
