package com.authorizationsystem.dao.impl;

import com.authorizationsystem.dao.UserDao;
import com.authorizationsystem.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Inserts a new record
     * <p></p>
     *
     * @param user instance of entity class
     */
    @Override
    public void create(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    /**
     * Updates a record
     * <p></p>
     *
     * @param user instance of entity class
     */
    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    /**
     * Deletes a record
     * <p></p>
     *
     * @param user instance of entity class
     */
    @Override
    public void remove(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(user);
    }

    /**
     * Selects all records
     * <p></p>
     *
     * @return list of records
     */
    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<User> query = session.getCriteriaBuilder()
                .createQuery(User.class);
        query.from(User.class);
        return session.createQuery(query).getResultList();
    }

    /**
     * Searches for a record by login
     *
     * @param login login
     * @return instance of User
     */
    @Override
    @SuppressWarnings("unchecked")
    public User findByLogin(String login) {
        String findByLoginQuery = "FROM User u WHERE u.login = :login";
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery(findByLoginQuery);
        query.setParameter("login", login);
        return query.uniqueResult();
    }

    /**
     * Searches for a record by email
     *
     * @param email email
     * @return instance of User
     */
    @Override
    @SuppressWarnings("unchecked")
    public User findByEmail(String email) {
        String findByEmailQuery = "FROM User u WHERE u.email = :email";
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery(findByEmailQuery);
        query.setParameter("email", email);
        return query.uniqueResult();
    }

    /**
     * Searches for a record by id
     * <p></p>
     *
     * @param id id
     * @return instance of entity class
     */
    @Override
    public User findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }
}
