package org.jakabhegy.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jakabhegy.pojo.Account;
import org.jakabhegy.pojo.Article;
import org.jakabhegy.pojo.Message;

public class ArticleDao {
	private EntityManager entityManager;

	public ArticleDao(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public Article create(Article article) {
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(article);
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
		}
		return article;
	}

	public Article update(Article article) {
		try {
			this.entityManager.getTransaction().begin();
			Article result = this.entityManager.merge(article);
			this.entityManager.getTransaction().commit();
			return result;
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			return article;
		}
	}

	public void delete(Article article) {
		try {
			this.entityManager.getTransaction().begin();
			Article mgd = this.entityManager.merge(article);
			this.entityManager.remove(mgd);
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
		}
	}

	public List<Article> listAll() {

		Query query = this.entityManager
				.createQuery("Select t from Article t"); //$NON-NLS-1$ //$NON-NLS-2$
		@SuppressWarnings("unchecked")
		List<Article> dataList = query.getResultList();

		return dataList;
	}

	public Article listOne(int id) {

		//	String sqlCommand = "select s from Student s where s.eha = '" + eha + "'"; //$NON-NLS-1$
		String sqlCommand = String.format(
				"select s from Article s where s.id = '%s'", id); //$NON-NLS-1$
		Query q = entityManager.createQuery(sqlCommand);

		try {
			return (Article) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Article> listByAuthor(Account author) {
		String sqlCommand = String.format(
				"select s from Article s where s.author.id = '%s'", author.getId()); //$NON-NLS-1$
		Query q = entityManager.createQuery(sqlCommand);
		
		List<Article> dataList = q.getResultList();

		return dataList;
	}

	public List<Article> searchInText(String text) {
		String command = "Select t from Article t where upper(t.text) like :text";
		Query query = this.entityManager.createQuery(command);
		query.setParameter("text", "%" + text.toUpperCase() + "%");

		List<Article> list = query.getResultList();
		return list;
	}

}
