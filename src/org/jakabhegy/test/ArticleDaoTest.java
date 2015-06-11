package org.jakabhegy.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jakabhegy.dao.AccountDao;
import org.jakabhegy.dao.ArticleDao;
import org.jakabhegy.pojo.Account;
import org.jakabhegy.pojo.Article;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArticleDaoTest {

	private static final String PERSISTENCE_UNIT_NAME = "test";
	private static EntityManagerFactory factory;
	private EntityManager em;
	private ArticleDao articleDao;
	private Article testArticle;
	private String testTitle = "test";
	private String testText = "test";

	@Before
	public void init() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		articleDao = new ArticleDao(em);
		testArticle = new Article();
		testArticle.setTitle(testTitle);
		testArticle.setText(testText);
		articleDao.create(testArticle);
	}

	@After
	public void delete() {
		articleDao.delete(testArticle);
	}

	@Test
	public void testListAll() {
		assertEquals(1, articleDao.listAll().size());
		Article article2 = new Article();
		articleDao.create(article2);
		assertEquals(2, articleDao.listAll().size());
		articleDao.delete(article2);
	}

	@Test
	public void testSearch() {
		Article article = new Article();
		article.setText("egy kis alma");
		articleDao.create(article);
		assertEquals(1, articleDao.searchInText("alma").size());
		assertEquals(0, articleDao.searchInText("szilva").size());
		articleDao.delete(article);
	}

	@Test
	public void testListByAuthor() {
		Account author = new Account();
		AccountDao accountDao = new AccountDao(em);
		accountDao.create(author);
		Article article = new Article();
		article.setAuthor(author);
		articleDao.create(article);
		assertEquals(1, articleDao.listByAuthor(author).size());
		articleDao.delete(article);
		accountDao.delete(author);
	}

}
