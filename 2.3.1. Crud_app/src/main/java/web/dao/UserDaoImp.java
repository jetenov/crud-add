package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    //@Autowired
    //private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        //sessionFactory.getCurrentSession().save(user);
        entityManager.persist(user);
    }

    @Override
    public List<User> UserList() {
        //TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
        //return query.getResultList();
        return entityManager.createQuery("SELECT e from User e").getResultList();
    }

    @Override
    public User getById(Long id) {
        //return sessionFactory.getCurrentSession().get(User.class, id);
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User user) {
        //sessionFactory.getCurrentSession().update(user);
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        //sessionFactory.getCurrentSession().delete(user);
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }


}