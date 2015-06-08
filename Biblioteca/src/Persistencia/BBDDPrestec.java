package Persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Domini.Exemplar;
import Domini.Prestec;
import Domini.Soci;

public class BBDDPrestec {

	public void afegirPrestec(Prestec prestec) throws Exception {
		EntityManager em = null;
		try {
			em = ConnexioJPA.getInstancia().getFactoria().createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(prestec);
			tx.commit();
		} catch (Exception e) {
			throw new Exception("Error al inserir el pr�stec: "
					+ e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}
	}

	public Prestec find(String prestec) throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria()
				.createEntityManager();
		Prestec retorn = em.find(Prestec.class, prestec);
		return retorn;
	}

	/**
	 * @Autor Xavi, Mauricio @ Retorna tots els prestecs actius del soci (els
	 *        que no han estat retornats i els que tenen un pagament pendent)
	 */
	public ArrayList<Prestec> findPrestecsSoci(Soci retorn) throws Exception {
		EntityManager em = null;
		ArrayList<Prestec> prestecs = new ArrayList<Prestec>();
		try {
			em = ConnexioJPA.getInstancia().getFactoria().createEntityManager();

			TypedQuery<Prestec> exemplars = em
					.createNamedQuery(
							"SELECT * FROM prestecs WHERE soci=? AND DATA_REAL_RETORN IS NULL",
							Prestec.class);
			exemplars.setParameter(1, retorn.getDni());
			prestecs.addAll(exemplars.getResultList());

			exemplars = em
					.createNamedQuery(
							"SELECT * FROM prestec WHERE soci=? AND DATA_REAL_RETORN IS NOT NULL AND DATA_PAGAMENT IS NULL",
							Prestec.class);
			exemplars.setParameter(1, retorn.getDni());
			prestecs.addAll(exemplars.getResultList());

			return prestecs;

		} catch (Exception e) {
			throw new Exception("Error al recuperar prestec: " + e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}
	}

	/**
	 * @Autor Manu
	 * @retun Retorna arraylist de prestecs que estan per pagar.
	 */
	public ArrayList<Prestec> findPrestecsPerPagar(String soci)
			throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria()
				.createEntityManager();
		ArrayList<Prestec> retorn = new ArrayList<Prestec>();
		Soci SociRetornat = em.find(Soci.class, soci);
		if (SociRetornat == null) {
			throw new Exception("Soci no trobat");
		}
		Query queryTornar = em
				.createNativeQuery(
						"SELECT * FROM prestec WHERE soci=? AND IMPORT_RETARD IS NOT NULL",
						Prestec.class);
		queryTornar.setParameter(1, soci);
		retorn.addAll(queryTornar.getResultList());
		em.close();
		return retorn;
	}

	/**
	 * @Autor Mauricio @ Endarrereix la data max de retorn d'un prestec p el
	 *        nombre de dies desitjats, comprobant que siguin positius
	 */
	public void endarrerirPrestec(Prestec p, int dies) throws Exception {
		if (dies < 1) {
			throw new Exception("Els dies han de ser superior o iguals a 1");
		}
		EntityManager em = ConnexioJPA.getInstancia().getFactoria()
				.createEntityManager();
		Query query = em
				.createNativeQuery("UPDATE prestec set DATA_MAX_RETORN=DATA_MAX_RETORN+? where soci=? and exemplar=?");
		query.setParameter(1, dies);
		query.setParameter(2, p.getSoci());
		query.setParameter(3, p.getExemplar());

	}

	public Prestec getPrestecPerEndarrerir(String obra, Soci objSoci)
			throws Exception {
		if (obra == null) {
			throw new Exception("Has de seleccionar una obra");
		}
		EntityManager em = ConnexioJPA.getInstancia().getFactoria()
				.createEntityManager();
		Prestec retorn = new Prestec();
		Query queryTornarPrestec = em
				.createNativeQuery("SELECT * FROM prestec join exemplar on prestec.exemplar="
						+ "exemplar.REGISTRE WHERE prestec.soci=? AND exemplar.obra=?");
		queryTornarPrestec.setParameter(1, objSoci.getDni());
		queryTornarPrestec.setParameter(2, obra);
		retorn = (Prestec) queryTornarPrestec.getResultList().get(0);
		em.close();
		return retorn;
	}

	public Prestec find(Exemplar exemplar) throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria()
				.createEntityManager();
		TypedQuery<Prestec> query = em
				.createNamedQuery(
						"SELECT * FROM prestec WHERE exemplar=? AND DATA_REAL_RETORN is NULL",
						Prestec.class);
		query.setParameter(1, exemplar.getRegistre());
		Prestec p = query.getSingleResult();
		em.close();
		return p;
	}

	public void retornarPrestec(Prestec prestec) throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria()
				.createEntityManager();
		em.getTransaction().begin();
		em.merge(prestec);
		em.getTransaction().commit();
	}

	public void pagarPrestec(Prestec prestec) {
		
	}

}
