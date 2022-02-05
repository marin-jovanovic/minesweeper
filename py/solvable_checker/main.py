from builtins import type
from collections import defaultdict

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
    open_tile(board, board_state, user_row, user_column, num_of_columns,
       num_of_rows, markings_state, markings)

    # board = [
    #     ['0', '1', 'x', '2', 'x'],
    #     ['2', '3', '2', '2', '1'],
    #     ['x', 'x', '1', '0', '0'],
    #     ['2', '2', 'u', '1', '1'],
    #     ['0', '0', '0', '1', 'x'],
    #
    # ]
    #
    # board_state = [
    #     [' ', ' ', ' ', ' ', ' '],
    #     [' ', ' ', 'o', 'o', 'o'],
    #     [' ', ' ', 'o', 'o', 'o'],
    #     ['o', 'o', 'o', 'o', 'o'],
    #     ['o', 'o', 'o', 'o', ' '],
    # ]
    # board = [
    # ['1', '2', '2', '2', 'x'],
    # ['1', 'x', 'x', '3', '1'],
    # ['2', '4', 'x', '2', '0'],
    # ['1', 'x', 'u', '1', '0'],
    # ['1', '1', '1', '0', '0'],
    # ]
    #
    # board_state = [
    # [' ', ' ', ' ', ' ', ' '],
    # [' ', ' ', ' ', 'o', 'o'],
    # [' ', 'o', ' ', 'o', 'o'],
    # [' ', ' ', 'o', 'o', 'o'],
    # [' ', 'o', 'o', 'o', 'o'],
    # ]

    # ['0', '0', '1', '2', 'x']
    # ['0', '0', '2', 'x', '4']
    # ['0', '0', '2', 'x', 'x']
    # ['1', '1', 'u', '2', '2']
    # ['1', 'x', '1', '0', '0']
    #
    # ['o', 'o', 'o', ' ', ' ']
    # ['o', 'o', 'o', ' ', ' ']
    # ['o', 'o', 'o', ' ', ' ']
    # ['o', 'o', 'o', 'o', 'o']
    # [' ', ' ', 'o', 'o', 'o']

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

    # this must not be reinitialized
    mines = set()
    to_open = set()
    # remove 1 (4, 3) [(4, 4)]
    # remove 2 (3, 1) [(2, 1), (2, 0)]

    while   new_mines := direct_extraction(front_opened_control):
        [mines.add(i) for i in new_mines]

        # cleanup front, find what can be opened
        front_opened_control, new_to_open = cleanup_front(front_opened_control, mines)
        [to_open.add(i) for i in new_to_open]

        print()
        print(f"{mines=}")
        print(f"{to_open=}")
        print("new front")
        [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
         front_opened_control.items()]

    print()

    [print([str(j) for j in i]) for i in board]
    print()
    for r, c in mines:
        board_state[r][c] = markings_state["mine"]

    for r, c in to_open:

        open_tile(board, board_state, r, c, num_of_columns,
                  num_of_rows, markings_state, markings)

    [print(i) for i in board_state]
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

    front_opened_control = subset_cleaner(front_opened_control)
    print()
    [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
     front_opened_control.items()]

    # front_opened_control = subset_cleaner(front_opened_control)
    # [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
    #  front_opened_control.items()]


def subset_cleaner(front_opened_control):
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

    # todo optimize with pop
    # front_opened_control = {
    #     i[0]: {j[0]: j[1] for j in i[1].items() if j[0] not in to_remove_tiles}
    #     for i in front_opened_control.items()}

    # breakpoint()

    # print("c f o c")
    # [[print(i[0], j) for j in i[1].items()] for i in front_opened_control.items()]

    print(reduced_tiles)

    for i in front_opened_control.items():
        for j in i[1].items():
            if j[0]  in to_remove_tiles:
                continue

            reduced_tiles[i[0]][j[0]] = j[1]

    # for i in reduced_tiles.items():
    #     for j in i[1].items():
    #         front_opened_control[i[0]][j[0]] = j[1]

    return reduced_tiles
    # print(reduced_tiles)
    # return front_opened_control


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
                # print("safe found", new_t_b_d)
                [to_open.add(i) for i in new_t_b_d]
            else:
                new_front[cardinality - cardinality_decrementer][t] = new_t_b_d
    front_opened_control = new_front
    return front_opened_control, to_open


def direct_extraction(front_opened_control):
    mines = set()
    # todo switch to front_opened
    for cardinality, tiles in front_opened_control.items():

        to_remove = []
        for t, t_b_d in tiles.items():

            if cardinality == len(t_b_d):
                [mines.add(i) for i in t_b_d]
                to_remove.append(t)

        [tiles.pop(i) for i in to_remove]
    print()
    return mines


def main():
    markings = {
        "mine": "x",
        "user": "u",
        "empty": 0
    }
    markings_state = {
        "open": "o",
        "closed": " ",
        "mine": "m"
    }

    board = generate_board(markings, 5, 5, 5, 3, 2)
    # [print(i) for i in board]

    solve_board(markings, board, markings_state)
    # print()


if __name__ == '__main__':
    main()

