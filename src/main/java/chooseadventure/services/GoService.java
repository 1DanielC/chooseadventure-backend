package chooseadventure.services;

import chooseadventure.data.entities.Room;
import chooseadventure.data.models.command.Direction;
import chooseadventure.data.models.session.Session;
import chooseadventure.data.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GoService implements ActionService {

    private RoomRepository roomRepository;

    @Autowired
    public GoService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Session execute(Session session, String subject) {
        if(!Direction.isDirection(subject)){
            session.setResult(String.format("You can't really go... %s", subject));
            return session;
        }

        Room room = session.getRoom();
        Direction direction = Direction.valueOf(subject);
        Optional<Room> nextRoom;
        switch (direction) {
            case FORWARD: {
                nextRoom = roomRepository.getRoomByRowAndCol(room.getRow(), room.getCol() + 1);
                break;
            } case BACKWARD: {
                nextRoom = roomRepository.getRoomByRowAndCol(room.getRow(), room.getCol() - 1);
                break;
            } case LEFT: {
                nextRoom = roomRepository.getRoomByRowAndCol(room.getRow() - 1, room.getCol() );
                break;
            } case RIGHT: {
                nextRoom = roomRepository.getRoomByRowAndCol(room.getRow() + 1, room.getCol() );
                break;
            } default: {
                nextRoom = Optional.empty();
            }
        }

        nextRoom.ifPresentOrElse(newRoom -> {
                    session.setRoom(newRoom);
                    session.setResult("You go that way");
                    session.setDialog(String.format("You are in room %s, %s", newRoom.getRow(), newRoom.getCol()));
                }, () -> session.setResult("You cannot go that way")
        );

        return session;
    }
}
