package ru.kravchenko.se;

import com.sun.istack.internal.Nullable;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.junit.Test;
import ru.kravchenko.se.repository.ProjectRepository;
import ru.kravchenko.se.repository.UserRepository;
import ru.kravchenko.se.model.entity.Project;
import ru.kravchenko.se.model.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * @author Roman Kravchenko
 */

public class ProjectRepositoryTest {

    private Lorem lorem = new LoremIpsum();

    private EntityManager create() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ENTERPRISE");
        return entityManagerFactory.createEntityManager();
    }

    private ProjectRepository projectDAO = new ProjectRepository(create());

    private UserRepository userDAO = new UserRepository(create());

    @Test
    public void addAnyProject() {
        for (int i = 0; i < 1; i++) projectDAO.insert(dateProject());
    }

    private Project dateProject() {
        @Nullable final Project project = new Project();
        project.setDateBegin(new Date());
        project.setDateEnd(new Date());
        project.setName(lorem.getWords(1));
        project.setDescription(lorem.getWords(4));
        project.setUser(dateUser());
        return project;
    }

    private User dateUser() {
        @Nullable final User user = new User();
        user.setPasswordHash(lorem.getUrl());
        user.setLogin(lorem.getFirstName());
        userDAO.persist(user);
        return user;
    }

    @Test
    public void findAll() {
        System.out.println(projectDAO.findAll());
    }

    @Test
    public void ids() {
        System.out.println(projectDAO.ids());
    }

    @Test
    public void findById() {
        System.out.println(projectDAO.findById("ba7c7d7d-9565-4861-a5fd-9390b8e04bb8"));
    }

    @Test
    public void findAllProjectByUserId() {
        System.out.println(projectDAO.findAllProjectByUserId("b0c0929a-8343-4d66-82d8-f5f4462d89ca"));
    }

    @Test
    public void removeById() {
        projectDAO.removeById("d523584e-53b3-421e-a0ce-9c899129d501");
    }

    @Test
    public void removeAllProjectByUserId() {
        projectDAO.removeAllProjectByUserId("b0c0929a-8343-4d66-82d8-f5f4462d89ca");
    }

    @Test
    public void clear() {
        projectDAO.clear();
    }

}
