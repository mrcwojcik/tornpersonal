package pl.koz.tornpersonal.modules.bookie.service;

import pl.koz.tornpersonal.modules.bookie.domain.Bet;
import java.util.List;

public interface BookieService {

    List<Bet> getOldBets();
    List<Bet> updateNewBets();

}
