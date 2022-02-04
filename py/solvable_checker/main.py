markings = {
    "mine": "x",
    "user": "u",
    "empty": 0
}


def generate_boardoard(num_of_rows, num_of_columns, num_of_mines, user_row, user_column):



    # todo check restrictions
    board = [[0 for _ in range(num_of_columns)] for _ in range(num_of_rows)]

    [print(i) for i in board]
    print()

    from random import randint

    board[user_row][user_column] = markings["user"]

    occupied_tiles = {(user_row, user_column)}

    c = 0
    while c != num_of_mines:

        row = randint(0, num_of_rows - 1)
        column = randint(0, num_of_columns - 1)

        if (row, column) not in occupied_tiles:
            occupied_tiles.add((row, column))
            c += 1

            board[row][column] = markings["mine"]

    [print(i) for i in board]
    print()

    for curr_row in range(num_of_rows):
        for curr_column in range(num_of_columns):
            if board[curr_row][curr_column] == markings["mine"]:

                # set_if_not_user_or_mine(board, curr_row, curr_column - 1)

                test_l = curr_column == 0
                test_r = curr_column ==  num_of_columns - 1
                test_u = curr_row == 0
                test_d = curr_row == num_of_rows - 1
                # print(curr_row, curr_column,"l" if test_l else "", "r" if test_r else "",
                #       "u" if test_u else "", "d" if test_d else "")

                if not test_u and not test_l:
                    set_if_not_user_or_mine(board, curr_row - 1, curr_column - 1)

                if not test_u:
                    set_if_not_user_or_mine(board, curr_row - 1, curr_column)

                if not test_u and not test_r:
                    set_if_not_user_or_mine(board, curr_row - 1, curr_column + 1)

                if not test_r:
                    set_if_not_user_or_mine(board, curr_row, curr_column + 1)

                if not test_r and not test_d:
                    set_if_not_user_or_mine(board, curr_row + 1, curr_column + 1)

                if not test_d:
                    set_if_not_user_or_mine(board, curr_row + 1, curr_column)

                if not test_d and not test_l:
                    set_if_not_user_or_mine(board, curr_row + 1, curr_column - 1)

                if not test_l:
                    set_if_not_user_or_mine(board, curr_row, curr_column - 1)

                [print(i) for i in board]

                #
                #
                # if curr_column != 0:
                #     print("max left", curr_row, curr_column)
                #     # print("l" if test_l else "", "r" if test_r else "",
                #     #       "u" if test_u else "", "d" if test_d else "")
                #
                #     set_if_not_user_or_mine(board, curr_row, curr_column - 1)
                #
                #     #     check top left
                #     #     check bottom left
                #
                # elif curr_column == num_of_columns - 1:
                #     print("max right")
                #     # print("l" if test_l else "", "r" if test_r else "", "u" if test_u else "", "d" if test_d else "")
                #
                # if curr_row == 0:
                #     print("top row")
                #     # print("l" if test_l else "", "r" if test_r else "",
                #     #       "u" if test_u else "", "d" if test_d else "")
                # elif curr_row == num_of_rows - 1:
                #     print("bottom row")
                #     # print("l" if test_l else "", "r" if test_r else "", "u" if test_u else "", "d" if test_d else "")

                print()
    # print(f"{occupied_tiles=}")
    # print(len(occupied_tiles))
    #
    #
    # boardoard = []
    # for y in range(num_of_rows):
    #     for x in range(num_of_columns):
    #         if (x, y) == (user_row, user_column):
    #
    #         if (x, y) in occupied_tiles:


    #
    #
    #         print((row, column))
    # [print(j) for j in board]
    #         print()
    #
    # print(occupied_tiles)

    return board


def set_if_not_user_or_mine(board, r, c):
    if board[r][c] not in [markings["user"], markings["mine"]]:
        board[r][c] += 1


def main():
    board = generate_boardoard(5, 3, 5, 2, 2)
    [print(i) for i in board]

if __name__ == '__main__':
    main()