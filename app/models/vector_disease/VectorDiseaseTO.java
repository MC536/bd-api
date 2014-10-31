package models.vector_disease;


import models.base.BaseTO;
import models.disease.DiseaseTO;
import models.vector.VectorTO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by joaochencci on 16/07/14.
 */
@Entity
public class VectorDiseaseTO extends BaseTO implements Serializable {

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
    private VectorTO vector;

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

    public VectorTO getVector() {
        return vector;
    }

    public void setVector(VectorTO vector) {
        this.vector = vector;
    }
}
