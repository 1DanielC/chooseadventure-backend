package chooseadventure.services;

import chooseadventure.data.entity.CardinalDirection;
import chooseadventure.data.entity.Room;
import chooseadventure.data.model.command.Direction;
import chooseadventure.data.model.session.Session;
import chooseadventure.data.repository.RoomRepository;
import chooseadventure.data.repository.DoorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GoService implements ActionService {

    private RoomRepository roomRepository;
    private DoorRepository doorRepository;


    @Autowired
    public GoService(RoomRepository roomRepository, DoorRepository doorRepository) {
        this.roomRepository = roomRepository;
        this.doorRepository = doorRepository;
    }


    public Session execute(Session session, String subject) {
        if (!Direction.isDirection(subject)) {
            session.setDialog(String.format("You can't really go... %s", subject));
            return session;
        }

        Room room = session.getRoom();

        Direction direction = Direction.valueOf(subject);
        CardinalDirection playerDirection = session.getPlayer().getDirectionFacing();
        CardinalDirection cardinalDirection = CardinalDirection.discernDirection(playerDirection, direction);
        session.getPlayer().setDirectionFacing(cardinalDirection);

        if (!room.hasDoor(cardinalDirection)) {
            session.setDialog("You cannot go that way");
            return session;
        }

        if(room.getDoor(cardinalDirection).isLocked()){
            session.setDialog("You attempt to open the door, but it is locked");
            return session;
        }

        Optional<Room> maybeNextRoom;
        switch (cardinalDirection) {
            case NORTH: {
                maybeNextRoom = roomRepository.getRoomByRowAndCol(room.getRow() + 1, room.getCol());
                break;
            }
            case SOUTH: {
                maybeNextRoom = roomRepository.getRoomByRowAndCol(room.getRow() - 1, room.getCol());
                break;
            }
            case EAST: {
                maybeNextRoom = roomRepository.getRoomByRowAndCol(room.getRow(), room.getCol() + 1);
                break;
            }
            case WEST: {
                maybeNextRoom = roomRepository.getRoomByRowAndCol(room.getRow(), room.getCol() - 1);
                break;
            }
            default: {
                maybeNextRoom = Optional.empty();
            }
        }

        if (maybeNextRoom.isPresent()) {
            Room nextRoom = maybeNextRoom.get();
            session.getPlayer().setDirectionFacing(cardinalDirection);
            session.setRoom(nextRoom);
            session.setDialog("You go that way");
            session.setScenario(String.format("You are in room %s, %s", nextRoom.getRow(), nextRoom.getCol()));
        } else {
            session.setDialog("You cannot go that way");
        }

        return session;
    }
}
