package lukaskodaj.planner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lukaskodaj.planner.PasswordUtility;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private UserRepository userRepository;

    private NotesRepository notesRepository;

    public DatabaseLoader(UserRepository userRepository, NotesRepository notesRepository) {
        this.userRepository= userRepository;
        this.notesRepository = notesRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        String salt = PasswordUtility.getSalt(30);

        this.userRepository.save(new User("Matus", "M", "matus@go.com", salt, PasswordUtility.generateSecurePassword("aaa", salt)));
        this.userRepository.save(new User("Palo", "F", "pavol@dotnet.com", salt,  PasswordUtility.generateSecurePassword("aaa", salt)));
        this.userRepository.save(new User("Lukas", "K", "lukaskodaj@gmail.com", salt,  PasswordUtility.generateSecurePassword("aaa", salt)));
        this.notesRepository.save(new Notes(3, "Prva poznamka" , "Prva poznamka v notese !!!", "Prva poznamka v notese !!!","c-done", new Date(), new Timestamp(System.currentTimeMillis())));
        this.notesRepository.save(new Notes(3, "Druha poznamka" , "Druha poznamka v notese !!!", "Druha poznamka v notese !!!","c-process", new Date(), new Timestamp(System.currentTimeMillis())));
        this.notesRepository.save(new Notes(3, "Tretia poznamka" , "Tretia poznamka v notese !!!", "Tretia poznamka v notese !!!","c-new", new Date(), new Timestamp(System.currentTimeMillis())));
        this.notesRepository.save(new Notes(3, "Stvrta poznamka" , "Stvrta poznamka v notese !!!", "Stvrta poznamka v notese !!!","c-done", new Date(), new Timestamp(System.currentTimeMillis())));
        this.notesRepository.save(new Notes(3, "Piata poznamka" , "Piata poznamka v notese !!!", "Piata poznamka v notese !!!","c-process", new Date(), new Timestamp(System.currentTimeMillis())));
        this.notesRepository.save(new Notes(3, "Siesta poznamka" , "Siesta poznamka v notese !!!", "Siesta poznamka v notese !!!","c-done", new Date(), new Timestamp(System.currentTimeMillis())));
        this.notesRepository.save(new Notes(3, "Siedma poznamka" , "Siedma poznamka v notese !!!", "Siedma poznamka v notese !!!","c-process", new Date(), new Timestamp(System.currentTimeMillis())));
    }
}
