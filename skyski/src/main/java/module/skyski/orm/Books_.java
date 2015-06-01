package module.skyski.orm;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-20T17:12:35.535+0100")
@StaticMetamodel(Books.class)
public class Books_ {
	public static volatile SingularAttribute<Books, Integer> id;
	public static volatile SingularAttribute<Books, Integer> edition;
	public static volatile SingularAttribute<Books, String> placeOfPublication;
	public static volatile SingularAttribute<Books, String> title;
	public static volatile SingularAttribute<Books, Integer> volume;
	public static volatile SingularAttribute<Books, Integer> yearOfPublication;
	public static volatile ListAttribute<Books, Authors> authors;
}
