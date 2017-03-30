package com.sudowrestlers.chatapi.persistence;

import com.sudowrestlers.chatapi.entity.Message;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 3/6/17.
 */
public class MessageDAO {
    /** Return a list of all messages
     *
     * @return All messages
     */
    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<Message>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        messages = session.createCriteria(Message.class).setMaxResults(20).list();
        return messages;
    }

    /** Get a single message for the given id
     *
     * @param id message's id
     * @return Message
     */
    public Message getMessage(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Message message = (Message) session.get(Message.class, id);
        return message;
    }

    /** Create a single message with given message body
     *
     * @param message message object's body
     * @return Message id
     */
    public int createMessage(String message) {
        Message newMessage = new Message();
        newMessage.setMessage(message);

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer newID;
        try {
            transaction = session.beginTransaction();
            newID = (Integer)session.save(newMessage);
        } catch(RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            newID = -1;
        } finally {
            session.flush();
            session.close();
        }
        return newID;
    }

}
