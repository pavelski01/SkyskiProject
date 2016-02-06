package module.core.dto;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class QuestionnaireDTO implements Serializable
{
	public QuestionnaireDTO(
		String _login, String _password, String _email, String _fname, String _lname,
		String _phone, String _street, String _postalCode, String _cityAddress, GregorianCalendar _dateOfBirth
	)
	{
			this.login = _login;
			this.password = _password;
			this.email = _email;
			this.fname = _fname;
			this.lname = _lname;
			this.phone = _phone;
			this.street = _street;
			this.postalCode = _postalCode;
			this.cityAddress = _cityAddress;
			this.dateOfBirth = _dateOfBirth;
	}
	
	public String getLogin() { return this.login; }
	public void setLogin(String _login) { this.login = _login; }
	public String getPassword() { return this.password; }
	public void setPassword(String _password) { this.password = _password; }
	public String getEmail() { return this.email; }
	public void setEmail(String _email) { this.email = _email; }
	public String getFname() { return this.fname; }
	public void setFname(String _fname) { this.fname = _fname; }
	public String getLname() { return this.lname; }
	public void setLname(String _lname) { this.lname = _lname; }
	public String getPhone() {	return this.phone; }
	public void setPhone(String _phone) { this.phone = _phone; }
	public String getStreet() {	return this.street; }
	public void setStreet(String _street) { this.street = _street; }
	public String getPostalCode() { return this.postalCode; }
	public void setPostalCode(String _postalCode) { this.postalCode = _postalCode; }
	public String getCityAddress() { return this.cityAddress; }
	public void setCityAddress(String _cityAddress) { this.cityAddress = _cityAddress; }
	public GregorianCalendar getDateOfBirth() { return this.dateOfBirth; }
	public void setDateOfBirth(GregorianCalendar _dateOfBirth) { this.dateOfBirth = _dateOfBirth; }

	private String login;
	private String password;
	private String email;
	private String fname;
	private String lname;
	private String phone;
	private String street;
	private String postalCode;
	private String cityAddress;
	private GregorianCalendar dateOfBirth;
	private static final long serialVersionUID = 1L;
}