package module.skyski.dto;

import java.io.Serializable;

public class CartDTO implements Serializable
{
	public CartDTO(String _clientId, String _merchandiseId, String _amount)
	{
		this.clientId = _clientId;
		this.merchandiseId = _merchandiseId;
		this.amount = _amount;
	}	
	
	public String getClientId() { return this.clientId; }
	public void setClientId(String _clientId) { this.clientId = _clientId; }
	public String getMerchandiseId() { return this.merchandiseId; }
	public void setMerchandiseId(String _merchandiseId) { this.merchandiseId = _merchandiseId; }
	public String getAmount() { return this.amount; }
	public void setAmount(String _amount) { this.amount = _amount; }
	
	private String clientId;
	private String merchandiseId;
	private String amount;
	private static final long serialVersionUID = 1L;
}