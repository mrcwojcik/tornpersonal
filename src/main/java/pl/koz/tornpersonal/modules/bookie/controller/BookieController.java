package pl.koz.tornpersonal.modules.bookie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koz.tornpersonal.modules.bookie.domain.Bet;
import pl.koz.tornpersonal.modules.bookie.service.BookieService;

import java.util.List;

@RestController
@RequestMapping("/bookie")
public class BookieController {

    private final BookieService bookieService;

    public BookieController(BookieService bookieService) {
        this.bookieService = bookieService;
    }

    @GetMapping
    public List<Bet> updateBookie(){
        return this.bookieService.updateNewBets();
    }

    @GetMapping
    public List<Bet> getOldBets(){
        return this.bookieService.getOldBets();
    }

}
