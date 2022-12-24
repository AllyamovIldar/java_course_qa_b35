package lesson.two.task4.appmanager;

import lesson.two.task4.model.ContactData;
import lesson.two.task4.model.Contacts;
import lesson.two.task4.model.GroupData;
import lesson.two.task4.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public GroupData groupsById(int group_id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        GroupData group = (GroupData) session.createQuery("from GroupData where group_id=:group_id").setParameter("group_id", group_id).getSingleResult();
        session.getTransaction().commit();
        session.close();
        return group;
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public ContactData contactsById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ContactData contact = (ContactData) session.createQuery("from ContactData where id=:id").setParameter("id", id).getSingleResult();
        session.getTransaction().commit();
        session.close();
        return contact;
    }
}
