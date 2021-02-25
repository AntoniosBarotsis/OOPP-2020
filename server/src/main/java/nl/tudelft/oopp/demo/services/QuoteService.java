package nl.tudelft.oopp.demo.services;

import java.util.List;
import java.util.Random;

import nl.tudelft.oopp.demo.entities.Quote;
import nl.tudelft.oopp.demo.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Quote service.
 */
@Service
public class QuoteService {
    private final QuoteRepository quoteRepository;

    /**
     * Instantiates a new Quote service.
     *
     * @param quoteRepository the quote repository
     */
    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    /**
     * Gets random quote from the database.
     *
     * @return the random quote
     */
    public Quote getRandomQuote() {

        List<Quote> quotes = quoteRepository.findAll();

        return quotes.get(new Random().nextInt(quotes.size()));
    }
}
