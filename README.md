# Minesweeper

## General information
Minesweeper is a single player game.

The project was migrated from java to maven and some I/O bugs have not yet been fixed.

Please note that this project is still in progress.
If the latest version isn't stable enough, you can use the previous stable version 
which can be found under the releases section (currently v1.0).


##Controls
You can open a tile with left click.

Right click is used to cycle through the options for any specific tile (mine, unknown original).

At the moment, the right click functions are a work in progress.


## Game settings
Change number of tiles and mines.


## Personalization
In the settings menu you can change the grid size and the number of mines that occur. 

You can also use any custom image or sound effect for each individual part of the program.

Currently supported formats are PNG and JPG for images and WAVE for sound effects.

Settings are saved automatically upon exiting the program.


## Tile customization
Picture path:

    main/resources/original_images

After you've copied all of your images, run this script:

    main/utils/imagesDrivers/ResizeImages


## TODO, FIXME
    Finalize settings naming
    UML diagrams, documentation
    installer for constants and images
    fixing game logic
        players first opened tile must not be a mine
        after clicking on a tile that is not a bomb and adjacent tile is a flag, empty tiles don't open in a predictable way


Source for stock files:
    
    https://craftpix.net/freebies/free-jungle-cartoon-2d-game-ui/
    https://www.freepik.com/free-icon/bomb_14141522.htm#page=1&query=bomb&position=7
    https://gooseninja.itch.io/pixel-minesweeper
