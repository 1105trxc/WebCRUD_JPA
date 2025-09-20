package k8Edu.dao;

import java.util.List;
import jakarta.persistence.*;
import k8Edu.entity.Category;

public class CategoryDaoImpl implements CategoryDao {
    private EntityManager em;

    public CategoryDaoImpl() {
        em = Persistence.createEntityManagerFactory("your-persistence-unit").createEntityManager();
    }

    @Override
    public void insert(Category category) {
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
    }

    @Override
    public void edit(Category category) {
        em.getTransaction().begin();
        em.merge(category);
        em.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        em.getTransaction().begin();
        Category category = em.find(Category.class, id);
        if (category != null) {
            em.remove(category);
        }
        em.getTransaction().commit();
    }

    @Override
    public Category get(int id) {
        return em.find(Category.class, id);
    }

    @Override
    public Category get(String name) {
        try {
            return em.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class)
                     .setParameter("name", name)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Category> getAll() {
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    @Override
    public List<Category> search(String keyword) {
        return em.createQuery("SELECT c FROM Category c WHERE c.name LIKE :kw", Category.class)
                 .setParameter("kw", "%" + keyword + "%")
                 .getResultList();
    }
}