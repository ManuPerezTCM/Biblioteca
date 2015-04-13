package Domini;

import java.io.Serializable;

import javax.persistence.*;

import Domini.EstatsSoci.estatAbs;

import java.util.ArrayList;
import java.util.Date;


/**
 * The persistent class for the SOCI database table.
 * 
 */
@Entity
@NamedQuery(name="Soci.findAll", query="SELECT s FROM Soci s")
public class Soci implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Manu: atribut afegit per a la gestió de l'estat.
	 */
	@Transient
	private estatAbs estatObj;
	
	/**
	 * Manu: atribut afegit per a la gestió de l'estat i control de quins préstecs té.
	 */
	@Transient
	private ArrayList<Prestec> prestecsPerPagar;
	
	/**
	 * Manu: atribut afegit per a la gestió de l'estat i control de quins préstecs té.
	 */
	@Transient
	private ArrayList<Prestec> prestecsPerTornar;
	
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
	
	/**
	 * @Autor Manu
	 * @ Mètode afegit per a la gestió d'estat
	 */
	public void demanarPrestec(Exemplar exemplar) throws Exception{
		this.estatObj = estatObj.demanarPrestec(this, exemplar);
		//add prestec a prestecsPerTornar i retornar el prestec al controlador
		//el controlador fara prestec p = soci.demanarPrestec(blablabla)
	}
	
	/**
	 * @Autor Manu
	 * @ Mètode afegit per a la gestió d'estat
	 */
	public void tornarPrestec(Exemplar exemplar) throws Exception{
		this.estatObj = estatObj.tornarPrestec(this, exemplar);
	}
	
	/**
	 * @Autor Manu
	 * @ Mètode afegit per a la gestió d'estat
	 */
	public void pagarPrestec(Exemplar exemplar) throws Exception{
		this.estatObj = estatObj.pagarPrestec(this, exemplar);
	}

	/**
	 * @Autor Manu
	 * @ Mètode afegit per a la gestió d'estat
	 */
	public ArrayList<Prestec> getPrestecsPerTornar(){
		return this.prestecsPerTornar;
	}

	/**
	 * @Autor Manu
	 * @ Mètode afegit per a la gestió d'estat
	 */
	public ArrayList<Prestec> getPrestecsPerPagar(){
		return this.prestecsPerPagar;
	}


}