import enum

game_status = {
    "solution found": 0,
    "solution not found": 1,
}
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


class Markings(enum.Enum):
    MINE = "x"
    USER = "u"
    EMPTY = 0

class MarkingsState(enum.Enum):
    OPEN = "o"
    CLOSED = " "
    MINE = "m"
    # todo
    # UNKNOWN = "?"
