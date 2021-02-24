package nl.tudelft.oopp.demo.services;

import nl.tudelft.oopp.demo.entities.Quote;
import nl.tudelft.oopp.demo.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuoteService {
    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote getRandomQuote() {

        List<Quote> quotes = quoteRepository.findAll();

        return quotes.get(new Random().nextInt(quotes.size()));
    }
}
