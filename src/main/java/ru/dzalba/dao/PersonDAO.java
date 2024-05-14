package ru.dzalba.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.dzalba.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> showAllPeople(){
        List<Person>people=new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
        return people;
    }

    public Optional<Person> showPerson(String id){
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void savePerson(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, yearOfBirth) VALUES (?,?)"
                ,person.getName(),person.getYearOfBirth());
    }

    public void updatePerson(int id, Person updatedPerson) {

        jdbcTemplate.update("UPDATE person SET name=?, yearOfBirth=? WHERE id=?",
                updatedPerson.getName(),updatedPerson.getYearOfBirth(),id);
    }

    public void deletePerson(int id) {

        jdbcTemplate.update("DELETE FROM person WHERE id=?",id);
    }
}
