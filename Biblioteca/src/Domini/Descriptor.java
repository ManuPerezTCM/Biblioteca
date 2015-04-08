package Domini;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "DESCRIPTOR" database table.
 * 
 */
@Entity
@Table(name="\"DESCRIPTOR\"")
@NamedQuery(name="Descriptor.findAll", query="SELECT d FROM Descriptor d")
public class Descriptor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String nom;

	public Descriptor() {
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}