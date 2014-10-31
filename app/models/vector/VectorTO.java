package models.vector;


import models.base.BaseTO;
import models.disease.DiseaseTO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by joaochencci on 16/07/14.
 */
@Entity
public class VectorTO extends BaseTO implements Serializable {

	private static final long serialVersionUID = -315667008559548676L;
	//Atributes
	@Id
	@Column(name="id", nullable=false)
	@SequenceGenerator(name="id",sequenceName="tokenSequence",allocationSize=1)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="id")
	private Long id;

	@Column(name = "family")
	private String family;

    @Column(name = "popularName")
    private String popularName;

    @Column(name = "scientificName")
    private String scientificName;

    @Column(name = "bestTemperature")
    private Integer bestTemperature;

    @ManyToOne
    private DiseaseTO disease;

	//Getters and Setters

	public Long getId() {
		return id;
	}

	private void setId(final Long id) {
		this.id = id;
	}

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getPopularName() {
        return popularName;
    }

    public void setPopularName(String popularName) {
        this.popularName = popularName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public Integer getBestTemperature() {
        return bestTemperature;
    }

    public void setBestTemperature(Integer bestTemperature) {
        this.bestTemperature = bestTemperature;
    }

    public DiseaseTO getDisease() {
        return disease;
    }

    public void setDisease(DiseaseTO disease) {
        this.disease = disease;
    }
}
