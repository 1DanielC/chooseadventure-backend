package chooseadventure.data.entities;

import chooseadventure.data.models.command.Direction;

public enum CardinalDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public static boolean isCardinalDirection(String string) {
        try {
            Direction.valueOf(string);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
