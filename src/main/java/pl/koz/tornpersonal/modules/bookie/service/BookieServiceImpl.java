package pl.koz.tornpersonal.modules.bookie.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import pl.koz.tornpersonal.modules.bookie.domain.Bet;
import pl.koz.tornpersonal.modules.bookie.domain.BookieRepository;
import pl.koz.tornpersonal.modules.bookie.domain.Result;
import pl.koz.tornpersonal.utils.tornApi.RequestToTornApi;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class BookieServiceImpl implements BookieService{

    private final RequestToTornApi requestToTornApi;
    private final BookieRepository bookieRepository;

    public BookieServiceImpl(RequestToTornApi requestToTornApi, BookieRepository bookieRepository) {
        this.requestToTornApi = requestToTornApi;
        this.bookieRepository = bookieRepository;
    }

    @Override
    public List<Bet> getOldBets() {
        Long timestamp = this.bookieRepository.getOldestBet();
        JSONObject jsonObject = this.requestToTornApi.makeRequestToApi("user", "log&log=8462,8461&to=" + timestamp);
        List<Bet> bets = this.castJsonToEntity(jsonObject.getJSONObject("log"));
        return this.saveBetsToDb(bets);
    }

    private List<Bet> saveBetsToDb(List<Bet> bets) {
        for(Bet bet : bets){
            this.bookieRepository.save(bet);
        }

        List<Bet> betsFromDb = this.bookieRepository.findAll();
        return betsFromDb;

    }

    private List<Bet> castJsonToEntity(JSONObject jsonObject){
        Iterator<String> keyList = jsonObject.keys();
        List<Bet> bets = new ArrayList<>();
        while (keyList.hasNext()){
            String key = keyList.next();
            JSONObject betObject = (JSONObject) jsonObject.get(key);
            Bet bet = castObjectToBet(betObject);
            bets.add(bet);
        }

        return bets;
    }

    private Bet castObjectToBet(JSONObject betObject) {
        Bet bet = new Bet();
        bet.setOdds(betObject.getJSONObject("data").getBigDecimal("odds"));
        bet.setResult(checkResult(betObject.getString("title")));
        bet.setBet(betObject.getJSONObject("data").getBigDecimal("bet"));
        bet.setTimestamp(betObject.getLong("timestamp"));
        setDatesColumns(bet.getTimestamp(), bet);
        bet.setWin();
        return bet;
    }

    private void setDatesColumns(Long timeStampString, Bet bet){
        LocalDate localDate1 = LocalDate.ofInstant(Instant.ofEpochSecond(timeStampString), ZoneId.systemDefault());
        bet.setYear(localDate1.getYear());
        bet.setMonth(localDate1.getMonthValue());
        bet.setDay(localDate1.getDayOfMonth());
    }

    private Result checkResult(String title) {
        if (title.contains("win")){
            return Result.WIN;
        } else {
            return Result.LOSE;
        }
    }

    @Override
    public List<Bet> updateNewBets() {
        Long timestamp = this.bookieRepository.getLatestBet();
        JSONObject jsonObject = this.requestToTornApi.makeRequestToApi("user", "log&log=8462,8461&from=" + timestamp);
        List<Bet> bets = this.castJsonToEntity(jsonObject.getJSONObject("log"));
        return this.saveBetsToDb(bets);
    }
}
