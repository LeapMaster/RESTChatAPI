package com.sudowrestlers.chatapi;

import com.sudowrestlers.chatapi.persistence.MessageDAO;
import com.sudowrestlers.chatapi.entity.Message;
import com.sudowrestlers.chatapi.persistence.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by student on 4/12/17.
 */
public class MessageDaoTest {
    MessageDAO dao;

    @Before
    public void setup() {
        dao = new MessageDAO();
    }

    @Test
    public void getRecentMessages() {
        List<Message> messages = dao.getRecentMessages();

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        messages = session.createCriteria(Message.class).setMaxResults(20).addOrder(Order.desc("id")).list();
        assertNotNull(messages);

    }

    @Test
    public List<Message> getAllMessages() {

    }


}
