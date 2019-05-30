package ru.kravchenko.se.repository;


import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import lombok.NoArgsConstructor;
import ru.kravchenko.se.model.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Roman Kravchenko
 */

@Transactional
@ApplicationScoped
@NoArgsConstructor
public class UserRepository {

    @Inject
    private EntityManager em;

    public UserRepository(@NotNull final EntityManager em) {
        this.em = em;
    }

    public void persist(final User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public List<User> findAll() {
        em.getTransaction().begin();
        List<User> users = em.createQuery("SELECT e FROM User e", User.class).getResultList();
        em.close();
        System.out.println(users);
        return users;
    }

    public List<String> ids() {
        em.getTransaction().begin();
        List<String> ids = em.createQuery("SELECT id FROM User e", String.class).getResultList();
        em.close();
        return ids;
    }

    public User findById(@NotNull final String id) {
        em.getTransaction().begin();
        @NotNull final User user = em.find(User.class, id);
        em.close();
        return user;
    }

    public User findByLogin(@NotNull final String login) {
        em.getTransaction().begin();
        @NotNull final User user = em.createQuery("SELECT e FROM User e WHERE e.login =:login", User.class)
                .setParameter("login", login).getSingleResult();
        em.close();
        return user;
    }

    public void removeById(@NotNull final String id) {
        em.getTransaction().begin();
        @NotNull final User user = em.find(User.class, id);
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }

    public void insert(@NotNull final User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public void clear() {
        em.getTransaction().begin();
        @Nullable final List<User> users = em.createQuery("SELECT e FROM User e", User.class).getResultList();
        for (final User u : users) em.remove(u);
        em.getTransaction().commit();
        em.close();
    }

    public List<String> loginList() {
        em.getTransaction().begin();
        @Nullable final List<String> loginList = em.createQuery("SELECT login FROM User e", String.class).getResultList();
        em.close();
        return loginList;
    }

}
