## Video Streaming Deployment
This readme tells how to run the camera stream on a Video Mesh Node.

- When you boot up the Pi, you should have the camera connected via the ribbon cable.
- You will first need to have the Raspberry Pi OS installed on the Pi (https://www.raspberrypi.org/downloads/raspberry-pi-os/). For the purpose of video streaming, you may use either the lite or desktop versions.
- You will also need to have Docker installed on the Pi. Use the convenience script method here (https://docs.docker.com/engine/install/debian/#install-using-the-convenience-script).
- In a terminal, run `sudo raspi-config`. Enable the camera in the Interfacing Options menu. Exit raspi-config and reboot the Pi.
- In a terminal, run the following commands (primarily for testing; these are not required in future power-ons).
    - `sudo apt update`
    - `sudo apt install python3-picamera`
    - `modprobe bcm2835-v4l2`
    - Optionally, you can run `raspistill -o test.jpg` to test the camera. Even in the non-desktop version of Raspberry Pi OS, you will see a preview of the camera as it takes the picture.
- You are now ready to build and run the CameraNode Docker container. Inside the CameraNode directory, execute the build.sh script.
- Now that you have built the Docker container, you are ready to run it. Run start.sh in the CameraNode directory.

The video stream is now running, and the User Application will be able to display the video stream for this node.