import os
from tkinter import filedialog
from tkinter import *

# for multiple extensions use this
# extensions = (".java", ".py", ".c")
extensions = ".py"


def get_num_of_lines_in_file(path):
    return len(open(path).readlines())


def get_number_of_lines_in_directory(directory):
    print(directory)
    number_of_lines_in_total = 0

    for root, dirs, files in os.walk(directory):
        for filename in files:

            name = os.path.join(root, filename)
            print(name)

            if name.endswith(extensions):
                number_of_lines = get_num_of_lines_in_file(name)
                print(number_of_lines)
                number_of_lines_in_total += number_of_lines

            print()

    return number_of_lines_in_total


if __name__ == '__main__':
    window = Tk().withdraw()
    selected_folder = filedialog.askdirectory()

    num_of_lines = get_number_of_lines_in_directory(selected_folder)
    print("total number of lines " + str(num_of_lines))