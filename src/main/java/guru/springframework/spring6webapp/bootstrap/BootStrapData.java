package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository ) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author author = new Author();
        author.setFirstName("Marcelo");
        author.setLasttName("Argentoni");

        Author savedAuthor = authorRepository.save(author);

        //1º livro
        Book firstBook = new Book();
        firstBook.setTitle("Spring Boot course");
        firstBook.setIsbn("123456");
        firstBook.getAuthors().add(savedAuthor);
        Book savedFirstBook = bookRepository.save(firstBook);

        //2º livro
        Book secondBook = new Book();
        secondBook.setTitle("Spring Boot course");
        secondBook.setIsbn("123456");
        secondBook.getAuthors().add(savedAuthor);
        Book savedSecondBook = bookRepository.save(secondBook);

        Publisher publisher = new Publisher();
        publisher.setPublisherName( "SpringBoot Academy");
        publisher.setState( "Valencia");
        publisher.setCity( "Valencia");
        publisher.setAddress( "Baleares" );
        publisher.setZipCode("46023");
        publisher.getBooks().add( firstBook );
        publisher.getBooks().add( secondBook );
        Publisher savedPublisher = publisherRepository.save(publisher);

        savedFirstBook.setPublisher( savedPublisher );
        bookRepository.save(savedFirstBook);

        savedSecondBook.setPublisher( savedPublisher );
        bookRepository.save(savedSecondBook);

        savedAuthor.getBooks().add(savedFirstBook);
        savedAuthor.getBooks().add(savedSecondBook);

        authorRepository.save(savedAuthor);

        System.out.println( "Everything is saved");


    }
}
