package models.climate;


import models.base.BaseTO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by joaochencci on 16/07/14.
 */
@Entity
public class ClimateTO extends BaseTO implements Serializable {

	private static final long serialVersionUID = -315667008559548676L;
	//Atributes
	@Id
	@Column(name="id", nullable=false)
	@SequenceGenerator(name="id",sequenceName="tokenSequence",allocationSize=1)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="id")
	private Long id;

	@Column(name = "name")
	private String name;

    @Column(name = "minTemperature")
    private Integer minTemperature;

    @Column(name = "maxTemperature")
    private Integer maxTemperature;

    @Column(name = "rangeTemperature")
    private Integer rangeTemperature;

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

    public Integer getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Integer minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Integer getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Integer maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Integer getRangeTemperature() {
        return rangeTemperature;
    }

    public void setRangeTemperature(Integer rangeTemperature) {
        this.rangeTemperature = rangeTemperature;
    }
}
