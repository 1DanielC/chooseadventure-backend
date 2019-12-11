package chooseadventure.services;

import chooseadventure.data.entity.Room;
import chooseadventure.data.model.session.Session;
import chooseadventure.data.repository.RoomRepository;
import chooseadventure.exceptions.BadRequestException;
import chooseadventure.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeleportService implements ActionService {

    private final RoomRepository roomRepository;

    @Autowired
    public TeleportService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Session execute(Session session, String subject) {
        String[] rowCol = subject.split(" ");
        if(rowCol.length != 2) {
            throw new BadRequestException("Your teleportation powers are weak");
        }

        int row, col;
        try {
            row = Integer.parseInt(rowCol[0]);
            col = Integer.parseInt(rowCol[1]);
        } catch(NumberFormatException e) {
            throw new BadRequestException("Your teleportation language is foreign to us");
        }

        Room room = roomRepository.getRoomByRowAndCol(row, col).orElse(null);

        if(room == null) {
            throw new NotFoundException("You can't teleport out of the map");
        }

        session.setRoom(room);
        session.setDialog("VYEWM WHOOSH!");
        session.setScenario(session.roomDescriptionWithItems());

        return session;
    }
}
