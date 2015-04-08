package Domini;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PRESTEC database table.
 * 
 */
@Entity
public class Prestec implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date data_prestec;
	
	@EmbeddedId
	private PrestecPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_MAX_RETORN")
	private Date dataMaxRetorn;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PAGAMENT")
	private Date dataPagament;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_REAL_RETORN")
	private Date dataRealRetorn;

	@Column(name="IMPORT_RETARD")
	private BigDecimal importRetard;

	//uni-directional many-to-one association to Exemplar
	@ManyToOne
	@JoinColumn(name="EXEMPLAR")
	private Exemplar exemplar;

	//uni-directional many-to-one association to Soci
	@ManyToOne
	@JoinColumn(name="SOCI")
	private Soci soci;

	public Prestec() {
	}

	public PrestecPK getId() {
		return this.id;
	}

	public void setId(PrestecPK id) {
		this.id = id;
	}

	public Date getDataMaxRetorn() {
		return this.dataMaxRetorn;
	}

	public void setDataMaxRetorn(Date dataMaxRetorn) {
		this.dataMaxRetorn = dataMaxRetorn;
	}

	public Date getDataPagament() {
		return this.dataPagament;
	}

	public void setDataPagament(Date dataPagament) {
		this.dataPagament = dataPagament;
	}

	public Date getDataRealRetorn() {
		return this.dataRealRetorn;
	}

	public void setDataRealRetorn(Date dataRealRetorn) {
		this.dataRealRetorn = dataRealRetorn;
	}

	public BigDecimal getImportRetard() {
		return this.importRetard;
	}

	public void setImportRetard(BigDecimal importRetard) {
		this.importRetard = importRetard;
	}

	public Exemplar getExemplar() {
		return this.exemplar;
	}

	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}

	public Soci getSoci() {
		return this.soci;
	}

	public void setSoci(Soci soci) {
		this.soci = soci;
	}
	
	public Date getDataPrestec(){
		return this.data_prestec;
	}
	
	public void setDataPrestec(Date dataPrestec){
		this.data_prestec = dataPrestec;
	}

}