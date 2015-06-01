package module.skyski.orm;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="currency")
@NamedQuery(name="Currency.findAll", query="SELECT c FROM Currency c")
public class Currency implements Serializable
{
	public Currency() {}

	public Integer getId() { return this.id; }
	public void setId(Integer _id) { this.id = _id; }
	public Character[] getCurrency() { return this.code;	}
	public void setCurrency(Character[] _currency) { this.code = _currency; }
	public String getLocale() { return this.locale; }
	public void setLocale(String _locale) { this.locale = _locale; }
	public double getRate() { return this.rate; }
	public void setRate(double _rate) { this.rate = _rate; }
	public String getSymbol() { return this.symbol; }
	public void setSymbol(String _symbol) { this.symbol = _symbol; }
	public String getNoun() { return this.noun; }
	public void setNoun(String _noun) { this.noun = _noun; }
	public String getAdjective() { return this.adjective; }
	public void setAdjective(String _adjective) { this.adjective = _adjective; }

	@Column(name="id") @Id	private Integer id;
	@Column(name="code") private Character[] code;
	@Column(name="locale") private String locale;
	@Column(name="rate") private Double rate;
	@Column(name="symbol") private String symbol;
	@Column(name="noun") private String noun;
	@Column(name="adjective") private String adjective;
	private static final long serialVersionUID = 1L;
}