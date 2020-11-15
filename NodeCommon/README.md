# Node Common
contains the code common to all nodes. This includes the Node Information API, mesh network, wifi access point, and bridge.

## To Install
To install the dependencies and properly configure the node, run Install.sh on each node in the system

## To Start
Before starting the network, navigate to network_settings.cfg and change the information in there to match the information for the node
  - node_ip_address
    - This should be a unique IP for the network you are trying to connect to
  - node_name
    - This should be a unique name identifying the type of node this is (like Camera1, CameraA, etc.)
  - node_type
    - This should be the type of node this is (right now, either CAMERA, MASTER, or RELAY)
  - mesh_network_name
    - This should be the name of the mesh network you want to connect to, must not include spaces

To start the mesh network, access point, bridge and api, run Start.sh
