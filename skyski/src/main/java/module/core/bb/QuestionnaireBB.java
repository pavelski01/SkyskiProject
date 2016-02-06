package module.core.bb;

import module.core.bd.MailBD;
import module.core.dao.QuestionnaireDAO;
import module.core.dto.QuestionnaireDTO;
import module.core.intl.Messages;
import module.core.srvt.CaptchaSRVT;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

@Named("questionnaire")
@SessionScoped
public class QuestionnaireBB implements Serializable, Validator
{
	public QuestionnaireBB() {}
	
	@Override public void validate(FacesContext _context, UIComponent _component, Object _value) {}
	
	public void clearAllInputs(ComponentSystemEvent _event)
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (!facesContext.isPostback())
		{
			UIComponent source = _event.getComponent();
			UIInput loginInput = (UIInput)source.findComponent("net:login");
			UIInput emailInput = (UIInput)source.findComponent("net:email");
			UIInput fnameInput = (UIInput)source.findComponent("net:fname");
			UIInput lnameInput = (UIInput)source.findComponent("net:lname");
			UIInput phoneInput = (UIInput)source.findComponent("net:phone");
			UIInput streetAddressInput = (UIInput)source.findComponent("net:streetAddress");
			UIInput postalCodeInput = (UIInput)source.findComponent("net:postalCode");
			UIInput cityAddressInput = (UIInput)source.findComponent("net:cityAddress");
			UIInput dayOfBirthInput = (UIInput)source.findComponent("net:dayOfBirth");
			UIInput monthOfBirthInput = (UIInput)source.findComponent("net:monthOfBirth");
			UIInput yearOfBirthInput = (UIInput)source.findComponent("net:yearOfBirth");		
			UIInput captchaInput = (UIInput)source.findComponent("net:captcha");		
			loginInput.resetValue();
			emailInput.resetValue();
			fnameInput.resetValue();
			lnameInput.resetValue();
			phoneInput.resetValue();
			streetAddressInput.resetValue();
			postalCodeInput.resetValue();
			cityAddressInput.resetValue();
			dayOfBirthInput.resetValue();
			monthOfBirthInput.resetValue();
			yearOfBirthInput.resetValue();
			captchaInput.resetValue();	
			loginInput.setValue("");
			emailInput.setValue("");
			fnameInput.setValue("");
			lnameInput.setValue("");
			phoneInput.setValue("");
			streetAddressInput.setValue("");
			postalCodeInput.setValue("");
			cityAddressInput.setValue("");
			dayOfBirthInput.setValue("");
			monthOfBirthInput.setValue("");
			yearOfBirthInput.setValue("");
			captchaInput.setValue("");
		}
	}	
		
	public void clearCaptcha(ComponentSystemEvent _event)
	{
		UIComponent source = _event.getComponent();
		UIInput captchaInput = (UIInput)source.findComponent("net:captcha");
		captchaInput.resetValue();
		captchaInput.setValue("");
	}
	
	public void validateCaptcha(FacesContext _context, UIComponent _component, Object _value)
    	throws ValidatorException
	{
			String givenString = _value.toString();
			FacesMessage facesMessage = new FacesMessage();
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			HttpServletRequest request =
				(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        HttpSession session = request.getSession();
	        String capkey = session.getAttribute(CaptchaSRVT.CAPTCHA_KEY).toString();
	        if (!givenString.equals(capkey) || givenString.equals(""))
				throw new ValidatorException(new FacesMessage(null));
	}
	
	public void validateDate(FacesContext _context, UIComponent _component, Object _value)
		throws ValidatorException
	{
		this.currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
		if (this.currentLocale.equals("en")) this.currentLocale = "_" + this.currentLocale;
		else if (this.currentLocale.equals("pl")) this.currentLocale = "_" + this.currentLocale;
		else if (this.currentLocale.equals("ru")) this.currentLocale = "_" + this.currentLocale;
		Integer dayOfBirthI;
		Integer monthOfBirthI;
		Integer yearOfBirthI;
		UIInput dayOfBirthUI = (UIInput)_component.findComponent("dayOfBirth");
		UIInput monthOfBirthUI = (UIInput)_component.findComponent("monthOfBirth");
		UIInput yearOfBirthUI = (UIInput)_component.findComponent("yearOfBirth");
		String dayOfBirthS =
			(dayOfBirthUI.getLocalValue() == null) ? "" : dayOfBirthUI.getLocalValue().toString();
		String monthOfBirthS =
			(monthOfBirthUI.getLocalValue() == null) ? "" : monthOfBirthUI.getLocalValue().toString();
		String yearOfBirthS =
			(yearOfBirthUI.getLocalValue() == null) ? "" : yearOfBirthUI.getLocalValue().toString();
		if (dayOfBirthS.equals(""))
		{
			if (this.dayOfBirth == null) dayOfBirthI = 0;
			else
			{
				dayOfBirthI = Integer.parseInt(this.dayOfBirth);
				if (dayOfBirthI == 0) dayOfBirthI = 0;
			}
		}
		else if (!Pattern.compile("[0-9]{2}").matcher(dayOfBirthS).find()) dayOfBirthI = -1;
		else
		{
			dayOfBirthI = Integer.parseInt(dayOfBirthS);
			if (dayOfBirthI == 0) dayOfBirthI = -1;
		}
		if (monthOfBirthS.equals(""))
			if (this.monthOfBirth == null) monthOfBirthI = 0;
			else
			{
				monthOfBirthI = Integer.parseInt(this.monthOfBirth);
				if (monthOfBirthI == 0) monthOfBirthI = -1;
			}
		else if (!Pattern.compile("[0-9]{2}").matcher(monthOfBirthS).find()) monthOfBirthI = -1;
		else {
			monthOfBirthI = Integer.parseInt(monthOfBirthS);
			if (monthOfBirthI == 0) monthOfBirthI = -1;
		}
		if (yearOfBirthS.equals(""))
			if (this.yearOfBirth == null) yearOfBirthI = 0;
			else
			{
				yearOfBirthI = Integer.parseInt(this.yearOfBirth);
				if (yearOfBirthI == 0) yearOfBirthI = -1;
			}
		else if (!Pattern.compile("[0-9]{4}").matcher(yearOfBirthS).find()) yearOfBirthI = -1;
		else
		{
			yearOfBirthI = Integer.parseInt(yearOfBirthS);
			if (yearOfBirthI == 0) yearOfBirthI = -1;
		}
		if ((dayOfBirthI != 0) || (monthOfBirthI != 0) || (yearOfBirthI != 0))
			if
			(
				!isValidDate(dayOfBirthI,monthOfBirthI,yearOfBirthI) ||
					(dayOfBirthI == -1) || (monthOfBirthI == -1) || (yearOfBirthI == -1) ||
						((Calendar.getInstance().get(Calendar.YEAR) - 10) < yearOfBirthI) ||
							((Calendar.getInstance().get(Calendar.YEAR) - 150) > yearOfBirthI)
			)
			{
				FacesMessage facesMessage =
					Messages.getMessage(
						"module.skyski.msg.messages" + this.currentLocale, "custom.valid.msg.invalidDate", null
					);
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(facesMessage);
			}
	}
	
	public void validateEmail(FacesContext _context, UIComponent _component, Object _value)
		throws ValidatorException
	{
		String email = _value.toString();
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if (!email.matches(regex))
		{
			facesMessage.setSummary(
				Messages.getMessage(
					"module.skyski.msg.messages" + this.currentLocale, "custom.valid.msg.invalidEmail", null
				).getSummary()
			);
			throw new ValidatorException(facesMessage);
		}
		if (this.questionnaireObject.check("email", email))
		{
			facesMessage.setSummary(
				Messages.getMessage(
					"module.skyski.msg.messages" + this.currentLocale, "custom.valid.msg.emailExists", null
				).getSummary()
			);
			throw new ValidatorException(facesMessage);
		}
	}
	
	public void validateLogin(FacesContext _context, UIComponent _component, Object _value)
		throws ValidatorException
	{
		String login = _value.toString();
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		this.currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
		if (this.currentLocale.equals("en")) this.currentLocale = "_" + this.currentLocale;
		else if (this.currentLocale.equals("pl")) this.currentLocale = "_" + this.currentLocale;
		else if (this.currentLocale.equals("ru")) this.currentLocale = "_" + this.currentLocale;
		Boolean letterFlag = false;
		Boolean digitFlag = false;
		String message = "";
		if (login.length() < 3)
			message = message.concat(
				Messages.getMessage(
					"module.skyski.msg.messages" + this.currentLocale, "custom.valid.msg.threeCharLogin", null
				).getSummary()
			);
		if (!Character.isLetter(login.charAt(0)))
		{
			if (!message.equals("")) message = message.concat(" ");
			message = message.concat(
				Messages.getMessage(
					"module.skyski.msg.messages" + this.currentLocale, "custom.valid.msg.firstLetterChar", null
				).getSummary()
			);
		}
		if (!message.equals(""))
		{
			facesMessage.setSummary(message);
			throw new ValidatorException(facesMessage);
		}
		for (int counter = 0; counter < login.length(); counter++)
		{
			Character ch = login.charAt(counter);
			if (Character.isLetter(ch) && !letterFlag) letterFlag = true;
			else if (Character.isDigit(ch) && !digitFlag) digitFlag = true;
			else if (!Character.isLetter(ch) && !Character.isDigit(ch))
			{
				facesMessage.setSummary(
					Messages.getMessage(
						"module.skyski.msg.messages" + this.currentLocale, "custom.valid.msg.loginLetterDigitOnly", null
					).getSummary()
				);
				throw new ValidatorException(facesMessage);
			}
		}
		if (!letterFlag || !digitFlag)
		{
			facesMessage.setSummary(
				Messages.getMessage(
					"module.skyski.msg.messages" + this.currentLocale, "custom.valid.msg.loginLetterDigit", null
				).getSummary()
			);
			throw new ValidatorException(facesMessage);
		}
		if (this.questionnaireObject.check("login", login))
		{
			facesMessage.setSummary(
				Messages.getMessage(
					"module.skyski.msg.messages" + this.currentLocale, "custom.valid.msg.loginExists", null
				).getSummary()
			);
			throw new ValidatorException(facesMessage);
		}
	}
	
	public void validatePassword(FacesContext _context, UIComponent _component, Object _value)
		throws ValidatorException
	{
			String repeatedPassword = _value.toString();
			String password = this.password == null ? "" : this.password;
			FacesMessage facesMessage = new FacesMessage();
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			if (!password.equals(repeatedPassword) || password.equals(""))
			{
				facesMessage.setSummary(
					Messages.getMessage(
						"module.skyski.msg.messages" + this.currentLocale, "custom.valid.msg.differentPasswords", null
					).getSummary()
				);
				throw new ValidatorException(facesMessage);
			}
	}
	
	public String process()
	{
		String forename = (this.getForename() == null) ? "" : this.getForename();
		String surname = (this.getSurname() == null) ? "" : this.getSurname();
		String phone = (this.getPhone() == null) ? "" : this.getPhone();
		String street = (this.getStreet() == null) ? "" : this.getStreet();
		String postalCode = (this.getPostalCode() == null) ? "" : this.getPostalCode();
		String cityAddress = (this.getCityAddress() == null) ? "" : this.getCityAddress();
		GregorianCalendar dateOfBirth;
		if ((this.getYearOfBirth() != null) && (this.getMonthOfBirth() != null) && (this.getDayOfBirth() != null))
		{
			dateOfBirth = new GregorianCalendar(
				Integer.parseInt(this.yearOfBirth),
				Integer.parseInt(this.monthOfBirth),
				Integer.parseInt(this.dayOfBirth)
			);
		}
		else dateOfBirth = null;
		String navigation = "registration_success";
		try
		{
			this.questionnaireObject.insert(
				new QuestionnaireDTO(
					this.login, this.password, this.email, forename, surname,
					phone, street, postalCode, cityAddress, dateOfBirth
				)
			);
		} 
		catch (EJBException ejbex)
		{ 
			navigation = "registration_failure";
			System.out.println(ejbex.toString());
		}
		this.mailObject.setEmail(this.email);
		this.mailObject.setForename(forename);
		this.mailObject.setLogin(this.login);
		this.mailObject.setPasswd(this.password.charAt(0));
		this.currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
		if (this.currentLocale.equals("en")) this.currentLocale = "_" + this.currentLocale;
		else if (this.currentLocale.equals("pl")) this.currentLocale = "_" + this.currentLocale;
		else if (this.currentLocale.equals("ru")) this.currentLocale = "_" + this.currentLocale;
		try { this.mailObject.newAccount(this.currentLocale); }
		catch (MessagingException mex) { System.out.println(mex.toString()); }
		return navigation;
	}
	
	public String getLogin() { return this.login; }
	public void setLogin(String _login) { this.login = _login; }
	public String getPassword() { return this.password; }
	public void setPassword(String _password) { this.password = _password; }
	public String getPasswordRepeat() { return this.passwordRepeat; }
	public void setPasswordRepeat(String _passwordRepeat) { this.passwordRepeat = _passwordRepeat; }
	public String getEmail() { return this.email; }
	public void setEmail(String _email) { this.email = _email; }
	public String getForename() { return this.forename; }
	public void setForename(String _forename) { this.forename = _forename; }
	public String getSurname() { return this.surname; }
	public void setSurname(String _surname) { this.surname = _surname; }
	public String getPhone() { return this.phone; }
	public void setPhone(String _phone) { this.phone = _phone; }
	public String getStreet() {	return this.street; }
	public void setStreet(String _street) { this.street = _street; }
	public String getPostalCode() {	return this.postalCode; }
	public void setPostalCode(String _postalCode) { this.postalCode = _postalCode; }
	public String getCityAddress() { return this.cityAddress; }
	public void setCityAddress(String _cityAddress) { this.cityAddress = _cityAddress; }
	public String getDayOfBirth() {	return this.dayOfBirth; }
	public void setDayOfBirth(String _dayOfBirth) { this.dayOfBirth = _dayOfBirth; }
	public String getMonthOfBirth() { return this.monthOfBirth; }
	public void setMonthOfBirth(String _monthOfBirth) { this.monthOfBirth = _monthOfBirth; }
	public String getYearOfBirth() { return this.yearOfBirth; }
	public void setYearOfBirth(String _yearOfBirth) { this.yearOfBirth = _yearOfBirth; }
	public String getCaptcha() { return this.captcha; }
    public void setCaptcha(String _captcha) { this.captcha = _captcha; }
    
	private boolean isValidDate(Integer _day, Integer _month, Integer _year)
	{
		if (_day < 1 || _month < 1 || _month > 12 || _year < 1) return false;
		switch (_month)
		{
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12: return _day <= 31;
			case 4:
			case 6:
			case 9:
			case 11: return _day <= 30;
			case 2:
				if ((_year % 4 == 0 && (_year % 400 == 0 || _year % 100 != 0))) return _day <= 29;
				else return _day <= 28;
			default: return false;
		}
	}
	
	@EJB private QuestionnaireDAO questionnaireObject;
	@EJB private MailBD mailObject;
	private String login;
	private String password;
	private String passwordRepeat;
	private String email;
	private String forename;
	private String surname;
	private String phone;
	private String street;
	private String postalCode;
	private String cityAddress;
	private String dayOfBirth;
	private String monthOfBirth;
	private String yearOfBirth;
	private String captcha;
	private String currentLocale;
	private static final long serialVersionUID = 1L;
}