import json
import yaml


def converter(path_to_json, path_to_yaml):
    with (open(path_to_json, 'r', encoding='utf-8') as json_file,
          open(path_to_yaml, "w", encoding='utf-8') as yaml_file):
        json_payload = json.load(json_file)
        yaml.dump(json_payload, yaml_file, sort_keys=False, allow_unicode=True)
