package module.core.bb;

import module.core.bd.MailBD;
import module.core.dao.CartDAO;
import module.core.dcr.SortDataModel;
import module.core.dto.CartDTO;
import module.core.dto.ShippingDTO;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;
import java.io.Serializable;
import java.text.Collator;
import java.util.*;

@Named("cart")
@SessionScoped
public class CartBB implements Serializable
{
	public CartBB()
	{
		this.amountASC = false;
		this.nameASC = true;
		this.priceASC = false;
		this.sumASC = false;
	}
	
	public void refreshModel() { initialize(); }
	
	public DataModel<ShippingDTO> getFilterModel()
	{
		String actualLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
		if (!this.currentLocale.equals(actualLocale)) initialize();
		return this.filterModel;
	}
	
	public String process()
	{
		this.clientId = Integer.toString(this.authenticateObject.clientIdentification());
		this.cartObject.insert(new CartDTO(this.clientId, this.merchandiseId, this.amount));
		this.merchandiseObject.refreshModel();
		this.merchandiseId = null;
		this.amount = null;
		this.refreshModel();
		return null;
	}	
	
	public String remove()
	{
		this.clientId = Integer.toString(this.authenticateObject.clientIdentification());
		this.cartObject.relegate(this.id, this.clientId);
		this.merchandiseObject.refreshModel();
		this.refreshModel();
		return null;
	}
	
	public String send()
	{
		this.clientId = Integer.toString(this.authenticateObject.clientIdentification());
		this.authenticateObject.clientData(this.clientId);
		this.mailObject.setEmail(this.authenticateObject.getEmail());
		this.mailObject.setLogin(this.authenticateObject.getLogin());
		this.mailObject.setForename(this.authenticateObject.getForename());
		this.currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
		if (this.currentLocale.equals("en")) this.currentLocale = "_" + this.currentLocale;
		else if (this.currentLocale.equals("pl")) this.currentLocale = "_" + this.currentLocale;
		else if (this.currentLocale.equals("ru")) this.currentLocale = "_" + this.currentLocale;
		this.filterModel.sortBy(new Comparator<ShippingDTO>() {
			@Override public int compare(ShippingDTO __s1, ShippingDTO __s2)
			{ return Collator.getInstance(new Locale(currentLocale)).compare(__s1.getName(), __s2.getName()); }
		});		
		List<String[]> dataList = new ArrayList<String[]>();
		ShippingDTO sto;
		for (int i = 0; i < this.filterModel.getRowCount(); i++)
		{
			this.filterModel.setRowIndex(i);
			sto = this.filterModel.getRowData();
			String[] dataArray = new String[5];
			dataArray[0] = Integer.toString(i + 1);
			dataArray[1] = sto.getName();
			dataArray[2] = Double.toString(sto.getPrice());
			dataArray[3] = Double.toString(sto.getAmount());
			dataArray[4] = Double.toString(sto.getAmount() * sto.getPrice());
			dataList.add(dataArray);
		}		
		try { this.mailObject.cargoShipping(this.currentLocale, dataList, this.getSum()); }
		catch (MessagingException me) { System.out.println(me.toString()); }
		this.merchandiseObject.refreshModel();
		this.refreshModel();
		return null;
	}
	
	public void destroy()
	{
		this.filterModel = null;
		this.cartObject.getDestruction();
	}
	
	public void initialize()
	{
		if (this.clientId == null) this.clientId = Integer.toString(this.authenticateObject.clientIdentification());
		this.currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
		if (this.currentLocale.equals("pl")) this.cartObject.getInitialize("TransactionsPl", this.clientId);
		else if (this.currentLocale.equals("ru")) this.cartObject.getInitialize("TransactionsRu", this.clientId);
		else this.cartObject.getInitialize("TransactionsEn", this.clientId);
		Collections.sort(this.cartObject.getList(), new Comparator<ShippingDTO>() {
			@Override public int compare(ShippingDTO __s1, ShippingDTO __s2)
			{ return Collator.getInstance(new Locale(currentLocale)).compare(__s1.getName(), __s2.getName()); }
		});
		this.filterModel =  new SortDataModel<ShippingDTO>(
			new ArrayDataModel<ShippingDTO>(this.cartObject.getArray())
		);
	}
	
