package se.anna.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.anna.business.Player;
import se.anna.business.PlayerAverage;

import java.util.List;
import java.util.Optional;

@Repository

public interface PlayerDAO extends JpaRepository<Player, Integer> {
    List<Player> findByName(String name);

    @Query("SELECT new se.anna.business.PlayerAverage(p.name, avg(result.score) as average ) " +
            " from se.anna.business.Player as p join p.results as result group by p.name order by average asc")
    List<PlayerAverage> getTopList();


}