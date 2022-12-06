package pl.koz.tornpersonal.modules.bookie.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Result result;
    private BigDecimal bet;
    private BigDecimal odds;
    private BigDecimal win;
    private Integer day;
    private Integer month;
    private Integer year;
    private Long timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWin(BigDecimal win) {
        this.win = win;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public BigDecimal getBet() {
        return bet;
    }

    public void setBet(BigDecimal bet) {
        this.bet = bet;
    }

    public BigDecimal getOdds() {
        return odds;
    }

    public void setOdds(BigDecimal odds) {
        this.odds = odds;
    }

    public BigDecimal getWin() {
        return win;
    }

    public void setWin() {
        if (this.result == Result.LOSE){
            this.win = BigDecimal.ZERO.subtract(this.bet);
        } else {
            this.win = this.bet.multiply(this.odds).subtract(this.bet);
        }
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
