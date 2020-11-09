## Running the CameraNode Software Manually
Here are the steps for running the CameraNode software from the command line
- Make sure Raspberry Pi OS is installed on the Pi (https://www.raspberrypi.org/downloads/raspberry-pi-os/)
- Start the Pi with the camera module and ribbon cable connected
- Install Docker (https://docs.docker.com/engine/install/debian/#install-using-the-convenience-script)
- Run these commands on the Pi
    - `sudo apt update`
    - `sudo apt install python3-picamera`
    - `modprobe bcm2835-v4l2`
- Execute build.sh
- Execute start.sh 
In future runs/reboots, you will only need to execute start.sh, or build.sh followed by start.sh if you change the CameraNode Docker image.

Additional details on inner workings and implementation can be found here: https://www.raspberrypi.org/downloads/raspberry-pi-os/