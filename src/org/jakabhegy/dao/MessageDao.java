package org.jakabhegy.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jakabhegy.pojo.Message;

public class MessageDao {
	private EntityManager entityManager;

	public MessageDao(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public Message create(Message message) {
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(message);
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
		}
		return message;
	}

	public Message update(Message message) {
		try {
			this.entityManager.getTransaction().begin();
			Message result = this.entityManager.merge(message);
			this.entityManager.getTransaction().commit();
			return result;
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			return message;
		}
	}

	public void delete(Message message) {
		try {
			this.entityManager.getTransaction().begin();
			Message mgd = this.entityManager.merge(message);
			this.entityManager.remove(mgd);
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
		}
	}

	public List<Message> listAll(String tableName) {

		Query query = this.entityManager
				.createQuery("Select t from " + tableName + " t"); //$NON-NLS-1$ //$NON-NLS-2$
		@SuppressWarnings("unchecked")
		List<Message> dataList = query.getResultList();

		return dataList;
	}

	public Message listOne(int id) {

		//	String sqlCommand = "select s from Student s where s.eha = '" + eha + "'"; //$NON-NLS-1$
		String sqlCommand = String.format(
				"select s from Message s where s.id = '%s'", id); //$NON-NLS-1$
		Query q = entityManager.createQuery(sqlCommand);

		try {
			return (Message) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
