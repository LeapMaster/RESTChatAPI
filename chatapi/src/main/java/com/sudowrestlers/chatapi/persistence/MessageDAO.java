package com.sudowrestlers.chatapi.persistence;

import com.sudowrestlers.chatapi.entity.Message;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by student on 3/6/17.
 */
public class MessageDAO {

    Properties properties;

    public MessageDAO() {
        loadProperties("/config/properties.properties");
    }


    /** Return a list of all messages
     *
     * @return All messages
     */
    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<Message>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        messages = session.createCriteria(Message.class).addOrder(Order.asc("id")).list();
        return messages;
    }

    public List<Message> getRecentMessages() {
        List<Message> messages = new ArrayList<Message>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        int count = Integer.parseInt(properties.getProperty("messages.recent"));
        System.out.println(count);
        messages = session.createCriteria(Message.class).setMaxResults(count).addOrder(Order.asc("id")).list();
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
    public int createMessage(String message, int userID) {
        Message newMessage = new Message();
        newMessage.setMessage(message);
        newMessage.setUserID(userID);

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;

        Integer newID;
        try {
            transaction = session.beginTransaction();
            newID = (Integer)session.save(newMessage);
        } catch(RuntimeException e) {
            // Check if null to prevent null-pointer exceptions on rollback
            if (transaction != null) {
                transaction.rollback();
            }
            newID = -1;
        } finally {
            //Make sure to commit any changes, then close the session
            transaction.commit();
            session.flush();
            session.close();
            trimMessages();
        }
        return newID;
    }

    /** Create a single message with given message body
     *
     * @param messageID id of message to delete
     * @return status number
     */
    public int deleteMessage(int messageID) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        Message message = getMessage(messageID);
        int status = 0;
        if (message == null) {
            status = -1;
        }
        try {
            transaction = session.beginTransaction();
            session.delete(message);
        } catch(RuntimeException e) {
            // Check if null to prevent null-pointer exceptions on rollback
            if (transaction != null) {
                transaction.rollback();
            }

        } finally {
            //Make sure to commit any changes, then close the session
            if (transaction != null) {
                transaction.commit();
                status = 1;

                trimMessages();
            }
            session.flush();
            session.close();
        }
        return status;
    }

    /** Trim old messages to fit the limit
     */
    public void trimMessages() {
        // Constant, can easily move to resources folder if needbe
        int MAX_MESSAGES_COUNT = Integer.parseInt(properties.getProperty("messages.trim"));


        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Long messageCountLong = (Long) session.createCriteria(Message.class).setProjection(Projections.rowCount()).uniqueResult();
        int messageCount = messageCountLong.intValue();
        if (messageCount > MAX_MESSAGES_COUNT) {
            // Calculate the difference between the correct and current amount of messages
            int difference = messageCount - MAX_MESSAGES_COUNT;
            List<Message> messages = new ArrayList<Message>();
            // Order by ascending to make sure we get the oldest messages, only select as many messages as we're over
            messages = session.createCriteria(Message.class).setMaxResults(difference).addOrder(Order.asc("id")).list();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                for (Message message : messages) {
                    session.delete(message);
                }
            }  catch(RuntimeException e) {
                // Check if null to prevent null-pointer exceptions on rollback
                if (transaction != null) {
                    transaction.rollback();
                }
            } finally {
                //Make sure to commit any changes, then close the session
                transaction.commit();
                session.flush();
                session.close();
            }
        }
    }

    public List<Message> getMessagesByUser(int userID) {
        List<Message> messages = new ArrayList<Message>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        messages = session.createCriteria(Message.class).add(Restrictions.eq("userID", userID)).addOrder(Order.desc("ID")).list();
        return messages;
    }

    /**
     * Load the properties file so its value can be retrieved
     * @param propertiesFilePath the file path to the properties file
     */
    public void loadProperties(String propertiesFilePath) {
        properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        } catch(IOException ioException) {
            ioException.printStackTrace();
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void main (String[] args) {
        MessageDAO dao = new MessageDAO();
        dao.trimMessages();

    }

}
