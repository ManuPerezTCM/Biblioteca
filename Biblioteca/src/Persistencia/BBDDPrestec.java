package Persistencia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
		System.out.println(prestec.getSoci().getDni());
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
	public ArrayList<Prestec> findPrestecsSoci(String soci) throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria()
				.createEntityManager();
		ArrayList<Prestec> retorn = new ArrayList<Prestec>();
		Soci SociRetornat = em.find(Soci.class, soci);
		if (SociRetornat == null) {
			throw new Exception("Soci no trobat");
		}
		Query queryTornar = em
				.createNativeQuery(
						"SELECT * FROM prestec WHERE soci=? AND  DATA_REAL_RETORN IS NULL",
						Prestec.class);
		queryTornar.setParameter(1, soci);
		retorn.addAll(queryTornar.getResultList());
		em.close();
		return retorn;
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
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(p.getDataMaxRetorn()); 
		c.add(Calendar.DATE, dies);
		p.setDataMaxRetorn(c.getTime());
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();

	}

	public Prestec getPrestecPerEndarrerir(String obra, String dniSoci)
			throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria()
				.createEntityManager();
		Prestec retorn = new Prestec();
		Query queryTornar = em
				.createNativeQuery(
						"SELECT * FROM prestec join exemplar on prestec.exemplar="
								+ "exemplar.REGISTRE join obra on exemplar.obra=obra.isbn WHERE prestec.soci=? AND obra.titol=?",
						Prestec.class);
		queryTornar.setParameter(1, dniSoci);
		queryTornar.setParameter(2, obra);
		retorn=(Prestec) queryTornar.getResultList().get(0);
		em.close();
		return retorn;
	}

	public Prestec find(Exemplar exemplar) throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria()
				.createEntityManager();
		Query query = em
				.createNativeQuery(
						"SELECT * FROM prestec WHERE exemplar=? AND DATA_REAL_RETORN is NULL",
						Prestec.class);
		query.setParameter(1, exemplar.getRegistre());
		
//		Query query = em
//			    .createNativeQuery(
//			      "SELECT * FROM prestec WHERE exemplar='"+exemplar.getRegistre()+"' AND DATA_REAL_RETORN is NULL",
//			      Prestec.class);
		
		Prestec p = (Prestec) query.getSingleResult();
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

	public void pagarPrestec(Prestec prestec) throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria()
				.createEntityManager();
//		Query queryImport = em
//				.createNativeQuery("UPDATE prestec set IMPORT_RETARD=0 where soci=? and exemplar=?");
//		queryImport.setParameter(1, prestec.getSoci().getDni());
//		queryImport.setParameter(2, prestec.getExemplar().getRegistre());
//		Query queryData = em
//				.createNativeQuery("UPDATE prestec set DATA_PAGAMENT=? where soci=? and exemplar=?");
//		queryData.setParameter(1, new Date());
//		queryData.setParameter(2, prestec.getSoci().getDni());
//		queryData.setParameter(1, prestec.getExemplar().getRegistre());
		prestec.setDataPagament(new Date());
		prestec.setImportRetard(BigDecimal.valueOf(0));
		em.getTransaction().begin();
		em.merge(prestec);
		em.getTransaction().commit();
	}

}
