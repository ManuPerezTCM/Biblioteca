package Domini;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the OBRA database table.
 * 
 */
@Entity
@NamedQuery(name="Obra.findAll", query="SELECT o FROM Obra o")
public class Obra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String isbn;

	private BigDecimal alcada;

	private BigDecimal pagines;

	private String titol;

	//uni-directional many-to-many association to Descriptor
	@ManyToMany
	@JoinTable(name="ESPECIFICAR", 
	joinColumns={
			@JoinColumn(name="OBRA")},
	inverseJoinColumns={@JoinColumn(name="DESCRIPTOR")})
	private List<Descriptor> descriptors;

	//uni-directional many-to-one association to Editorial
	@ManyToOne
	@JoinColumn(name="EDITORIAL")
	private Editorial editorial;

	public Obra() {
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public BigDecimal getAlcada() {
		return this.alcada;
	}

	public void setAlcada(BigDecimal alcada) {
		this.alcada = alcada;
	}

	public BigDecimal getPagines() {
		return this.pagines;
	}

	public void setPagines(BigDecimal pagines) {
		this.pagines = pagines;
	}

	public String getTitol() {
		return this.titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public List<Descriptor> getDescriptors() {
		return this.descriptors;
	}

	public void setDescriptors(List<Descriptor> descriptors) {
		this.descriptors = descriptors;
	}

	public Editorial getEditorial() {
		return this.editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

}