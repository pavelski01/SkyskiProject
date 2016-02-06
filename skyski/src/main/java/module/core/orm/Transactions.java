package module.core.orm;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="transactions")
@NamedQueries({
	@NamedQuery(name="Transactions.findAll", query="SELECT t FROM Transactions t"),
	@NamedQuery(
		name="TransactionsEn",
		query=
			"SELECT m.id, m.name, m.price, t.amount, m.countable " +
			"FROM Transactions t JOIN t.merchandise m WHERE t.clientId = :programClientId"
	),
	@NamedQuery(
		name="TransactionsPl",
		query=
			"SELECT m.id, mpl.name, m.price, t.amount, m.countable " +
			"FROM Transactions t JOIN t.merchandise m JOIN m.merchandisePl mpl WHERE t.clientId = :programClientId"
	),
	@NamedQuery(
		name="TransactionsRu",
		query=
			"SELECT m.id, mru.name, m.price, t.amount, m.countable " +
			"FROM Transactions t JOIN t.merchandise m JOIN m.merchandiseRu mru WHERE t.clientId = :programClientId"
	)
})
public class Transactions implements Serializable
{
	public Transactions() {}

	public Integer getId() { return this.id; }
	public void setId(Integer _id) { this.id = _id; }
	public double getAmount() {	return this.amount;	}
	public void setAmount(double _amount) { this.amount = _amount; }
	public Integer getClientId() { return this.clientId; }
	public void setClientId(Integer _clientId) { this.clientId = _clientId; }
	public Integer getMerchandiseId() { return this.merchandiseId; }
	public void setMerchandiseId(Integer _merchandiseId) { this.merchandiseId = _merchandiseId; }
	public Merchandise getMerchandise() { return this.merchandise; }
	public void setMerchandise(Merchandise _merchandise) { this.merchandise = _merchandise; }
	
	@Column(name="id") @Id private Integer id;
	@Column(name="amount") private double amount;
	@Column(name="client_id") private Integer clientId;
	@Column(name="merchandise_id") private Integer merchandiseId;
	@JoinColumn(name="merchandise_id", insertable=false, updatable=false)
	@OneToOne
	private Merchandise merchandise;
	@JoinColumn(name="client_id", insertable=false, updatable=false)
	@ManyToOne
	private Client client;
	private static final long serialVersionUID = 1L;
}