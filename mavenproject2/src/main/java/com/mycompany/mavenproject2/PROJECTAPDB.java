package com.mycompany.mavenproject2;

import java.io.Serializable;
import java.util.List;
import org.hibernate.*;

public class PROJECTAPDB {

    public static void main(String[] args) {
    /*    User st1 = new User();
        st1.setUserName("Khaled Abdullah");
        st1.setEmail("Khaled57525@gmail.com");
        st1.setPassword("securepassword");

        User st2 = new User();
        st2.setUserName("Rami");
        st2.setEmail("rami8866@gmail.com");
        st2.setPassword("securepassword");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Serializable sId = session.save(st1);
            tx.commit();
            System.out.println("Inserted User: " + st1.getUserName());
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Serializable sId2 = session.save(st2);
            tx.commit();
            System.out.println("Inserted User: " + st2.getUserName());
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "from User"; 
            Query<User> query = session.createQuery(queryStr, User.class);
            List<User> sList = query.list();
            System.out.println("User list size: " + sList.size());
            for (User s : sList) {
                System.out.println(s.getUserName());
            }
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            User Supdate = session.get(User.class, 1);  
            if (Supdate != null) {
                Supdate.setUserName("Mohammad");
                session.update(Supdate);
            }
            tx.commit();
            System.out.println("User with ID 1 was updated to: " + Supdate.getUserName());
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx3 = session.beginTransaction();
            User userToDelete = session.get(User.class, st1.getId());  
            if (userToDelete != null) {
                session.delete(userToDelete);
            }
            tx3.commit();
            System.out.println("Deleted User: " + st1.getUserName());
        }*/
    }
}
