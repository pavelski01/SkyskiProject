package module.skyski.orm;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-04-02T13:39:32.805+0200")
@StaticMetamodel(Merchandise.class)
public class Merchandise_ {
	public static volatile SingularAttribute<Merchandise, Integer> id;
	public static volatile SingularAttribute<Merchandise, Double> amount;
	public static volatile SingularAttribute<Merchandise, Boolean> countable;
	public static volatile SingularAttribute<Merchandise, String> name;
	public static volatile SingularAttribute<Merchandise, Double> price;
	public static volatile SingularAttribute<Merchandise, MerchandisePl> merchandisePl;
	public static volatile SingularAttribute<Merchandise, MerchandiseRu> merchandiseRu;
	public static volatile SingularAttribute<Merchandise, Transactions> transactions;
}
