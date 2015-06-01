package module.skyski.bb;

import module.skyski.dao.MerchandiseDAO;
import module.skyski.dcr.SortDataModel;
import module.skyski.dto.MerchandiseDTO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.io.Serializable;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

@Named("merchandise")
@SessionScoped
public class MerchandiseBB implements Serializable, Validator
{
	public MerchandiseBB()
	{
		this.amountASC = false;
		this.nameASC = true;
		this.priceASC = false;
	}
	
	@Override public void validate(FacesContext _context, UIComponent _component, Object _value)
		throws ValidatorException {}
	
	@PostConstruct private void initialize()
	{
		this.currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
		if (this.currentLocale.equals("pl")) this.merchandiseList.getInitialize("MerchandisePl");
		else if (this.currentLocale.equals("ru")) this.merchandiseList.getInitialize("MerchandiseRu");
		else this.merchandiseList.getInitialize("MerchandiseEn");
		Collections.sort(this.merchandiseList.getList(), new Comparator<MerchandiseDTO>()
		{
			@Override public int compare(MerchandiseDTO __m1, MerchandiseDTO __m2)
			{ return Collator.getInstance(new Locale(currentLocale)).compare(__m1.getName(), __m2.getName()); }
		});
		this.filterModel = new SortDataModel<MerchandiseDTO>(
			new ArrayDataModel<MerchandiseDTO>(this.merchandiseList.getArray())
		);
	}
	
	public void validateProduct(FacesContext _context, UIComponent _component, Object _value)
		throws ValidatorException
	{
			boolean refreshFlag = false;
			StringBuilder product = new StringBuilder(_value.toString());
			product = this.checkOrder(product);
			if (!product.toString().equals(_value.toString())) refreshFlag = true;
			if (refreshFlag)
			{
				UIInput inputComponent = (UIInput)_component;
				inputComponent.resetValue();	
				inputComponent.setValue(product);
				FacesMessage facesMessage = new FacesMessage();
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(facesMessage);
			}
	}
	
	public void refreshModel() { this.initialize(); }
	
	public DataModel<MerchandiseDTO> getFilterModel()
	{
		String actualLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
		if (!this.currentLocale.equals(actualLocale)) this.initialize();
		return this.filterModel;
	}
	
	public String sortByAmount()
	{
		this.amountASC = !this.amountASC;
		this.nameASC = false;
		this.priceASC = false;
		this.filterModel.sortBy(new Comparator<MerchandiseDTO>() {
			@Override public int compare(MerchandiseDTO __m1, MerchandiseDTO __m2)
			{
				if (amountASC)
				{
					if ((__m1.isCountable() && !__m2.isCountable())) return -1;
					else if ((!__m1.isCountable() && __m2.isCountable())) return 1;
					else return (int)Math.ceil(__m2.getAmount() - __m1.getAmount());
				}
				else {
					if ((__m1.isCountable() && !__m2.isCountable())) return -1;
					else if ((!__m1.isCountable() && __m2.isCountable())) return 1;
					else return (int)Math.ceil(__m1.getAmount() - __m2.getAmount());
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
		this.filterModel.sortBy(new Comparator<MerchandiseDTO>() {
			@Override public int compare(MerchandiseDTO __m1, MerchandiseDTO __m2) {
				if (nameASC)
					return Collator.getInstance(new Locale(currentLocale)).compare(__m1.getName(), __m2.getName());
				else return Collator.getInstance(new Locale(currentLocale)).compare(__m2.getName(), __m1.getName());
			}
		});
		return null;
	}
	
	public String sortByPrice()
	{
		this.amountASC = false;
		this.nameASC = false;
		this.priceASC = !this.priceASC;
		this.filterModel.sortBy(new Comparator<MerchandiseDTO>() {
			@Override public int compare(MerchandiseDTO __m1, MerchandiseDTO __m2)
			{
				if (priceASC) return (__m2.getPrice() - __m1.getPrice()) < 0 ? -1 : 1;
				else return (__m1.getPrice() - __m2.getPrice()) < 0 ? -1 : 1;
			}
		});
		return null;
	}
	
	private StringBuilder checkOrder(StringBuilder _wordOrder)
	{
		if (!Character.isDigit(_wordOrder.charAt(0)))
		{
			_wordOrder = _wordOrder.deleteCharAt(0);
			if (_wordOrder.length() > 0) _wordOrder = checkOrder(_wordOrder);
		}
		boolean dotFlag = false;
		if (_wordOrder.length() > 1) for (int i = 1; i < _wordOrder.length(); i++) 
			if (Character.isDigit(_wordOrder.charAt(i))) continue;
			else if (_wordOrder.charAt(i) == '.' && !dotFlag) dotFlag = true;
			else if (_wordOrder.charAt(i) == '.' && dotFlag) {
				dotFlag = false;
				_wordOrder = _wordOrder.deleteCharAt(i);
			}
			else _wordOrder = _wordOrder.deleteCharAt(i);
		return _wordOrder;
	}
	
	@EJB private MerchandiseDAO merchandiseList;
	private SortDataModel<MerchandiseDTO> filterModel;
	private String currentLocale;
	private boolean amountASC;
	private boolean nameASC;
	private boolean priceASC;
	private static final long serialVersionUID = 1L;
}