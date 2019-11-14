package chooseadventure.controller;

import chooseadventure.data.entity.Room;
import chooseadventure.data.model.session.Player;
import chooseadventure.data.model.session.Session;
import chooseadventure.data.repository.RedisSessionRepository;
import chooseadventure.data.repository.RoomRepository;
import chooseadventure.security.SessionAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        session.setScenario("You awake in a room full of ball pit balls.");
        session.setDialog("You stand up after awaking");

        Room firstRoom = roomRepository.getRoomByRowAndCol(0, 0).orElse(null);
        session.setRoom(firstRoom);

        Player player = new Player();
        session.setPlayer(player);

        redisSessionRepository.updateSession(sessionAuth.getSessionToken(), session);
        return ResponseEntity.ok(session.toResource());
    }
}
