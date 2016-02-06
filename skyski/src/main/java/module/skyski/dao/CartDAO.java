package module.skyski.dao;

import module.skyski.bb.CurrencyBB;
import module.skyski.dto.CartDTO;
import module.skyski.dto.ShippingDTO;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Stateful
@TransactionManagement(TransactionManagementType.BEAN)
public class CartDAO implements Serializable
{
	public CartDAO() {}
	
	public void insert(CartDTO _obj)
	{
		boolean committed = false;
		Integer id = null;
		UserTransaction ut = this.ejbContext.getUserTransaction();
		try
		{
			ut.begin();
			try
			{
				id = (Integer)this.em.createNativeQuery(
					"SELECT id FROM Merchandise WHERE id = " + _obj.getMerchandiseId() +
						" AND amount >= " + _obj.getAmount() + ""
				).getSingleResult();
			}
			catch (NoResultException nre) { System.out.println(nre.toString()); }
			if (id != null)
			{
				id = null;
				try
				{
					id = (Integer)this.em.createNativeQuery(
						"SELECT id FROM Transactions WHERE client_id = " + _obj.getClientId() +
							" AND merchandise_id = " + _obj.getMerchandiseId() + ""
					).getSingleResult();
				}
				catch (NoResultException nre) { System.out.println(nre.toString()); }
				if (id == null)
					this.em.createNativeQuery(
						"INSERT INTO Transactions(client_id, merchandise_id, amount) VALUES ('" +
							_obj.getClientId() + "','" + _obj.getMerchandiseId() + "','" + _obj.getAmount() + "')"
					).executeUpdate();
				else
					this.em.createNativeQuery("UPDATE Transactions SET amount = amount + " +
						_obj.getAmount() + " WHERE id = " + id + ""
					).executeUpdate();
				this.em.createNativeQuery(
					"UPDATE Merchandise SET amount = amount - " + _obj.getAmount() +
						" WHERE id = " + _obj.getMerchandiseId() + ""
				).executeUpdate();
			}
			ut.commit();
			committed = true;
		}
		catch (HeuristicMixedException hme) { System.out.println(hme.toString()); }
		catch (HeuristicRollbackException hre) { System.out.println(hre.toString()); }
		catch (IllegalStateException ise) { System.out.println(ise.toString()); }
		catch (NotSupportedException nse) { System.out.println(nse.toString()); }
		catch (RollbackException re) { System.out.println(re.toString()); }
		catch (SecurityException se) { System.out.println(se.toString()); }
		catch (SystemException se) { System.out.println(se.toString()); }
		if (!committed)
		{
			try { ut.rollback(); }
			catch (IllegalStateException ise) { System.out.println(ise.toString()); }
			catch (SecurityException se) { System.out.println(se.toString()); }
			catch (SystemException se) { System.out.println(se.toString()); }
		}
	}
	
	public void relegate(Integer _id, String _clientId)
	{
		boolean committed = false;
		double quantity;
		UserTransaction ut = this.ejbContext.getUserTransaction();
		try
		{
			ut.begin();			
			quantity = (Double)this.em.createNativeQuery(
				"SELECT amount FROM Transactions WHERE merchandise_id=" +
					Integer.toString(_id) + " AND client_id = " + _clientId
			).getSingleResult();
			this.em.createNativeQuery(
				"UPDATE Merchandise SET amount = amount + " + quantity + " WHERE id = " + Integer.toString(_id)
			).executeUpdate();
			this.em.createNativeQuery(
				"DELETE FROM Transactions WHERE merchandise_id = " + Integer.toString(_id) +
					" AND client_id=" + _clientId + ""
			).executeUpdate();
			ut.commit();
			committed = true;
		}
		catch (HeuristicMixedException hme) { System.out.println(hme.toString()); }
		catch (HeuristicRollbackException hre) { System.out.println(hre.toString()); }
		catch (IllegalStateException ise) { System.out.println(ise.toString()); }
		catch (NotSupportedException nse) { System.out.println(nse.toString()); }
		catch (RollbackException re) { System.out.println(re.toString()); }
		catch (SecurityException se) { System.out.println(se.toString()); }
		catch (SystemException se) { System.out.println(se.toString()); }
		if (!committed)
		{
			try { ut.rollback(); } 
			catch (IllegalStateException ise) { System.out.println(ise.toString()); }
			catch (SecurityException se) { System.out.println(se.toString()); }
			catch (SystemException se) { System.out.println(se.toString()); }
		}
	} 
	
	public void getInitialize(String _transactionsLang, String _programClientId)
	{
		List<Object[]> resultList = this.em.createNamedQuery(_transactionsLang, Object[].class).
			setParameter("programClientId", Integer.parseInt(_programClientId)).getResultList();
		this.transactionList = new ArrayList<ShippingDTO>(resultList.size());
		for (Object[] result : resultList) this.transactionList.add(
			new ShippingDTO(
				(Integer)result[0],
				result[1].toString(),
				Math.round((Double)result[2] * this.currencyObject.getRate() * 100.0) / 100.0,
				Math.round((Double)result[3] * 100.0) / 100.0,
				Boolean.toString((Boolean)result[4])
			)
		);
		Collections.sort(this.transactionList, new Comparator<ShippingDTO>() {
			@Override public int compare(ShippingDTO __s1, ShippingDTO __s2)
			{ return __s1.getName().compareTo(__s2.getName()); }
		});
	}
	
	public void getDestruction() { this.transactionList = null; }
	public ShippingDTO[] getArray()
	{ return this.transactionList.toArray(new ShippingDTO[this.transactionList.size()]); }
	public List<ShippingDTO> getList() { return this.transactionList; }
	
	@Inject private CurrencyBB currencyObject;
	@PersistenceContext(name="persistence/skyski", unitName="skyskiPu") private EntityManager em;
	@Resource private SessionContext ejbContext;
	private List<ShippingDTO> transactionList;
	private static final long serialVersionUID = 1L;
}