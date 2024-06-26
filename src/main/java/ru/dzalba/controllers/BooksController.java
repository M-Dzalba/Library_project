package ru.dzalba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dzalba.dao.BookDAO;
import ru.dzalba.dao.PersonDAO;
import ru.dzalba.models.Book;
import ru.dzalba.models.Person;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String showAllBooks(Model model){
        model.addAttribute("books",bookDAO.showAllBooks());
        return "books/index";
    }
    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.showBook(id));
        Optional<Person> bookOwner=bookDAO.getBookOwner(id);
        if(bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        }
        else {
            model.addAttribute("people", personDAO.showAllPeople());
        }
        return "books/show";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book")Book book) {

        return "books/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "books/new";
        }
        bookDAO.saveBook(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.showBook(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,BindingResult bindingResult, @PathVariable("id") int id) {

        if(bindingResult.hasErrors()){
            return "books/edit";
        }
        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookDAO.release(id);
        return "redirect:/books/"+id;
    }
    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person")Person selectedPerson){
        bookDAO.assign(id, selectedPerson);
        return "redirect:/books/"+id;
    }


}
