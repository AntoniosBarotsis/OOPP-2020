package nl.tudelft.oopp.demo.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
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
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class DataConfig {

    /**
     * Api docket for swagger.
     *
     * @return the docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
    }

    /**
     * Command line runner command line runner.
     *
     * @param quoteRepository    the quote repository
     * @param userRepository     the user repository
     * @param roomRepository     the room repository
     * @param questionRepository the question repository
     * @param pollRepository     the poll repository
     * @return the command line runner
     */
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

            String ip = "";
            try {
                // Get the public IP address of the user.
                ip = new BufferedReader(new InputStreamReader(
                    new URL("http://checkip.amazonaws.com").openStream())).readLine();
            } catch (IOException e) {
                System.out.println(e);
            }

            ElevatedUser u1 = new ElevatedUser("Admin", ip, true);
            User u2 = new ElevatedUser("Mod", ip);
            User u22 = new ElevatedUser("Mod2", ip, false);
            User u3 = new Student("Student", ip);
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
