package org.betavzw.javaadvanced;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Jef on 5/09/2016.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        new Main().run();
    }
    private void run() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try(SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory()){
            initDb(sessionFactory);
            queryDbWithStreams(sessionFactory);
            try(Session session = sessionFactory.openSession()){
                Stream<Singer> singers = session.createQuery("SELECT s FROM Singer s", Singer.class).stream();
                singers.map(s -> String.format("%1$s was born on %2$td-%2$tm-%2$tY and is %3$d years old", s.getFirstName(), s.getBirthdate(), s.getAge()) )
                        .forEach(m -> System.out.println(m));
                Stream<Object[]> singers2 = session.createQuery("SELECT s.firstName, s.birthdate FROM Singer s").stream();
                singers2.map(b -> String.format("%s was born on %2$td-%2$tm-%2$tY", (String) b[0], (LocalDate)b[1]))
                        .forEach(s-> System.out.println(s));

            }

        }
        catch (Exception e) {
            System.out.println("In Exception: " + e.getMessage() + " " + e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    private void queryDbWithStreams(SessionFactory sessionFactory) {
        //no reason to do this: use SQL
        try(Session session = sessionFactory.openSession()){
            Stream<Singer> singers = session.createQuery("SELECT s FROM Singer s", Singer.class).stream();
            List<Singer> K3 =  singers.filter(s -> s.getFirstName().startsWith("K")).collect(Collectors.toList());
            K3.forEach(s -> System.out.println(s.getFirstName()));
        }
    }

    private void initDb(SessionFactory sessionFactory) {
        Singer[] singers = {new Singer(1, "Karen", "Daemen", LocalDate.of(1974, Month.OCTOBER, 28)),
                new Singer(2, "Kristel", "Verbeke", LocalDate.of(1975, Month.DECEMBER, 10)),
                new Singer(3, "Kathleen", "Aerts", LocalDate.of(1978, Month.JUNE, 18)),
                new Singer(4, "Josje", "Huisman", LocalDate.of(1986, Month.FEBRUARY, 16)),
                new Singer(5, "Hanne", "Verbruggen", LocalDate.of(1994, Month.MARCH, 3)),
                new Singer(6, "Marthe", "De Pillecyn", LocalDate.of(1996, Month.JULY, 16)),
                new Singer(7, "Klaasje", "Meijer", LocalDate.of(1995, Month.MARCH, 2))
        };
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            for (Singer s : singers) {
                session.persist(s);
            }
            session.getTransaction().commit();
        }
    }
}
