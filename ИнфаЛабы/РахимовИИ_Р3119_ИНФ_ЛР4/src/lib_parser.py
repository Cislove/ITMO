import json


def parser(path_to_json):
    with open(path_to_json, 'r', encoding='utf-8') as json_file:
        data = json.load(json_file)
        return data
