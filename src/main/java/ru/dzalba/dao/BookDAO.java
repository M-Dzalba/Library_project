package ru.dzalba.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.dzalba.models.Book;
import ru.dzalba.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> showAllBooks(){
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book showBook(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void saveBook(Book bookk){
        jdbcTemplate.update("INSERT INTO book(author, year, title) VALUES (?,?,?)"
                ,bookk.getAuthor(),bookk.getYear(),bookk.getTitle());
    }
    public void updateBook(int id, Book updatedBook) {

        jdbcTemplate.update("UPDATE book SET author=?, year=?, title=? WHERE book_id=?",
                updatedBook.getAuthor(),updatedBook.getYear(),updatedBook.getTitle(),id);
    }

    public void deleteBook(int id) {

        jdbcTemplate.update("DELETE FROM book WHERE book_id=?",id);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id=Person.person_id "+
                "WHERE Book.book_id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public void release(int id) {
        jdbcTemplate.update("UPDATE book SET person_id=NULL WHERE book_id=?",id);
    }

    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?",selectedPerson.getPerson_id(),id);
    }
}
