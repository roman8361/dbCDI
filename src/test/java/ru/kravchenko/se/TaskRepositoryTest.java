package ru.kravchenko.se;

import com.sun.istack.internal.Nullable;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.junit.Test;
import ru.kravchenko.se.repository.ProjectRepository;
import ru.kravchenko.se.repository.TaskRepository;
import ru.kravchenko.se.repository.UserRepository;
import ru.kravchenko.se.model.entity.Project;
import ru.kravchenko.se.model.entity.Task;
import ru.kravchenko.se.model.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * @author Roman Kravchenko
 */

public class TaskRepositoryTest {

    private Lorem lorem = new LoremIpsum();

    private EntityManager create() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ENTERPRISE");
        return entityManagerFactory.createEntityManager();
    }

    private ProjectRepository projectDAO = new ProjectRepository(create());

    private UserRepository userDAO = new UserRepository(create());

    private TaskRepository taskDAO = new TaskRepository(create());

    @Test
    public void addAnyProject() { taskDAO.insert(dateTask());}

    private Task dateTask() {
        @Nullable final Task task = new Task();
        task.setDateBegin(new Date());
        task.setDateEnd(new Date());
        task.setName(lorem.getWords(1));
        task.setDescription(lorem.getWords(3));
        task.setUser(dateUser());
        task.setProject(dateProject());
        return task;
    }

    private User dateUser() {
        @Nullable final User user = new User();
        user.setPasswordHash(lorem.getUrl());
        user.setLogin(lorem.getFirstName());
        userDAO.persist(user);
        return user;
    }

    private Project dateProject() {
        @Nullable final Project project = new Project();
        project.setUser(dateUser());
        project.setDateBegin(new Date());
        project.setDateEnd(new Date());
        project.setName(lorem.getWords(1));
        project.setDescription(lorem.getWords(3));
        projectDAO.insert(project);
        return project;
    }

    @Test
    public void findAll() {
        System.out.println(taskDAO.findAll());
    }

    @Test
    public void ids() {
        System.out.println(taskDAO.ids());
    }

    @Test
    public void findById() {
        System.out.println(taskDAO.findById("cef5dd42-9eca-46df-820b-7da00c7af70e"));
    }

    @Test
    public void findAllTaskByUserId() {
        System.out.println(taskDAO.findAllTaskByUserId("a97ed97e-5a62-4345-b72a-69f423f04fbe"));
    }

    @Test
    public void removeById() {
        taskDAO.removeById("2e1d1d0f-8907-42a2-b292-329fd878b5f0");
    }

    @Test
    public void removeAllTaskByUserId() {
        taskDAO.removeAllTaskByUserId("a97ed97e-5a62-4345-b72a-69f423f04fbe");
    }

    @Test
    public void clear() {
        taskDAO.clear();
    }

}
