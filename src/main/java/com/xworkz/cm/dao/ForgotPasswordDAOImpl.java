package com.xworkz.cm.dao;

import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xworkz.cm.entity.RegisterEntity;
import com.xworkz.cm.exception.ForgotPasswordException;

@Component
public class ForgotPasswordDAOImpl implements ForgotPasswordDAO {

	@Autowired
	private SessionFactory factory;

	public ForgotPasswordDAOImpl() {
		super();
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public RegisterEntity getEmail(String email) {
		RegisterEntity registerEntity = null;
		Session session = null;
		try {
			session = factory.openSession();
			Query namedQuery = session.getNamedQuery("fetchEmail");
			namedQuery.setParameter("email", email);
			Object object = namedQuery.uniqueResult();
			registerEntity = (RegisterEntity) object;
			System.out.println("data found" + registerEntity);
			if (registerEntity != null)
				return registerEntity;

		} catch (ForgotPasswordException e) {
			e.printStackTrace();
			throw new ForgotPasswordException("problem in getting email from db");
		} finally {
			if (Objects.nonNull(session)) {
				session.close();
				System.out.println("Session is closed");

			} else {
				System.out.println("Session is not closed");
			}
		}
		return registerEntity;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int resetPasswordAndLoginCount(int noOfLoginAttempt, String resetPassword, int rID) {
		Session session = null;
		int numberOfEntities = 0;
		try {
			session = factory.openSession();
			session.beginTransaction();
			Query namedQuery = session.getNamedQuery("resetPasswordAndLoginCount");
			namedQuery.setParameter("count", noOfLoginAttempt);
			namedQuery.setParameter("pwd", resetPassword);
			namedQuery.setParameter("rid", rID);
			numberOfEntities = namedQuery.executeUpdate();
			session.getTransaction().commit();
			if (numberOfEntities == 1) {
				return numberOfEntities;
			}

		} catch (ForgotPasswordException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw new ForgotPasswordException("problem in updating password and login count");

		} finally {
			if (Objects.nonNull(session)) {
				session.close();
				System.out.println("Session is closed");

			} else {
				System.out.println("Session is not closed");
			}
		}

		return numberOfEntities;
	}

}
