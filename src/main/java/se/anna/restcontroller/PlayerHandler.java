package se.anna.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.anna.business.Player;
import se.anna.business.PlayerAverage;
import se.anna.storage.PlayerDAO;

import java.util.List;

@RestController
public class PlayerHandler {


    @Autowired
    PlayerDAO playerDAO;

    @GetMapping("/rest/getPlayerById/{id}")
    public Player getPlayerById(@PathVariable(value = "id") int id) {
        return playerDAO.findById(id).get();
    }

    @GetMapping("/rest/getAllPlayers")
    public List<Player> getAllPlayers() {
        return playerDAO.findAll();
    }

    @GetMapping("/rest/getTopPlayer")
    public PlayerAverage getTopPlayer() {
        return playerDAO.getTopList().get(0);

    }
}
