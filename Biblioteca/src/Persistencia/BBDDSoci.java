package Persistencia;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Domini.Soci;

public class BBDDSoci {

	public Soci find(String soci) throws Exception {
		System.out.println("a");
		EntityManager em = ConnexioJPA.getInstancia().getFactoria().createEntityManager();
		System.out.println("b");
		Soci retorn = em.find(Soci.class, soci);
		System.out.println("c");
		em.close();
		return retorn;
	}
	
	public boolean potDemanarPrestec(String soci, String exemplar)throws Exception{
		if(find(soci)==null){
			return false;
		}
		EntityManager em = ConnexioJPA.getInstancia().getFactoria().createEntityManager();
		Query q = em.createNativeQuery("select * from prestec where DATA_REAL_RETORN IS NULL and soci=:soci");
		q.setParameter("soci", soci);
		if(q.getResultList().size()>2){
			return false;
		}
		q = em.createNativeQuery("select * from prestec where DATA_REAL_RETORN IS NULL and soci=:soci and exemplar=:exemplar");
		q.setParameter("exemplar", exemplar);
		if(q.getResultList().size()>0){
			return false;
		}
		q = em.createNativeQuery("select estat from soci where DNI=:soci");
		q.setParameter("soci", soci);
		if(q.getResultList().get(0).equals("Moros")){
			return false;
		}		
		return true;
	}

}
