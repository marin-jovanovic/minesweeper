from collections import defaultdict
from solvable_checker.constants import game_status

from solvable_checker.util import get_all_mines, create_front

def subset_strategy(front, board_state, markings_state, board,
                   markings,
                   num_of_columns,
                   num_of_rows, ):

    """
    1 (1, 2) [(0, 2), (0, 1), (0, 3), (1, 1)]
    1 (1, 4) [(0, 4), (0, 3)]
    2 (1, 3) [(0, 3), (0, 2), (0, 4)]

    remove last one, mark (0, 2) as mine
    1 (1, 2) [(0, 2), (0, 1), (0, 3), (1, 1)]
    1 (1, 4) [(0, 4), (0, 3)]

    -------------------------

    1 (1, 4) [(0, 4), (0, 3)]
    3 (1, 3) [(0, 3), (0, 2), (0, 4), (0, 5)]

    decrement last one cardinality, remove first one, basic strategy will solve rest
    1 (1, 4) [(0, 4), (0, 3)]
    2 (1, 3) [(0, 2), (0, 5)]

    """

    #         status, mines, front = subset_strategy(front, board_state, markings_state, board,
    print()

    # [[print( i[0], j[0],j[1]) for j in i[1].items()] for i in front.items()]
    # print()

    print("subsets")
    front, is_sth_changed = subset_cleaner(front)
    print()
    [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
     front.items()]

    if not is_sth_changed:
        return game_status["solution not found"], get_all_mines(board_state, markings_state), front, "changed"

    else:
        # todo add solution found
        return game_status["solution not found"], get_all_mines(board_state,
                                                                markings_state), front, "not changed"

    return None, None, None

    # print(80 * "-")
    # return get_all_mines(board_state, markings_state)
    # # ##########################################################################
    #

    #
    # # todo remove same rows, check if that is possible
    #
    # print("subsets")
    # front, is_sth_changed = subset_cleaner(front)
    # print()
    # [[print(i[0], j[0], j[1]) for j in i[1].items()] for i in
    #  front.items()]
    #
    # c = remove_mines_from_board_state(front, mines)
    # # if c:
    # #     is_sth_changed = True
    #
    # is_sth_changed = True if c else is_sth_changed

def subset_cleaner(front_opened_control):
    seen = set()
    to_remove_tiles = set()
    reduced_tiles = defaultdict(dict)
    for cardinality_1, tiles_1 in front_opened_control.items():

        for t_1, t_b_d_1 in tiles_1.items():
            # print("\t", t_1, "-", t_b_d_1)
            seen.add(t_1)
            for cardinality_2, tiles_2 in front_opened_control.items():

                for t_2, t_b_d_2 in tiles_2.items():
                    if t_2 in seen:
                        continue

                    # print("\t\t", t_2, "-", t_b_d_2)

                    for i in [(t_b_d_1, t_b_d_2, t_2), (t_b_d_2, t_b_d_1, t_1)]:

                        if set(i[0]).issubset(set(i[1])):
                            # print("match")
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