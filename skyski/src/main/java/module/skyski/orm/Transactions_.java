package module.skyski.orm;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-04-05T19:38:44.504+0200")
@StaticMetamodel(Transactions.class)
public class Transactions_ {
	public static volatile SingularAttribute<Transactions, Integer> id;
	public static volatile SingularAttribute<Transactions, Double> amount;
	public static volatile SingularAttribute<Transactions, Integer> clientId;
	public static volatile SingularAttribute<Transactions, Integer> merchandiseId;
	public static volatile SingularAttribute<Transactions, Merchandise> merchandise;
	public static volatile SingularAttribute<Transactions, Client> client;
}
