package chooseadventure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @Autowired
    public HealthController() {
    }

    @GetMapping("/health")
    public ResponseEntity<String> ok() {
        return ResponseEntity.ok("OK");
    }
}
