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

    int hemligt;
    int guessCount;

    Player player;
    boolean isLoggedin;

    @Autowired
    PlayerDAO dao;

    @PostConstruct
    public void init() {
        hemligt = (int)(Math.random()*10 + 1);
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
        if (tal < hemligt) {
            return "Too small number";
        }
        if (tal > hemligt) {
            return "To big number";
        }
        int resultat = guessCount;

        init();//

        registerResult(resultat);
        return "Rätt på " + resultat + " gissningar! Nytt tal på gång!";
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