package models.incidence;


import models.base.BaseTO;
import models.disease.DiseaseTO;
import models.location.LocationTO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by joaochencci on 16/07/14.
 */
@Entity
public class IncidenceTO extends BaseTO implements Serializable {

	private static final long serialVersionUID = -315667008559548676L;
	//Atributes
	@Id
	@Column(name="id", nullable=false)
	@SequenceGenerator(name="id",sequenceName="tokenSequence",allocationSize=1)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="id")
	private Long id;

    @ManyToOne
    private DiseaseTO disease;

    @ManyToOne
    private LocationTO location;

    @Column(name = "value")
    private Integer value;

	//Getters and Setters

	public Long getId() {
		return id;
	}

	private void setId(final Long id) {
		this.id = id;
	}

    public DiseaseTO getDisease() {
        return disease;
    }

    public void setDisease(DiseaseTO disease) {
        this.disease = disease;
    }

    public LocationTO getLocation() {
        return location;
    }

    public void setLocation(LocationTO location) {
        this.location = location;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
