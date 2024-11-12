package ru.netology.DiplomCloudStorage.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.netology.DiplomCloudStorage.model.entity.User;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LoginRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LoginRepository loginRepository;

    @Test
    public void whenFindByUsername_thenReturnUser() {
        User user = new User("username", "password");
        entityManager.persist(user);
        entityManager.flush();

        User foundUser = loginRepository.findByUsername("username");

        assertNotNull(foundUser);
        assertEquals(user.getUsername(), foundUser.getUsername());
        assertEquals(user.getPassword(), foundUser.getPassword());
    }
}