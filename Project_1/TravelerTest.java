package hw1;
/**
 * @author Richard Smith
 */

public class TravelerTest
{
	public static void main(String[] args)
	{
		// a few cities to visit
		City paris = new City("Paris", 75);
		City rome = new City("Rome", 50);

		 // start out in Paris
		Traveler t = new Traveler(500, paris);

		// initial state
		System.out.println(t.getCurrentCity()); // expected "Paris"
		System.out.println(t.getJournal()); // expected "Paris(start)"
		// try going to Rome
		t.visit(rome, 2);
		System.out.println(t.getCurrentCity()); // expected "Rome"
		System.out.println(t.getJournal()); // expected "Paris(start),Rome(2)"
		 // back to Paris for a week
		t.visit(paris, 7);
		System.out.println(t.getCurrentCity()); // expected "Paris"
		System.out.println(t.getJournal()); // "Paris(start),Rome(2),Paris(7)"
		
		t = new Traveler(500, paris); // start over

		// initial state
		System.out.println(t.getCurrentFunds()); // expected 500

		// visit a city
		t.visit(rome, 2);
		System.out.println(t.getCurrentFunds()); // expected 400

	}

}
