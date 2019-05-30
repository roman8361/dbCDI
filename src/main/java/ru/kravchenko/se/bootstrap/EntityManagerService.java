package ru.kravchenko.se.bootstrap;

import com.sun.istack.internal.NotNull;
import ru.kravchenko.se.service.PropertyService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Roman Kravchenko
 */

@ApplicationScoped
public class EntityManagerService {

    @NotNull
    private static final String UNIX_NAME = "ENTERPRISE";

//    @Inject
//    @PersistenceUnitName(UNIX_NAME)
//    private EntityManagerFactory entityManagerFactory;

    @Inject
    private PropertyService propertyService;

    @NotNull
    @Produces
    public EntityManager create() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ENTERPRISE");
        return entityManagerFactory.createEntityManager();
    }

    public void dispose(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) entityManager.close();
    }

//    public EntityManager getEntityManager() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ENTERPRISE");
//        return emf.createEntityManager();
//    }

//    @Produces
//    public EntityManagerFactory getFactory() {
//        final Map<String, String> settings = new HashMap<>();
//        settings.put(Environment.DRIVER, propertyService.getJdbcDriver());
//        settings.put(Environment.URL, propertyService.getJdbcURL());
//        settings.put(Environment.USER, propertyService.getJdbcUser());
//        settings.put(Environment.PASS, propertyService.getJdbcPassword());
//        settings.put(Environment.DIALECT, propertyService.getDialect());
//        settings.put(Environment.HBM2DDL_AUTO, propertyService.getHBM2DDL_AUTO());
//        settings.put(Environment.SHOW_SQL, propertyService.getShowSQL());
//        final StandardServiceRegistryBuilder registryBuilder
//                = new StandardServiceRegistryBuilder();
//        System.out.println(settings);
//        registryBuilder.applySettings(settings);
//        final StandardServiceRegistry registry = registryBuilder.build();
//        final MetadataSources sources = new MetadataSources(registry);
//        sources.addAnnotatedClass(Cat.class);
////        sources.addAnnotatedClass(Project.class);
////        sources.addAnnotatedClass(User.class);
////        sources.addAnnotatedClass(Session.class);
//        final Metadata metadata = sources.getMetadataBuilder().build();
//        return metadata.getSessionFactoryBuilder().build();
//    }

}
