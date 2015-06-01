package module.skyski.bd;

import module.skyski.intl.Messages;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

@Singleton
@Startup
public class MailBD
{
	public MailBD() {}
	
	public void newAccount(String _currentLocale) throws MessagingException
	{
		String subjectTitle =
			Messages.getMessage(
				"module.skyski.msg.messages" + _currentLocale, "custom.mail.msg.subjectTitle", null
			).getSummary();
		this.forename = (this.forename == null || this.forename.equals("")) ? "" : " " + this.forename;
		String registrationInfo =
			Messages.getMessage(
				"module.skyski.msg.messages" + _currentLocale,
				"custom.mail.msg.registrationInfo",
				new Object[] {this.forename, this.login, this.passwd.toString()}
			).getSummary();
		this.mailSession.setDebug(true);
		MimeMessage mime = new MimeMessage(this.mailSession);
		mime.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.email, false));
		mime.setSubject(subjectTitle);		
		mime.setText(registrationInfo);
		mime.setSentDate(new Date());
		mime.setHeader("Content-Type", "text/plain");
		mime.saveChanges();
		Transport trans;
		trans = this.mailSession.getTransport();
		String serverUsername = this.mailSession.getProperty("mail.smtps.username");
		String serverPassword = this.mailSession.getProperty("mail.smtps.password");
		trans.connect(serverUsername, serverPassword);
		trans.sendMessage(mime, mime.getAllRecipients());
		trans.close();
		this.email = null;
		this.forename = null;
		this.login = null;
		this.passwd = null;
	}

	public void cargoShipping(String _currentLocale, List<String[]> _dataList, Double _sum) throws MessagingException
	{
		String subjectTitle =
			Messages.getMessage(
				"module.skyski.msg.messages" + _currentLocale, "custom.mail.msg.subjectTitle2", null
			).getSummary();
		String introduction =
			Messages.getMessage(
				"module.skyski.msg.messages" + _currentLocale, "shipment", null
			).getSummary() + ": " + this.email + "\n\n";
		String expand;
		if (this.forename == null || this.forename.equals(""))
		{
			expand = Messages.getMessage("module.skyski.msg.messages" + _currentLocale, "buy", null).getSummary();
			expand = expand.substring(0, 1).toUpperCase().concat(expand.substring(1, expand.length())).concat(":\n"); 
		}
		else
			expand = this.forename + " " +
				Messages.getMessage("module.skyski.msg.messages" + _currentLocale, "buy", null).getSummary() + ":\n";
		String currency = Messages.getMessage("module.skyski.msg.messages" + _currentLocale, "currency", null).getSummary();
		String pice = Messages.getMessage("module.skyski.msg.messages" + _currentLocale, "pice", null).getSummary();
		String kilogram = Messages.getMessage("module.skyski.msg.messages" + _currentLocale, "kilogram", null).getSummary();
		StringBuilder shippingList = new StringBuilder();
		for (String[] o : _dataList)
			shippingList = shippingList.append(o[0] + ". " + o[1].substring(0, 1).toUpperCase().
				concat(o[1].substring(1, o[1].length())) + ", " + o[2] + " " + currency +
					" * " + o[3] + " " + pice + "/" + kilogram + " = " + o[4] + " " + currency + "\n"
			);
		String ending = Messages.getMessage("module.skyski.msg.messages" + _currentLocale, "total", null).
			getSummary().toUpperCase().concat(": " + Double.toString(_sum) + " " + currency + "\n"
		);
		String greetings =
			"\n" + Messages.getMessage("module.skyski.msg.messages" + _currentLocale, "greetings", null
		).getSummary() + "!";
		String message = introduction + expand + shippingList.toString() + ending + greetings;
		this.mailSession.setDebug(true);
		MimeMessage mime = new MimeMessage(this.mailSession);
		mime.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.email, false));
		mime.setSubject(subjectTitle);		
		mime.setText(message);
		mime.setSentDate(new Date());
		mime.setHeader("Content-Type", "text/plain");
		mime.saveChanges();
		Transport trans = this.mailSession.getTransport();
		String serverUsername = this.mailSession.getProperty("mail.smtps.username");
		String serverPassword = this.mailSession.getProperty("mail.smtps.password");
		trans.connect(serverUsername, serverPassword);
		trans.sendMessage(mime, mime.getAllRecipients());
		trans.close();
		this.email = null;
		this.forename = null;
		this.login = null;
		this.passwd = null;
	}
	
	public void setPasswd(Character _passwd) { this.passwd = _passwd;	}
	public void setEmail(String _email) { this.email = _email; }
	public void setForename(String _forename) { this.forename = _forename; }
	public void setLogin(String _login) { this.login = _login; }

	@Resource(name="mail/skyskiGmail") private Session mailSession;
	private Character passwd;
	private String email;
	private String forename;
	private String login;
}
