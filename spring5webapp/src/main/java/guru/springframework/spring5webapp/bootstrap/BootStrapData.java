package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Publisher publisher = Publisher.builder()
                .name("SFG Publishing")
                .city("Mirzapur")
                .books(new HashSet<>())
                .state("UP").build();

        publisherRepository.save(publisher);

        Author eric = Author.builder()
                .firstname("Eric")
                .lastname("Evans")
                .books(new HashSet<>())
                .build();
        Book ddd = Book.builder()
                .title("J2EE Development without E2B")
                .isbn("1234556")
                .authors(new HashSet<>())
                .publisher(publisher)
                .build();

        publisher.getBooks().add(ddd);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);


        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        System.out.println("Publisher count: " + publisherRepository.count());
        System.out.println("Author count: " + authorRepository.count());
    }
}
