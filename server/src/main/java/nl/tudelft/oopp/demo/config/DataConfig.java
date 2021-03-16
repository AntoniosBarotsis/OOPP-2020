package nl.tudelft.oopp.demo.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Quote;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.entities.users.User;
import nl.tudelft.oopp.demo.repositories.PollRepository;
import nl.tudelft.oopp.demo.repositories.QuestionRepository;
import nl.tudelft.oopp.demo.repositories.QuoteRepository;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {

    @Bean
    CommandLineRunner commandLineRunner(QuoteRepository quoteRepository,
                                        UserRepository userRepository,
                                        RoomRepository roomRepository,
                                        QuestionRepository questionRepository,
                                        PollRepository pollRepository) {
        return args -> {
            Quote quote1 = new Quote(
                    1,
                    "A clever person solves a problem. A wise person avoids it.",
                    "Albert Einstein"
            );
            Quote quote2 = new Quote(
                    2,
                    "The computer was born to solve problems that did not exist before.",
                    "Bill Gates"
            );
            Quote quote3 = new Quote(
                    3,
                    "Tell me and I forget.  Teach me and I remember.  Involve me and I learn.",
                    "Benjamin Franklin"
            );
            quoteRepository.saveAll(List.of(quote1, quote2, quote3));

            ElevatedUser u1 = new ElevatedUser("Admin", "ip1", true);
            User u2 = new ElevatedUser("Mod", "ip2");
            User u22 = new ElevatedUser("Mod2", "ip22", false);
            User u3 = new Student("Student", "ip3");
            userRepository.saveAll(List.of(u1, u2, u3, u22));

            Poll p1 = new Poll("Poll title", "Poll text", new ArrayList<>(),
                List.of("Correct answer"));
            pollRepository.saveAll(List.of(p1));

            final Question q1 = new Question("Question text 1", u1);
            final Question q2 = new Question("Question text 2", u2);
            final Question q3 = new Question("Question text 3", u2);
            final Question q4 = new Question("Question text 4", u3);
            q1.setScore(1);
            q2.setScore(2);
            q3.setScore(5);
            q4.setScore(4);
            q3.setAnswer("Answer");
            questionRepository.saveAll(List.of(q1, q2, q3, q4));

            Room r1 = new Room("Room Title", false, u1);

            r1.setQuestions(Stream.of(q1, q2, q3, q4)
                .collect(Collectors.toSet())
            );
            r1.setPolls(Stream.of(p1)
                .collect(Collectors.toSet())
            );

            roomRepository.save(r1);
        };
    }

}
