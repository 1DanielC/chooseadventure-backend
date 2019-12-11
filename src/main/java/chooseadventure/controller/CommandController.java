package chooseadventure.controller;

import chooseadventure.data.model.command.Action;
import chooseadventure.data.model.command.Command;
import chooseadventure.data.model.session.Session;
import chooseadventure.data.repository.RedisSessionRepository;
import chooseadventure.exceptions.NotFoundException;
import chooseadventure.security.SessionAuthentication;
import chooseadventure.services.CommandService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandController {

    private static final int ACTION_INDEX = 0;
    private static final int SUBJECT_INDEX = 1;

    private CommandService commandService;
    private RedisSessionRepository redisSessionRepository;

    @Autowired
    public CommandController(RedisSessionRepository redisRepo,
                             CommandService commandService) {
        this.commandService = commandService;
        redisSessionRepository = redisRepo;
    }

    @PostMapping("/command")
    public ResponseEntity<Session> runCommand(
            @ModelAttribute("command") String com) {
        SessionAuthentication sessionAuth = (SessionAuthentication) SecurityContextHolder.getContext().getAuthentication();

        String normalizedCommand = com.replaceAll("\\s+", " ").trim().toUpperCase();
        List<String> sentence = List.of(normalizedCommand.split(" ", 2));
        Session session = sessionAuth.getSession();

        if (sentence.size() < 2) {
            session.setDialog("That's not a valid command... what are you doing?");
            return ResponseEntity.ok(session.toResource());
        }
        String action = sentence.get(ACTION_INDEX);
        String subject = sentence.get(SUBJECT_INDEX);

        if(!EnumUtils.isValidEnum(Action.class, action)) {
            throw new NotFoundException("Not a valid action");
        }

        Command command = new Command(action, subject);
        Session nextSession = commandService.executeCommand(session, command);
        redisSessionRepository.updateSession(sessionAuth.getSessionToken(), nextSession);

        return ResponseEntity.ok(nextSession.toResource());
    }

    @RequestMapping(value = {"/getcommand"}, method = RequestMethod.GET)
    public ResponseEntity<String> getStuff() {
        return ResponseEntity.ok("STUFF");
    }
}
