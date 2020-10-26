#!/usr/bin/python

import flask
from flask import request
import os

ip_addr = ''
mesh_network_name = ''


try:
    f = open("network_settings.cfg")
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

if ip_addr != None:
    octets = str(ip_addr).split('.')
    if len(octets) > 2:
        node_id = octets[3]
else : node_id = ''

app = flask.Flask(__name__)
app.config["DEBUG"] = False

@app.route('/', methods=["GET"])
def home():
    return ""


@app.route('/node', methods=["GET"])
def api_node_information():

    return {
           "id": node_id,
            "type": node_type,
            "name": node_name,
            "ip": ip_addr,
            "batteryPercentage": ""
                 }

@app.route('/node/type', methods=["GET"])
def api_node_type():
    try:
        f = open("network_settings.cfg")
        lines = f.readlines()
        for line in lines:
            line = line[0: len(line)-1]
            type = line.split('=')

            if(type[0] == 'NODE_TYPE'):
                return type[1]

        f.close()

    except Exception as e:
        return e

@app.route('/node/name', methods=["GET"])
def api_node_name():
    try:
        f = open("network_settings.cfg")
        lines = f.readlines()
        for line in lines:
            line = line[0: len(line)-1]
            type = line.split('=')

            if(type[0] == 'NODE_NAME'):
                return type[1]

        f.close()

    except Exception as e:
        return e

@app.route('/node/ipaddr', methods=["GET"])
def api_node_ipaddr():
    try:
        f = open("network_settings.cfg")
        lines = f.readlines()
        for line in lines:
            line = line[0: len(line)-1]
            type = line.split('=')


            if(type[0] == 'NODE_IP_ADDRESS'):
                return type[1]


        f.close()

    except Exception as e:
        return e

@app.route('/node/networkname', methods=["GET"])
def api_node_networkname():
    try:
        f = open("network_settings.cfg")
        lines = f.readlines()
        for line in lines:
            line = line[0: len(line)-1]
            type = line.split('=')

            if(type[0] == 'MESH_NETWORK_NAME'):
                return type[1]

        f.close()

    except Exception as e:
        return e

@app.route('/node/type/<type>', methods=["POST"])
def api_node_type_control(type):
    try:
        if request.method == "POST":
            if(len(type) < 20):

                try:
                    f = open("network_settings.cfg", "r+")
                    lines = f.readlines()
                    f.close()

                    f = open("network_settings.cfg", "w").close()
                    f = open("network_settings.cfg", "r+")

                    for ind in range (0, len(lines)):
                        type_data = lines[ind].split('=')
                        if(type_data[0] == 'NODE_TYPE'):
                            type_data[1] = type
                            mod_line = str(type_data[0]) + '=' + str(type_data[1]) + '\n'
                            lines[ind] = mod_line
                            print(lines)
                            f.writelines(lines)

                    f.close()

                except Exception as e:
                    print(e)


                return 'ok'
            else:
                return 'Limit type to 20 characters'

    except Exception as e:
        print(e)
        return type


@app.route('/node/name/<name>', methods=["POST"])
def api_node_name_control(name):
    try:
        if request.method == "POST":
            if(len(name) < 20):

                try:
                    f = open("network_settings.cfg", "r+")
                    lines = f.readlines()
                    f.close()

                    f = open("network_settings.cfg", "w").close()
                    f = open("network_settings.cfg", "r+")

                    for ind in range (0, len(lines)):
                        line_data = lines[ind].split('=')
                        if(line_data[0] == 'NODE_NAME'):
                            line_data[1] = name
                            mod_line = str(line_data[0]) + '=' + str(line_data[1]) + '\n'
                            lines[ind] = mod_line
                            print(lines)
                            f.writelines(lines)

                    f.close()

                except Exception as e:
                    print(e)


                return 'ok'
            else:
                return 'Limit type to 20 characters'

    except Exception as e:
        print(e)
        return name

@app.route('/node/ipaddr/<ipaddr>', methods=["POST"])
def api_node_ipaddr_control(ipaddr):
        try:
            if request.method == "POST":
                if(len(ipaddr) < 20):
                    subnets = ipaddr.split('.')
                    if(len(subnets)==4):
                        for num in subnets:
                            #print num
                            number = int(num)
                            if(not( (number < 256) and (number >-1))):
                                return "ip address not in range"

                        try:
                            f = open("network_settings.cfg", "r+")
                            lines = f.readlines()
                            f.close()

                            f = open("network_settings.cfg", "w").close()
                            f = open("network_settings.cfg", "r+")

                            for ind in range (0, len(lines)):
                                line_data = lines[ind].split('=')
                                if(line_data[0] == 'NODE_IP_ADDRESS'):
                                    line_data[1] = ipaddr
                                    mod_line = str(line_data[0]) + '=' + str(line_data[1]) + '\n'
                                    lines[ind] = mod_line
                            f.writelines(lines)

                            f.close()
                            return "ip address set"

                        except Exception as e:
                            print(e)

        except Exception as e:
            print(e)
#    return "error setting ip address: incorrect format"

@app.route('/node/networkname/<name>', methods=["POST"])
def api_node_network_name_control(name):
        try:
            if request.method == "POST":
                if(len(name) < 20):

                    try:
                        f = open("network_settings.cfg", "r+")
                        lines = f.readlines()
                        f.close()

                        f = open("network_settings.cfg", "w").close()
                        f = open("network_settings.cfg", "r+")

                        for ind in range (0, len(lines)):
                            line_data = lines[ind].split('=')
                            if(line_data[0] == 'MESH_NETWORK_NAME'):
                                line_data[1] = name
                                mod_line = str(line_data[0]) + '=' + str(line_data[1]) + '\n'
                                lines[ind] = mod_line
                                print(lines)
                                f.writelines(lines)

                        f.close()

                    except Exception as e:
                        print(e)


                    return 'ok'
                else:
                    return 'Limit type to 20 characters'

        except Exception as e:
            print(e)
            return name

app.run(host='0.0.0.0')
