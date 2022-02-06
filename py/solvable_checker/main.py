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
    update_front_opened_control(board, board_state, front_opened_control,
                                markings, markings_state, num_of_columns,
                                num_of_rows)

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

        if not front_opened_control:
            print("job done")
            break

        # ##########################################################################

        # solve this kind of problems
        # remove 1 (4, 3) [(4, 4)]
        # remove 2 (3, 1) [(2, 1), (2, 0)]
        # todo
        #   remove 0 (3, 1) [(2, 1), (2, 0)]

        # extract new mines
        # delete rows from front_opened_control

        is_sth_changed = True
        while is_sth_changed:
            is_sth_changed = False

            # 	 1 [(3, 0)] -> remove, add to new_mines
            # 	 3 [(2, 0), (2, 2), (4, 0), (3, 0)]
            front_opened_control, new_mines = direct_extraction(front_opened_control, board_state, markings_state)
            if new_mines:
                is_sth_changed = True

            if not new_mines:
                break

            print()
            print("after removing cardinality == len")
            [print("\t",[str(j) for j in i]) for i in board]
            print()
            [print("\t", i) for i in board_state]
            print()

            front_opened_control, new_to_open = cleanup_front(front_opened_control, new_mines)

            # print("after removing mines")
            [[print("\t", i[0], j[0],j[1]) for j in i[1].items()] for i in
             front_opened_control.items()]
            print()

            print(f"\t{new_mines=}")
            print(f"\t{new_to_open=}")
            print()

            for r, c in new_to_open:
                click_log.add((r, c))
                open_tile(board, board_state, r, c, num_of_columns,
                          num_of_rows, markings_state, markings)

            update_front_opened_control(board, board_state, front_opened_control,
                                        markings, markings_state, num_of_columns,
                                        num_of_rows)

            # print("reduced to")
            [[print("\t", i[0], j[0],j[1]) for j in i[1].items()] for i in
             front_opened_control.items()]

            if not front_opened_control:
                print("front opened control empty, board must be solved")
                return

            [mines.add(i) for i in new_mines]
            print()
        print()
        print("direct extraction")
        print(f"\t{click_log=}")
        print(f"\t{mines=}")

        print()
        [print("\t",i) for i in board_state]
        print()
        [[print("\t",i[0], j[0], j[1]) for j in i[1].items()] for i in
         front_opened_control.items()]
        print()

        print(80 * "-")
        return
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

        c = remove_mines_from_board_state(front_opened_control, mines)
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


def update_front_opened_control(board, board_state, front_opened_control,
                                markings, markings_state, num_of_columns,
                                num_of_rows):

    for i, row in enumerate(board_state):
        for j, val in enumerate(row):
            if val == markings_state["open"]:
                if board[i][j] in [ markings["user"], markings["mine"]]:
                    continue

                targetable = what_is_targetable(i, j, num_of_rows,
                                                num_of_columns)
                targetable_filtered = []

                for r, c in targetable:
                    if board_state[r][c] == markings_state["closed"]:
                        targetable_filtered.append((r, c))

                if targetable_filtered:
                    try:
                        front_opened_control[int(board[i][j])][(i, j)] = targetable_filtered
                    except Exception as e:
                        print("type", board[i][j])
                        raise e

def remove_mines_from_board_state(front_opened_control, mines):

    print("remove_mines_from_board_state")
    print(f"{front_opened_control=}")
    # print()
    #
    # remove mines from rows
    for  row in front_opened_control.items():
        print(f"{row[0]=} {row[1]=}")
        for c_t, t_b_d in enumerate(row[1].items()):
            # print(f" {row=} {c_t=} {t_b_d=}")
            for j in t_b_d[1]:
                if j in mines:
                    print("removing", j)
                    print("bcz", mines)
                    t_b_d[1].remove(j)
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
    """
    1 (1, 0) [(0, 0), (0, 1)]
    1 (1, 3) [(0, 3), (0, 2), (0, 4), (2, 4), (1, 4)] -> mine (1, 4) -> remove row, add to new_to_open
    1 (2, 3) [(1, 4), (3, 4), (2, 4)]
    1 (4, 2) [(4, 3)]
    2 (1, 1) [(0, 1), (0, 0), (0, 2)]
    2 (1, 2) [(0, 2), (0, 1), (0, 3)]
    3 (3, 3) [(2, 4), (4, 3), (4, 4), (3, 4)]

    1 (1, 0) [(0, 0), (0, 1)]
    1 (4, 2) [(4, 3)]
    2 (1, 1) [(0, 1), (0, 0), (0, 2)]
    2 (1, 2) [(0, 2), (0, 1), (0, 3)]
    3 (3, 3) [(2, 4), (4, 3), (4, 4), (3, 4)]

    to_del=[(2, 3), (1, 4), (4, 1)]
    new_to_open={(2, 4), (0, 4), (3, 4), (0, 3), (0, 2)}

    """


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
                if not new_t_b_d: continue
                new_front[cardinality - cardinality_decrementer][t] = new_t_b_d

    return new_front, to_open


def direct_extraction(front_opened_control,board_state,markings_state):
    """
    1 (1, 0) [(0, 0), (0, 1)]
    1 (1, 3) [(0, 3), (0, 2), (0, 4), (2, 4), (1, 4)]
    1 (2, 3) [(1, 4), (3, 4), (2, 4)]
    1 (4, 2) [(4, 3)] -> remove this, add to mines
    2 (1, 1) [(0, 1), (0, 0)] -> remove this, add to mines
    2 (1, 2) [(0, 2), (0, 1), (0, 3)]
    3 (3, 3) [(2, 4), (4, 3), (4, 4), (3, 4)]

    """

    new_front_opened = defaultdict(dict)
    mines = set()

    for cardinality, tiles in front_opened_control.items():

        to_remove = []
        for t, t_b_d in tiles.items():

            if cardinality == len(t_b_d):
                [mines.add(i) for i in t_b_d]
                to_remove.append(t)
            else:
                new_front_opened[cardinality][t] = t_b_d

        [tiles.pop(i) for i in to_remove]
    # print()

    for r, c in mines:
        board_state[r][c] = markings_state["mine"]

    # front_opened_control = new_front_opened

    return new_front_opened, mines


def print_boards(board, board_state):
    [print([str(j) for j in i], l) for i, l in zip(board, board_state)]


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

    num_of_rows = 5
    num_of_columns = 5
    num_of_mines = 5
    user_row = 3
    user_column = 2

    # can not be mine
    # can not be number other than 0 because then you need to guess
    board = generate_board(markings, num_of_rows, num_of_columns, num_of_mines,
                   user_row, user_column)

    board_state = [[markings_state["closed"] for _ in range(num_of_columns)] for
                   _ in range(num_of_rows)]

    open_tile(board, board_state, user_row, user_column, num_of_columns,
             num_of_rows, markings_state, markings)

    print_boards(board, board_state)

    # todo handle user

    return

    solve_board(markings, board, markings_state)
    # print()


if __name__ == '__main__':
    main()

