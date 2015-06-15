package org.jakabhegy.test;

import static org.junit.Assert.*;

import java.util.Arrays;

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
		assertEquals(Arrays.asList(testArticle), articleDao.listAll());
		Article article2 = new Article();
		articleDao.create(article2);
		assertEquals(Arrays.asList(testArticle,article2), articleDao.listAll());
		articleDao.delete(article2);
	}

	@Test
	public void testSearch() {
		Article article = new Article();
		article.setText("egy kis alma");
		articleDao.create(article);
		assertEquals(Arrays.asList(article), articleDao.searchInText("alma"));
		assertEquals(Arrays.asList(), articleDao.searchInText("szilva"));
		articleDao.delete(article);
	}

	@Test
	public void testDelete() {
		Article article = new Article();
		assertEquals(Arrays.asList(testArticle),articleDao.listAll());
		articleDao.create(article);
		assertEquals(Arrays.asList(testArticle,article),articleDao.listAll());
		articleDao.delete(article);
		assertEquals(Arrays.asList(testArticle),articleDao.listAll());
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
		assertEquals(author, articleDao.listByAuthor(author).get(0).getAuthor());
		article.deleteAuthor();
		articleDao.delete(article);
		accountDao.delete(author);
	}

}
