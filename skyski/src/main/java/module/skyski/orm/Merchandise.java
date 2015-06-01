package module.skyski.orm;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="Merchandise.findAll", query="SELECT m FROM Merchandise m"),
	@NamedQuery(
		name="MerchandiseEn",
		query="SELECT m.id, m.name, m.countable, m.amount, m.price FROM Merchandise m"
	),
	@NamedQuery(
		name="MerchandisePl",
		query="SELECT m.id, mpl.name, m.countable, m.amount, m.price FROM Merchandise m JOIN m.merchandisePl mpl"
	),
	@NamedQuery(
		name="MerchandiseRu",
		query="SELECT m.id, mru.name, m.countable, m.amount, m.price FROM Merchandise m JOIN m.merchandiseRu mru"
	)
})
@Table(name="merchandise")
public class Merchandise implements Serializable
{
	public Merchandise() {}

	public Integer getId() { return this.id; }
	public void setId(Integer _id) { this.id = _id; }
	public Double getAmount() { return this.amount;	}
	public void setAmount(Double _amount) { this.amount = _amount; }
	public Boolean getCountable() {	return this.countable; }
	public void setCountable(Boolean _countable) { this.countable = _countable; }
	public String getName() { return this.name;	}
	public void setName(String _name) { this.name = _name; }
	public Double getPrice() { return this.price; }
	public void setPrice(Double _price) { this.price = _price; }
	public MerchandisePl getMerchandisePl() { return this.merchandisePl; }
	public void setMerchandisePl(MerchandisePl _merchandisePl) { this.merchandisePl = _merchandisePl; }
	public MerchandiseRu getMerchandiseRu() { return this.merchandiseRu; }
	public void setMerchandiseRu(MerchandiseRu _merchandiseRu) { this.merchandiseRu = _merchandiseRu; }
	public Transactions getTransactions() { return this.transactions; }
	public void setTransactions(Transactions _transactions) { this.transactions = _transactions; }
	
	@Column(name="id") @Id private Integer id;
	@Column(name="amount") private Double amount;
	@Column(name="countable") private Boolean countable;
	@Column(name="name") private String name;
	@Column(name="price") private Double price;
	@OneToOne(mappedBy="merchandise") private MerchandisePl merchandisePl;
	@OneToOne(mappedBy="merchandise") private MerchandiseRu merchandiseRu;
	@OneToOne(mappedBy="merchandise") private Transactions transactions;
	private static final long serialVersionUID = 1L;
}