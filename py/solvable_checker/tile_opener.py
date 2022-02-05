from solvable_checker.util import what_is_targetable


def open_zero(board, board_state, curr_column, curr_row, num_of_columns,
              num_of_rows, markings_state, markings):
    # test_l = curr_column == 0
    # test_r = curr_column == num_of_columns - 1
    # test_u = curr_row == 0
    # test_d = curr_row == num_of_rows - 1
    #
    # if not isinstance(board[curr_row][curr_column], int):
    #     return
    # print(board[curr_row][curr_column])

    if board[curr_row][curr_column] != markings["user"] and not isinstance(
            board[curr_row][curr_column], int):
        return

    if board_state[curr_row][curr_column] == markings_state["open"]:
        return

    board_state[curr_row][curr_column] = markings_state["open"]

    if (board[curr_row][curr_column] == markings["empty"]) \
            or board[curr_row][curr_column] == markings["user"]:

        for r, c in what_is_targetable(curr_row, curr_column, num_of_rows,
                                       num_of_columns):
            open_zero(board, board_state, c, r, num_of_columns,
                      num_of_rows, markings_state, markings)


def open_tile(board, board_state,user_row, user_column,  num_of_columns,
              num_of_rows, markings_state, markings):



    if board[user_row][user_column] == markings["user"] or         board[user_row][user_column] == markings["empty"]:
        open_zero(board, board_state, user_column, user_row, num_of_columns,
                  num_of_rows, markings_state, markings)

        # todo extract to map
        return "dont know if game is won"
    else:
        if board[user_row][user_column] == markings["mine"]:
            board_state[user_row][user_column] = markings_state["open"]

            return "game lost"

        else:
            board_state[user_row][user_column] = markings_state["open"]

            return "dont know if game is won"