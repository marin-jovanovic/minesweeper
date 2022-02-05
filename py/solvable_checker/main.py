from collections import defaultdict

from solvable_checker.board_generator import generate_board
from solvable_checker.tile_opener import open_tile
from solvable_checker.util import what_is_targetable


def solve_board(markings, board, markings_state, user_row=None, user_column=None,
                num_of_rows=None, num_of_columns=None):

    board_state, num_of_columns, num_of_rows = solver_init_config(board,
                                                                  markings,
                                                                  markings_state,
                                                                  num_of_columns,
                                                                  num_of_rows,
                                                                  user_column,
                                                                  user_row)

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
    print()

    # todo run this while new mines can be extracted

    # this must not be reinitialized
    mines = set()

    click_log = set()
    # to_open = set()

    iteration = 0
    is_sth_changed = True
    while is_sth_changed:
        print(80 * "-")
        print(80 * "-")
        print(80 * "-")
        print(f"{iteration=}")
        iteration += 1

        # ##########################################################################

        # solve this kind of problems
        # remove 1 (4, 3) [(4, 4)]
        # remove 2 (3, 1) [(2, 1), (2, 0)]
        # todo
        #   remove 0 (3, 1) [(2, 1), (2, 0)]

        # extract new mines
        # delete rows from front_opened_control
        while new_mines := direct_extraction(front_opened_control):
            [mines.add(i) for i in new_mines]

            remove_mines_from_board_state(board_state, mines)

            for r, c in new_mines:
                board_state[r][c] = markings_state["mine"]

            # cleanup front, find what can be opened
            front_opened_control, new_to_open = cleanup_front(front_opened_control, mines)

            for r, c in new_to_open:
                click_log.add((r, c))
                open_tile(board, board_state, r, c, num_of_columns,
                          num_of_rows, markings_state, markings)

        print()
        print("direct extraction")
        print(f"\t{click_log=}")
        print(f"\t{mines=}")

        print()
        [print("\t",i) for i in board_state]
        print()
        [[print("\t",i[0], j[1]) for j in i[1].items()] for i in
         front_opened_control.items()]
        print()

        print(80 * "-")
        # ##########################################################################

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

        print("subsets")
        front_opened_control, is_sth_changed = subset_cleaner(front_opened_control)
        print()
        [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
         front_opened_control.items()]

        c = remove_mines_from_board_state(board_state, mines)
        # if c:
        #     is_sth_changed = True

        is_sth_changed = True if c else is_sth_changed

    print(80 * "-")
    print(f"{click_log=}")
    print(f"{mines=}")

    print()
    [print(i) for i in board_state]
    print()
    [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
     front_opened_control.items()]
    print()


    # if 0 in front_opened_control:
    #     print("cleanup", front_opened_control[0])

    # front_opened_control = subset_cleaner(front_opened_control)
    # [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
    #  front_opened_control.items()]


def remove_mines_from_board_state(board_state, mines):
    # remove mines from rows
    for i, row in enumerate(board_state):
        for c_t, t_b_d in enumerate(row):
            for j in t_b_d:
                if j in mines:
                    print("removing", j)
                    print("bcz", mines)
                    t_b_d.remove(j)
#                    todo decrement cardinality


def solver_init_config(board, markings, markings_state, num_of_columns,
                       num_of_rows, user_column, user_row):
    if not num_of_rows:
        num_of_rows = len(board)
    if not num_of_columns:
        num_of_columns = len(board[0])
    board_state = [[markings_state["closed"] for _ in range(num_of_columns)] for
                   _ in range(num_of_rows)]
    if not user_row and not user_column:
        is_found = False

        for i, row in enumerate(board):
            if is_found:
                break
            for j, val in enumerate(row):
                if val == markings["user"]:
                    is_found = True
                    user_row = i
                    user_column = j
                    break
    open_tile(board, board_state, user_row, user_column, num_of_columns,
              num_of_rows, markings_state, markings)
    return board_state, num_of_columns, num_of_rows


def subset_cleaner(front_opened_control):
    seen = set()
    to_remove_tiles = set()
    reduced_tiles = defaultdict(dict)
    for cardinality_1, tiles_1 in front_opened_control.items():

        for t_1, t_b_d_1 in tiles_1.items():
            print("\t", t_1, "-", t_b_d_1)
            seen.add(t_1)
            for cardinality_2, tiles_2 in front_opened_control.items():

                for t_2, t_b_d_2 in tiles_2.items():
                    if t_2 in seen:
                        continue

                    print("\t\t", t_2, "-", t_b_d_2)

                    for i in [(t_b_d_1, t_b_d_2, t_2), (t_b_d_2, t_b_d_1, t_1)]:

                        if set(i[0]).issubset(set(i[1])):
                            new_cardinality = abs(cardinality_2 - cardinality_1)
                            new_t_b_d = [j for j in i[1] if j not in i[0]]
                            to_remove_tiles.add(i[-1])
                            # todo check what happens if from first for loop is removed element
                            #   do you need to rerun this portion of code till no change

                            if new_t_b_d:
                                reduced_tiles[new_cardinality][i[2]] = new_t_b_d

    # todo optimize with pop

    # print(reduced_tiles)

    for i in front_opened_control.items():
        for j in i[1].items():
            if j[0] in to_remove_tiles:
                continue

            reduced_tiles[i[0]][j[0]] = j[1]

    # print("sth changed", reduced_tiles != front_opened_control)

    return reduced_tiles, reduced_tiles != front_opened_control
    # print(reduced_tiles)
    # return front_opened_control


def cleanup_front(front_opened_control, mines):
    new_front = defaultdict(dict)
    to_open = set()

    for cardinality, tiles in front_opened_control.items():

        for t, t_b_d in tiles.items():

            new_t_b_d = []
            cardinality_decrementer = 0

            for i in t_b_d:
                if i in mines:

                    cardinality_decrementer += 1
                else:
                    new_t_b_d.append(i)

            if cardinality - cardinality_decrementer <= 0:
                [to_open.add(i) for i in new_t_b_d]
            else:
                new_front[cardinality - cardinality_decrementer][t] = new_t_b_d

    return new_front, to_open


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
    # print()
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

