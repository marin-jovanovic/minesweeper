from src_py.solvable_checker.util import get_tile_neighbours


def open_zero(board, board_state, curr_column, curr_row, num_of_columns,
              num_of_rows, markings_state, markings):
    if board[curr_row][curr_column] != markings["user"] and not isinstance(
            board[curr_row][curr_column], int):
        return

    if board_state[curr_row][curr_column] == markings_state["open"]:
        return

    board_state[curr_row][curr_column] = markings_state["open"]

    if (board[curr_row][curr_column] == markings["empty"]) \
            or board[curr_row][curr_column] == markings["user"]:

        for r, c in get_tile_neighbours(curr_row, curr_column, num_of_rows,
                                        num_of_columns):
            open_zero(board, board_state, c, r, num_of_columns,
                      num_of_rows, markings_state, markings)


def open_tile(board, board_state, user_row, user_column, num_of_columns,
              num_of_rows, markings_state, markings):
    # if board[user_row][user_column] == markings["user"] or         board[user_row][user_column] == markings["empty"]:
    if board[user_row][user_column] == markings["empty"]:
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
