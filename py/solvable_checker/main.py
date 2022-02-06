from collections import defaultdict

from solvable_checker.basic_strategy import basic_strategy
from solvable_checker.board_generator import generate_board_with_first_move
from solvable_checker.constants import game_status
from solvable_checker.constants import markings, markings_state
from solvable_checker.util import get_all_mines, \
    create_front
from solvable_checker.subset_strategy import subset_strategy


def solve_board(markings, board, markings_state, board_state, num_of_rows=None,
                num_of_columns=None):
    # todo add hints
    #   need click log     # click_log = set()

    num_of_rows = num_of_rows if num_of_rows else len(board)
    num_of_columns = num_of_columns if num_of_columns else len(board[0])

    front = create_front(board, board_state, None, num_of_columns, num_of_rows)

    print()
    [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in front.items()]
    print()

    # todo run this while new mines can be extracted

    # this must not be reinitialized
    mines = set()

    iteration = 0
    is_sth_changed = True
    while is_sth_changed:
        is_sth_changed= False
        print(80 * "-")
        print(80 * "-")
        print(80 * "-")
        print(f"{iteration=}")
        iteration += 1

        if not front:
            print("job done")
            break

        # updates front
        status, mines, front = basic_strategy(front, board_state, markings_state,
                                       board, None, num_of_columns,
                                       num_of_rows, )

        if status == game_status["solution found"]:
            return mines

        print("after removing cardinality == len and cardinality == 0")

        print_boards(board, board_state)
        print()
        [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in front.items()]

        c = 0
        for i,j in front.items():
            for k, l in j.items():
                c += 1
        print(f"{c=}")

        status, mines, front, change_status = subset_strategy(front, board_state, markings_state, board,
                   markings,
                   num_of_columns,
                   num_of_rows, )

        if status == game_status["solution found"]:
            return mines

        if change_status == "changed":
            is_sth_changed = True

        print("after subset strategy")
        [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in front.items()]
        print_boards(board, board_state)

        # 1 (3, 3) [(2, 4), (4, 4), (3, 4)]
        # 1 (4, 1) [(2, 4), (4, 4), (3, 4), (x, y)] -> mark (x, y) as safe

        # 1 (3, 3) [(2, 4), (4, 4), (3, 4)]
        # 2 (4, 1) [(2, 4), (4, 4), (3, 4), (x, y)] -> mark (x, y) as mine

        # 1 (3, 3) [(2, 4), (4, 4)]
        # 2 (4, 1) [(2, 4), (4, 4), (3, 4), (x, y)] -> mark (x, y) as safe

    return get_all_mines(board_state, markings_state)

    print(80 * "-")
    print(f"{click_log=}")
    print(f"{mines=}")

    print()
    [print(i) for i in board_state]
    print()
    [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
     front.items()]
    print()

    # if 0 in front:
    #     print("cleanup", front[0])

    # front = subset_cleaner(front)
    # [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
    #  front.items()]


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
