package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //1.annotation for controllers
public class BookController {


    private final BookRepository bookRepository; //3.declare field

    //3.constructor to inject the bookRepository using the private field
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books") //2.annotation for request mapping with method referencing to books page
    public String getBooks(Model model) {
    //2.the Model model above is connecting to Model POJO required by the view

        model.addAttribute("books", bookRepository.findAll());
        //4.when Spring receives the request to the /books url it will execute the books method providing the method to model
        //4.The model object will add the attribute called books, which will return to the viewer the info required

        return "books/list"; //2 declaration of the getBooks method returning the attribute model books
    }
}
