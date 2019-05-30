package ru.kravchenko.se.repository;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import ru.kravchenko.se.model.entity.Task;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Roman Kravchenko
 */

public class TaskRepository {

    @Inject
    private EntityManager em;

    public TaskRepository(@NotNull final EntityManager em) {
        this.em = em;
    }

    public List<Task> findAll() {
        em.getTransaction().begin();
        @Nullable final List<Task> task = em.createQuery("SELECT e FROM Task e", Task.class).getResultList();
        em.close();
        return task;
    }

    public List<String> ids() {
        em.getTransaction().begin();
        @Nullable final List<String> tasks = em.createQuery("SELECT id FROM Task e", String.class).getResultList();
        em.close();
        return tasks;
    }

    public Task findById(@NotNull final String id) {
        em.getTransaction().begin();
        @NotNull final Task task = em.find(Task.class, id);
        em.close();
        return task;
    }

    public List<Task> findAllTaskByUserId(@NotNull final String userId) {
        em.getTransaction().begin();
        @NotNull final List<Task> tasks = em.createQuery("SELECT e FROM Task e WHERE e.user.id =:userId", Task.class)
                .setParameter("userId", userId)
                .getResultList();
        return tasks;
    }

    public void removeById(@NotNull final String id) {
        em.getTransaction().begin();
        @NotNull final Task task = em.find(Task.class, id);
        em.remove(task);
        em.getTransaction().commit();
        em.close();
    }

    public void removeAllTaskByUserId(@NotNull final String userId) {
        em.getTransaction().begin();
        @NotNull final List<Task> tasks = em.createQuery("SELECT e FROM Task e WHERE e.user.id =:userId", Task.class)
                .setParameter("userId", userId)
                .getResultList();
        for (Task t : tasks) em.remove(t);
        em.getTransaction().commit();
        em.close();
    }

    public void insert(@NotNull final Task task) {
        em.getTransaction().begin();
        em.persist(task);
        em.getTransaction().commit();
        em.close();
    }

    public void clear() {
        em.getTransaction().begin();
        @Nullable final List<Task> tasks = em.createQuery("SELECT e FROM Task e", Task.class).getResultList();
        for (Task t : tasks) em.remove(t);
        em.getTransaction().commit();
        em.close();
    }

}
