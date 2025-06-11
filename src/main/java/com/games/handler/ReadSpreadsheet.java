package com.games.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.games.bean.Coach;
import com.games.bean.Parent;
import com.games.bean.Player;
import com.games.bean.Team;

public class ReadSpreadsheet {

	private static final String FILENAME = "915nebaseballwebsitedata.xlsx";

	HashMap<String, Parent> parents = new HashMap<>();
	HashMap<String, Player> players = new HashMap<>();
	HashMap<String, Team> teams = new HashMap<>();
	HashMap<String, Coach> coaches = new HashMap<>();
	HashMap<String, Parent> moms = new HashMap<>();

	public ReadSpreadsheet(String filename) throws IOException {
		FileInputStream file = new FileInputStream(new File(filename));
		try (Workbook workbook = new XSSFWorkbook(file);) {
			readCurrentPlayerSheet(workbook);
			readCoaches(workbook);
		}
		
		//print what we read
//		for (String key : parents.keySet()) {
//			System.out.println(parents.get(key).toString());
//		}
//		
//		for (String key : players.keySet()) {
//			System.out.println(players.get(key).toString());
//		}
//
//		for (String key : teams.keySet()) {
//			System.out.println(teams.get(key).toString());
//		}
		
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		for (Entry<String, Parent> entry : parents.entrySet()) {
			System.out.println(parents.get(entry.getKey()).toString());
			Parent parent = parents.get(entry.getKey());
			session.persist(parent);
		}

		for (String key : players.keySet()) {
			Player player = players.get(key);
			System.out.println(player.toString());
			// Save parents of the player first
			for (Parent parent : player.getParents()) {
				if (!session.contains(parent)) {
					session.persist(parent);
				}
			}
			session.persist(player);
		}

		for (String key : teams.keySet()) {
			Team team = teams.get(key);
			System.out.println(team.toString());
			// Save players of the team first
			for (Player player : team.getPlayers()) {
				if (!session.contains(player)) {
					session.persist(player);
				}
			}
			session.persist(team);
		}

		for (String key : coaches.keySet()) {
			Coach coach = coaches.get(key);
			System.out.println(coach.toString());
			session.persist(coach);
		}

		transaction.commit();
		session.close();
		sessionFactory.close();
	}

	private void readCoaches(Workbook workbook) {
		Sheet sheet = workbook.getSheet("Coaches & Team Moms");
		DataFormatter dataFormatter = new DataFormatter();
		for (int i = 1; i < 15; i++) {
			Row row = sheet.getRow(i);
			String coachName = row.getCell(1).getRichStringCellValue().getString();
			String email = row.getCell(2).getRichStringCellValue().getString();
			String phone = dataFormatter.formatCellValue(row.getCell(3));
			//System.out.println(coachName + "\t" + email + "\t'" + phone + "'" );
			Coach coach = new Coach(coachName,  phone, email);
			coaches.put(coachName, coach);
		}
		
		for (int i = 16; i < 23; i++) {
			Row row = sheet.getRow(i);
			String momName = row.getCell(1).getRichStringCellValue().getString();
			String email = row.getCell(2).getRichStringCellValue().getString();
			String phone = dataFormatter.formatCellValue(row.getCell(3));
			//System.out.println(momName + "\t" + email + "\t'" + phone + "'" );
			Parent mom = new Parent(momName,  phone, email);
			moms.put(momName, mom);
		}

		//Read the team coaches - parse by columns
		for(int col=5; col<11; col++) {
			Team team = null;
			String teamName = "";
			for (int r = 1; r < 7; r++) {
				Row row = sheet.getRow(r);
				String coachName = row.getCell(col).getRichStringCellValue().getString();
				//System.out.println("'" + coachName + "'" );
				if (r == 1) {
					teamName = coachName;
					team = findTeamByShortName(teamName);
				} else if (!coachName.equals("")) {
					team.getCoaches().add(coaches.get(coachName));
				}
			}
			teams.put(teamName, team);
		}
		
	}

	private Team findTeamByShortName(String teamName) {
		for (String s : teams.keySet()) {
			if (s.startsWith(teamName)) {
				return teams.get(s);
			}
		}
		return new Team("No players", teamName);
	}

	private void readCurrentPlayerSheet(Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(0);


		DataFormatter dataFormatter = new DataFormatter();
		for (Row row : sheet) {
			if (row.getCell(1) == null) {
				//skip header row
				continue;
			}
			String parentName = row.getCell(1).getRichStringCellValue().getString();
			if (parentName.equals("Parent Name") || parentName.equals("")) {
				continue;
			}
			
			String email = row.getCell(2).getRichStringCellValue().getString();
			String phone = dataFormatter.formatCellValue(row.getCell(3));
			String playerName = row.getCell(4).getRichStringCellValue().getString();
			String dob = dataFormatter.formatCellValue(row.getCell(5));
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
			LocalDate localDate = LocalDate.parse(dob,formatter);
			Date date = Date.valueOf(localDate);
			String jersey = dataFormatter.formatCellValue(row.getCell(6));
			String teamName = row.getCell(7).getRichStringCellValue().getString();
//			System.out.println("parent Name :" + parentName +  "\n"
//					+ "email:" + email + "\n"
//					+ "phone:" + phone + "\n"
//					+ "email:" + email + "\n"
//					+ "playerName:" + playerName + "\n"
//					+ "dob:" + dob + "\n"
//					+ "jersey:" + jersey + "\n"
//					+ "teamName:" + teamName + "\n"
//					+ "===========");
			Parent parent = null;
			if (!parents.containsKey(parentName) ) {
				parent = new Parent(parentName, phone, email);
				parents.put(parentName, parent);
			} else {
				parent = parents.get(parentName);
			}
			
			Team team = null;
			if (teams.containsKey(teamName) ) {
				team = teams.get(teamName);
			} else {
				team = new Team("2025-Spring", teamName);
				teams.put(teamName, team);
			}
			int jerseyNum = 0;
			if ( ! jersey.trim().equals("")) {
				jerseyNum = Integer.valueOf(jersey);
			}
			Player player = new Player(playerName, dob, jerseyNum);
			player.setDate_of_birth(date);
			//see if player already exists
			if (players.containsKey(playerName)) {
				//Don't add parents again, just add to the additional team
				//System.out.println("Duplicate user '" + playerName + "'");
				team.getPlayers().add(player);				
			} else {
				player.getParents().add(parent);
				players.put(playerName, player);
				parent.getPlayers().add(player);
				team.getPlayers().add(player);
			}
		}
		
//		
	}

	public static void main(String[] args) throws IOException {
		new ReadSpreadsheet(FILENAME);
	}

}
