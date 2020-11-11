#!/usr/bin/python

import configparser
import flask
from flask import request

NETWORK_SETTINGS_FILE_PATH = 'network_settings.cfg'

app = flask.Flask(__name__)
app.config['DEBUG'] = False


@app.route('/', methods=['GET'])
def home():
    return ''


@app.route('/node', methods=['GET'])
def api_node():
    return get_json()


@app.route('/node/update', methods=['POST'])
def api_node_update():
    return persist_node(request.json)


def get_json():
    config = configparser.ConfigParser()
    config.read(NETWORK_SETTINGS_FILE_PATH)

    ip_addr = config['node_properties']['NODE_IP_ADDRESS']
    node_name = config['node_properties']['NODE_NAME']
    node_type = config['node_properties']['NODE_TYPE']
    mesh_network_name = config['node_properties']['MESH_NETWORK_NAME']
    battery_percentage = 100

    return {
        'ipAddress': ip_addr,
        'name': node_name,
        'type': node_type,
        'networkName': mesh_network_name,
        'batteryPercentage': battery_percentage
    }


def persist_node(node_json):
    config = configparser.ConfigParser()

    config.add_section('node_properties')
    config['node_properties']['NODE_IP_ADDRESS'] = node_json.get('ipAddress', 'ERROR')
    config['node_properties']['NODE_NAME'] = node_json.get('name', 'ERROR')
    config['node_properties']['NODE_TYPE'] = node_json.get('type', 'ERROR')
    config['node_properties']['MESH_NETWORK_NAME'] = node_json.get('networkName', 'ERROR')

    with open(NETWORK_SETTINGS_FILE_PATH, 'w') as config_file:
        config.write(config_file)

    return 'ok'


app.run(host='0.0.0.0')
