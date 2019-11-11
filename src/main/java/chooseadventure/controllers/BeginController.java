package chooseadventure.controllers;

import chooseadventure.data.models.session.Session;
import chooseadventure.data.repositories.RedisSessionRepository;
import chooseadventure.security.SessionAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeginController {

    private RedisSessionRepository redisSessionRepository;

    @Autowired
    public BeginController(RedisSessionRepository redisSessionRepository) {
        this.redisSessionRepository = redisSessionRepository;
    }

    @GetMapping("/begin")
    public ResponseEntity<Session> begin() {
        SessionAuthentication sessionAuth = (SessionAuthentication) SecurityContextHolder.getContext().getAuthentication();

        Session session = new Session();
        session.setDialog("You awake in a room full of ball pit balls. there is a door in the front of the room");

        redisSessionRepository.updateSession(sessionAuth.getSessionToken(), session);
        return ResponseEntity.ok(session);
    }
}
