package org.jakabhegy.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jakabhegy.dao.AccountDao;
import org.jakabhegy.pojo.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountDaoTest {

	private static final String PERSISTENCE_UNIT_NAME = "test";
	private static EntityManagerFactory factory;
	private EntityManager em;
	private AccountDao accountDao;
	private String username = "user";
	private String password = "1234";
	private String email = "test@test.com";
	Account testUser;

	@Before
	public void init() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		accountDao = new AccountDao(em);
		testUser = new Account();
		testUser.setUsername(username);
		testUser.setEmail(email);
		testUser.setPassword(password);
		accountDao.create(testUser);
	}

	@After
	public void delete() {
		accountDao.delete(testUser);
	}

	@Test
	public void testListAll() {
		assertEquals(1, accountDao.listAll("Account").size());
		Account user2=new Account();
		accountDao.create(user2);
		assertEquals(2, accountDao.listAll("Account").size());
		accountDao.delete(user2);
	}

	@Test
	public void testListOne() {
		Account userFromDao = accountDao.listOne(username, password);
		assertEquals(email, userFromDao.getEmail());
		// nem létezõ felhasználónév-jelszó párosra null értéket kell adnia
		assertEquals(null, accountDao.listOne("not", "exist"));
	}

	@Test
	public void testUpdate() {
		Account userFromDao = accountDao.listOne(username, password);
		userFromDao.setCheckText("test");
		accountDao.update(userFromDao);
		userFromDao = accountDao.listOne(username, password);
		assertEquals("test", userFromDao.getCheckText());
	}

	@Test
	public void testUsedProperties() {
		assertTrue(accountDao.emailIsUsed(email));
		assertTrue(accountDao.usernameIsUsed(username));
	}

}
