package hw1;
/**
 * @author Richard Smith
 */

public class City {

	public static final double RELATIVE_COST_OF_POSTCARD = 0.05;
	/**
	 * city name variable
	 */
	private String givenName;
	/**
	 * city lodging cost variable
	 */
	private int givenLodgingCost;
	
	/**
	 * constructs a new city with a given name and lodging cost
	 * @param givenName
	 * @param givenLodgingCost
	 */
	public City(String givenName, int givenLodgingCost) {
		this.givenName = givenName;
		this.givenLodgingCost = givenLodgingCost;
	}
	/**
	 * returns the name of the city
	 * @return
	 */
	public String getName() {
		return givenName;
	}
	/**
	 * returns lodging cost of the city
	 * @return
	 */
	public int lodgingCost() {
		return givenLodgingCost;
	}
	/**
	 * returns cost to send a postcard
	 * @return
	 */
	public int costToSendPostcard() {
		return (int) Math.round(RELATIVE_COST_OF_POSTCARD * givenLodgingCost);
	}
	/**
	 * returns maximum length of staying with given funds
	 * @param funds
	 * @return
	 */
	public int maxLengthOfStay(int funds) {
		return funds / givenLodgingCost;
	}
	/**
	 * returns maximum number of postcards purchasable with given funds
	 * @param funds
	 * @return
	 */
	public int maxNumberOfPostcards(int funds) {
		return (int)(funds / RELATIVE_COST_OF_POSTCARD);
	}
	
}
