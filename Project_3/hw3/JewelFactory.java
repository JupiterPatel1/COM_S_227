/**
 * @author Richard Smith
 */
package hw3;
import java.lang.Object;
import java.util.Random;
import api.Jewel;
import hw3.JewelFactory;

public class JewelFactory extends java.lang.Object {

	private int maxType;
	
	/**
	 * Constructs a JewelFactory that will create Jewel objects 
	 * with types 0 through givenMaxType - 1.
	 * @param givenMaxType
	 * 	givenMaxType - number of types of Jewels
	 */
	public JewelFactory(int givenMaxType)
	{	// constructs new JewelFactory with givenMaxType -1
		maxType = givenMaxType -1;
	}
	
	/**
	 * Constructs a JewelFactory that will create jewels with 
	 * types 0 through givenMaxType - 1. using the given Random instance.
	 * @param givenMaxType
	 * 	givenMaxType - number of types of jewels
	 * @param rand
	 * 	rand - random number generator to use
	 */
	public JewelFactory(int givenMaxType, java.util.Random rand)
	{	// contructs new JewelFactory with random instance and givenMaxType -1
		maxType = rand.nextInt(givenMaxType -1);
	}
	
	/**
	 * Creates a grid of the given width and height that is initialized 
	 * with random Jewels with type less than this factory's maximum type. 
	 * The Jewels are produced using this factory's random number generator, 
	 * where the random selection is restricted so that the resulting 
	 * grid has no runs.
	 * @param width
	 * 	width - number of columns for the created grid
	 * @param height
	 * 	height - number of rows for the created grid
	 * @return
	 * 	a 2D array of randomly selected Jewel objects that contains no runs
	 */
	public Jewel[][] createGrid(int width, int height)
	{	// creates new grid of jewels
		Jewel[][] grid = new Jewel[height][width];
		return grid;
	}
	
	/**
	 * Returns a random instance of Jewel with a type that is less 
	 * than this factory's maximum value.
	 * @return
	 * 	a Jewel object with randomly selected type
	 */
	public Jewel generate()
	{
		// created ran variable to make Jewel of a random type
		Random ran = new Random();
		Jewel jew = new Jewel(ran.nextInt(maxType -1));
		
		return jew;
	}
}
