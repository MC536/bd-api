package models.base;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by joaochencci on 26/06/14.
 */
@MappedSuperclass
public abstract class BaseTO {

	//Atributes
	@Column(name = "creationDate")
	protected Date creationDate;

	@Column(name = "modifyDate")
	protected Date modifyDate;

	@Column(name = "active")
	protected Boolean active;

	//Getters and Setters
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Date getCreationDate(final Date date) {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getId() {
		return null;
	}
}
