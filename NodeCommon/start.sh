#!/bin/bash

#setup of mesh network on startup

#absolute path to network_setup.py, may be different
python /home/pi/network_setup.py

ifdown wlan0
hostapd -B /etc/hostapd/hostapd.conf

#python3 /home/pi/node-api.py &
