from builtins import type

from solvable_checker.board_generator import generate_board
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
from collections import defaultdict

def solve_board(markings, board, markings_state):
    num_of_rows = len(board)
    num_of_columns = len(board[0])

    board_state = [[markings_state["closed"] for _ in range(num_of_columns)] for
                   _ in range(num_of_rows)]

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
    open_zero(board, board_state, user_column, user_row, num_of_columns,
              num_of_rows, markings_state, markings)

    # [print([str(j) for j in i]) for i in board]
    # print()
    # [print(i) for i in board_state]

    from collections import defaultdict

    # board = [    ['1', '1', '3', 'x', 'x', '1', '1', '3', 'x', '3'],
    #     ['2', 'x', '5', 'x', '3', '1', '1', 'x', 'x', 'x'],
    #     ['3', 'x', 'x', '3', '3', '2', '3', '4', 'x', '3'],
    #     ['x', '3', 'u', '2', 'x', 'x', '3', 'x', '2', '1'],
    #     ['2', '2', '0', '1', '2', '3', 'x', '2', '1', '0'],
    #     ['x', '3', '2', '1', '1', '2', '2', '2', '0', '0'],
    #     ['x', 'x', '2', 'x', '1', '1', 'x', '1', '0', '0'],
    #     ['x', '5', '3', '1', '1', '1', '1', '1', '0', '0'],
    #     ['x', 'x', '1', '0', '0', '0', '0', '0', '0', '0'],
    #     ['x', '3', '1', '0', '0', '0', '0', '0', '0', '0'],
    # ]
    #
    # board_state = [
    #
    #     [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    #     [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    #     [' ', ' ', ' ', 'o', ' ', ' ', ' ', ' ', ' ', ' '],
    #     [' ', 'o', 'o', 'o', ' ', ' ', ' ', ' ', ' ', ' '],
    #     [' ', 'o', 'o', 'o', ' ', ' ', ' ', ' ', ' ', ' '],
    #     [' ', 'o', 'o', 'o', ' ', ' ', ' ', ' ', ' ', ' '],
    #     [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    #     [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    #     [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    #     [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
    #
    # ]

    board = [
        ['0', '1', 'x', '2', 'x'],
        ['2', '3', '2', '2', '1'],
        ['x', 'x', '1', '0', '0'],
        ['2', '2', 'u', '1', '1'],
        ['0', '0', '0', '1', 'x'],

    ]

    board_state = [
        [' ', ' ', ' ', ' ', ' '],
        [' ', ' ', 'o', 'o', 'o'],
        [' ', ' ', 'o', 'o', 'o'],
        ['o', 'o', 'o', 'o', 'o'],
        ['o', 'o', 'o', 'o', ' '],
    ]

    [print([str(j) for j in i]) for i in board]
    print()
    [print(i) for i in board_state]

    front_opened_control = defaultdict(dict)
    front_opened = {}
    for i, row in enumerate(board_state):
        for j, val in enumerate(row):
            if val == markings_state["open"]:
                if board[i][j] == markings["user"]:
                    continue

                targetable = what_is_targetable(i, j, num_of_rows,
                                                num_of_columns)
                targetable_filtered = []

                for r, c in targetable:
                    if board_state[r][c] == markings_state["closed"]:
                        targetable_filtered.append((r, c))

                if targetable_filtered:
                    front_opened_control[int(board[i][j])][(i, j)] = targetable_filtered
                    front_opened[int(board[i][j])] = targetable_filtered

    print()
    [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
     front_opened_control.items()]

    # todo run this while new mines can be extracted
    mines = set()

    # remove 1 (4, 3) [(4, 4)]
    # remove 2 (3, 1) [(2, 1), (2, 0)]
    direct_extraction(front_opened_control, mines)

    print(f"{mines=}")
    [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
     front_opened_control.items()]
    print()

    # cleanup front, find what can be opened
    front_opened_control, to_open = cleanup_front(front_opened_control, mines)

    print()
    print("new front")
    [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
     front_opened_control.items()]

    print()
    # 1 (1, 2) [(0, 2), (0, 1), (0, 3), (1, 1)]
    # 1 (1, 4) [(0, 4), (0, 3)]
    # 2 (1, 3) [(0, 3), (0, 2), (0, 4)]
    #
    # remove last one and mark (0, 2) as mine
    # 1 (1, 2) [(0, 2), (0, 1), (0, 3), (1, 1)]
    # 1 (1, 4) [(0, 4), (0, 3)]

    # 1 (1, 2) [(0, 2), (0, 1), (0, 3), (1, 1)]
    # 1 (1, 4) [(0, 4), (0, 3)]
    # 2 (1, 3) [(0, 3), (0, 2), (0, 4), (7, 7)]
    #
    # 1 (1, 2) [(0, 2), (0, 1), (0, 3), (1, 1)]
    # 1 (1, 4) [(0, 4), (0, 3)]
    # 1 (1, 3) [(0, 2), (7, 7)]

    # todo remove same rows, check if that is possible

    seen = set()
    to_remove_tiles = set()
    reduced_tiles = defaultdict(dict)
    for cardinality_1, tiles_1 in front_opened_control.items():

        for t_1, t_b_d_1 in tiles_1.items():
            print(t_1, "-", t_b_d_1)
            seen.add(t_1)
            for cardinality_2, tiles_2 in front_opened_control.items():

                for t_2, t_b_d_2 in tiles_2.items():
                    if t_2 in seen:
                        continue

                    print("\t", t_2, "-", t_b_d_2)

                    for i in [(t_b_d_1, t_b_d_2, t_2), (t_b_d_2, t_b_d_1, t_1)]:

                        if set(i[0]).issubset(set(i[1])):
                            new_cardinality = abs(cardinality_2 - cardinality_1)
                            new_t_b_d = [j for j in i[1] if j not in i[0]]
                            to_remove_tiles.add(i[-1])
                            # todo check what happens if from first for loop is removed element
                            #   do you need to rerun this portion of code till no change
                            reduced_tiles[new_cardinality][i[2]] = new_t_b_d

    front_opened_control = {i[0]: {j[0]: j[1] for j in i[1].items() if j[0] not in to_remove_tiles} for i in front_opened_control.items()}
    # front_opened_control[]
    # front_opened_control[]
    for i in reduced_tiles.items():
        for j in i[1].items():

            front_opened_control[i[0]][j[0]] = j[1]

    # [[front_opened_control[i[0]][j[0]] = j[1]  for j in  i[1].items()] for i in reduced_tiles.items()]
    # front_opened_control += reduced_tiles
    print(reduced_tiles)
    [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
     front_opened_control.items()]

