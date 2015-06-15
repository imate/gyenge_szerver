package org.jakabhegy.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jakabhegy.dao.ArticleDao;
import org.jakabhegy.dao.MessageDao;
import org.jakabhegy.pojo.Article;
import org.jakabhegy.pojo.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MessageDaoTest {

	private static final String PERSISTENCE_UNIT_NAME = "test";
	private static EntityManagerFactory factory;
	private EntityManager em;
	private MessageDao messageDao;
	private Message testMessage;

	@Before
	public void init() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		messageDao = new MessageDao(em);
		testMessage = new Message();
		messageDao.create(testMessage);
	}

	@After
	public void delete() {
		messageDao.delete(testMessage);
	}

	@Test
	public void testListAll() {
		assertEquals(Arrays.asList(testMessage), messageDao.listAll("Message"));
		Message message2 = new Message();
		messageDao.create(message2);
		assertEquals(Arrays.asList(testMessage,message2), messageDao.listAll("Message"));
		messageDao.delete(message2);
	}

	@Test
	public void testDelete() {
		Message message = new Message();
		assertEquals(Arrays.asList(testMessage),messageDao.listAll("Message"));
		messageDao.create(message);
		assertEquals(Arrays.asList(testMessage,message),messageDao.listAll("Message"));
		messageDao.delete(message);
		assertEquals(Arrays.asList(testMessage),messageDao.listAll("Message"));
	}

}
