package Persistencia;

import javax.persistence.EntityManager;

import Domini.Soci;

public class BBDDSoci {

	public Soci find(String soci) throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria().createEntityManager();
		Soci retorn = em.find(Soci.class, soci);
		em.close();
		return retorn;
	}

}
