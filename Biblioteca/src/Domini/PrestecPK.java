package Domini;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PRESTEC database table.
 * 
 */
@Embeddable
public class PrestecPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private long exemplar;

	@Column(insertable=false, updatable=false)
	private String soci;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PRESTEC")
	private java.util.Date dataPrestec;

	public PrestecPK() {
	}
	public long getExemplar() {
		return this.exemplar;
	}
	public void setExemplar(long exemplar) {
		this.exemplar = exemplar;
	}
	public String getSoci() {
		return this.soci;
	}
	public void setSoci(String soci) {
		this.soci = soci;
	}
	public java.util.Date getDataPrestec() {
		return this.dataPrestec;
	}
	public void setDataPrestec(java.util.Date dataPrestec) {
		this.dataPrestec = dataPrestec;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PrestecPK)) {
			return false;
		}
		PrestecPK castOther = (PrestecPK)other;
		return 
			(this.exemplar == castOther.exemplar)
			&& this.soci.equals(castOther.soci)
			&& this.dataPrestec.equals(castOther.dataPrestec);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.exemplar ^ (this.exemplar >>> 32)));
		hash = hash * prime + this.soci.hashCode();
		hash = hash * prime + this.dataPrestec.hashCode();
		
		return hash;
	}
}