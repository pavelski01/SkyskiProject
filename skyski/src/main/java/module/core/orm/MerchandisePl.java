package module.core.orm;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NamedQuery(name="MerchandisePl.findAll", query="SELECT m FROM MerchandisePl m")
@Table(name="merchandise_pl")
public class MerchandisePl implements Serializable
{
	public MerchandisePl() {}

	public Integer getId() { return this.id; }
	public void setId(Integer _id) { this.id = _id; }
	public String getName() { return this.name; }
	public void setName(String _name) { this.name = _name; }
	public Merchandise getMerchandise() { return this.merchandise; }
	public void setMerchandise(Merchandise _merchandise) { this.merchandise = _merchandise; }

	@Column(name="id") @Id private Integer id;
	@Column(name="name") private String name;
	@JoinColumn(name="id", insertable=false, updatable=false)
	@OneToOne
	private Merchandise merchandise;
	private static final long serialVersionUID = 1L;
}