	public double getSum()
	{
		double sum = 0.0;
		for (ShippingDTO obj : this.cartObject.getList()) sum += obj.getPrice() * obj.getAmount();
		return sum;
	}
	
	public String sortByAmount()
	{
		this.amountASC = !this.amountASC;
		this.nameASC = false;
		this.priceASC = false;
		this.sumASC = false;
		this.filterModel.sortBy(new Comparator<ShippingDTO>() {
			@Override public int compare(ShippingDTO __s1, ShippingDTO __s2)
			{
				if (amountASC)
				{
					if ((Boolean.valueOf(__s1.getCountable()) && !Boolean.valueOf(__s2.getCountable()))) return -1;
					else if ((!Boolean.valueOf(__s1.getCountable()) && Boolean.valueOf(__s2.getCountable()))) return 1;
					else return (int)Math.ceil(__s2.getAmount() - __s1.getAmount());
				}
				else
				{
					if ((Boolean.valueOf(__s1.getCountable()) && !Boolean.valueOf(__s2.getCountable()))) return -1;
					else if ((!Boolean.valueOf(__s1.getCountable()) && Boolean.valueOf(__s2.getCountable()))) return 1;
					else return (int)Math.ceil(__s1.getAmount() - __s2.getAmount());
				}
			}
		});
		return null;
	}
	
	public String sortByName()
	{
		this.amountASC = false;
		this.nameASC = !this.nameASC;
		this.priceASC = false;
		this.sumASC = false;
		this.filterModel.sortBy(new Comparator<ShippingDTO>() {
			@Override public int compare(ShippingDTO __s1, ShippingDTO __s2)
			{
				if (nameASC)
					return Collator.getInstance(new Locale(currentLocale)).compare(__s1.getName(), __s2.getName());
				else return Collator.getInstance(new Locale(currentLocale)).compare(__s2.getName(), __s1.getName());
			}
		});
		return null;
	}
	
	public String sortByPrice()
	{
		this.amountASC = false;
		this.nameASC = false;
		this.priceASC = !this.priceASC;
		this.sumASC = false;
		this.filterModel.sortBy(new Comparator<ShippingDTO>() {
			@Override public int compare(ShippingDTO __s1, ShippingDTO __s2) {
				if (priceASC) return (__s2.getPrice() - __s1.getPrice()) < 0 ? -1 : 1;
				else return (__s1.getPrice() - __s2.getPrice()) < 0 ? -1 : 1;
			}
		});
		return null;
	}
	
	public String sortBySum()
	{
		this.amountASC = false;
		this.nameASC = false;
		this.priceASC = false;
		this.sumASC = !this.sumASC;
		this.filterModel.sortBy(new Comparator<ShippingDTO>() {
			@Override public int compare(ShippingDTO __s1, ShippingDTO __s2)
			{
				if (sumASC)
					return ((__s2.getPrice() * __s2.getAmount()) - (__s1.getPrice() * __s1.getAmount())) < 0 ? -1 : 1;
				else return ((__s1.getPrice() * __s1.getAmount()) - (__s2.getPrice() * __s2.getAmount())) < 0 ? -1 : 1;
			}
		});
		return null;
	}
	
	public Integer getId() { return this.id; }
	public void setId(Integer _id) { this.id = _id; }
	public String getClientId() { return this.clientId; }
	public void setClientId(String _clientId) { this.clientId = _clientId; }
	public String getMerchandiseId() { return this.merchandiseId; }
	public void setMerchandiseId(String _merchandiseId) { this.merchandiseId = _merchandiseId; }
	public String getAmount() { return this.amount; }
	public void setAmount(String _amount) { this.amount = _amount; }

	@EJB private CartDAO cartObject;
	@EJB private MailBD mailObject;
	@Inject private AuthenticateBB authenticateObject;
	@Inject private MerchandiseBB merchandiseObject;
	private SortDataModel<ShippingDTO> filterModel;
	private Integer id;
	private String amount;
	private String clientId;
	private String merchandiseId;
	private String currentLocale;
	private boolean amountASC;
	private boolean nameASC;
	private boolean priceASC;
	private boolean sumASC;
	private static final long serialVersionUID = 1L;
}   