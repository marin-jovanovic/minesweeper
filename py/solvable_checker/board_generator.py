from random import randint


def generate_board(markings, num_of_rows, num_of_columns, num_of_mines, user_row, user_column):


    # todo check restrictions
    board = [[0 for _ in range(num_of_columns)] for _ in range(num_of_rows)]

    board[user_row][user_column] = markings["user"]

    occupied_tiles = {(user_row, user_column)}

    place_mines(board, markings, num_of_columns, num_of_mines, num_of_rows,
                occupied_tiles)

    place_hints(board, markings, num_of_columns, num_of_rows)

    return board


def place_hints(board, markings, num_of_columns, num_of_rows):
    for curr_row in range(num_of_rows):
        for curr_column in range(num_of_columns):
            if board[curr_row][curr_column] == markings["mine"]:

                test_l = curr_column == 0
                test_r = curr_column == num_of_columns - 1
                test_u = curr_row == 0
                test_d = curr_row == num_of_rows - 1

                if not test_u and not test_l:
                    set_if_not_user_or_mine(board, markings, curr_row - 1,
                                            curr_column - 1)

                if not test_u:
                    set_if_not_user_or_mine(board, markings, curr_row - 1,
                                            curr_column)

                if not test_u and not test_r:
                    set_if_not_user_or_mine(board, markings, curr_row - 1,
                                            curr_column + 1)

                if not test_r:
                    set_if_not_user_or_mine(board, markings, curr_row,
                                            curr_column + 1)

                if not test_r and not test_d:
                    set_if_not_user_or_mine(board, markings, curr_row + 1,
                                            curr_column + 1)

                if not test_d:
                    set_if_not_user_or_mine(board, markings, curr_row + 1,
                                            curr_column)

                if not test_d and not test_l:
                    set_if_not_user_or_mine(board, markings, curr_row + 1,
                                            curr_column - 1)

                if not test_l:
                    set_if_not_user_or_mine(board, markings, curr_row,
                                            curr_column - 1)


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
    if board[r][c] not in [markings["user"], markings["mine"]]:
        board[r][c] += 1