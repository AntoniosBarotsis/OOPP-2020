package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.Quote;
import nl.tudelft.oopp.demo.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuoteController {
    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    /**
     * GET Endpoint to retrieve a random quote.
     *
     * @return randomly selected {@link Quote}.
     */
    @GetMapping("quote")
    @ResponseBody
    public Quote getRandomQuote() {
        return quoteService.getRandomQuote();
    }
}
