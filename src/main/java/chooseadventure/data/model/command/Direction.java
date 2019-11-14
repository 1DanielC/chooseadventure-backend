package chooseadventure.data.model.command;

public enum Direction {
    FORWARD,
    BACKWARD,
    LEFT,
    RIGHT;

    public static boolean isDirection(String string) {
        try {
            Direction.valueOf(string);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
