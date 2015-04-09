package Domini;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EXEMPLAR database table.
 * 
 */
@Entity
@NamedQuery(name="Exemplar.findAll", query="SELECT e FROM Exemplar e")
public class Exemplar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXEMPLAR_REGISTRE_GENERATOR", sequenceName="INCREMENT_REGISTRE_EXEMPLAR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXEMPLAR_REGISTRE_GENERATOR")
	private long registre;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_BAIXA")
	private Date dataBaixa;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_COMPRA")
	private Date dataCompra;

	//uni-directional many-to-one association to Obra
	@ManyToOne
	@JoinColumn(name="OBRA")
	private Obra obra;

	public Exemplar() {
	}

	public long getRegistre() {
		return this.registre;
	}

	public void setRegistre(long registre) {
		this.registre = registre;
	}

	public Date getDataBaixa() {
		return this.dataBaixa;
	}

	public void setDataBaixa(Date dataBaixa) {
		this.dataBaixa = dataBaixa;
	}

	public Date getDataCompra() {
		return this.dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Obra getObra() {
		return this.obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}
	//disponible retorna un BOOLEAN indicant si el exemplar est� disponible.
	public boolean disponible() {
		return getDataBaixa()==null;
	}

	

}