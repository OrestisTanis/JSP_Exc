package services;

import entities.User;
import java.util.List;

/**
 *
 * @author Walter
 */
public interface IUser {
    User findById(int id);
    
    List<User> findAll();
    
    boolean updateById(int id, User user);
    
    boolean save(User user);
    
    boolean deleteById(int id);
}
