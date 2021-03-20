package nl.tudelft.oopp.demo.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class QuestionAuthorTest {

    private QuestionAuthor author = new QuestionAuthor(42, "universe");

    @Test
    void getId() {
        assertEquals(42, author.getId());
    }

    @Test
    void setId() {
        author.setId(22);
        assertEquals(22, author.getId());
    }

    @Test
    void getUsername() {
        assertEquals("universe", author.getUsername());
    }

    @Test
    void setUsername() {
        author.setUsername("qwerty");
        assertEquals("qwerty", author.getUsername());
    }
}