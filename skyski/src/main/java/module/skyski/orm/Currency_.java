package module.skyski.orm;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-26T23:38:58.150+0100")
@StaticMetamodel(Currency.class)
public class Currency_ {
	public static volatile SingularAttribute<Currency, Integer> id;
	public static volatile SingularAttribute<Currency, Character[]> code;
	public static volatile SingularAttribute<Currency, String> locale;
	public static volatile SingularAttribute<Currency, Double> rate;
	public static volatile SingularAttribute<Currency, String> symbol;
	public static volatile SingularAttribute<Currency, String> noun;
	public static volatile SingularAttribute<Currency, String> adjective;
}
