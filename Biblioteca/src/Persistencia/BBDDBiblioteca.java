package Persistencia;

import javax.persistence.EntityManager;

import CapaAplicacio.controladorLogin;
import Domini.Biblioteca;

public class BBDDBiblioteca {

	public Biblioteca find() throws Exception {
		try {
			EntityManager em = ConnexioJPA.getInstancia().getFactoria()
					.createEntityManager();
			Biblioteca retorn = em.find(Biblioteca.class, "1");
			em.close();
			if(retorn == null){
				throw new Exception("No hi ha cap Biblioteca.");
			}else{
			return retorn;
			}
		} catch (Exception e) {
			throw new Exception("Error al trobar la biblioteca: "+e.getMessage());
		}
	}

}
