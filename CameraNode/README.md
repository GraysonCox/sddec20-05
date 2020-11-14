## Video Streaming Deployment
This readme tells how to deploy the Video Streaming Software to run the video stream from the camera on a Video Mesh Node.

- You will first need to have the Raspberry Pi OS installed on the Pi (https://www.raspberrypi.org/downloads/raspberry-pi-os/). For the purpose of video streaming, you may use either the lite or desktop versions. You will need a monitor, keyboard, and mouse for the setup. Alternatively, you can enable ssh and execute the necessary commands remotely.
- Boot up the Pi. You should have the camera connected via the ribbon cable.
- Connect the Pi to the internet (either via a wired Ethernet connection or a wireless connection). The process depends on whether you are using the lite or desktop versions of the OS. After you are connected, you should install any updates for the OS. This can be done by executing `sudo apt update` followed by `sudo apt upgrade` on the command line. If you are using the Desktop OS, you may be prompted to install the updates within the GUI.
- You will need to have Docker installed on the Pi. Use the convenience script method here (https://docs.docker.com/engine/install/debian/#install-using-the-convenience-script).
- In a terminal, run `sudo raspi-config`. Enable the camera in the Interface Options menu. Exit raspi-config by selecting Finish and reboot the Pi.
- In a terminal, run the following commands (primarily for testing; these are not required in future power-ons).
    - `sudo apt install python3-picamera`
    - `modprobe bcm2835-v4l2`
    - Optionally, you can run `raspistill -o test.jpg` to test the camera. Even in the non-desktop version of Raspberry Pi OS, you will see a preview of the camera as it takes the picture. This will take a few seconds.
- Download the source code of this repository to the Pi. You may download it directly from GitLab. Alternatively, you may want to clone it with Git, which will allow you to work with the repo and change the code. It is assumed that the reader has experience with Git.
- You are now ready to build and run the CameraNode Docker container on the Pi. Inside the CameraNode directory of the repository, execute the build.sh script. You will probably need to use sudo to execute it, i.e. `sudo ./build.sh`.
- Now that you have built the Docker container, you are ready to run it. Run start.sh in the CameraNode directory. You will likely need to use sudo for this script as well. The console is interactive, although output is printed to the console regularly. To stop the stream, you can type "exit" in the console.

Assuming you have the Pi connected to the internet, the stream is running on your local area network. To test this with the User Application, you can hard-code the IP address for the camera nodes in the User Application. With the repository checked out on a personal computer connected to the same network, locate node-detail.component.html and replace the IP address variable with the hard-coded value of the Pi's IP address. You may then run the User Application (either with the real or mock Backend Application), load the User Application page in a browser on the computer, go to the detail page for a camera node, and view the stream from the camera.

To run the stream within the mesh network, follow the procedures for connecting the Pi to the mesh network instead of your internet connection. Use the start.sh script to start the stream once the Pi is connected.