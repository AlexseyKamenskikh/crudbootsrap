package com.example.crud.dao;

import com.example.crud.model.Role;
import com.example.crud.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT user FROM User user").getResultList();
    }

    @Override
    public void save(User user) {

        entityManager.persist(user);
    }

    @Override
    public User findById(Long id) {

        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteById(Long id) {
        String querry = "DELETE FROM User U WHERE U.id = " + id;
        entityManager.createQuery(querry).executeUpdate();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findByUsername(String username) {
        Query query = entityManager.createQuery("select U from User U WHERE U.name = :userNameParam");
        query.setParameter("userNameParam", username);
        User tmmp = (User) query.getSingleResult();
        return tmmp;
    }

    @Override
    public User findByEmail(String email) {
        Query query = entityManager.createQuery("select U from User U WHERE U.email = :emailParam");
        query.setParameter("emailParam", email);
        User tmp = (User) query.getSingleResult();
        return tmp;
    }

    @Override
    public List<Role> allRoles() {
        return entityManager.createQuery("SELECT role FROM Role role").getResultList();
    }
}