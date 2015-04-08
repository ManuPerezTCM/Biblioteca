package Domini;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BIBLIOTECARI database table.
 * 
 */
@Entity
@NamedQuery(name="Bibliotecari.findAll", query="SELECT b FROM Bibliotecari b")
public class Bibliotecari implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BibliotecariPK id;

	private BigDecimal encarregat;

	public Bibliotecari() {
	}

	public BibliotecariPK getId() {
		return this.id;
	}

	public void setId(BibliotecariPK id) {
		this.id = id;
	}

	public BigDecimal getEncarregat() {
		return this.encarregat;
	}

	public void setEncarregat(BigDecimal encarregat) {
		this.encarregat = encarregat;
	}

}