package Utility.com;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Utility {
	
private static EntityManagerFactory  emf;
	
	
	static {
		emf = Persistence.createEntityManagerFactory("myJPAUnit");
	}
	
	public static EntityManager provideEntityManager() {
		
		return emf.createEntityManager();
	}

}


