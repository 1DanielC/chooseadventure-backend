package chooseadventure.controller;

import chooseadventure.data.entity.Room;
import chooseadventure.data.model.session.Player;
import chooseadventure.data.model.session.Session;
import chooseadventure.data.repository.RedisSessionRepository;
import chooseadventure.data.repository.RoomRepository;
import chooseadventure.exceptions.ServerErrorException;
import chooseadventure.security.SessionAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.Set;

@RestController
public class BeginController {

    private RedisSessionRepository redisSessionRepository;
    private RoomRepository roomRepository;

    @Autowired
    public BeginController(RedisSessionRepository redisSessionRepository, RoomRepository roomRepository) {
        this.redisSessionRepository = redisSessionRepository;
        this.roomRepository = roomRepository;
    }

    @GetMapping("/begin")
    public ResponseEntity<Session> begin() {
        SessionAuthentication sessionAuth = (SessionAuthentication) SecurityContextHolder.getContext().getAuthentication();

        Session session = new Session();
        Room firstRoom = null;

        Player player = new Player();
        session.setPlayer(player);

        if(player.getName().equals("Queen of Ico")) {
            firstRoom = roomRepository.getRoomByRowAndCol(0, 0).orElse(null);
        } else {
            firstRoom = roomRepository.getRoomByRowAndCol(0, 0).orElse(null);
        }

        if(firstRoom == null) {
            throw new ServerErrorException("The server tried to put you in a room that doesn't exist... " +
                    "my gosh, what foolish person made this game?");
        }

        session.setScenario("You are the " + name + ". You awake in the room you have laid in for thousands of years. ");
        session.setDialog("What do you do? Feel free to type 'help' to get started");
        session.setRoom(firstRoom);


        redisSessionRepository.updateSession(sessionAuth.getSessionToken(), session);
        return ResponseEntity.ok(session.toResource());
    }
}
