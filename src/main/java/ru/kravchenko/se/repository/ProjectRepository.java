package ru.kravchenko.se.repository;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import ru.kravchenko.se.model.entity.Project;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Roman Kravchenko
 */
public class ProjectRepository {

    @Inject
    private EntityManager em;

    public ProjectRepository(@NotNull final EntityManager em) {
        this.em = em;
    }

    public List<Project> findAll() {
        em.getTransaction().begin();
        List<Project> projects = em.createQuery("SELECT e FROM Project e", Project.class).getResultList();
        em.close();
        return projects;
    }

    public List<String> ids() {
        em.getTransaction().begin();
        @Nullable final List<String> project = em.createQuery("SELECT id FROM Project e", String.class).getResultList();
        em.close();
        return project;
    }

    public Project findById(@NotNull final String id) {
        em.getTransaction().begin();
        @NotNull final Project project = em.find(Project.class, id);
        em.close();
        return project;
    }

    public List<Project> findAllProjectByUserId(@NotNull final String userId) {
        em.getTransaction().begin();
        @Nullable final List<Project> projects = em.createQuery("SELECT e FROM Project e WHERE e.user.id =:userId", Project.class)
                .setParameter("userId", userId)
                .getResultList();
        return projects;
    }

    public void removeById(@NotNull final String id) {
        em.getTransaction().begin();
        @NotNull final Project project = em.find(Project.class, id);
        em.remove(project);
        em.getTransaction().commit();
    }

    public void removeAllProjectByUserId(@NotNull final String userId) {
        em.getTransaction().begin();
        @NotNull final List<Project> projects = em.createQuery("SELECT e FROM Project e WHERE e.user.id =:userId", Project.class)
                .setParameter("userId", userId)
                .getResultList();
        for (Project p : projects) em.remove(p);
        em.getTransaction().commit();
        em.close();
    }

    public void insert(@NotNull final Project project) {
        em.getTransaction().begin();
        em.persist(project);
        em.getTransaction().commit();
        em.close();
    }

    public void clear() {
        em.getTransaction().begin();
        @NotNull final List<Project> projects = em.createQuery("SELECT e FROM Project e", Project.class).getResultList();
        for (Project p : projects) em.remove(p);
        em.getTransaction().commit();
        em.close();
    }

}
