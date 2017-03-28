package com.sudowrestlers.chatapi.persistence;

import com.sudowrestlers.chatapi.entity.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 3/6/17.
 */
public class UserDAO {
    /** Return a list of all messages
     *
     * @return All user
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        users = session.createCriteria(User.class).list();
        return users;
    }

    /** Get a single user for the given id
     *
     * @param id user's id
     * @return username
     */
    public User getUser(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        User user = (User) session.get(User.class, id);
        return user;
    }

    /** Create a single message with given message body
     *
     * @param username user's name
     * @return user id
     */
    public long createUser(String username) {
        User newUser = new User();
        newUser.setUsername(username);

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        Long newID = null;
        try {
            transaction = session.beginTransaction();
            newID = (Long)session.save(newUser);
        } catch(RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.flush();
            session.close();
        }
        return newID;
    }

}
