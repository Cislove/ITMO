import lib_parser
import lib_converter
import my_parser
import my_converter
import time


def measurement(path_to_json, path_to_yaml1, path_to_yaml2):
    t_start = time.time()
    for i in range(100):
        my_parser.parser(path_to_json)
    t_finish = time.time()
    print(f"\tСкорость работы моего парсера: {t_finish - t_start}")
    t_start = time.time()
    for i in range(100):
        my_converter.converter(path_to_json, path_to_yaml1)
    t_finish = time.time()
    print(f"\tСкорость работы моего конвертора: {t_finish - t_start}")
    t_start = time.time()
    for i in range(100):
        lib_parser.parser(path_to_json)
    t_finish = time.time()
    print(f"\tСкорость работы библиотечного парсера: {t_finish - t_start}")
    t_start = time.time()
    for i in range(100):
        lib_converter.converter(path_to_json, path_to_yaml2)
    t_finish = time.time()
    print(f"\tСкорость библиотечного конвертора: {t_finish - t_start}")
