package services;

import dao.UserDaoImpl;
import entities.User;
import java.util.List;

public class UserImpl implements IUser {
    /* Fields */
    UserDaoImpl userDao;
    
    /* Constructor */
    public UserImpl(){
        userDao = new UserDaoImpl();
    }

    /* Methods */
    @Override
    public User findById(int id) {
        if (id <= 0) {
            return null;
        } else {
            return userDao.findById(id);
        }
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean updateById(int id, User user) {
        if (id <= 0 || user == null) {
            return false;
        } else {
            return userDao.updateById(id, user);
        }
    }

    @Override
    public boolean save(User user) {
        if (user == null){
            return false;
        }
        else {
            return userDao.save(user);
        }
    }

    @Override
    public boolean deleteById(int id) {
        if (id <= 0) {
            return false;
        } else {
            return userDao.deleteById(id);
        }
    }
}
