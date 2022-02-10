import sys
from collections import defaultdict

from solvable_checker.constants import game_status
from solvable_checker.util import get_all_mines, create_front, what_is_targetable
from solvable_checker.constants import markings, markings_state


def basic_strategy(front_opened, board_state, markings_state, board,
                   markings,
                   num_of_columns,
                   num_of_rows, ):
    """


    1 [(3, 0)] -> remove, mark as mine
    0 [(3, 0)] -> remove, open
    1 [(2, 0), (2, 2)] -> nothing



    """
    is_sth_changed = True
    while is_sth_changed:
        front_opened, new_mines = direct_extraction(front_opened, board_state,
                                                    markings_state)

        is_sth_changed = True if new_mines else False

        """
        handle 
        0 (a, b) [(2, 0), (2, 2), (4, 0), (3, 0)]
        0 [(3, 0)] -> remove, open
        """
        what_is_opened = set()
        for cardinality, tiles in front_opened.items():

            for t, t_b_d in tiles.items():

                if cardinality == 0:
                    for r, c in t_b_d:
                        what_is_opened.add((r, c))
                        board_state[r][c] = markings_state["open"]
                    is_sth_changed = True

        # todo iterate over all current rows and update them
        # todo add opened to rows

        # todo iter over what is opened and add to rows
        # todo go over all neiqhbours of what is opeend and update their opened neigh.

        if what_is_opened:
            print("----")
            for i in what_is_opened:
                r, c = i[0], i[1]

                print(r,c)

                targetable = what_is_targetable(r, c, num_of_rows,
                                                num_of_columns)

                for j in targetable:
                    if board_state[j[0]][j[1]] == markings_state["open"]:
                        print("update", j)

                        is_found = False
                        curr_num = -1
                        tmp = None
                        for k, m in front_opened.items():
                            print(k, m)
                            curr_num = k
                            for n, p in m.items():
                                if n == j:
                                    is_found = True
                                    print("this")

                                    tmp = m.pop(n)
                                    print("poped", tmp)
                                    print(j in tmp)
                                    tmp.remove(j)
                                    # m[j] ==
                                    break

                            if is_found:
                                break
                                    # front_opened.pop()
                        if tmp:
                            front_opened[curr_num - 1][j] = tmp

                        [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
                         front_opened.items()]

            sys.exit()



        # # # fixme recursion infinite error
        # front_opened = update_front(board, board_state, front_opened,
        #                             num_of_columns,
        #                             num_of_rows, mines_present=True)

        if not front_opened:
            print("all tiles opened and all mines marked")
            return game_status["solution found"], get_all_mines(board_state,
                                                                markings_state), front_opened

    return game_status["solution not found"], None, front_opened


def update_front(board, board_state, front_opened_control, num_of_columns,
                 num_of_rows, mines_present=False):
    # todo add option to iterate over prev closed so that you do not need to
    #   check alr. opened

    from collections import defaultdict
    front = defaultdict(dict)

    for i, row in enumerate(board_state):
        for j, val in enumerate(row):
            if val == markings_state["open"]:

                if board[i][j] == markings["mine"]:
                    if not mines_present:
                        continue

                targetable = what_is_targetable(i, j, num_of_rows,
                                                num_of_columns)
                targetable_filtered = []
                cardinality_decrementer = 0

                for r, c in targetable:
                    if board_state[r][c] == markings_state["closed"]:
                        targetable_filtered.append((r, c))

                    if mines_present:
                        if board_state[r][c] == markings_state["mine"]:
                            cardinality_decrementer += 1

                if targetable_filtered:
                    try:
                        front[
                            int(board[i][j]) - cardinality_decrementer][
                            (i, j)] = targetable_filtered
                    except Exception as e:
                        print("type", board[i][j])
                        raise e

    return front

def direct_extraction(front, board_state, markings_state):
    """
    filters front

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

    for cardinality, tiles in front.items():

        to_remove = []
        for t, t_b_d in tiles.items():

            if cardinality == len(t_b_d):
                [mines.add(i) for i in t_b_d]
                to_remove.append(t)
            else:
                new_front_opened[cardinality][t] = t_b_d

        [tiles.pop(i) for i in to_remove]

    for r, c in mines:
        board_state[r][c] = markings_state["mine"]

    return new_front_opened, mines
