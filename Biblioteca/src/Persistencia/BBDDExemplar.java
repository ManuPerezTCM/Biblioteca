package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Domini.Exemplar;
import Domini.Soci;

public class BBDDExemplar {

	public Exemplar find(Long exemplar) throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria().createEntityManager();
		Exemplar retorn = em.find(Exemplar.class, exemplar);
		em.close();
		return retorn;
	}
	
	public boolean disponible(Exemplar exemplar) throws Exception{
		EntityManager em = ConnexioJPA.getInstancia().getFactoria().createEntityManager();
		//mirem que no hi hagi cap prestec fet amb aquest exemplar
		Query q = em.createNativeQuery("select * from prestec where exemplar=? and DATA_REAL_RETORN IS NULL");
		q.setParameter(1, exemplar.getRegistre());
		if( q.getResultList().size()>0){
			throw new Exception("L'exemplar seleccionat ja està siguent prestat.");
		}
		
		//mirem que el exemplar no estigui de baixa
		q = em.createNativeQuery("select * from exemplar where registre=? and DATA_BAIXA IS NULL");
		q.setParameter(1, exemplar.getRegistre());
		if( q.getResultList().size()>0){
			return true;
		}	
		else{
			throw new Exception("L'exemplar seleccionat no està disponible");		
		}
		
	}

}
