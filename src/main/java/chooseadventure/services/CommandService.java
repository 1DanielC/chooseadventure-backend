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
            GoService goService,
            ExamineService examineService,
            TakeService takeService,
            DropService dropService,
            InspectService inspectService,
            TeleportService teleportService
    ) {
        actionMap = new HashMap<>();
        actionMap.put(Action.GO, goService);
        actionMap.put(Action.EXAMINE, examineService);
        actionMap.put(Action.TAKE, takeService);
        actionMap.put(Action.DROP, dropService);
        actionMap.put(Action.INSPECT, inspectService);
        actionMap.put(Action.TELEPORT, teleportService);
    }

    public CommandService() {
    }

    public Session executeCommand(Session session, Command command) {
        ActionService actionService = actionMap.get(command.getAction());

        return actionService.execute(session, command.getSubject());
    }
}
