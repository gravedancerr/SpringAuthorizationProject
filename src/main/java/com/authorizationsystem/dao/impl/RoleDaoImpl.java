package com.authorizationsystem.dao.impl;

import com.authorizationsystem.dao.RoleDao;
import com.authorizationsystem.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Inserts a new record
     * <p></p>
     *
     * @param role instance of entity class
     */
    @Override
    public void create(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(role);
    }

    /**
     * Updates a record
     * <p></p>
     *
     * @param role instance of entity class
     */
    @Override
    public void update(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(role);
    }

    /**
     * Deletes a record
     * <p></p>
     *
     * @param role instance of entity class
     */
    @Override
    public void remove(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(role);
    }

    /**
     * Searches for a record by name
     * <p></p>
     *
     * @param name role name
     * @return role
     */
    @Override
    @SuppressWarnings("unchecked")
    public Role findByName(String name) {
        String findByEmailQuery = "FROM Role r WHERE r.name = :name";
        Session session = sessionFactory.getCurrentSession();
        Query<Role> query = session.createQuery(findByEmailQuery);
        query.setParameter("name", name);
        return query.uniqueResult();
    }

    /**
     * Selects all records
     * <p></p>
     *
     * @return list of records
     */
    @Override
    public List<Role> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<Role> query = session.getCriteriaBuilder()
                .createQuery(Role.class);
        query.from(Role.class);
        return session.createQuery(query).getResultList();
    }

    /**
     * Searches for a record by id
     * <p></p>
     *
     * @param id id
     * @return instance of entity class
     */
    @Override
    public Role findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Role.class, id);
    }
}
