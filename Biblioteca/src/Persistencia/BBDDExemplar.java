package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Domini.Exemplar;
import Domini.Soci;

public class BBDDExemplar {

	public Exemplar find(Long exemplar) throws Exception {
		System.out.println("5a");
		EntityManager em = ConnexioJPA.getInstancia().getFactoria().createEntityManager();
		System.out.println("5b");
		Exemplar retorn = em.find(Exemplar.class, exemplar);
		System.out.println("5c");
		em.close();
		return retorn;
	}
	
	public boolean estaEnPrestec(String exemplar) throws Exception{
		EntityManager em = ConnexioJPA.getInstancia().getFactoria().createEntityManager();
		Query q = em.createNativeQuery("select * from prestec where exemplar=:exemplar and DATA_REAL_RETORN IS NULL");
		q.setParameter("exemplar", exemplar);
		return q.getResultList().size()>0;
	}

}
