package chooseadventure.services;

import chooseadventure.data.entity.Item;
import chooseadventure.data.model.session.Session;
import org.springframework.stereotype.Service;

@Service
public class DropService extends InventoryService {

    public DropService() {
    }

    @Override
    public Session doSomething(Session session, Item item) {
        session.getPlayer().removeItem(item);
        session.setDialog("You drop your " + item.getName().toUpperCase() + ". In an instant, before the item can fall to the floor, " +
                "the item dissolves to dust. It doesn't even hit the floor before dissipating.");
        session.setScenario(session.roomDescriptionWithItems());

        return session;
    }
}
