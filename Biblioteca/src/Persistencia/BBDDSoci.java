package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Domini.Prestec;
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
			throw new Exception("El soci ja té 3 prèstecs i no pot fer més fins que retorni algun");
		}
		
		query = em.createNativeQuery("select * from prestec where DATA_REAL_RETORN IS NULL and soci=? and exemplar=?");
		query.setParameter(1, soci);
		query.setParameter(2, exemplar);
		if(query.getResultList().size()>0){
			throw new Exception("El soci ja té en prèstec un exemplar de la mateixa obra");
		}
		
		
		query = em.createNativeQuery("select estat from soci where DNI=?");
		query.setParameter(1, soci);
		if(query.getResultList().get(0).equals("Moros")){
			throw new Exception("El soci es morós i fins que deixi de ser-ho no pot demanar un altre prèstec");
		}		
		return true;
	}

	public void prestecAfegit(Prestec prestec) throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria().createEntityManager();
		Soci soci = prestec.getSoci();
		em.getTransaction().begin();
		soci.setEstat("AmbPrestec");
		em.getTransaction().commit();
	}

}
