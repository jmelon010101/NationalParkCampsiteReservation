package com.techelevator.campground;

import java.awt.peer.SystemTrayPeer;
import java.time.LocalDate;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;
import com.techelevator.campground.model.ReservationDAO;
import com.techelevator.campground.model.Site;
import com.techelevator.campground.model.SiteDAO;
import com.techelevator.campground.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.campground.model.jdbc.JDBCParkDAO;
import com.techelevator.campground.model.jdbc.JDBCReservationDAO;
import com.techelevator.campground.model.jdbc.JDBCSiteDAO;
import com.techelevator.campground.view.Menu;

public class CampgroundCLI {
	private static final String MAIN_HEADER = "View Parks Interface";
	private static final String SUB_HEADER = "Select a Command";
	
	private static final String PARK_MENU_VIEW_CAMPGROUND = "View Campgrounds";
	private static final String PARK_MENU_SEARCH_RES = "Search for Reservation";
	private static final String PARK_MENU_RETURN = "Return to Previous Screen";
	private static final String[] PARK_MENU_OPTIONS = { PARK_MENU_VIEW_CAMPGROUND,PARK_MENU_SEARCH_RES,PARK_MENU_RETURN};
	
	
	private Menu menu;
	private CampgroundDAO campgroundDAO;
	private ParkDAO parkDAO;
	private SiteDAO siteDAO;
	private ReservationDAO reservationDAO;
	
	
	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword(System.getenv("DB_PASSWORD"));

		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource datasource) {
		campgroundDAO = new JDBCCampgroundDAO(datasource);
		parkDAO = new JDBCParkDAO(datasource); 
		siteDAO = new JDBCSiteDAO(datasource);
		reservationDAO = new JDBCReservationDAO(datasource);
		menu = new Menu(System.in, System.out);
		
	}

	public void run() {
		while(true) {
			Park selectedPark = showParks();
			showParkOptions(selectedPark);
			
		}
	}
	
	private void printHeading(String headingText) {
		System.out.println("\n"+headingText);
		for(int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	private Park showParks() {
		Park result = null;
		printHeading(MAIN_HEADER);
		List<Park> allParks = parkDAO.getAllParks();
		if(allParks.size() > 0) {
			System.out.println("\n*** Select a Park for Further Details ***");
			Park selectedPark = (Park)menu.getChoiceFromOptions(allParks.toArray());
			String parkDescription = parkDAO.getParkInfoByName(selectedPark.getName());
			System.out.println(parkDescription);
			result = selectedPark;
		} else {
			System.out.println("\n*** No results ***");
		}
		return result;
	}
	private void showParkOptions(Park selectedPark) {
		while(true) {			
			List<Campground> allCampgrounds = campgroundDAO.getAllCampgroundsByParkId(selectedPark.getId());
			printHeading(SUB_HEADER);
			String choice = (String)menu.getChoiceFromParkOptions(PARK_MENU_OPTIONS);
			if (choice.equals(PARK_MENU_VIEW_CAMPGROUND)) {
				formatCampgroundList(allCampgrounds, selectedPark);
			} else if (choice.equals(PARK_MENU_SEARCH_RES)) {
				Campground campgroundChoice = campgroundChoice(allCampgrounds, selectedPark);
				LocalDate arrivalDate = menu.getArrivalDate();
				LocalDate departureDate = menu.getDepartureDate();
				List<Site> availableSites = siteDAO.getAvailableSites(campgroundChoice,arrivalDate, departureDate);
				Site selectedSite = siteChoice(availableSites);
				String reservationName = menu.getReservationName();
				menu.printSiteInfo(selectedSite, reservationName);
				System.out.println(reservationDAO.addReservation(selectedSite.getId(), reservationName, LocalDate.now(), 5, LocalDate.now()) + " is your reservation ID");
				
			} else if (choice.equals(PARK_MENU_RETURN)) {
				return;
			}
		}
	}
	//Adding a menu which allows the user to select a site.
	private Site siteChoice(List<Site> availableSites) {
		System.out.format("%-10s %-10s %-13s %-20s %-15s %-15s \n", "Site No.", "Max Occup.", "Accessible?","Max RV Length","Utility","Cost");
		Site selectedSite = (Site)menu.getChoiceFromSiteOptions(availableSites.toArray());
		System.out.println("Which site should be reserved (enter 0 to cancel) ?");
		System.out.println("Great! You've selected Site #" + selectedSite.getId());
		return selectedSite;
	}
	
	
	private Campground campgroundChoice(List<Campground> allCampgrounds, Park selectedPark) {		
		System.out.println("\n Which campground (enter 0 to cancel)?");
		Campground selectedCampground = (Campground)menu.getChoiceFromOptions(allCampgrounds.toArray());
		System.out.println(selectedCampground.getName() + " YAAAAAY!!!! WE'RE GOING ON VACATION BRUHHH!!!");
		return selectedCampground;
	}
	
	private void formatCampgroundList(List<Campground> list, Park selectedPark) {
		String title = "Park Campgrounds";
		String parkNameString = selectedPark.getName() + "National Park Campgrounds";
		System.out.println(title);
		System.out.println(parkNameString);
		System.out.format("%s %s %s %s \n", "Name","Open","Close","Daily Fee");
		for (Campground c : list) {
			System.out.println(c.toString());			
		}
	}
	
}
