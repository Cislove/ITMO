import my_converter
import my_parser
import lib_parser
import lib_converter
import speed_measurement


def task1():
    print("Задание №1")
    path_to_json = 'D:/Sublime Text/Projects/mySchedule'
    path_to_yaml = 'D:/Sublime Text/Projects/YAML_file#1.yaml'
    print(f'\tПарсинг файла {path_to_json}')
    data = my_parser.parser(path_to_json)
    print(f'\tПарсинг файла завершен: \n'
          f'\t{data}')
    print(f'\tКонвертация файла {path_to_json} в {path_to_yaml}')
    my_converter.converter(path_to_json, path_to_yaml)
    print(f'\tКонвертация завершена')
    print("\n\n")


def add_task1():
    print("Дополнительное задание №1")
    path_to_json = 'D:/Sublime Text/Projects/mySchedule'
    path_to_yaml = 'D:/Sublime Text/Projects/YAML_file#2.yaml'
    print(f'\tПарсинг файла {path_to_json}')
    data = lib_parser.parser(path_to_json)
    print(f'\tПарсинг файла завершен: \n'
          f'\t{data}')
    print(f'\tКонвертация файла {path_to_json} в {path_to_yaml}')
    lib_converter.converter(path_to_json, path_to_yaml)
    print(f'\tКонвертация завершена')
    print("\n\n")


def add_task4():
    print("Дополнительное задание №4")
    path_to_json = 'D:/Sublime Text/Projects/mySchedule'
    path_to_yaml1 = 'D:/Sublime Text/Projects/YAML_file#1.yaml'
    path_to_yaml2 = 'D:/Sublime Text/Projects/YAML_file#2.yaml'
    speed_measurement.measurement(path_to_json, path_to_yaml1, path_to_yaml2)


if __name__ == '__main__':
    task1()
    add_task1()
    add_task4()
