package com.games.handler;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.games.bean.Coach;
import com.games.bean.Player;
import com.games.bean.Team;

import jakarta.persistence.TypedQuery;

public class ShowTeams {
	public static void main(String[] args) {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();

		Session session = sessionFactory.openSession();
		// Transaction transaction = session.beginTransaction();

		String hql = "FROM Team";
		List<Team> teams = session.createQuery(hql, Team.class).getResultList();

		System.out.println("Teams:");
		for (Team team : teams) {
			System.out.println(team.toString());
			List<Player> players = team.getPlayers();
			System.out.println("Players: ");
			for (Player player : players) {
				System.out.println(player.toString());
			}
			List<Coach> coaches = team.getCoaches();
			System.out.println("Coaches: ");
			for (Coach coach : coaches) {
				System.out.println(coach.toString());
			}
			System.out.println("-------------------------------------------------");
		}
		System.out.println("=================================================");

//		System.out.println("Parents:");
//		String hql2 = "FROM Parent";
//		List<Parent> parents = session.createQuery(hql2, Parent.class).getResultList();
//		for (Parent parent : parents) {
//			System.out.println(parent.toString());
//			List<Player> players = parent.getPlayers();
//			System.out.println("Players: ");
//			for (Player player : players) {
//				System.out.println(player.toString());
//			}
//			System.out.println("-------------------------------------------------");
//		}
//		System.out.println("=================================================");
//
//		System.out.println("Players:");
//		String hql3 = "FROM Player";
//		List<Player> players = session.createQuery(hql3, Player.class).getResultList();
//		for (Player player : players) {
//			System.out.println(player.toString());
//			List<Parent> parents2 = player.getParents();
//			for (Parent parent : parents2) {
//				System.out.println(parent.toString());
//			}
//			System.out.println("-------------------------------------------------");
//		}
//		System.out.println("=================================================");

		//now use the named query
		TypedQuery<Team> queryTeamsInSeason = session.createNamedQuery(Team.QUERY_TEAMS_BY_SEASON, Team.class);
		String season = "NA";
		queryTeamsInSeason.setParameter(Team.SEASONNAME, season);
		List<Team> teamsInSeason = queryTeamsInSeason.getResultList();
		System.out.println("There are " + teamsInSeason.size() + " in season " + season);
		teamsInSeason.clear();
		//
		season = "2025-Spring";
		queryTeamsInSeason.setParameter(Team.SEASONNAME, season);
		teamsInSeason = queryTeamsInSeason.getResultList();
		System.out.println("There are " + teamsInSeason.size() + " in season " + season);
		
		System.out.println("=================================================");

		
		// transaction.commit();
		session.close();
		sessionFactory.close();
	}
}
