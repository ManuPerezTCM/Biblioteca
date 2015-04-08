package Persistencia;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnexioJPA {
	
	private static ConnexioJPA connexio;
	private static EntityManagerFactory emf;
	
	private ConnexioJPA(String login, String password)throws Exception{
		try {
			Map<String,String> properties ;
			properties = new HashMap<String,String>();
			properties.put("javax.persistence.jdbc.user", login);
			properties.put("javax.persistence.jdbc.password", password);	
			System.out.println("1");
			emf = Persistence.createEntityManagerFactory("Biblioteca", properties);
			System.out.println("2");
			EntityManager em = emf.createEntityManager();
			System.out.println("3");
			em.close();
		} catch (Exception e) {
			throw new Exception("Error Connexio JPA 2: "+e.getMessage());
		}
	}
	
	public synchronized static void getInstancia(String login, String password) throws Exception{
		if(emf == null){
			connexio = new ConnexioJPA(login,password);
		}
	}
	
	public synchronized static ConnexioJPA getInstancia() throws Exception{
		if (connexio == null){
			throw new Exception("Error connexió JPA: Falta login i password.");
		}
		return connexio;
	}
	
	public EntityManagerFactory getFactoria() throws Exception{
		if (emf == null){
			throw new Exception("Error connexió JPA: Cal fer la connexió primer");
		}
		return emf;
	}
	
}
