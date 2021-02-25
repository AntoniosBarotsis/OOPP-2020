package nl.tudelft.oopp.demo.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import nl.tudelft.oopp.demo.entities.Quote;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.questions.McQuestion;
import nl.tudelft.oopp.demo.entities.questions.Question;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.entities.users.User;
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
                                        QuestionRepository questionRepository) {
        return args -> {
            Quote q1 = new Quote(
                    1,
                    "A clever person solves a problem. A wise person avoids it.",
                    "Albert Einstein"
            );

            Quote q2 = new Quote(
                    2,
                    "The computer was born to solve problems that did not exist before.",
                    "Bill Gates"
            );

            Quote q3 = new Quote(
                    3,
                    "Tell me and I forget.  Teach me and I remember.  Involve me and I learn.",
                    "Benjamin Franklin"
            );

            quoteRepository.saveAll(List.of(q1, q2, q3));

            ElevatedUser u1 = new ElevatedUser("Admin", "ip", true);
            User u2 = new ElevatedUser("Mod", "ip");
            User u22 = new ElevatedUser("Mod2", "ip", false);
            User u3 = new Student("Student", "ip");

            userRepository.saveAll(List.of(u1, u2, u3, u22));

            roomRepository.save(new Room("Room Title", false, u1));

            Question question1 = new McQuestion("McQuestion", "text", u3,
                new Date(), new ArrayList<>(), List.of("Correct answer"));

            questionRepository.saveAll(List.of(question1));
        };
    }

}
