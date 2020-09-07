#!/bin/bash

#setup of mesh network on startup

ip link set wlan1 down #disable interface
iw wlan1 set type ibss #set interface to ibss (ad-hoc)
ifconfig wlan1 mtu 1532 # This is necessary for batman-adv
iwconfig wlan1 channel 3 #ensure all channels are the same
ip link set wlan1 up #bring up interface
iw wlan1 ibss join 'mesh-network' 2432 #join the network named 'mesh-network'
#Frequency is 2.432. 0.0x should match with channel.


sleep 1

modprobe batman-adv
batctl if add wlan1
ip link set up dev wlan1
ip link set up dev bat0
ifconfig bat0 172.27.0.2
