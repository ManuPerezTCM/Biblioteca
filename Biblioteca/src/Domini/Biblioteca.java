package Domini;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BIBLIOTECA database table.
 * 
 */
@Entity
@NamedQuery(name="Biblioteca.findAll", query="SELECT b FROM Biblioteca b")
public class Biblioteca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_BIBLIOTECA")
	private String idBiblioteca;

	@Column(name="DIES_DE_PRESTEC")
	private BigDecimal diesDePrestec;

	@Column(name="IMPORT_PER_DIA_RETARD")
	private BigDecimal importPerDiaRetard;

	@Column(name="PRESTECS_SIMULTANIS")
	private BigDecimal prestecsSimultanis;
	
	private static Biblioteca instancia;

	public Biblioteca() {
		instancia = this;
	}
	
	public static Biblioteca getInstancia(){
			return instancia;
	}

	public String getIdBiblioteca() {
		return this.idBiblioteca;
	}

	public void setIdBiblioteca(String idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}

	public BigDecimal getDiesDePrestec() {
		return this.diesDePrestec;
	}

	public void setDiesDePrestec(BigDecimal diesDePrestec) {
		this.diesDePrestec = diesDePrestec;
	}

	public BigDecimal getImportPerDiaRetard() {
		return this.importPerDiaRetard;
	}

	public void setImportPerDiaRetard(BigDecimal importPerDiaRetard) {
		this.importPerDiaRetard = importPerDiaRetard;
	}

	public BigDecimal getPrestecsSimultanis() {
		return this.prestecsSimultanis;
	}

	public void setPrestecsSimultanis(BigDecimal prestecsSimultanis) {
		this.prestecsSimultanis = prestecsSimultanis;
	}

}