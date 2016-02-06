package module.core.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import module.core.dto.QuestionnaireDTO;

import java.text.DecimalFormat;
import java.util.Calendar;

@Stateless public class QuestionnaireDAO
{
	public QuestionnaireDAO() {}
	
	public Boolean check(String _key, String _value)
	{
		Boolean flag = false;
		try
		{
			flag = !Boolean.valueOf(this.em.createNativeQuery(
				"SELECT " + _key + " FROM Clients WHERE " + _key + "='" + _value + "'"
			).getSingleResult().toString());
		} catch (NoResultException nre) { System.out.println(nre.toString()); }
		return flag;	
	}
	
	public void insert(QuestionnaireDTO _obj)
	{
		StringBuilder columns = new StringBuilder("login, passwd, email");
		StringBuilder values = new StringBuilder(
			"'" + _obj.getLogin() + "','" + _obj.getPassword() + "','" + _obj.getEmail() + "'"
		);
		if (!_obj.getFname().equals(""))
		{
			columns.append(", forename");
			values.append(", '" + _obj.getFname() + "'");
		}
		if (!_obj.getLname().equals(""))
		{
			columns.append(", surname");
			values.append(", '" + _obj.getLname() + "'");
		}
		if (!_obj.getPhone().equals(""))
		{
			columns.append(", phone");
			values.append(", '" + _obj.getPhone() + "'");
		}
		if (!_obj.getStreet().equals(""))
		{
			columns.append(", street");
			values.append(", '" + _obj.getStreet() + "'");
		}
		if (!_obj.getPostalCode().equals(""))
		{
			columns.append(", postal_code");
			values.append(", '" + _obj.getPostalCode() + "'");
		}
		if (!_obj.getCityAddress().equals(""))
		{
			columns.append(", city");
			values.append(", '" + _obj.getCityAddress() + "'");
		}
		if (_obj.getDateOfBirth() != null)
		{
			columns.append(", date_of_birth");
			values.append(
				", '" + _obj.getDateOfBirth().get(Calendar.YEAR) +
					"-" + new DecimalFormat("00").format(_obj.getDateOfBirth().get(Calendar.MONTH) + 1) + 
						"-" + new DecimalFormat("00").format(_obj.getDateOfBirth().get(Calendar.DAY_OF_MONTH)) + "'"
			);
		}
		columns.append(", confirmed");
		values.append(", FALSE");
		this.em.createNativeQuery("INSERT INTO Clients(" + columns.toString() + ") " +
			"VALUES (" + values.toString() + ")"
		).executeUpdate();
	}
	
	@PersistenceContext(name="persistence/skyski", unitName="skyskiPu") private EntityManager em;
}