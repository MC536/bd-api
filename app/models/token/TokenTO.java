package models.token;


import models.base.BaseTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by joaochencci on 16/07/14.
 */
@Entity
public class TokenTO extends BaseTO implements Serializable {

	private static final long serialVersionUID = -315667008559548676L;
	//Atributes
	@Id
	@Column(name="id", nullable=false)
	@SequenceGenerator(name="id",sequenceName="tokenSequence",allocationSize=1)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="id")
	private Long id;

	@Column(name = "value")
	private String value;

	@Column(name = "expiration")
	private Date expiration;

	//Getters and Setters

	public Long getId() {
		return id;
	}

	private void setId(final Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String code) {
		this.value = code;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(final Date expiration) {
		this.expiration = expiration;
	}
}
