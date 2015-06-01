package module.skyski.orm;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-20T17:12:35.268+0100")
@StaticMetamodel(Authors.class)
public class Authors_ {
	public static volatile SingularAttribute<Authors, Integer> id;
	public static volatile SingularAttribute<Authors, String> forename;
	public static volatile SingularAttribute<Authors, String> surname;
	public static volatile ListAttribute<Authors, Books> books;
}
