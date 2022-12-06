package pl.koz.tornpersonal.modules.bookie.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookieRepository extends CrudRepository<Bet, Long> {

    @Query("SELECT b FROM Bet b")
    public List<Bet> findAll();

    @Query("SELECT b.timestamp FROM Bet b ORDER BY b.timestamp")
    public Long getLatestBet();

    @Query("SELECT b.timestamp FROM Bet b ORDER BY b.timestamp DESC")
    public Long getOldestBet();
}
