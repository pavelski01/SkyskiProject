package module.core.intl;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages
{
	public static FacesMessage getMessage(String _bundle, String _resourceId, Object[] _params)
	{
		ClassLoader loader;
		FacesContext facesContext;
		Locale locale;
		String summary;
		facesContext = FacesContext.getCurrentInstance();
		locale = (facesContext.getViewRoot() == null) ?
			Locale.getDefault() : facesContext.getViewRoot().getLocale();
		loader = (Thread.currentThread().getContextClassLoader() == null) ?
			ClassLoader.getSystemClassLoader() : Thread.currentThread().getContextClassLoader();
		summary = getMsg(_bundle, _resourceId, locale, loader, _params);
		if (summary == null) summary = "???" + _resourceId + "???";
		return new FacesMessage(summary);
	}
	
	private static String getMsg(
		String _bundleID, String _resourceID, Locale _locale, ClassLoader _loader, Object[] _params
	)
	{
		ResourceBundle bundle;
		MessageFormat formatter;
		String resource = null;
		try
		{
			bundle = ResourceBundle.getBundle(_bundleID, _locale, _loader);
			resource = bundle.getString(_resourceID);
		}
		catch (MissingResourceException mre) { System.out.println(mre.toString()); }
		if (resource == null) return null;
		if (_params == null) return resource;
		formatter = new MessageFormat(resource, _locale);
		return formatter.format(_params);
	}
}