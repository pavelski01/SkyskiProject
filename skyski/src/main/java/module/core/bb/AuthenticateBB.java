package module.core.bb;

import module.core.dao.AuthenticateDAO;
import module.core.dto.AuthenticateDTO;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Locale;

@Named("authenticate")
@SessionScoped
public class AuthenticateBB implements Serializable
{
	public AuthenticateBB() { this.loggedIn = false; }
	
	public void clientData(String _id)
	{
		Object[] objects = null;
		try { objects = this.authenticateObject.testification(_id); }
		catch (EJBException ejbex) { System.out.println(ejbex.toString()); }
		if (objects != null)
		{
			this.login = objects[0].toString().trim();
			this.forename = (objects[1] == null) ? "" : objects[1].toString().trim();
			this.email = objects[2].toString().trim();
		}
	}
	
	public Integer clientIdentification()
	{
		if (this.loggedIn)
		{
			try
			{
				this.clientIdentification =
					this.authenticateObject.identification(new AuthenticateDTO(this.login, this.password));
			}
			catch (EJBException ejbex)
			{
				this.loggedIn = false;
				this.clientIdentification = null;
				System.out.println(ejbex.toString());
			}
			return this.clientIdentification;
		}
		return null;
	}
	
	public String process()
	{
		if (!this.loggedIn)
		{
			try { this.loggedIn = this.authenticateObject.check(new AuthenticateDTO(this.login, this.password)); }
			catch (EJBException ejbex)
			{ 
				this.loggedIn = false;
				System.out.println(ejbex.toString());
			}
		}
		else { this.loggedIn = false; }
		if (this.loggedIn)
		{
			((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(true);
			this.cartObject.initialize();
		}
		else
		{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			Locale loc = externalContext.getRequestLocale();
			externalContext.invalidateSession();
			facesContext.getViewRoot().setLocale(loc);
			if (this.cartObject != null)
			{
				this.cartObject.setClientId(null);
				this.cartObject.setMerchandiseId(null);
				this.cartObject.setAmount(null);
				this.cartObject.destroy();
			}
			this.currencyObject.setLocalizationLocale(loc);
		}
		return null;
	}
	
	public Integer getClientIdentification() { return this.clientIdentification = this.clientIdentification(); }
	public Boolean getLoggedIn() { return this.loggedIn; }
	public String getEmail() { return this.email; }
	public String getForename() { return this.forename; }
	public String getLogin() { return this.login; }
	public void setLogin(String _login) { this.login = _login; }
	public String getPassword() { return this.password; }
	public void setPassword(String _password) { this.password = _password; }
	
	@EJB private AuthenticateDAO authenticateObject;
	@Inject private CartBB cartObject;
	@Inject private CurrencyBB currencyObject;
	private Boolean loggedIn;
	private Integer clientIdentification;
	private String password;
	private String login;
	private String forename;
	private String email;
	private static final long serialVersionUID = 1L;
}