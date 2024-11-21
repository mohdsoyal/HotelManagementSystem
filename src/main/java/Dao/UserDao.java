package Dao;

import java.sql.SQLException;

import Model.User;

public interface UserDao {

	 public boolean signup(User user) throws SQLException;
	 
	 public boolean login(String username, String password) throws SQLException;
}
