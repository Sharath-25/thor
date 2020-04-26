package com.xworkz.cm.dao;

import java.util.Objects;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xworkz.cm.entity.RegisterEntity;

@Component
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SessionFactory factory;

	public LoginDAOImpl() {
		super();
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public RegisterEntity getRegisterEntity(String email) {
		System.out.println("Invoked DAO()");
		Session session = null;
		RegisterEntity registerEntity = null;

		try {
			session = factory.openSession();
			Query namedQuery = session.getNamedQuery("fetchByEmail");
			namedQuery.setParameter("email", email);
			Object object = namedQuery.uniqueResult();
			registerEntity = (RegisterEntity) object;
			if (registerEntity != null) {
				System.out.println("data found");
				return registerEntity;

			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (Objects.nonNull(session)) {
				System.out.println("Session is closed");
				session.close();
			} else {
				System.out.println("Session is not closed");
			}
		}
		return registerEntity;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Integer updateLoginCount(Integer noOfLoginAttempt, Integer regID) {
		System.out.println("Invoked updateLoginCount()");
		Session session = null;
		Integer update = 0;
		try {
			session = factory.openSession();
			session.beginTransaction();
			Query namedQuery = session.getNamedQuery("updateLoginCount");
			namedQuery.setParameter("count", noOfLoginAttempt);
			namedQuery.setParameter("rid", regID);
			update = namedQuery.executeUpdate();
			session.getTransaction().commit();
			System.out.println("login count is updated");

		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (Objects.nonNull(session)) {
				System.out.println("Session is closed");
				session.close();
			} else {
				System.out.println("Session is not closed");
			}
		}
		return update;
	}

}
