package org.jakabhegy.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jakabhegy.pojo.Account;

public class AccountDao {
	private EntityManager entityManager;

	public AccountDao(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public Account create(Account account) {
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(account);
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
		}
		return account;
	}

	public Account update(Account account) {
		try {
			this.entityManager.getTransaction().begin();
			Account result = this.entityManager.merge(account);
			this.entityManager.getTransaction().commit();
			return result;
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			return account;
		}
	}

	public void delete(Account account) {
		try {
			this.entityManager.getTransaction().begin();
			Account mgd = this.entityManager.merge(account);
			this.entityManager.remove(mgd);
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
		}
	}

	public List<Account> listAll(String tableName) {

		Query query = this.entityManager
				.createQuery("Select t from " + tableName + " t"); //$NON-NLS-1$ //$NON-NLS-2$
		@SuppressWarnings("unchecked")
		List<Account> dataList = query.getResultList();

		return dataList;
	}
	

	
	public Account listOne(int id) {

		//	String sqlCommand = "select s from Student s where s.eha = '" + eha + "'"; //$NON-NLS-1$
		String sqlCommand = String.format(
				"select s from Account s where s.id = '%s'", id); //$NON-NLS-1$
		Query q = entityManager.createQuery(sqlCommand);

		try {
			return (Account) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public Account listOne(String username, String password) {
		String sqlCommand = String
				.format("select s from Account s where s.username = '%s' and s.password='%s'", username, password); //$NON-NLS-1$
		Query q = entityManager.createQuery(sqlCommand);

		try {
			return (Account) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public boolean usernameIsUsed(String username) {
		String sqlCommand = String.format(
				"select count(s.username) from Account s where s.username = '%s'", username); //$NON-NLS-1$
		Query q = entityManager.createQuery(sqlCommand);
		long count=(long) q.getSingleResult();
		System.out.println(count+" db "+username);
		return count>0;
	}

}
