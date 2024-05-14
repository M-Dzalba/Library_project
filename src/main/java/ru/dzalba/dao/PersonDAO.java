package ru.dzalba.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.dzalba.models.Person;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> showAllPeople(){
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person showPerson(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void savePerson(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, year_Of_birth) VALUES (?,?)"
                ,person.getName(),person.getYearOfBirth());
    }

    public void updatePerson(int id, Person updatedPerson) {

        jdbcTemplate.update("UPDATE person SET name=?, year_Of_birth=? WHERE person_id=?",
                updatedPerson.getName(),updatedPerson.getYearOfBirth(),id);
    }

    public void deletePerson(int id) {

        jdbcTemplate.update("DELETE FROM person WHERE person_id=?",id);
    }
}
