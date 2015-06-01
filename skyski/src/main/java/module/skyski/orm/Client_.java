package module.skyski.orm;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-04-05T20:19:36.811+0200")
@StaticMetamodel(Client.class)
public class Client_ {
	public static volatile SingularAttribute<Client, Integer> id;
	public static volatile SingularAttribute<Client, String> city;
	public static volatile SingularAttribute<Client, Boolean> confirmed;
	public static volatile SingularAttribute<Client, Date> dateOfBirth;
	public static volatile SingularAttribute<Client, String> email;
	public static volatile SingularAttribute<Client, String> login;
	public static volatile SingularAttribute<Client, String> passwd;
	public static volatile SingularAttribute<Client, String> phone;
	public static volatile SingularAttribute<Client, String> postalCode;
	public static volatile SingularAttribute<Client, String> street;
	public static volatile SingularAttribute<Client, String> forename;
	public static volatile SingularAttribute<Client, String> surname;
	public static volatile ListAttribute<Client, Transactions> transactions;
}
