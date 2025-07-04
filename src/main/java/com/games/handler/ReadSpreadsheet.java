package com.games.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
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
import com.games.bean.GroupLevel;
import com.games.bean.Parent;
import com.games.bean.Player;
import com.games.bean.Season;
import com.games.bean.Team;

public class ReadSpreadsheet {

	private static final String FILENAME = "915nebaseballwebsitedata.xlsx";

	Season season25;
	HashMap<String, Parent> parents = new HashMap<>();
	HashMap<String, Player> players = new HashMap<>();
	HashMap<String, Team> teams = new HashMap<>();
	HashMap<String, Coach> coaches = new HashMap<>();
	HashMap<String, Parent> moms = new HashMap<>();

	public ReadSpreadsheet(String filename) throws IOException {
		

		/*
		 *  // Example with standard ISO format
    String isoDateString = "2023-10-26";
    LocalDate isoLocalDate = LocalDate.parse(isoDateString);
    java.sql.Date sqlDateFromIso = java.sql.Date.valueOf(isoLocalDate);
    System.out.println("SQL Date from ISO string: " + sqlDateFromIso);

    // Example with custom format
    String customDateString = "26/10/2023";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate customLocalDate = LocalDate.parse(customDateString, formatter);
    java.sql.Date sqlDateFromCustom = java.sql.Date.valueOf(customLocalDate);
    System.out.println("SQL Date from custom string: " + sqlDateFromCustom);

		 */
		
		season25 = new Season();
		// date must be in standard ISO format
		String dateString = "2025-05-01";
		LocalDate isoLocalDate = LocalDate.parse(dateString);
		Date sqlDateFromIso = java.sql.Date.valueOf(isoLocalDate);
		season25.setBaseDate(sqlDateFromIso);
		season25.setSeasonName("2025 Spring");
		
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

		session.persist(season25);

		GroupLevel[] gl = new GroupLevel[11]; 
		//BigDecimal registration, teamFee, uniform
		gl[0] = new GroupLevel("5U",5, new BigDecimal("40.00"), new BigDecimal("15.00"), new BigDecimal("40.00") );
		gl[1] = new GroupLevel("7U",5, new BigDecimal("40.00"), new BigDecimal("15.00"), new BigDecimal("40.00") );
		gl[2] = new GroupLevel("8U CP",5, new BigDecimal("40.00"), new BigDecimal("15.00"), new BigDecimal("40.00") );
		//TODO: 10U is not filled-in
		gl[3] = new GroupLevel("10U",5, new BigDecimal("40.00"), new BigDecimal("15.00"), new BigDecimal("40.00") );
		//TODO: 12U need cost of uniform -guessing 75
		gl[4] = new GroupLevel("12U",5, new BigDecimal("75.00"), new BigDecimal("50.00"), new BigDecimal("75.00") );
		gl[5] = new GroupLevel("14U",5, new BigDecimal("90.00"), new BigDecimal("35.00"), new BigDecimal("75.00") );
		//TODO: 16U need cost of uniform -guessing 75
		gl[6] = new GroupLevel("16U",5, new BigDecimal("90.00"), new BigDecimal("35.00"), new BigDecimal("75.00") );
		gl[7] = new GroupLevel("BYAA",5, new BigDecimal("90.00"), new BigDecimal("35.00"), new BigDecimal("75.00") );
		gl[8] = new GroupLevel("HS JV",5, new BigDecimal("100.00"), new BigDecimal("35.00"), new BigDecimal("75.00") );
		gl[9] = new GroupLevel("HS",5, new BigDecimal("100.00"), new BigDecimal("35.00"), new BigDecimal("75.00") );
		gl[10] = new GroupLevel("HS Tournamet",5, new BigDecimal("125.00"), new BigDecimal("35.00"), new BigDecimal("75.00") );
		
		for (GroupLevel g : gl) {
			session.persist(g);
		}
		
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
			NameSplitter name = new NameSplitter(coachName);
			String email = row.getCell(2).getRichStringCellValue().getString();
			String phone = dataFormatter.formatCellValue(row.getCell(3));
			//System.out.println(coachName + "\t" + email + "\t'" + phone + "'" );
			Coach coach = new Coach(name.getFirstName(), name.getLastName(),  phone, email);
			coaches.put(coachName, coach);
		}
		
		for (int i = 16; i < 23; i++) {
			Row row = sheet.getRow(i);
			String momName = row.getCell(1).getRichStringCellValue().getString();
			String email = row.getCell(2).getRichStringCellValue().getString();
			String phone = dataFormatter.formatCellValue(row.getCell(3));
			//System.out.println(momName + "\t" + email + "\t'" + phone + "'" );
			NameSplitter name = new NameSplitter(momName);
			Parent mom = new Parent(name.getFirstName(), name.getLastName(),  phone, email);
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
					if (team != null) {
						team.getCoaches().add(coaches.get(coachName));
					}
				}
			}
			teams.put(teamName, team);
		}
		
	}

	private Team findTeamByShortName(String teamName) {
		for (String s : teams.keySet()) {
			if (s.startsWith(teamName)
					|| (s.contains("JV") && teamName.equals("JV"))
					|| (s.contains("Tournament") && teamName.equals("HS Tournament"))
					) {
				return teams.get(s);
			}
		}
		return null;
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
				NameSplitter name = new NameSplitter(parentName);
				parent = new Parent(name.getFirstName(), name.getLastName(), phone, email);
				parents.put(parentName, parent);
			} else {
				parent = parents.get(parentName);
			}
			
			Team team = null;
			if (teams.containsKey(teamName) ) {
				team = teams.get(teamName);
			} else {
				team = new Team(season25, teamName);
				teams.put(teamName, team);
			}
			int jerseyNum = 0;
			if ( ! jersey.trim().equals("")) {
				jerseyNum = Integer.valueOf(jersey);
			}
			NameSplitter name = new NameSplitter(playerName);
			Player player = new Player(name.getFirstName(), name.getLastName(), dob, jerseyNum);
			player.setDateOfBirth(date);
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
