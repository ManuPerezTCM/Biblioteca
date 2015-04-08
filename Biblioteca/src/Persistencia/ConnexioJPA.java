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
			Map<String,String> properties = new HashMap<String,String>();
			properties.put("javax.persistence.jdbc.user", login);
			properties.put("javax.persistence.jdbc.password", password);
			emf = Persistence.createEntityManagerFactory("Biblioteca", properties);
			EntityManager em = emf.createEntityManager();
			em.close();
		} catch (Exception e) {
			throw new Exception("Error Connexio JPA: "+e.getMessage());
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
