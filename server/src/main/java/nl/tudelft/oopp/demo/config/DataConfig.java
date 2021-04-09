package nl.tudelft.oopp.demo.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.tudelft.oopp.demo.entities.Answer;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.log.LogJoin;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.entities.users.User;
import nl.tudelft.oopp.demo.repositories.AnswerRepository;
import nl.tudelft.oopp.demo.repositories.LogEntryRepository;
import nl.tudelft.oopp.demo.repositories.PollRepository;
import nl.tudelft.oopp.demo.repositories.QuestionRepository;
import nl.tudelft.oopp.demo.repositories.RoomConfigRepository;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {
    //
    //    /**
    //     * Command line runner command line runner.
    //     *
    //     * @param answerRepository   the answer repository
    //     * @param userRepository     the user repository
    //     * @param roomRepository     the room repository
    //     * @param questionRepository the question repository
    //     * @param pollRepository     the poll repository
    //     * @return the command line runner
    //     */
    //    @Bean
    //    CommandLineRunner commandLineRunner(AnswerRepository answerRepository,
    //                                        UserRepository userRepository,
    //                                        RoomRepository roomRepository,
    //                                        QuestionRepository questionRepository,
    //                                        PollRepository pollRepository,
    //                                        LogEntryRepository logEntryRepository,
    //                                        RoomConfigRepository roomConfigRepository) {
    //        return args -> {
    //            String ip = "";
    //            try {
    //                // Get the public IP address of the user.
    //                ip = new BufferedReader(new InputStreamReader(
    //                        new URL("http://checkip.amazonaws.com").openStream())).readLine();
    //            } catch (IOException e) {
    //                System.out.println(e);
    //            }
    //
    //            ElevatedUser u1 = new ElevatedUser("Admin", ip, true);
    //            User u2 = new ElevatedUser("Mod", ip);
    //            User u22 = new ElevatedUser("Mod2", ip, false);
    //            User u3 = new Student("Student", ip);
    //            userRepository.saveAll(List.of(u1, u2, u3, u22));
    //
    //            Poll p1 = new Poll("Poll text",
    //                    List.of("A", "B", "Correct answer", "D", "E", "F"),
    //                    List.of("Correct answer"));
    //
    //            Poll p2 = new Poll("Poll text",
    //                    List.of("A", "B", "Correct answer", "D", "E", "F", "7", "8", "9"),
    //                    List.of("Correct answer"));
    //            p2.setStatus(Poll.PollStatus.CLOSED);
    //
    //            pollRepository.saveAll(List.of(p1, p2));
    //
    //            final Question q1 = new Question("Question text 1", u1);
    //            final Question q2 = new Question("Question text 2", u2);
    //            final Question q3 = new Question("Question text 3", u2);
    //            final Question q4 = new Question("Question text 4", u3);
    //            q1.setScore(1);
    //            q2.setScore(2);
    //            q3.setScore(5);
    //            q4.setScore(4);
    //            q3.setAnswer("Answer");
    //            questionRepository.saveAll(List.of(q1, q2, q3, q4));
    //
    //            Room r1 = new Room("Room Title", false, u1);
    //
    //            r1.setQuestions(Stream.of(q1, q2, q3, q4)
    //                    .collect(Collectors.toSet())
    //            );
    //            r1.setPolls(Stream.of(p1, p2)
    //                    .collect(Collectors.toSet())
    //            );
    //            Set<ElevatedUser> mods = new HashSet<>();
    //            mods.add(u1);
    //            r1.setModerators(mods);
    //
    //            RoomConfig roomConfig = new RoomConfig();
    //            r1.setRoomConfig(roomConfig);
    //
    //            roomConfigRepository.save(roomConfig);
    //            roomRepository.save(r1);
    //
    //            LogJoin logJoin = new LogJoin(u1, r1);
    //            logEntryRepository.save(logJoin);
    //
    //            Answer answer1 = new Answer(List.of("A"), p1.getId(), u3.getId());
    //            Answer answer2 = new Answer(List.of("A", "B"), p1.getId(), u3.getId());
    //            Answer answer3 = new Answer(List.of("A", "B", "Correct Answer"), p1.getId(),
    //                    u3.getId());
    //            Answer answer4 = new Answer(List.of("D"), p1.getId(), u3.getId());
    //            answerRepository.saveAll(List.of(answer1, answer2, answer3, answer4));
    //            p1.addAnswer(answer1);
    //            p1.addAnswer(answer2);
    //            p1.addAnswer(answer3);
    //            p1.addAnswer(answer4);
    //            pollRepository.save(p1);
    //        };
    //    }

}
