package module.skyski.dto;

import java.io.Serializable;

public class CurrencyDTO implements Serializable
{
	public CurrencyDTO(
		Integer _id, Character[] _code, String _locale, Double _rate,
		String _symbol, String _noun, String _adjective
	)
	{
		this.id = _id;
		this.code = _code;
		this.locale = _locale;
		this.rate = _rate;
		this.symbol = _symbol;
		this.noun = _noun;
		this.adjective = _adjective;
	}
	
	public Integer getId() { return this.id;	}
	public void setId(Integer _id) {	this.id = _id; }
	public Character[] getCode() { return this.code; }
	public void setCode(Character[] _code) { this.code = _code; }
	public String getLocale() { return this.locale; }
	public void setLocale(String _locale) { this.locale = _locale; }
	public Double getRate() { return this.rate; }
	public void setRate(Double _rate) { this.rate = _rate; }
	public String getSymbol() { return this.symbol; }
	public void setSymbol(String _symbol) { this.symbol = _symbol; }
	public String getNoun() { return this.noun; }
	public void setNoun(String _noun) { this.noun = _noun; }
	public String getAdjective() { return this.adjective; }
	public void setAdjective(String _adjective) { this.adjective = _adjective; }

	private Character[] code;
	private Integer id;
	private String locale;
	private Double rate;
	private String symbol;
	private String noun;
	private String adjective;
	private static final long serialVersionUID = 1L;
}