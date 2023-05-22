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

    @PostMapping("/rest/createPlayer")
    public Player createPlayer(@RequestBody Player player) {
        return playerDAO.save(player);
    }


    @PutMapping("/rest/updatePlayer/{id}/{name}")
    public ResponseEntity<Player> updatePlayer(@PathVariable(value = "id") int id,
                                               @PathVariable(value = "name") String name) {
        Player player = playerDAO.findById(id).get();
        player.setName(name);
        Player updatedPlayer = playerDAO.save(player);
        return ResponseEntity.ok(updatedPlayer);
    }


    @DeleteMapping("/rest/deletePlayer/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable(value = "id") int id){
        Player player = playerDAO.findById(id).get();
        playerDAO.delete(player);
        Player updatedPlayer = playerDAO.save(player);
        return ResponseEntity.ok(updatedPlayer);


    }
}
