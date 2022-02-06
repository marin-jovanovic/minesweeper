constraints_log = {}


def what_is_targetable(row, column, num_of_rows, num_of_columns):
    if (row, column) in constraints_log:
        # print(f"i have this {row}, {column} -> {constraints_log[(row, column)]}")
        return constraints_log[(row, column)]

    test_l = column == 0
    test_r = column == num_of_columns - 1
    test_u = row == 0
    test_d = row == num_of_rows - 1

    existing = []

    if not test_u:

        existing.append((row - 1, column))

        if not test_l:
            existing.append((row - 1, column - 1))

        if not test_r:
            existing.append((row - 1, column + 1))

    if not test_d:

        existing.append((row + 1, column))

        if not test_l:
            existing.append((row + 1, column - 1))

        if not test_r:
            existing.append((row + 1, column + 1))

    if not test_r:
        existing.append((row, column + 1))

    if not test_l:
        existing.append((row, column - 1))

    constraints_log[(row, column)] = existing
    return existing


def get_all_mines(board_state, markings_state):
    """
    find locations of all opened mines
    """

    mines = set()
    for i, row in enumerate(board_state):
        indices = [i for i, x in enumerate(row) if x == markings_state["mine"]]

        if indices:
            [mines.add((i, j)) for j in indices]
    return mines


def create_front(board, board_state, front_opened_control,
                 markings, markings_state, num_of_columns,
                 num_of_rows, mines_present=False):

    # todo add option to iterate over prev closed so that you do not need to
    #   check alr. opened

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
                        front_opened_control[
                            int(board[i][j]) - cardinality_decrementer][
                            (i, j)] = targetable_filtered
                    except Exception as e:
                        print("type", board[i][j])
                        raise e