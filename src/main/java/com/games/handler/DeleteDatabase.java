package com.games.handler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class DeleteDatabase {

	public static void main(String[] args) {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		
		Session session = sessionFactory.openSession();

		//FIXME: This is probably the wrong approach to dropping the tables.
		//Hybernate is first creating or altering the tables and then they get dropped here 
		
		String[] dropQueries = new String[] {
				"DROP TABLE teams_coaches",
				"DROP TABLE teams_players",
				"DROP TABLE players_parents",
				"DROP TABLE teams",
				"DROP TABLE players",
				"DROP TABLE parents",
				"DROP TABLE coaches",
		};

		for (String sqlQuery : dropQueries ) {
			Transaction transaction = session.beginTransaction();
			//createNativeQuery is deprecated
			Query query = session.createNativeQuery(sqlQuery);
			int result = query.executeUpdate(); // Execute the drop table statement
			session.getTransaction().commit();
			System.out.println("Table my_example_table dropped successfully.");
		}
		session.close();
		sessionFactory.close();
	}

}
