package Persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import Domini.Prestec;

public class BBDDPrestec {

	
	public void afegirPrestec(Prestec prestec) throws Exception {
		EntityManager em = null;
		BBDDSoci bbddSoci = new BBDDSoci();
		try{
			em = ConnexioJPA.getInstancia().getFactoria().createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(prestec);
			tx.commit();
		}catch (Exception e){
			throw new Exception("Error al inserir el pr�stec: "+e.getMessage());
		}finally{
			if(em != null)
				em.close();
		}
	}
	
		
}
