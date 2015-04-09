package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Domini.Soci;

public class BBDDSoci {

	public Soci find(String soci) throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria().createEntityManager();
		Soci retorn = em.find(Soci.class, soci);
		em.close();
		return retorn;
	}
	
	public boolean potDemanarPrestec(String soci, String exemplar)throws Exception{
		if(find(soci)==null){
			return false;
		}
		
		EntityManager em = ConnexioJPA.getInstancia().getFactoria().createEntityManager();
		Query query = em.createNativeQuery("SELECT DATA_PRESTEC FROM prestec where DATA_REAL_RETORN IS NULL and soci=?");
		query.setParameter(1, soci);
		List<String> obs = query.getResultList();
		if(obs.size()>2){
			return false;
		}
		
		query = em.createNativeQuery("select * from prestec where DATA_REAL_RETORN IS NULL and soci=? and exemplar=?");
		query.setParameter(1, soci);
		query.setParameter(2, exemplar);
		if(query.getResultList().size()>0){
			return false;
		}
		
		
		query = em.createNativeQuery("select estat from soci where DNI=?");
		query.setParameter(1, soci);
		if(query.getResultList().get(0).equals("Moros")){
			return false;
		}		
		return true;
	}

}
