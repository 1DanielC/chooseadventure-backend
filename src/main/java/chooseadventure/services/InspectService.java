package chooseadventure.services;

import chooseadventure.data.entity.Item;
import chooseadventure.data.model.session.Session;
import org.springframework.stereotype.Service;

@Service
public class InspectService extends InventoryService {

    @Override
    public Session doSomething(Session session, Item item) {
        session.setDialog(item.getDescription());
        return session;
    }
}
