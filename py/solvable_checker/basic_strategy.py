import sys
from collections import defaultdict

from solvable_checker.constants import game_status
from solvable_checker.util import get_all_mines, create_front, get_tile_neighbours
from solvable_checker.constants import markings, markings_state
# from solvable_checker.main import print_boards
from solvable_checker.util import print_boards
from solvable_checker.constants import Markings, MarkingsState


def recalculate_tile_info(r, c,num_of_rows,num_of_columns, board_state, board):

    # todo throw exception if mine or zero

    t_b_d = set()

    cardinality_decrementer = 0
    for neighbour in get_tile_neighbours(r, c, num_of_rows, num_of_columns):

        if board_state[neighbour[0]][neighbour[1]] == MarkingsState.MINE.value:
            cardinality_decrementer += 1

        elif board_state[neighbour[0]][neighbour[1]] != MarkingsState.OPEN.value:
            t_b_d.add(neighbour)

    cardinality = board[r][c] - cardinality_decrementer

    return cardinality, (r, c), t_b_d


def basic_strategy(front, board_state, markings_state, board,
                   markings,
                   num_of_columns,
                   num_of_rows, ):
    """

    1 [(3, 0)] -> remove, mark as mine
    0 [(3, 0)] -> remove, open
    1 [(2, 0), (2, 2)] -> nothing

    """

    # [[print(i[0], tile[0], tile[1]) for tile in i[1].items()] for i in
    #  front.items()]

    is_sth_changed = True
    while is_sth_changed:

        front, new_mines = direct_extraction(front, board_state,
                                             markings_state)

        # todo check if needed
        # front = recalculate_front(board, board_state, front,num_of_columns, num_of_rows)

        if new_mines:

            for i in new_mines:
                r, c = i[0], i[1]

                for neighbour in get_tile_neighbours(r, c, num_of_rows,
                                                 num_of_columns):

                    if board_state[neighbour[0]][neighbour[1]] == markings_state["open"]:

                        for cardinality, tile_composite_dict in front.items():
                            if neighbour in tile_composite_dict:
                                tmp = tile_composite_dict.pop(neighbour)
                                tmp.remove(i)

                                if tmp:
                                    front[cardinality - 1][neighbour] = tmp

                                break

        is_sth_changed = True if new_mines else False

        """
        handle 
        0 (a, b) [(2, 0), (2, 2), (4, 0), (3, 0)]
        0 [(3, 0)] -> remove, open
        """
        what_is_opened = set()
        for cardinality, tile_composite_dict in front.items():

            for tile, t_b_d in tile_composite_dict.items():

                if cardinality == 0:
                    for r, c in t_b_d:
                        what_is_opened.add((r, c))
                        board_state[r][c] = markings_state["open"]
                    is_sth_changed = True

        # front = recalculate_front(board, board_state, front,num_of_columns, num_of_rows)

        # iterate over newly opened tiles and add them to front, update their neigh.
        if what_is_opened:

            for i in what_is_opened:
                r, c = i[0], i[1]

                for neighbour in get_tile_neighbours(r, c, num_of_rows,
                                                 num_of_columns):

                    if board_state[neighbour[0]][neighbour[1]] == markings_state["open"]:

                        for cardinality, tile_composite_dict in front.items():
                            if neighbour in tile_composite_dict:
                                tmp = tile_composite_dict.pop(neighbour)
                                tmp.remove(i)

                                if tmp:
                                    front[cardinality - 1][neighbour] = tmp

                                break

            # todo mines

        if not front:
            print("all tiles opened and all mines marked")
            return game_status["solution found"], get_all_mines(board_state,
                                                                markings_state), front

    return game_status["solution not found"], None, front


def recalculate_front(board, board_state, front, num_of_columns, num_of_rows):
    new_front = defaultdict(dict)
    for a, b in front.items():
        for c, d in b.items():
            cardinality, tile, t_b_d = recalculate_tile_info(c[0], c[1],
                                                             num_of_rows,
                                                             num_of_columns,
                                                             board_state, board)

            new_front[cardinality][tile] = t_b_d
    return new_front


def update_front(board, board_state, front_opened_control, num_of_columns,
                 num_of_rows, mines_present=False):
    # todo add option to iterate over prev closed so that you do not need to
    #   check alr. opened

    from collections import defaultdict
    front = defaultdict(dict)

    for i, row in enumerate(board_state):
        for tile, val in enumerate(row):
            if val == markings_state["open"]:

                if board[i][tile] == markings["mine"]:
                    if not mines_present:
                        continue

                targetable = get_tile_neighbours(i, tile, num_of_rows,
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
                            int(board[i][tile]) - cardinality_decrementer][
                            (i, tile)] = targetable_filtered
                    except Exception as e:
                        print("type", board[i][tile])
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