def cleanup_front(front_opened_control, mines):
    new_front = defaultdict(dict)
    # todo switch to front_opened
    to_open = set()
    for cardinality, tiles in front_opened_control.items():

        # to_remove = []
        for t, t_b_d in tiles.items():
            # print(t, "-", t_b_d)

            new_t_b_d = []
            cardinality_decrementer = 0

            for i in t_b_d:
                if i in mines:
                    # print("remove", i)

                    cardinality_decrementer += 1
                else:
                    new_t_b_d.append(i)

            # print(new_t_b_d)

            if cardinality - cardinality_decrementer <= 0:
                print("safe found", new_t_b_d)
                [to_open.add(i) for i in new_t_b_d]
            else:
                new_front[cardinality - cardinality_decrementer][t] = new_t_b_d
    front_opened_control = new_front
    return front_opened_control, to_open


def direct_extraction(front_opened_control, mines):
    # todo switch to front_opened
    for cardinality, tiles in front_opened_control.items():

        to_remove = []
        for t, t_b_d in tiles.items():

            if cardinality == len(t_b_d):
                [mines.add(i) for i in t_b_d]
                to_remove.append(t)

        [tiles.pop(i) for i in to_remove]
    print()


def main():
    markings = {
        "mine": "x",
        "user": "u",
        "empty": 0
    }
    markings_state = {
        "open": "o",
        "closed": " "
    }

    board = generate_board(markings, 5, 5, 5, 3, 2)
    # [print(i) for i in board]

    solve_board(markings, board, markings_state)
    # print()


if __name__ == '__main__':
    main()

# ['1', '1', '3', 'x', 'x', '1', '1', '3', 'x', '3']
# ['2', 'x', '5', 'x', '3', '1', '1', 'x', 'x', 'x']
# ['3', 'x', 'x', '3', '3', '2', '3', '4', 'x', '3']
# ['x', '3', 'u', '2', 'x', 'x', '3', 'x', '2', '1']
# ['2', '2', '0', '1', '2', '3', 'x', '2', '1', '0']
# ['x', '3', '2', '1', '1', '2', '2', '2', '0', '0']
# ['x', 'x', '2', 'x', '1', '1', 'x', '1', '0', '0']
# ['x', '5', '3', '1', '1', '1', '1', '1', '0', '0']
# ['x', 'x', '1', '0', '0', '0', '0', '0', '0', '0']
# ['x', '3', '1', '0', '0', '0', '0', '0', '0', '0']
#
# [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ']
# [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ']
# [' ', ' ', ' ', 'o', ' ', ' ', ' ', ' ', ' ', ' ']
# [' ', 'o', 'o', 'o', ' ', ' ', ' ', ' ', ' ', ' ']
# [' ', 'o', 'o', 'o', ' ', ' ', ' ', ' ', ' ', ' ']
# [' ', 'o', 'o', 'o', ' ', ' ', ' ', ' ', ' ', ' ']
# [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ']
# [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ']
# [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ']
# [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ']
#
# 3 (2, 3) [(1, 3), (1, 2), (1, 4), (3, 4), (2, 4), (2, 2)]
# 3 (3, 1) [(2, 1), (2, 0), (2, 2), (4, 0), (3, 0)]
# 3 (5, 1) [(4, 0), (6, 1), (6, 0), (6, 2), (5, 0)]
# 2 (3, 3) [(2, 2), (2, 4), (4, 4), (3, 4)]
# 2 (4, 1) [(3, 0), (5, 0), (4, 0)]
# 2 (5, 2) [(6, 2), (6, 1), (6, 3)]
# 1 (4, 3) [(3, 4), (5, 4), (4, 4)]
# 1 (5, 3) [(4, 4), (6, 3), (6, 2), (6, 4), (5, 4)]
#
# Process finished with exit code 0
