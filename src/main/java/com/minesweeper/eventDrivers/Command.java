package com.minesweeper.eventDrivers;

/**
 * used for firing events
 */
public enum Command {
    //    center panel
    GAME_OVER,
    GAME_WON,

    //    north panel
    NEW_GAME,

    //    when settings window is closed this command is fired
    RESTART_MAINFRAME,

    RESTART_NORTH_PANEL,

    RESTART_TIMER,
    STOP_TIMER,
    START_TIMER
}
