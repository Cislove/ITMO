import re

ind = 0


def change_file(file):
    text = ''
    flag = False
    for line in file.readlines():  # Убираем ненужную табуляцию и тд
        for i in range(1, len(line) - 1):
            if line[i] == '\"':
                flag = not flag
                text += line[i]
            elif ((line[i] == ' ' and flag) or (line[i] != ' ') or (line[i] == ' ' and line[i - 1] == ':')) and (
                    line[i] != '\t'):
                text += line[i]
    return text


def value_checker(value):
    if not (isinstance(value, str)):
        return value
    if value == 'true':
        value = True
    elif value == 'False':
        value = False
    elif value == 'null':
        value = None
    elif re.fullmatch(r'(?:(?:\b| )(-?\d+[.]?(?:\d+)?\b))', value):
        if '.' in value:
            value = float(value)
        else:
            value = int(value)
    return value


def add_seg(text):
    global ind
    if text[ind] == '[':
        doc = []
        key = 0
        ind += 1
    else:
        if ind == 0:
            ind = -1
        doc = {}
        key = None
        ind += 1
    value = None
    el = ''
    flag = True
    while ind < len(text):
        if text[ind] == ':':
            if flag:
                key = el.replace('\"', '')
                el = ''
            else:
                el += text[ind]
        elif text[ind] == '{':
            value = add_seg(text)
        elif text[ind] == '}':
            if value is None:
                value = el
            doc[key] = value_checker(value)
            return doc
        elif text[ind] == '[':
            value = add_seg(text)
        elif text[ind] == ']':
            if value is None:
                value = el
            doc.append(value_checker(value))
            return doc
        elif text[ind] == ',':
            if flag:
                if value is None:
                    value = el
                if isinstance(doc, list):
                    key += 1
                    doc.append(value_checker(value))
                else:
                    doc[key] = value_checker(value)
                value = None
                el = ''
            else:
                el += text[ind]
        elif text[ind] != '"' or (text[ind] == '"' and el != '' and el[-1] == '\\'):
            if not (el == '' and text[ind] == ' '):
                el += text[ind]
        else:
            flag = not flag
        ind += 1
    doc[key] = value_checker(value)
    return doc


def parser(path_to_json):
    global ind
    ind = 0
    json_file = open(path_to_json, 'r', encoding='utf-8')
    text = change_file(json_file)
    data = add_seg(text)
    return data
