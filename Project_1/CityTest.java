package hw1;
/**
 * @author Richard Smith
 */

public class CityTest
 {
	public static void main(String[] args)
	{
		City c = new City("Paris", 75);
		System.out.println(c.getName());
		System.out.println(c.lodgingCost());
		System.out.println(c.costToSendPostcard());
		System.out.println(c.maxLengthOfStay(154));
		System.out.println(c.maxNumberOfPostcards(3));
	}
	
	

}
