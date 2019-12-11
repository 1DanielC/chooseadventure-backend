package chooseadventure.services;

import chooseadventure.data.entity.Item;
import chooseadventure.data.model.session.Player;
import chooseadventure.data.model.session.Session;
import org.springframework.stereotype.Service;

@Service
public class TakeService implements ActionService {

    @Override
    public Session execute(Session session, String subject) {
        Player player = session.getPlayer();
        Item item = session.getRoom().findItem(subject).orElse(null);

        if (item != null
                && !player.hasItem(item)
                && (!item.isHidden() || player.discoveredItem(item))) {
            player.addItem(item);
            session.setDialog("You take the " + item.getName());
            session.setScenario(session.roomDescriptionWithItems());
        } else {
            session.setDialog("There is nothing you find in the vicinity of your existence " +
                    "that would be described as a " + subject + ".");
        }

        return session;
    }
}
