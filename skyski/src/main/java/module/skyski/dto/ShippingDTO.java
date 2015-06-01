package module.skyski.dto;

import java.io.Serializable;

public class ShippingDTO implements Serializable
{
	public ShippingDTO(Integer _id, String _name, Double _price, Double _amount, String _countable)
	{
		this.id = _id;
		this.name = _name;
		this.price = _price;
		this.amount = _amount;
		this.countable = _countable;
	}
	
	public Integer getId() { return this.id; }
	public void setId(Integer _id) { this.id = _id; }
	public String getName() { return this.name; }
	public void setName(String _name) { this.name = _name; }
	public Double getPrice() { return this.price; }
	public void setPrice(Double _price) { this.price = _price; }
	public Double getAmount() { return this.amount; }
	public void setAmount(Double _amount) { this.amount = _amount; }
	public String getCountable() { return this.countable; }
	public void setCountable(String _countable) { this.countable = _countable; }
	
	private Integer id;
	private String name;
	private Double price;
	private Double amount;
	private String countable;
	private static final long serialVersionUID = 1L;
}