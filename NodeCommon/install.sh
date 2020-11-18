#!/bin/bash

sudo apt install libnl-3-dev libel-genl-3-dev
git clone https://git.open-mesh.org/batctl.git
cd batctl
sudo make install
sudo apt-get install alfred

sudo apt-get install dnsmasq

cd

sudo apt-get install hostapd

sudo mv /etc/hostapd/hostapd.conf /etc/hostapd/hostapd.conf.orig
sudo mv hostapd.conf /etc/hostapd

sudo mv /etc/dnsmasq.conf /etc/dnsmasq.conf.orig
sudo mv dnsmasq.conf /etc

sudo mv /etc/dhcpcd.conf /etc/dhcpcd.conf.orig
sudo mv dhcpcd.conf /etc/dhcpcd.conf

sudo mv /etc/sysctl.conf /etc/sysctl.conf.orig
sudo mv sysctl.conf /etc/sysctl.conf