package module.skyski.orm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="clients")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable
{
	public Client() {}

	public Integer getId() { return this.id; }
	public void setId(Integer _id) { this.id = _id; }
	public String getCity() { return this.city;	}
	public void setCity(String _city) { this.city = _city; }
	public Boolean getConfirmed() { return this.confirmed; }
	public void setConfirmed(Boolean _confirmed) { this.confirmed = _confirmed; }
	public Date getDateOfBirth() { return this.dateOfBirth;	}
	public void setDateOfBirth(Date _dateOfBirth) { this.dateOfBirth = _dateOfBirth; }
	public String getEmail() { return this.email; }
	public void setEmail(String _email) { this.email = _email; }
	public String getLogin() { return this.login; }
	public void setLogin(String _login) { this.login = _login; }
	public String getPasswd() {	return this.passwd;	}
	public void setPasswd(String _passwd) { this.passwd = _passwd; }
	public String getPhone() { return this.phone; }
	public void setPhone(String _phone) { this.phone = _phone; }
	public String getPostalCode() { return this.postalCode;	}
	public void setPostalCode(String _postalCode) { this.postalCode = _postalCode; }
	public String getStreet() {	return this.street; }
	public void setStreet(String _street) { this.street = _street; }
	public String getForename() { return this.forename;	}
	public void setForename(String _forename) { this.forename = _forename; }
	public String getSurname() { return this.surname; }
	public void setSurname(String _surname) { this.surname = _surname; }
	
	@Column(name="id") @Id private Integer id;
	@Column(name="city") private String city;
	@Column(name="confirmed") private Boolean confirmed;	
	@Column(name="date_of_birth") @Temporal(TemporalType.DATE) private Date dateOfBirth;
	@Column(name="email") private String email;	
	@Column(name="login") private String login;
	@Column(name="passwd") private String passwd;
	@Column(name="phone") private String phone;
	@Column(name="postal_code")	private String postalCode;
	@Column(name="street") private String street;
	@Column(name="forename") private String forename;
	@Column(name="surname") private String surname;
	@OneToMany(mappedBy="client") private List<Transactions> transactions;
	private static final long serialVersionUID = 1L;
}