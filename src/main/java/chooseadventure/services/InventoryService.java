package chooseadventure.services;

import chooseadventure.data.entity.Item;
import chooseadventure.data.model.session.Player;
import chooseadventure.data.model.session.Session;

public abstract class InventoryService implements ActionService {

    @Override
    public Session execute(Session session, String subject) {
        Player player = session.getPlayer();
        Item item = player.findItem(subject).orElse(null);
        if (item != null) {
            Session newSession = doSomething(session, item);
            session.setScenario(session.roomDescriptionWithItems());
            return newSession;
        } else {
            session.setDialog("That item does not exist in your inventory");
        }

        return session;
    }

    abstract Session doSomething(Session session, Item item);
}
