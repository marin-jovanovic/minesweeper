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
