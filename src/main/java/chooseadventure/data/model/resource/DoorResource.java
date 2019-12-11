package chooseadventure.data.model.resource;

import chooseadventure.data.model.command.CardinalDirection;
import chooseadventure.data.entity.Door;
import chooseadventure.data.model.command.Direction;

public class DoorResource {

    Direction direction;

    public DoorResource(Door door, CardinalDirection directionFacing){
        direction = CardinalDirection.discernDirection(directionFacing, door.getDirection());
    }

    public DoorResource(Door door){
        int i = 0;
    }
}
