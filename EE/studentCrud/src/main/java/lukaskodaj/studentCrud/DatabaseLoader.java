package lukaskodaj.studentCrud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private StudentRepository repository;

    public DatabaseLoader(StudentRepository repository) {this.repository=repository;}

    @Override
    public void run(String... args) throws Exception {
        this.repository.save(new Student("Matus", "M", "matus@go.com"));
        this.repository.save(new Student("Palo", "F", "pavol@dotnet.com"));
    }
}
