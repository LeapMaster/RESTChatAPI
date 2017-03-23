package com.sudowrestlers.chatapi.persistence;

import com.sudowrestlers.chatapi.entity.Message;

import org.hibernate.Session;

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

}
