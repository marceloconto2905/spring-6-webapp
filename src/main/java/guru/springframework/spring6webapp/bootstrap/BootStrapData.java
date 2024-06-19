package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository ) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author author = new Author();
        author.setFirstName("Marcelo");
        author.setLasttName("Argentoni");

        Book book = new Book();
        book.setTitle("Spring Boot course");
        book.setIsbn("123456");

        Author savedAuthor = authorRepository.save(author);
        Book savedBook = bookRepository.save(book);

        savedAuthor.getBooks().add(savedBook);
        savedBook.getAuthors().add(author);

        authorRepository.save(savedAuthor);
        bookRepository.save(savedBook);
    }
}
