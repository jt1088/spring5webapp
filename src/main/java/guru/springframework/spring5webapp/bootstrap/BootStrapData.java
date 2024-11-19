package guru.springframework.spring5webapp.bootstrap;


import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    //properties
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    //new property for Publisher
    private final PublisherRepository publisherRepository;

    //constructor to initialize the properties
    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        //Add publisher with arguments
        Publisher publisher = new Publisher();
        publisher.setName("Publisher Name Example");
        publisher.setCity("Publisher City Example");
        publisher.setState("Publisher State Example");

        publisherRepository.save(publisher); //add publisher info

        System.out.println("Publisher Count: " + publisherRepository.count()); //print publisher for functionality test

        Author eric = new Author("Eric", "Evans"); //Add author object
        Book ddd = new Book("Domain Driven Design", "123123"); //Add book object

        authorRepository.save(eric); //save author before adding and saving book
        eric.getBooks().add(ddd); //input the book from this author
        ddd.getAuthors().add(eric); //input the author from this book
        bookRepository.save(ddd); //then save the book
        ddd.setPublisher(publisher); //after publisher creation, add the publisher to the 'ddd' book
        publisher.getBooks().add(ddd); //add ddd to the publisher
        publisherRepository.save(publisher); //save the publisher



        //add another author
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");

        authorRepository.save(rod);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        bookRepository.save(noEJB);
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);
        publisherRepository.save(publisher);

        //print to test the functionality for Books and Authors
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of books: " + publisher.getBooks().size());
    }

}
