from collections import defaultdict

from solvable_checker.constants import game_status

from solvable_checker.util import get_all_mines, create_front


def basic_strategy(front_opened, board_state, markings_state, board,
                   markings,
                   num_of_columns,
                   num_of_rows, ):
    """        # 	 1 [(3, 0)] -> remove, mark as mine
            # 	 0 [(3, 0)] -> remove, open
            # 	 1 [(2, 0), (2, 2)] -> nothing
    """
    is_sth_changed = True
    while is_sth_changed:

        # 	 1 [(3, 0)] -> remove, add to new_mines
        # 	 3 [(2, 0), (2, 2), (4, 0), (3, 0)]
        front_opened, new_mines = direct_extraction(front_opened, board_state,
                                                    markings_state)

        is_sth_changed = True if new_mines else False

        """
        handle 
        # 	 0 (a, b) [(2, 0), (2, 2), (4, 0), (3, 0)]
               # 	 0 [(3, 0)] -> remove, open
         """
        for cardinality, tiles in front_opened.items():

            for t, t_b_d in tiles.items():

                if cardinality == 0:
                    for r, c in t_b_d:
                        board_state[r][c] = markings_state["open"]
                    is_sth_changed = True

        front_opened = defaultdict(dict)
        create_front(board, board_state,
                     front_opened,
                     markings, markings_state,
                     num_of_columns,
                     num_of_rows,
                     mines_present=True)

        if not front_opened:
            print("all tiles opened and all mines marked")
            # print_boards(board, board_state)
            return game_status["solution found"], get_all_mines(board_state,
                                                                markings_state)

    return game_status["solution not found"], None
    # , front_opened,


def direct_extraction(front_opened_control, board_state, markings_state):
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

    for r, c in mines:
        board_state[r][c] = markings_state["mine"]

    return new_front_opened, mines