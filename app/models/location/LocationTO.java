package models.location;


import models.base.BaseTO;
import models.climate.ClimateTO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by joaochencci on 16/07/14.
 */
@Entity
public class LocationTO extends BaseTO implements Serializable {

	private static final long serialVersionUID = -315667008559548676L;
	//Atributes
	@Id
	@Column(name="id", nullable=false)
	@SequenceGenerator(name="id",sequenceName="tokenSequence",allocationSize=1)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="id")
	private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "population")
    private Integer population;

    @ManyToOne
    private ClimateTO climate;

    //Getters and Setters

	public Long getId() {
		return id;
	}

	private void setId(final Long id) {
		this.id = id;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public ClimateTO getClimate() {
        return climate;
    }

    public void setClimate(ClimateTO climate) {
        this.climate = climate;
    }
}
