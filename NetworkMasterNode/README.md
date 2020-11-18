# NetworkMasterNode

## Overview

This node is responsible for hosting `UserApplication` and `BackendApplication`.

## Deployment

These instructions are strictly for deploying the software on a Raspberry Pi. The Docker configuration of this module uses `network_mode: 'host'`, which works only in Linux. If you want to run these programs locally on your development machine for testing purposes, view the `README.md` instructions in either subdirectory.

#### Building

Run `make build` in this directory to build the `UserApplication` and `BackendApplication` Docker containers.

#### Starting

Run `make start` in this directory to run the applications in the background.

You can access the application by navigating to `http://<IP address of Network Master Node>:4200/` in any browser on any machine in the same network.

#### Stopping

Run `make stop` in this directory to stop the Docker containers.

## Other Notes

#### Installing `docker-compose`

The deployment scripts in this directory use `docker-compose`, which is annoying to install on a Raspberry Pi, so I wrote the steps to do that here.

```
# Install docker
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh
sudo usermod -aG docker ${USER}
sudo su - ${USER}

# Install docker-compose
sudo apt-get install libffi-dev libssl-dev
sudo apt install python3-dev
sudo apt-get install -y python3 python3-pip
sudo pip3 install docker-compose
```

#### Viewing Logs

You can view the logs from `UserApplication` and `BackendApplication` by running `docker-compose logs` in this directory. If you want to view the logs continuously, you can do `docker-compose logs -f`. 
