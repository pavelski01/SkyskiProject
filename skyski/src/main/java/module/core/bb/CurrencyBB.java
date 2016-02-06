package module.core.bb;

import module.core.dao.CurrencyDAO;
import module.core.dto.CurrencyDTO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Named("currency")
@SessionScoped
public class CurrencyBB implements Serializable
{
	public CurrencyBB() {}
	
	@PostConstruct public void initialize()
	{
		this.currencyArray = this.currencyObject.getArray();
		this.localizationLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		this.currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
		for (CurrencyDTO c : this.currencyArray) if (this.currentLocale.equals(c.getLocale().substring(0, 2)))
		{
			this.code = c.getCode();
			this.locale = c.getLocale();
			this.rate = c.getRate();
			this.symbol = c.getSymbol();
			this.noun = c.getNoun();
			this.adjective = c.getAdjective();
			break;
		}
	}
	
	public String localeChange()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String loc = facesContext.getExternalContext().getRequestParameterMap().get("loc");
		facesContext.getViewRoot().setLocale(this.localizationLocale = new Locale(loc));
		this.initialize();
		return null;
	}
	
	public CurrencyDTO[] getCurrencyArray() { return this.currencyArray; }
	public Locale getLocalizationLocale() { return this.localizationLocale; }
	public void setLocalizationLocale(Locale _loc) { this.localizationLocale = _loc; }
	public String getCode()
	{
		this.checkLocale();
		return this.code.toString();
	}
	public String getLocale()
	{
		this.checkLocale();
		return this.locale;
	}
	public String getSymbol()
	{
		this.checkLocale();
		return this.symbol;
	}
	public Double getRate()
	{
		this.checkLocale();
		return this.rate;
	}	
	public String getNoun()
	{
		this.checkLocale();
		return this.noun;
	}
	public String getAdjective()
	{
		this.checkLocale();
		return this.adjective;
	}
	
	private void checkLocale()
	{
		String actualLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
		if (!currentLocale.equals(actualLocale)) initialize();
	}

	@EJB private CurrencyDAO currencyObject;
	private CurrencyDTO[] currencyArray;
	private Character[] code;
	private Locale localizationLocale;
	private Double rate;
	private String currentLocale;
	private String locale;
	private String symbol;
	private String noun;
	private String adjective;
	private static final long serialVersionUID = 1L;
}