package Domini;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the APAREIXER database table.
 * 
 */
@Embeddable
public class ExemplarPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String obra;

	@Column(insertable=false, updatable=false)
	private String autor;

	public ExemplarPK() {
	}
	public String getObra() {
		return this.obra;
	}
	public void setObra(String obra) {
		this.obra = obra;
	}
	public String getAutor() {
		return this.autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ExemplarPK)) {
			return false;
		}
		ExemplarPK castOther = (ExemplarPK)other;
		return 
			this.obra.equals(castOther.obra)
			&& this.autor.equals(castOther.autor);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.obra.hashCode();
		hash = hash * prime + this.autor.hashCode();
		
		return hash;
	}
}