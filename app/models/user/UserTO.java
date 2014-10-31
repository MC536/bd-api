package models.user;

import models.base.BaseTO;
import models.token.TokenTO;
import org.mindrot.jbcrypt.BCrypt;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by joaochencci on 25/10/14.
 */
@Entity
public class UserTO extends BaseTO implements Serializable {

	private static final long serialVersionUID = 8484160626069576101L;
	//Atributes
	@Id
	@Column(name = "id", nullable = false)
	@SequenceGenerator(name = "id", sequenceName = "userSequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
	private Long id;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

    @OneToOne
    private TokenTO token;

	//Getters and Setters

	public Long getId() {
		return id;
	}

	private void setId(final Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String username) {
		this.email = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

    public TokenTO getToken() {
        return token;
    }

    public void setToken(TokenTO token) {
        this.token = token;
    }
}
