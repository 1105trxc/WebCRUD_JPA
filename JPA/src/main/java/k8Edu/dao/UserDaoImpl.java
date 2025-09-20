package k8Edu.dao;

import jakarta.persistence.*;
import k8Edu.entity.User;

public class UserDaoImpl implements UserDao {
    private EntityManager em;

    public UserDaoImpl() {
        em = Persistence.createEntityManagerFactory("your-persistence-unit").createEntityManager();
    }

    @Override
    public User get(String username) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.userName = :username", User.class)
                     .setParameter("username", username)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean insert(User user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                       .setParameter("email", email)
                       .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean checkExistUsername(String username) {
        Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.userName = :username", Long.class)
                       .setParameter("username", username)
                       .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.phone = :phone", Long.class)
                       .setParameter("phone", phone)
                       .getSingleResult();
        return count > 0;
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                     .setParameter("email", email)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean updatePassword(Long userId, String newHashedPassword) {
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, userId);
            if (user != null) {
                user.setPassword(newHashedPassword);
                em.merge(user);
                em.getTransaction().commit();
                return true;
            }
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }
}