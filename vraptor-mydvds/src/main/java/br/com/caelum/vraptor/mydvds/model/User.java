package br.com.caelum.vraptor.mydvds.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

/**
 * User entity.
 *
 * This class represents the User table from the database.
 *
 * A persisted object of this class represents a record in the database.
 *
 * The class is annotated with <code>@Component</code> and <code>@SessionScoped</code>,
 * thus its instances can be injected to other classes who depend on Users.
 */
@Entity
@Component
@SessionScoped
public class User {

	// Hibernate validator's annnotations/rules
	@Id
	@NotNull
	@Length(min = 3, max = 20)
	private String login;

	// Hibernate validator's annnotations/rules
	@NotNull
	@Length(min = 6, max = 20)
	private String password;

	// Hibernate validator's annnotations/rules
	@NotNull
	@Length(min = 3, max = 100)
	private String name;

	// user to dvd mapping,
	@OneToMany(mappedBy="owner")
	private Set<DvdCopy> copies;

	public Set<DvdCopy> getCopies() {
		if (copies == null) {
			copies = new HashSet<DvdCopy>();
		}
		return copies;
	}

	public void setCopies(Set<DvdCopy> dvds) {
		this.copies = dvds;
	}


	public Set<Dvd> getDvds() {
		if (copies == null) {
			return null;
		}
		return new HashSet<Dvd>(Collections2.transform(copies, new Function<DvdCopy, Dvd>() {
			public Dvd apply(DvdCopy copy) {
				return copy.getDvd();
			}
		}));
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
