import my_parser

cbrace_counter = 0
nbrace_counter = 0
sbrace_counter = 0
stack = ['', '']


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


def linefeed_checker(line, flag, yaml_file):
    global cbrace_counter, nbrace_counter, sbrace_counter, stack
    if line != (' ' * len(line)) and line[-2:] != '- ':
        if '\":' in line:
            line = line.replace('\"', '', 2)
        yaml_file.write(line + '\n')
    if stack[-1] == '[' or (stack[-2] == '[' and stack[-1] == '{' and flag):
        line = '  ' * (cbrace_counter + sbrace_counter - 1) + '- '
    else:
        line = '  ' * (cbrace_counter + sbrace_counter)
    return line


def converter(path_to_json, path_to_yaml):
    try:
        my_parser.parser(path_to_json)
    except:
        print("Ваш json файл очень кривой, сделайте его нормальным!!!!!!")
    json_file = open(path_to_json, 'r', encoding='utf-8')
    yaml_file = open(path_to_yaml, 'w', encoding='utf-8')
    text = change_file(json_file)
    global cbrace_counter, nbrace_counter, sbrace_counter, stack
    line = ''
    for ch in text:
        if ch == '{':
            stack.append('{')
            if stack[-2] != '[':
                cbrace_counter += 1
                line = linefeed_checker(line, False, yaml_file)
            else:
                line = linefeed_checker(line, True, yaml_file)
        elif ch == '}':
            if stack[-2] != '[':
                cbrace_counter -= 1
            stack.pop()
            line = linefeed_checker(line, False, yaml_file)
        elif ch == '[':
            sbrace_counter += 1
            stack.append('[')
            line = linefeed_checker(line, False, yaml_file)
        elif ch == ']':
            sbrace_counter -= 1
            stack.pop()
            line = linefeed_checker(line, False, yaml_file)
        elif ch == ',':
            if '\\n' in line:
                d = '|'
                if line[-2:] != 'n\"':
                    d += '-'
                else:
                    line = line[:len(line) - 3] + '\"'
                line = line.replace(':', f': {d}\n ', 1)
                line = line.replace('\\n', f'\n  {"  " * (cbrace_counter + sbrace_counter)}')
                line = line.replace('\"', '')
            line = linefeed_checker(line, False, yaml_file)
        else:
            line += ch
    linefeed_checker(line, False, yaml_file)
    json_file.close()
    yaml_file.close()
    cbrace_counter = 0
    nbrace_counter = 0
    sbrace_counter = 0
    stack = ['', '']
