from solvable_checker.board_generator import generate_board


def open_zero(board, board_state, curr_column, curr_row, num_of_columns, num_of_rows, markings_state, markings):
    test_l = curr_column == 0
    test_r = curr_column == num_of_columns - 1
    test_u = curr_row == 0
    test_d = curr_row == num_of_rows - 1

    if     board_state[curr_row][curr_column] == markings_state["open"]:
        return

    board_state[curr_row][curr_column] = markings_state["open"]

    if (board[curr_row][curr_column] == markings["empty"])\
            or board[curr_row][curr_column] == markings["user"]:


        if not test_u and not test_l:
            r = curr_row - 1
            c = curr_column - 1

            open_zero_recursion(board, board_state, c, markings, markings_state,
                                num_of_columns, num_of_rows, r)

        if not test_u:
            r = curr_row - 1
            c = curr_column

            open_zero_recursion(board, board_state, c, markings, markings_state,
                                num_of_columns, num_of_rows, r)

        if not test_u and not test_r:
            r = curr_row - 1
            c = curr_column + 1

            open_zero_recursion(board, board_state, c, markings, markings_state,
                                num_of_columns, num_of_rows, r)

        if not test_r:
            r = curr_row
            c = curr_column + 1

            open_zero_recursion(board, board_state, c, markings, markings_state,
                                num_of_columns, num_of_rows, r)

        if not test_r and not test_d:
            r = curr_row + 1
            c = curr_column + 1

            open_zero_recursion(board, board_state, c, markings, markings_state,
                                num_of_columns, num_of_rows, r)

        if not test_d:
            r = curr_row + 1
            c = curr_column

            open_zero_recursion(board, board_state, c, markings, markings_state,
                                num_of_columns, num_of_rows, r)

        if not test_d and not test_l:
            r = curr_row + 1
            c = curr_column - 1

            open_zero_recursion(board, board_state, c, markings, markings_state,
                                num_of_columns, num_of_rows, r)

        if not test_l:
            r = curr_row
            c = curr_column - 1

            open_zero_recursion(board, board_state, c, markings, markings_state,
                                num_of_columns, num_of_rows, r)


def open_zero_recursion(board, board_state, c, markings, markings_state,
                        num_of_columns, num_of_rows, r):


    # if (board[r][c] == markings["empty"]):
        # \
            # or (isinstance(board[r][c], int) and board[r][c] > 0 and board[r][c] < 9):
    open_zero(board, board_state, c, r,
              num_of_columns, num_of_rows, markings_state,
              markings)

    # elif isinstance(board[r][c], int):
    #     board_state[r][c] = markings_state["open"]

    # board_state[r][c] = markings_state["open"]


def solve_board(markings, board):

    markings_state=  {
        "open": "o",
        "closed" : " "
    }

    num_of_rows = len(board)
    num_of_columns = len(board[0])

    board_state = [[markings_state["closed"] for _ in range(num_of_columns)] for _ in range(num_of_rows)]

    is_found = False
    user_row = None
    user_column = None
    for i, row in enumerate(board):
        if is_found:
            break
        for j, val in enumerate(row):
            if val == markings["user"]:
                # print(i, j)
                is_found = True
                # board_state[i][j] = markings_state["open"]
                user_row = i
                user_column = j
                # markings["user"]
                break

    # print()
    # [print(i) for i in board_state]
    print()
    open_zero(board, board_state, user_column, user_row, num_of_columns, num_of_rows, markings_state, markings)

    [print(i) for i in board_state]

    # for curr_row in range(num_of_rows):
    #     for curr_column in range(num_of_columns):
    #         if board[curr_row][curr_column] == markings["mine"]:
    #
    #             test_l = curr_column == 0
    #             test_r = curr_column == num_of_columns - 1
    #             test_u = curr_row == 0
    #             test_d = curr_row == num_of_rows - 1
    #
    #             if not test_u and not test_l:
    #                 set_if_not_user_or_mine(board, markings, curr_row - 1,
    #                                         curr_column - 1)
    #
    #             if not test_u:
    #                 set_if_not_user_or_mine(board, markings, curr_row - 1,
    #                                         curr_column)
    #
    #             if not test_u and not test_r:
    #                 set_if_not_user_or_mine(board, markings, curr_row - 1,
    #                                         curr_column + 1)
    #
    #             if not test_r:
    #                 set_if_not_user_or_mine(board, markings, curr_row,
    #                                         curr_column + 1)
    #
    #             if not test_r and not test_d:
    #                 set_if_not_user_or_mine(board, markings, curr_row + 1,
    #                                         curr_column + 1)
    #
    #             if not test_d:
    #                 set_if_not_user_or_mine(board, markings, curr_row + 1,
    #                                         curr_column)
    #
    #             if not test_d and not test_l:
    #                 set_if_not_user_or_mine(board, markings, curr_row + 1,
    #                                         curr_column - 1)
    #
    #             if not test_l:
    #                 set_if_not_user_or_mine(board, markings, curr_row,
    #                                         curr_column - 1)

    # print(num_of_rows, num_of_columns)



def main():
    markings = {
        "mine": "x",
        "user": "u",
        "empty": 0
    }

    board = generate_board(markings, 15, 3, 5, 3, 2)
    [print(i) for i in board]

    solve_board(markings, board)

if __name__ == '__main__':
    main()
