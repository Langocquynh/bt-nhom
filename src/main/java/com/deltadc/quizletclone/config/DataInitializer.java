package com.deltadc.quizletclone.config;

import com.deltadc.quizletclone.card.Card;
import com.deltadc.quizletclone.card.CardRepository;
import com.deltadc.quizletclone.folder.Folder;
import com.deltadc.quizletclone.folder.FolderRepository;
import com.deltadc.quizletclone.folderset.FolderSet;
import com.deltadc.quizletclone.folderset.FolderSetRepository;
import com.deltadc.quizletclone.review.Review;
import com.deltadc.quizletclone.review.ReviewRepository;
import com.deltadc.quizletclone.set.Set;
import com.deltadc.quizletclone.set.SetRepository;
import com.deltadc.quizletclone.user.Role;
import com.deltadc.quizletclone.user.User;
import com.deltadc.quizletclone.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final SetRepository setRepository;
    private final FolderRepository folderRepository;
    private final ReviewRepository reviewRepository;
    private final CardRepository cardRepository;
    private final FolderSetRepository folderSetRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        //tao nguoi dung ao
        if(userRepository.count() == 0) {
            User user = new User(
                    "admin",
                    "admin@gmail.com",
                    passwordEncoder.encode("admin"),
                    Role.ADMIN,
                    true
            );

            User pdc = new User(
                    "pdc",
                    "pdc@gmail.com",
                    passwordEncoder.encode("pdc"),
                    Role.ADMIN,
                    true
            );


            User user1 = new User(
                    "Alice",
                    "alice@gmail.com",
                    passwordEncoder.encode("alice"),
                    Role.USER,
                    true
            );

            User user2 = new User(
                    "Bob",
                    "bob@gmail.com",
                    passwordEncoder.encode("bob"),
                    Role.USER,
                    true
            );

            User user3 = new User(
                    "Charlie",
                    "charlie@gmail.com",
                    passwordEncoder.encode("charlie"),
                    Role.USER,
                    true
            );

            User user4 = new User(
                    "David",
                    "david@gmail.com",
                    passwordEncoder.encode("david"),
                    Role.USER,
                    true
            );

            User user5 = new User(
                    "Eve",
                    "eve@gmail.com",
                    passwordEncoder.encode("eve"),
                    Role.USER,
                    true
            );

            User user6 = new User(
                    "Frank",
                    "frank@gmail.com",
                    passwordEncoder.encode("frank"),
                    Role.USER,
                    true
            );

            User user7 = new User(
                    "Grace",
                    "grace@gmail.com",
                    passwordEncoder.encode("grace"),
                    Role.USER,
                    true
            );

            User user8 = new User(
                    "Henry",
                    "henry@gmail.com",
                    passwordEncoder.encode("henry"),
                    Role.USER,
                    true
            );

            User user9 = new User(
                    "Ivy",
                    "ivy@gmail.com",
                    passwordEncoder.encode("ivy"),
                    Role.USER,
                    true
            );

            User user10 = new User(
                    "Jack",
                    "jack@gmail.com",
                    passwordEncoder.encode("jack"),
                    Role.USER,
                    true
            );

            // Save users to the repository
            userRepository.saveAll(Arrays.asList(pdc, user, user1, user2, user3, user4, user5, user6, user7, user8, user9, user10));
        }

        //tao cac set cua nugoi dung do
        List<User> users = userRepository.findAll();

        if(setRepository.count() == 0) {
            for (User user : users) {
                int random = (int) (Math.random() * 100) + 1;
                for(int i = 1; i <= random; i++) {
                    Set set1 = new Set(user.getUser_id(), "Set " + i + " for " + user.getName(), "Description for Set "+ i + " of " + user.getName(), true);
                    setRepository.save(set1);
                }
            }
        }


        //tao cac card thuoc ve set
        List<Set> sets = setRepository.findAll();

        if(cardRepository.count() == 0) {
            for (Set set : sets) {
                int random = (int) (Math.random() * 100) + 1;
                for (int i = 1; i <= random; i++) {
                    String frontText = "Front text for Card " + i + " of Set " + set.getSet_id();
                    String backText = "Back text for Card " + i + " of Set " + set.getSet_id();

                    Card card = new Card(set.getSet_id(), frontText, backText, false);
                    cardRepository.save(card);
                }
            }
        }


        if(reviewRepository.count() == 0) {
            //tao review ao
            for (User user : users) {
                for (Set set : sets) {
                    Random random = new Random();
                    int randomNumber = random.nextInt(2);
                    if(randomNumber == 0) {
                        continue;
                    }

                    int totalStars = (int) (Math.random() * 5) + 1;

                    Review review = new Review(user.getUser_id(), set.getSet_id(), totalStars);
                    reviewRepository.save(review);
                }
            }
        }

        if(folderRepository.count() == 0) {
            //tao folder ao
            for (User user : users) {
                int random = (int) (Math.random() * 20) + 1;
                for(int i = 1; i <= random; i++) {
                    Folder folder = new Folder(user.getUser_id(), "#" + i + ". Folder for " + user.getName(), "Description for folder "+ i + " of " + user.getName(), true);
                    folderRepository.save(folder);
                }
            }
        }

        if(folderSetRepository.count() == 0) {
            List<Folder> folders = folderRepository.findAll();
            for (Folder folder : folders) {
                // Shuffle the list of sets to randomly assign sets to folders
                Collections.shuffle(sets);

                // Take a random number of sets to add to the folder (between 1 and 5 for example)
                int numSetsToAdd = (int) (Math.random() * 5) + 1;
                List<Set> setsToAdd = sets.subList(0, numSetsToAdd);

                for (Set set : setsToAdd) {
                    FolderSet folderSet = new FolderSet(folder.getFolder_id(), set.getSet_id());
                    folderSetRepository.save(folderSet);
                }
            }
        }

    }
}
