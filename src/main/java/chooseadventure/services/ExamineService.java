package chooseadventure.services;

import chooseadventure.data.entity.Item;
import chooseadventure.data.entity.Room;
import chooseadventure.data.model.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamineService implements ActionService {

    @Autowired
    public ExamineService() {
    }

    public Session execute(Session session, String subject) {
        Room room = session.getRoom();

        if (subject.toLowerCase().equalsIgnoreCase("room")) {
            session.setScenario(session.roomDescriptionWithItems());
            session.setDialog("You examine the room");
        } else {
            Optional<Item> item = session.findIfRoomHasItem(subject);
            item.ifPresentOrElse(i -> session.setDialog(i.getDescription()),
                    () -> session.setDialog(subject.toUpperCase() + " is not something you can examine..." +
                            " or is not something that exists")
            );
        }

        return session;
    }

}
