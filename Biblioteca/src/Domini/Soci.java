package Domini;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SOCI database table.
 * 
 */
@Entity
@NamedQuery(name="Soci.findAll", query="SELECT s FROM Soci s")
public class Soci implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	private String cognom1;

	private String cognom2;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ALTA")
	private Date dataAlta;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_BAIXA")
	private Date dataBaixa;

	private String estat;

	private String nom;

	public Soci() {
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCognom1() {
		return this.cognom1;
	}

	public void setCognom1(String cognom1) {
		this.cognom1 = cognom1;
	}

	public String getCognom2() {
		return this.cognom2;
	}

	public void setCognom2(String cognom2) {
		this.cognom2 = cognom2;
	}

	public Date getDataAlta() {
		return this.dataAlta;
	}

	public void setDataAlta(Date dataAlta) {
		this.dataAlta = dataAlta;
	}

	public Date getDataBaixa() {
		return this.dataBaixa;
	}

	public void setDataBaixa(Date dataBaixa) {
		this.dataBaixa = dataBaixa;
	}

	public String getEstat() {
		return this.estat;
	}

	public void setEstat(String estat) {
		this.estat = estat;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}