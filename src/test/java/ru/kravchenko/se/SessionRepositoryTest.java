package ru.kravchenko.se;

import com.sun.istack.internal.Nullable;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.junit.Test;
import ru.kravchenko.se.repository.SessionRepository;
import ru.kravchenko.se.repository.UserRepository;
import ru.kravchenko.se.model.entity.Session;
import ru.kravchenko.se.model.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * @author Roman Kravchenko
 */

public class SessionRepositoryTest {

    private Lorem lorem = new LoremIpsum();

    private EntityManager create() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ENTERPRISE");
        return entityManagerFactory.createEntityManager();
    }

    private UserRepository userDAO = new UserRepository(create());

    private SessionRepository sessionDAO = new SessionRepository(create());

    @Test
    public void addAnySession() {
        for (int i = 0; i < 1; i++) sessionDAO.insert(dateSession());
    }

    private Session dateSession() {
        @Nullable final Session session = new Session();
        session.setSignature(lorem.getZipCode());
        session.setTimestamp(new Date());
        session.setUser(dateUser());
        return session;
    }

    private User dateUser() {
        @Nullable final User user = new User();
        user.setPasswordHash(lorem.getUrl());
        user.setLogin(lorem.getFirstName());
        userDAO.persist(user);
        return user;
    }

    @Test
    public void findAll() { System.out.println(sessionDAO.findAll()); }

    @Test
    public void ids() { System.out.println(sessionDAO.ids()); }

    @Test
    public void findOne() {
        System.out.println(sessionDAO.findOne("cb037238-1e97-4d08-ac49-a5dc7c908e32"));
    }

    @Test
    public void findOnByUserId() { sessionDAO.findOnByUserId("86c6c3e3-5769-463f-b4f5-630811ffcec0"); }

    @Test
    public void removeById() { sessionDAO.removeById("e4d8d00a-8519-4038-bad0-1d0fb38a4f9d"); }

    @Test
    public void clear() { sessionDAO.clear(); }


}
