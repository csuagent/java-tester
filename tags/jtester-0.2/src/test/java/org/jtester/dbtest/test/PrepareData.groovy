/**
 * 
 */
package org.jtester.dbtest.test

import org.jtester.dbtest.bean.User;
import org.hibernate.Session;
import org.jtester.dbtest.bean.Address;

/**
 * @author davey.wu  
 *
 */
public class PrepareData
{
	public static void prepare(Session session)
	{
		(1..3).each
				{ 	User user = new User();  
					user.id = it;
					user.email = "mail${it}@test.com";
					user.name = "name${it}";
					(1..3).each{
						Address address = new Address();
						address.id = user.id * 3 + it;
						address.user = user;
						address.city = "hz";

						session.save(address);
					} 
					session.save(user);
				}
		session.flush();
		session.clear();
	}
}
