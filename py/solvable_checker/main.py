from solvable_checker.board_generator import generate_board
from solvable_checker.util import what_is_targetable


def open_zero(board, board_state, curr_column, curr_row, num_of_columns, num_of_rows, markings_state, markings):
    # test_l = curr_column == 0
    # test_r = curr_column == num_of_columns - 1
    # test_u = curr_row == 0
    # test_d = curr_row == num_of_rows - 1
    #
    # if not isinstance(board[curr_row][curr_column], int):
    #     return
    # print(board[curr_row][curr_column])

    if board[curr_row][curr_column] != markings["user"] and not isinstance(board[curr_row][curr_column], int):
        return

    if     board_state[curr_row][curr_column] == markings_state["open"]:
        return

    board_state[curr_row][curr_column] = markings_state["open"]

    if (board[curr_row][curr_column] == markings["empty"])\
            or board[curr_row][curr_column] == markings["user"]:

        for r, c in what_is_targetable(curr_row, curr_column, num_of_rows,
                                       num_of_columns):
            open_zero(board, board_state, c,r, num_of_columns,
                      num_of_rows, markings_state, markings)


        # if not test_u and not test_l:
        #     r = curr_row - 1
        #     c = curr_column - 1
        #
        #     open_zero(board, board_state, c,r, num_of_columns,
        #               num_of_rows, markings_state, markings)
        #
        # if not test_u:
        #     r = curr_row - 1
        #     c = curr_column
        #
        #     open_zero(board, board_state, c,r, num_of_columns,
        #               num_of_rows, markings_state, markings)
        #
        # if not test_u and not test_r:
        #     r = curr_row - 1
        #     c = curr_column + 1
        #
        #     open_zero(board, board_state, c,r, num_of_columns,
        #               num_of_rows, markings_state, markings)
        #
        # if not test_r:
        #     r = curr_row
        #     c = curr_column + 1
        #
        #     open_zero(board, board_state, c,r, num_of_columns,
        #               num_of_rows, markings_state, markings)
        #
        # if not test_r and not test_d:
        #     r = curr_row + 1
        #     c = curr_column + 1
        #
        #     open_zero(board, board_state, c,r, num_of_columns,
        #               num_of_rows, markings_state, markings)
        #
        # if not test_d:
        #     r = curr_row + 1
        #     c = curr_column
        #
        #     open_zero(board, board_state, c,r, num_of_columns,
        #               num_of_rows, markings_state, markings)
        #
        # if not test_d and not test_l:
        #     r = curr_row + 1
        #     c = curr_column - 1
        #
        #     open_zero(board, board_state, c,r, num_of_columns,
        #               num_of_rows, markings_state, markings)
        #
        # if not test_l:
        #     r = curr_row
        #     c = curr_column - 1
        #
        #     open_zero(board, board_state, c,r, num_of_columns,
        #               num_of_rows, markings_state, markings)


# def open_zero_recursion(board, board_state, c, markings, markings_state,
#                         num_of_columns, num_of_rows, r):
#
#     open_zero(board, board_state, c, r,
#               num_of_columns, num_of_rows, markings_state,
#               markings)


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
                is_found = True
                user_row = i
                user_column = j
                break

    print()
    open_zero(board, board_state, user_column, user_row, num_of_columns, num_of_rows, markings_state, markings)

    [print([str(j) for j in i]) for i in board]
    print()
    [print(i) for i in board_state]

    from collections import defaultdict
    front_opened = defaultdict(dict)
    for i, row in enumerate(board_state):
        for j, val in enumerate(row):
            if val == markings_state["open"]:
                if board[i][j] == markings["user"]:
                    continue
                targetable = what_is_targetable(i, j ,num_of_rows, num_of_columns)
                targetable_filtered = []

                for r, c in targetable:
                    if board_state[r][c] == markings_state["closed"]:
                        targetable_filtered.append((r, c))

                if targetable_filtered:
                    front_opened[board[i][j]     ][(i, j)] = targetable_filtered

    print()
    [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in front_opened.items()]


def main():
    markings = {
        "mine": "x",
        "user": "u",
        "empty": 0
    }

    board = generate_board(markings, 10,10,25, 3, 2)
    # [print(i) for i in board]

    solve_board(markings, board)
    # print()

if __name__ == '__main__':
    main()
