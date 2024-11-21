package DaoImpl;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;



import Dao.UserDao;
import Model.User;
import Utility.com.Utility;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean signup(User user) throws SQLException {
		
		EntityManager em = Utility.provideEntityManager();

		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		
		return false;

	}
		
	

	@Override
	public boolean login(String username, String password) throws SQLException {
		
		EntityManager em = Utility.provideEntityManager();
		
		
	        Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password");

	        query.setParameter("username", username);
	        query.setParameter("password", password);

	        query.getSingleResult();
	        return true;
	    
	}

	
}
