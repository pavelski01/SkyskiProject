package module.core.dao;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import module.core.dto.AuthenticateDTO;

@Stateful public class AuthenticateDAO
{
	public AuthenticateDAO() {}
	
	public Boolean check(AuthenticateDTO _obj)
	{
		String password = "";
		Boolean flag = false;
		try
		{
			password = this.em.createNativeQuery(
				"SELECT passwd FROM Clients WHERE login = '" + _obj.getLogin() + "'"
			).getSingleResult().toString();
		}
		catch (NoResultException nre) { System.out.println(nre.toString()); }
		if (password.equals(_obj.getPassword())) flag = true;
		return flag;
	}
	
	public Object[] testification(String _id)
	{
		Object[] result;
		try
		{
			result = (Object[])this.em.createNativeQuery(
				"SELECT login, forename, email FROM Clients WHERE id = " + _id + ""
			).getSingleResult();
			return result;
		}
		catch (NoResultException nre) { System.out.println(nre.toString()); }
		return null;
	}
	
	public Integer identification(AuthenticateDTO _obj)
	{
		Integer clientId = null;
		try
		{
			clientId = (Integer)this.em.createNativeQuery(
				"SELECT id FROM Clients WHERE login = '" + _obj.getLogin() + "'"
			).getSingleResult();
		}
		catch (NoResultException nre) { System.out.println(nre.toString()); }
		return clientId;
	}
	
	@PersistenceContext(name="persistence/skyski", unitName="skyskiPu") private EntityManager em;
}
