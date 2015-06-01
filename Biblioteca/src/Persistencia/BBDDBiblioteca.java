package Persistencia;

import javax.persistence.EntityManager;

import CapaAplicacio.controladorLogin;
import Domini.Biblioteca;
import Domini.Bibliotecari;
import Domini.BibliotecariPK;

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

	public Bibliotecari findBibliotecari(String nom, String contrasenya) throws Exception {
		try {
			EntityManager em = ConnexioJPA.getInstancia().getFactoria()
					.createEntityManager();
			BibliotecariPK pk = new BibliotecariPK();
			pk.setContrasenya(contrasenya);
			pk.setNom(nom);
			Bibliotecari retorn = em.find(Bibliotecari.class, pk);
			em.close();
			if(retorn == null){
				throw new Exception("No hi ha cap bibliotecari amb aquest Nom i aquesta Contrasenya.");
			}else{
			return retorn;
			}
		} catch (Exception e) {
			throw new Exception("Error al trobar bibliotecari: "+e.getMessage());
		}
		
	}

}
