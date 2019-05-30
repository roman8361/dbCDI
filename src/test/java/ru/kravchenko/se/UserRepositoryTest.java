package ru.kravchenko.se;

import com.sun.istack.internal.Nullable;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.junit.Test;
import ru.kravchenko.se.repository.UserRepository;
import ru.kravchenko.se.model.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Unit test for simple App.
 */

public class UserRepositoryTest {

    private Lorem lorem = new LoremIpsum();

    private EntityManager create() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ENTERPRISE");
        return entityManagerFactory.createEntityManager();
    }

    private UserRepository userDAO = new UserRepository(create());

    @Test
    public void addAnyUser() {
        for (int i = 0; i < 10; i++) userDAO.persist(dateUser());
    }

    private User dateUser() {
        @Nullable final User user = new User();
        user.setPasswordHash(lorem.getUrl());
        user.setLogin(lorem.getFirstName());
        return user;
    }

    @Test
    public void findByIdUser() {
        final User user = userDAO.findById("1f3c229c-40de-49a4-a18f-edf8f3767b2f");
        System.out.println(user.getPasswordHash());
    }

    @Test
    public void findByLoginUser() {
        final User user = userDAO.findByLogin("Sue");
        System.out.println(user.getPasswordHash());
    }

    @Test
    public void removeByIdUser() {
        userDAO.removeById("59a3ff54-3430-48d9-974b-a7d7f870963e");
    }

    @Test
    public void loginList() {
        System.out.println(userDAO.loginList());
    }

    @Test
    public void findAllUser() {
        System.out.println(userDAO.findAll());
    }

    @Test
    public void idsList() {
        System.out.println(userDAO.ids());
    }

    @Test
    public void clear() {
        userDAO.clear();
    }

}
