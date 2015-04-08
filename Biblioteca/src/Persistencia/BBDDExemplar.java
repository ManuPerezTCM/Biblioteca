package Persistencia;

import javax.persistence.EntityManager;

import Domini.Exemplar;
import Domini.Soci;

public class BBDDExemplar {

	public Exemplar find(String exemplar) throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria().createEntityManager();
		Exemplar retorn = em.find(Exemplar.class, exemplar);
		em.close();
		return retorn;
	}

}
