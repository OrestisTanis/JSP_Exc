/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.Database;
import java.util.List;
import models.User;

/**
 *
 * @author Walter
 */
public interface IUser {
    public int save(User user, Database db);
    
    public boolean updateById(int id, User user);
    
    public boolean deleteById(int id);
    
    public List<User> getAllUsers();
    
    public User findById(int id);
}
