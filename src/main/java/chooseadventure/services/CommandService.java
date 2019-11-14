package chooseadventure.services;


import chooseadventure.data.model.command.Action;
import chooseadventure.data.model.command.Command;
import chooseadventure.data.model.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CommandService {

    private HashMap<Action, ActionService> actionMap;

    @Autowired
    public CommandService(
            GoService goService
    ) {
        actionMap = new HashMap<>();
        actionMap.put(Action.GO, goService);
    }

    public CommandService() {
    }

    public Session executeCommand(Session session, Command command) {
        ActionService actionService = actionMap.get(command.getAction());

        return actionService.execute(session, command.getSubject());
    }
}
