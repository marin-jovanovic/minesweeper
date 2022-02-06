from random import randint

from solvable_checker.tile_opener import open_tile
from solvable_checker.util import what_is_targetable
from solvable_checker.constants import markings_state, markings

def generate_board(markings, num_of_rows, num_of_columns, num_of_mines,
                   user_row, user_column):

    # todo check restrictions
    board = [[0 for _ in range(num_of_columns)] for _ in range(num_of_rows)]

    for r, c in what_is_targetable(user_row, user_column,
                                   num_of_rows, num_of_columns):
        board[r][c] = markings["user"]

    occupied_tiles = {(user_row, user_column)}

    place_mines(board, markings, num_of_columns, num_of_mines, num_of_rows,
                occupied_tiles)

    for r, c in what_is_targetable(user_row, user_column,
                                   num_of_rows, num_of_columns):
        board[r][c] = markings["empty"]

    place_hints(board, markings, num_of_columns, num_of_rows)

    return board


def place_hints(board, markings, num_of_columns, num_of_rows):
    for curr_row in range(num_of_rows):
        for curr_column in range(num_of_columns):
            if board[curr_row][curr_column] == markings["mine"]:

                for r, c in what_is_targetable(curr_row, curr_column,
                                               num_of_rows, num_of_columns):
                    set_if_not_user_or_mine(board, markings, r,
                                            c)


def place_mines(board, markings, num_of_columns, num_of_mines, num_of_rows,
                occupied_tiles):
    c = 0
    while c != num_of_mines:

        row = randint(0, num_of_rows - 1)
        column = randint(0, num_of_columns - 1)

        if (row, column) not in occupied_tiles:
            occupied_tiles.add((row, column))
            c += 1

            board[row][column] = markings["mine"]


def set_if_not_user_or_mine(board, markings, r, c):

    if board[r][c] != markings["mine"]:
        board[r][c] += 1


def generate_board_with_first_move(num_of_rows, num_of_columns, num_of_mines,
                                   user_row, user_column):
    # can not be mine
    # can not be number other than 0 because then you need to guess
    board = generate_board(markings, num_of_rows, num_of_columns, num_of_mines,
                           user_row, user_column)

    board_state = [[markings_state["closed"] for _ in range(num_of_columns)] for
                   _ in range(num_of_rows)]

    open_tile(board, board_state, user_row, user_column, num_of_columns,
             num_of_rows, markings_state, markings)

    return board, board_state