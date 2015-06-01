package module.skyski.dto;

import java.io.Serializable;

public class MerchandiseDTO implements Serializable
{
	public MerchandiseDTO(Integer _id, String _name, Boolean _countable, Double _amount, Double _price)
	{
		this.id = _id;
		this.amount = _amount;
		this.countable = _countable;
		this.name = _name;
		this.price = _price;
	}
	
	public boolean isCountable() { return this.countable; }
	public void setCountable(boolean _countable) { this.countable = _countable; }
	public Integer getId() { return this.id; }
	public void setId(Integer _id) { this.id = _id; }
	public String getName() { return this.name; }
	public void setName(String _name) { this.name = _name; }
	public Double getAmount() { return this.amount; }
	public void setAmount(Double _amount) { this.amount = _amount; }
	public Double getPrice() { return this.price; }
	public void setPrice(Double _price) { this.price = _price; }
	
	private Integer id;
	private Boolean countable;
	private String name;
	private Double amount;
	private Double price;
	private static final long serialVersionUID = 1L;
}