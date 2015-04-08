package Domini;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the BIBLIOTECARI database table.
 * 
 */
@Embeddable
public class BibliotecariPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String contrasenya;

	private String nom;

	public BibliotecariPK() {
	}
	public String getContrasenya() {
		return this.contrasenya;
	}
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BibliotecariPK)) {
			return false;
		}
		BibliotecariPK castOther = (BibliotecariPK)other;
		return 
			this.contrasenya.equals(castOther.contrasenya)
			&& this.nom.equals(castOther.nom);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.contrasenya.hashCode();
		hash = hash * prime + this.nom.hashCode();
		
		return hash;
	}
}