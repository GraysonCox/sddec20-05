# NetworkMasterNode

## Overview

This node is responsible for hosting `UserApplication` and `BackendApplication`.

## Deployment

#### Building

Run the `build.sh` script in this directory to build the `UserApplication` and `BackendApplication` Docker containers.

#### Starting

Run the `start.sh` script in this directory to run the applications in the background.

#### Stopping

Run `stop.sh` to stop the Docker containers.

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
