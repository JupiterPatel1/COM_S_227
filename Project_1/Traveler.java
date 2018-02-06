package hw1;
/**
 * @author Richard Smith
 */

public class Traveler {
	
			// Sets sympathy constant
	public static final int SYMPATHY_FACTOR = 30;
	
			// Variable to store the town name.
	private City townName;
	
			// Variable to store money amount between the classes.
	private int money;
	
			// Variable to store amount of postcards sent home
	private int sentPostcardAmount;
	
			// Variable to store all journal entries.
	private String journal;
	
			// Variable to store amount of nights in a train station.
	private int stationNights;
	
	
	
		/**
		 * constructs a new traveler object.
		 * @param initialFunds
		 * @param initialCity
		 */
	public Traveler(int initialFunds, City initialCity) {
			
		townName = initialCity;	//Constructs initial town name.
			
		money = initialFunds;	// Constructs initial Funds to money variable.
		
		journal = townName.getName() + "(start)";	// uses city getName() method to feed name into journal variable.
		
		sentPostcardAmount = 0;		// Sets sent postcard amount variable to to 0.
		
		stationNights = 0;	//sets nights spent at station variable to 0
		
	}
	
		/**
		 * Returns the name of the currentCity.
		 * @return
		 */
	public String getCurrentCity() {
		return townName.getName(); 
	}
	
		/** 
		 * Returns amount of money the traveler has.
		 * @return
		 */
	public int getCurrentFunds() {
		return money;
	}
	
		/**
		 * Creates the getJournal method.
		 * String of comma separated values
		 * shows the cities visited by the traveler in the order visited
		 * and the number of nights in each.
		 * @return
		 */
	public String getJournal() {
		return journal;
	}
	
		/**
		 * returns true if the travler doesn't have enough money to send a postcard
		 * returns false otherwise.
		 * @return
		 */
	public boolean isSOL() {
		if (townName.costToSendPostcard() > money) {
			return true;
		}
		else return false;
	}
	
		/**
		 * Returns the number of nights the traveler spent in the train station
		 * when visiting a city.
		 * @return
		 */
	public int getTotalNightsInTrainStation() {
		return stationNights;
	}

		/**
		 * reduces travelers funds after
		 * Simulating a visit to a city for a select number of nights
		 * @param c
		 * @param numNights
		 */
	public void visit(City c, int numNights) {
		townName = c;
		if (money - (townName.lodgingCost()*numNights)< 0) {
			stationNights = stationNights - (townName.maxLengthOfStay(money)- numNights); 	// When there's not enough money it
			money = money - (townName.maxLengthOfStay(money)*townName.lodgingCost());		// increases the amount of nights at
		}																					// the train station
		else { 
			money = money - (townName.lodgingCost()*numNights);
		}
		journal += "," + townName.getName()+ "(" + String.valueOf(numNights) + ")" ; 		// Updates journal
	}
	
		/**
		 * increases amount of sent postcards.
		 * reduces amount of travelers money.
		 * if possible, sends the amount of postcards given.
		 * if there is not enough money it will send as many postcards as possible.
		 * @param howMany
		 */
	public void sendPostcardsHome(int howMany) {
		sentPostcardAmount = Math.min(howMany, townName.maxNumberOfPostcards(money));	// makes sure you can't spend
		money = money - sentPostcardAmount * townName.costToSendPostcard();				// more than the money amount.
	}
	
		/**
		 * Increases money by an amount equal to 
		 * sympathy * the number of postcards sent.
		 * The last call
		 * resets number of postcards to 0.
		 */
	public void callHomeForMoney() {
		money = money + (sentPostcardAmount * SYMPATHY_FACTOR);
		sentPostcardAmount = 0;
	}
}