package dao;

import entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDaoImpl implements IUserDao {
    /* Fields */
    private EntityManagerFactory emf;
    private EntityManager em;

    /* Constructor */
    public UserDaoImpl() {
        emf = Persistence.createEntityManagerFactory("UserServletPU");
        em = emf.createEntityManager();
    }

    /* Methods */
    @Override
    public User findById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        List<User> users = em.createQuery("Select a From User a", User.class).getResultList();
        return users;
    }

    @Override
    public boolean updateById(int id, User user) {
        User oldUser = em.find(User.class, id);
        if (oldUser != null) {
            em.getTransaction().begin();
            oldUser.setFirstName(user.getFirstName());
            oldUser.setLastName(user.getLastName());
            oldUser.setTel(user.getTel());
            oldUser.setEmail(user.getEmail());
            em.getTransaction().commit();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean save(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
            em.getTransaction().commit();
            return true;
        } else {
            return false;
        }
    }
}
