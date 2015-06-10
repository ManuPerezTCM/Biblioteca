package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import Domini.Exemplar;
import Domini.Prestec;
import Domini.Soci;
import Domini.EstatsSoci.estatAbs;
import Domini.EstatsSoci.estatAmbPrestec;
import Domini.EstatsSoci.estatMoros;
import Domini.EstatsSoci.estatSensePrestec;

public class BBDDSoci {

	private BBDDPrestec bbddPrestec;
	
	public BBDDSoci(){
		this.bbddPrestec = new BBDDPrestec();
	}
	public Soci find(String soci) throws Exception {
		EntityManager em = ConnexioJPA.getInstancia().getFactoria()
				.createEntityManager();
		Soci retorn = em.find(Soci.class, soci);
		if(retorn==null){
			throw new Exception("Soci no trobat");
		}
		if(retorn.getDataBaixa() != null){ // aix� ha de controlar que no �s puguin declarar socis donats de baixa
			return null;
		}
		retorn.setEstatObj((estatAbs) Class.forName("Domini.EstatsSoci.estat"+retorn.getEstatString()).newInstance());
		retorn.setPrestecs(this.bbddPrestec.findPrestecsSoci(retorn.getDni()));
		
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
				return true;
			}
		}
		return false;
	}
	public void actualitzarSoci(Soci soci) throws Exception {
		EntityManager em = null;
		try {
			em = ConnexioJPA.getInstancia().getFactoria().createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.merge(soci);
			tx.commit();
		} catch (Exception e) {
			throw new Exception("Error al inserir el pr�stec: "
					+ e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}
		
	}

}
