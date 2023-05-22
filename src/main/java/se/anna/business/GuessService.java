package se.anna.business;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.anna.storage.PlayerDAO;

import java.util.List;

@Service
@SessionScope
public class GuessService {

    int secret;
    int guessCount;

    Player player;
    boolean isLoggedin;

    @Autowired
    PlayerDAO dao;

    @PostConstruct
    public void init() {
        secret = (int)(Math.random()*10 + 1);
        guessCount = 0;
    }

    public void login(String playerName) {

        if (isLoggedin) return;

        List<Player> pList = dao.findByName(playerName);
        if (pList.size()>0) {
            player = pList.get(0);
        } else {
            player = new Player(playerName);
            player = dao.save(player);
        }
        isLoggedin = true;
    }


    public String guess(int tal) {
        if (!isLoggedin) throw new IllegalStateException("Not logged in");
        guessCount++;
        if (tal < secret) {
            return "Too small number";
        }
        if (tal > secret) {
            return "To big number";
        }
        int result = guessCount;

        init();//

        registerResult(result);
        return "Correct at " + result + " guesses! New number coming up!";
    }


    public void registerResult(int nGuesses) {
        if (!isLoggedin) return;

        player.addResult(nGuesses);
        player = dao.save(player);
    }

    public List<PlayerAverage> getTopList() {
        return dao.getTopList();
    }

}
