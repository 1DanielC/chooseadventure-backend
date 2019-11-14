package chooseadventure.data.entity;

import chooseadventure.data.model.command.Direction;

import java.util.ArrayList;
import java.util.List;

import static chooseadventure.data.model.command.Direction.BACKWARD;
import static chooseadventure.data.model.command.Direction.FORWARD;
import static chooseadventure.data.model.command.Direction.LEFT;
import static chooseadventure.data.model.command.Direction.RIGHT;

public enum CardinalDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    static List<CardinalDirection> allCardinalDirections = new ArrayList<>();
    static {
        allCardinalDirections.add(NORTH);
        allCardinalDirections.add(EAST);
        allCardinalDirections.add(SOUTH);
        allCardinalDirections.add(WEST);
    }

    static List<Direction> allDirections = new ArrayList<>();
    static {
        allDirections.add(FORWARD);
        allDirections.add(RIGHT);
        allDirections.add(BACKWARD);
        allDirections.add(LEFT);
    }

    public static boolean isCardinalDirection(String string) {
        try {
            Direction.valueOf(string);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static CardinalDirection discernDirection(CardinalDirection playerfacing, Direction direction) {
        //Consider a clock turning clockwise. so long as the objects are sorted and
        // are turning in one direction, any direction can be derived with the equation:
        // x = (c + t) % n, where c is the current position, t is the amount of "ticks" on the clock,
        // and n is the amount of "ticks" in the clock (n = 60 if you want to represent minutes,
        // 12 if you want to represent hours, 4 if you want to represent cardinal directions).
        int index = (allCardinalDirections.indexOf(playerfacing) + allDirections.indexOf(direction)) % 4;

        return allCardinalDirections.get(index);
    }

    public static Direction discernDirection(CardinalDirection playerfacing, CardinalDirection direction) {
        int playerIndex = allCardinalDirections.indexOf(playerfacing);
        int doorIndex = allCardinalDirections.indexOf(direction);

        // x = (c + t) % n
        // ALGEBRA ? It makes sense in my head, but have no idea how I got to this conclusion.
        // t = n - (x % n) + c
        int index = 4 - (playerIndex % 4) + doorIndex;

        return allDirections.get(index % 4);
    }
}
