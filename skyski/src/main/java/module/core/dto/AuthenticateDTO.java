package module.core.dto;

import java.io.Serializable;

public class AuthenticateDTO implements Serializable
{
	public AuthenticateDTO(String _login, String _password)
	{
		this.login = _login;
		this.password = _password;
	}
	
	public String getLogin() { return this.login; }
	public void setLogin(String _login) { this.login = _login; }
	public String getPassword() { return this.password; }
	public void setPassword(String _password) { this.password = _password; }

	private String login;
	private String password;
	private static final long serialVersionUID = 1L;
}