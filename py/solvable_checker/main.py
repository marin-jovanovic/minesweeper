from collections import defaultdict

from solvable_checker.basic_strategy import basic_strategy
from solvable_checker.board_generator import generate_board_with_first_move
from solvable_checker.util import get_all_mines, \
    create_front
from solvable_checker.constants import game_status

def solve_board(markings, board, markings_state, board_state, num_of_rows=None,
                num_of_columns=None):
    # todo add hints
    #   need click log     # click_log = set()

    if not num_of_rows:
        num_of_rows = len(board)
    if not num_of_columns:
        num_of_columns = len(board[0])

    front_opened = defaultdict(dict)
    create_front(board, board_state, front_opened,
                 markings, markings_state, num_of_columns,
                 num_of_rows)

    print()
    [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
     front_opened.items()]
    print()

    # todo run this while new mines can be extracted

    # this must not be reinitialized
    mines = set()

    iteration = 0
    is_sth_changed = True
    while is_sth_changed:
        print(80 * "-")
        print(80 * "-")
        print(80 * "-")
        print(f"{iteration=}")
        iteration += 1

        if not front_opened:
            print("job done")
            break

        status, mines = basic_strategy(front_opened, board_state, markings_state, board,
                                       markings,
                                       num_of_columns,
                                       num_of_rows, )

        if status == game_status["solution found"]:
            return mines

        print("after removing cardinality == len and cardinality == 0")

        print_boards(board, board_state)
        # print()
        # [[print( i[0], j[0],j[1]) for j in i[1].items()] for i in
        #  front_opened.items()]
        print()
        # [print(i) for i in board_state]


        print()
        [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
         front_opened.items()]
        print()

        print(80 * "-")
        return get_all_mines(board_state, markings_state)
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
        front_opened, is_sth_changed = subset_cleaner(front_opened)
        print()
        [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
         front_opened.items()]

        c = remove_mines_from_board_state(front_opened, mines)
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
     front_opened.items()]
    print()

    # if 0 in front_opened:
    #     print("cleanup", front_opened[0])

    # front_opened = subset_cleaner(front_opened)
    # [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
    #  front_opened.items()]


def remove_mines_from_board_state(front, mines):
    print("remove_mines_from_board_state")
    print(f"{front=}")

    # remove mines from rows
    for row in front.items():
        print(f"{row[0]=} {row[1]=}")
        for c_t, t_b_d in enumerate(row[1].items()):
            for j in t_b_d[1]:
                if j in mines:
                    print("removing", j)
                    print("bcz", mines)
                    t_b_d[1].remove(j)


#                    todo decrement cardinality


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





def print_boards(board, board_state):
    [print([str(j) for j in i], l) for i, l in zip(board, board_state)]

from solvable_checker.constants import markings, markings_state
def main():

    num_of_rows = 10
    num_of_columns = 5
    num_of_mines = 15
    user_row = 3
    user_column = 2

    board, board_state = generate_board_with_first_move(num_of_rows,
                                                        num_of_columns,
                                                        num_of_mines, user_row,
                                                        user_column)

    print_boards(board, board_state)

    # todo handle user

    mines_location = solve_board(markings, board, markings_state, board_state)

    print("main mines", mines_location)

    correct_mines_location = set()
    for i, r in enumerate(board):
        for j, elem in enumerate(r):
            if elem == markings["mine"]:
                correct_mines_location.add((i, j))

    # if all([i for i in mines_location])
    if mines_location.issubset(correct_mines_location):
        print("all found mines are correct")

    else:
        print("mines wrongly detected, marked wrongly but not dead")

    assert len(mines_location), num_of_mines


if __name__ == '__main__':
    main()
