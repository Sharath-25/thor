package com.xworkz.cm.dao;

import java.util.Objects;

import org.apache.log4j.Logger;
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

	private static final Logger logger = Logger.getLogger(ForgotPasswordDAOImpl.class);

	public ForgotPasswordDAOImpl() {
		super();
		logger.info(this.getClass().getSimpleName() + "\t Object Created");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public RegisterEntity getEmail(String email) {
		logger.info("invoked ForgotPasswordDAOImpl getEmail()");
		RegisterEntity registerEntity = null;
		Session session = null;
		try {
			session = factory.openSession();
			Query namedQuery = session.getNamedQuery("fetchEmail");
			namedQuery.setParameter("email", email);
			Object object = namedQuery.uniqueResult();
			registerEntity = (RegisterEntity) object;
			logger.info("data found" + registerEntity);
			if (registerEntity != null)
				return registerEntity;

		} catch (ForgotPasswordException e) {
			logger.error(e.getMessage(), e);
			throw new ForgotPasswordException("problem in getting email from db");
		} finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.info("Session is closed");

			} else {
				logger.info("Session is not closed");
			}
		}
		return registerEntity;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int resetPasswordAndLoginCount(int noOfLoginAttempt, String resetPassword, int rID) {
		logger.info("invoked resetPasswordAndLoginCount()");
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
			session.getTransaction().rollback();
			logger.error(e.getMessage(), e);
			throw new ForgotPasswordException("problem in updating password and login count");

		} finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.info("Session is closed");

			} else {
				logger.info("Session is not closed");
			}
		}

		return numberOfEntities;
	}

}
