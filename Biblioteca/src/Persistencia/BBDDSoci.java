package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Domini.Exemplar;
import Domini.Prestec;
import Domini.Soci;
import Domini.EstatsSoci.estatAmbPrestec;
import Domini.EstatsSoci.estatMoros;
import Domini.EstatsSoci.estatSensePrestec;

public class BBDDSoci {

	public Soci find(String soci) throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria()
				.createEntityManager();
		Soci retorn = em.find(Soci.class, soci);
		if (retorn.getEstatString().equals("SensePrestec")) {
			retorn.setEstatObj(new estatSensePrestec());
		} else {
			if (retorn.getEstatString().equals("AmbPrestec")) {
				retorn.setEstatObj(new estatAmbPrestec());
			} else {
				retorn.setEstatObj(new estatMoros());
			}
		}
		Query queryTornar = em
				.createNativeQuery("SELECT * FROM prestec WHERE soci=? AND DATA_REAL_RETORN IS NULL");
		queryTornar.setParameter(1, soci);
		int perTornar = queryTornar.getResultList().size();
		
		Query queryPagar = em
				.createNativeQuery("SELECT * FROM prestec WHERE soci=? AND DATA_REAL_RETORN IS NOT NULL AND DATA_PAGAMENT IS NULL");
		queryPagar.setParameter(1, soci);
		int perPagar = queryPagar.getResultList().size();
		retorn.setPrestecsPerPagar(perPagar);
		retorn.setPrestecsPerTornar(perTornar);
		em.close();
		return retorn;
	}

	public boolean potDemanarPrestec(String soci, String exemplar)
			throws Exception {
		if (find(soci) == null) {
			return false;
		}

		EntityManager em = ConnexioJPA.getInstancia().getFactoria()
				.createEntityManager();
		Query query = em
				.createNativeQuery("SELECT DATA_PRESTEC FROM prestec where DATA_REAL_RETORN IS NULL and soci=?");
		query.setParameter(1, soci);
		List<String> obs = query.getResultList();
		if (obs.size() > 2) {
			throw new Exception(
					"El soci ja t� 3 pr�stecs i no pot fer m�s fins que retorni algun");
		}

		query = em
				.createNativeQuery("select * from prestec where DATA_REAL_RETORN IS NULL and soci=? and exemplar=?");
		query.setParameter(1, soci);
		query.setParameter(2, exemplar);
		if (query.getResultList().size() > 0) {
			throw new Exception(
					"El soci ja t� en pr�stec un exemplar de la mateixa obra");
		}

		query = em.createNativeQuery("select estat from soci where DNI=?");
		query.setParameter(1, soci);
		if (query.getResultList().get(0).equals("Moros")) {
			throw new Exception(
					"El soci es mor�s i fins que deixi de ser-ho no pot demanar un altre pr�stec");
		}
		return true;
	}

	public void prestecAfegit(Prestec prestec) throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria()
				.createEntityManager();
		Soci soci = prestec.getSoci();
		em.getTransaction().begin();
		em.merge(soci);
		em.getTransaction().commit();
	}

	public boolean sociTeObra(Exemplar exemplarObj, Soci sociObj) throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria()
				.createEntityManager();
		Query query;
		
		query = em
				.createNativeQuery("select e.obra from prestec p join exemplar e on p.exemplar = e.registre where DATA_REAL_RETORN IS NULL and soci=? and exemplar=?");
		query.setParameter(1, sociObj.getDni());
		query.setParameter(2, exemplarObj.getRegistre());
		
		List<String> lista = query.getResultList();		
		for(String obra : lista ){
			if(obra.equals(exemplarObj.getObra().getIsbn())){
				return false;
			}
		}
		return true;
	}

}
