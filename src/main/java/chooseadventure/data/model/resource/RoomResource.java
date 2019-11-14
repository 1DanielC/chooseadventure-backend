package chooseadventure.data.model.resource;

import chooseadventure.data.entity.Room;

import java.util.Set;
import java.util.stream.Collectors;

public class RoomResource {

    Set<DoorResource> doors;

    public RoomResource(Room room) {
        doors = room.getDoors().stream()
                .map(DoorResource::new)
                .collect(Collectors.toSet());
    }
}
