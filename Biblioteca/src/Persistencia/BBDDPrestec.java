package Persistencia;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Domini.Prestec;
import Domini.Soci;

public class BBDDPrestec {

	
	public void afegirPrestec(Prestec prestec) throws Exception {
		EntityManager em = null;
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
	
	public Prestec find(String prestec) throws Exception{
		EntityManager em = ConnexioJPA.getInstancia().getFactoria()
				.createEntityManager();
		Prestec retorn = em.find(Prestec.class, prestec);		
		return retorn;
	}
	/**
	 * @Autor Xavi, Mauricio
	 * @ Retorna tots els prestecs actius del soci (els que no han estat retornats i els que tenen un pagament pendent)
	 */
	public ArrayList<Prestec> findPrestecsSoci(Soci retorn) throws Exception {
		EntityManager em = null;
		ArrayList<Prestec> prestecs = new ArrayList<Prestec>();
		try{
			em = ConnexioJPA.getInstancia().getFactoria().createEntityManager();
			
			TypedQuery<Prestec> exemplars = em.createNamedQuery("SELECT * FROM prestecs WHERE soci=? AND DATA_REAL_RETORN IS NULL",Prestec.class);
			exemplars.setParameter(1, retorn.getDni());
			prestecs.addAll(exemplars.getResultList());
			
			exemplars = em.createNamedQuery("SELECT * FROM prestec WHERE soci=? AND DATA_REAL_RETORN IS NOT NULL AND DATA_PAGAMENT IS NULL",Prestec.class);
			exemplars.setParameter(1, retorn.getDni());
			prestecs.addAll(exemplars.getResultList());
			
			return prestecs;

		}catch (Exception e){
			throw new Exception("Error al recuperar prestec: "+e.getMessage());
		}finally{
			if(em != null)
				em.close();
		}
	}
	
	/**
	 * @Autor Manu
	 * @ Retorna arraylist de prestecs que estan per pagar.
	 */
	public ArrayList<Prestec> findPrestecsPerPagar(String soci) throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria()
				.createEntityManager();
		ArrayList<Prestec> retorn = new ArrayList<Prestec>();
		Soci SociRetornat = em.find(Soci.class, soci);
		if(SociRetornat==null){
			throw new Exception("Soci no trobat");
		}
		Query queryTornar = em
				.createNativeQuery("SELECT * FROM prestec WHERE soci=? AND IMPORT_RETARD IS NOT NULL");
		queryTornar.setParameter(1, soci);
		retorn = (ArrayList<Prestec>) queryTornar.getResultList();
		em.close();
		return retorn;
	}
		
}
