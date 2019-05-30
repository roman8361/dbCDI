package ru.kravchenko.se.repository;

import com.sun.istack.internal.NotNull;
import ru.kravchenko.se.model.entity.Session;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Roman Kravchenko
 */
public class SessionRepository {

    @Inject
    private EntityManager em;

    public SessionRepository(@NotNull final EntityManager em) {
        this.em = em;
    }

    public List<Session> findAll() {
        em.getTransaction().begin();
        @NotNull final List<Session> sessions = em.createQuery("SELECT e FROM Session e", Session.class).getResultList();
        em.close();
        return sessions;
    }

    public List<String> ids() {
        em.getTransaction().begin();
        @NotNull final List<String> sessions = em.createQuery("SELECT id FROM Session e", String.class).getResultList();
        em.close();
        return sessions;
    }

    public Session findOne(@NotNull final String id) {
        em.getTransaction().begin();
        @NotNull final Session session = em.find(Session.class, id);
        em.close();
        return session;
    }

    public Session findOnByUserId(@NotNull final String userId) {
        em.getTransaction().begin();
        Session session = em.createQuery("SELECT e FROM Session e WHERE e.user.id =:userId", Session.class)
                .setParameter("userId", userId).getSingleResult();
        return session;
    }

    public void removeById(@NotNull final String id) {
        em.getTransaction().begin();
        @NotNull final Session session = em.find(Session.class, id);
        em.remove(session);
        em.getTransaction().commit();
        em.close();
    }

    public void insert(@NotNull final Session session) {
        em.getTransaction().begin();
        em.persist(session);
        em.getTransaction().commit();
        em.close();
    }

    public void clear() {
        em.getTransaction().begin();
        @NotNull final List<Session> sessions = em.createQuery("SELECT e FROM Session e", Session.class).getResultList();
        for (Session s : sessions) em.remove(s);
        em.getTransaction().commit();
        em.close();
    }

}
