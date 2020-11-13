import os


ip_addr = ''
mesh_network_name = ''


try:
    f = open("/home/pi/sddec20-05/NodeCommon/network_settings.cfg")
    lines = f.readlines()
    for line in lines:
        line = line[0: len(line)-1]
        type = line.split('=')


        if(type[0] == 'NODE_IP_ADDRESS'):
            ip_addr = type[1]
        if(type[0] == 'MESH_NETWORK_NAME'):
            mesh_network_name = type[1]

    f.close()

except Exception as e:
    print("File not found")

os.system('ip link set wlan1 down ') #disable interface
os.system('iw wlan1 set type ibss') #set interface to ibss (ad-hoc)
os.system('ifconfig wlan1 mtu 1532') # This is necessary for batman-adv
os.system('iwconfig wlan1 channel 3') #ensure all channels are the same
os.system('ip link set wlan1 up ')#bring up interface

command = 'iw wlan1 ibss join ' +  mesh_network_name + ' 2432'
os.system(command)#join the network named 'mesh-network'
#Frequency is 2.432. 0.0x should match with channel.

os.system('sleep 1')

#batman setup
os.system('modprobe batman-adv') #load batman into kernel
os.system('batctl if add wlan1') #connect batman to interface
os.system('ip link set up dev wlan1') #link dev to wlan1
os.system('ip link set up dev bat0') #link dev bat0

command = 'ifconfig bat0 ' +  ip_addr
os.system(command) #configure batman with correct ip address

#create bridge
os.system('ip link add name mesh-bridge type bridge')
os.system('ip link set dev mesh-bridge up')
os.system('ip link set dev wlan0 master mesh-bridge')
os.system('ip link set dev bat0 master mesh-bridge')
