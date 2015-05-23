package org.jakabhegy.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.jakabhegy.pojo.Article;

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

	public List<Article> listAll(String tableName) {

		Query query = this.entityManager.createQuery("Select t from " + tableName + " t"); //$NON-NLS-1$ //$NON-NLS-2$
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

}