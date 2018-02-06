package hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import api.Jewel;

/**
 * Utility class that isolates the fundamental array algorithms needed
 * for implementation of the Bejeweled game.  Also includes some useful
 * methods for converting strings into arrays of Jewel objects.
 */
public class Util
{
  /**
   * Private constructor disables instantiation.
   */
  private Util()
  {    
  }
  
  /**
   * Finds all runs of three or more Jewels of matching type in the 
   * given array. The return value is a list of the indices of all
   * elements that are part of some run.  The given array is not modified.
   * Caller should ensure that there are no null elements in the given
   * array.
   * @param arr
   *   array of Jewels
   * @return
   *   indices of all Jewels that are part of a run
   */
  public static ArrayList<Integer> findRuns(Jewel[] arr)
  { // ArrayList to hold the runs indicies
	ArrayList<Integer> indexRunList = new ArrayList<>();
	// counts matches while iterating
	int count = 0;
	// variable to be adjacent to i while iterating to check if values match
	int j = 0; 
	
	for(int i = 1; i < arr.length; i++)
	{
		if(arr[i].equals(arr[j]))
		{	// if the adjacent array values equal each other then add one to the count
			count += 1;
		}
		else
		{	// if the adjacent array values dont equal each other then reset count
			count = 0;
		}
		if(count == 2)
		{	// if count hits two that means a run is made and it records the 3 objects in the run
			indexRunList.add(j-1);
			indexRunList.add(j);
			indexRunList.add(i);
		}
		if(count >= 3)
		{	// if run continues then continue adding objects to list
			indexRunList.add(i);
		}
	// increments j
	j++;
	}
    return indexRunList;
  }
  
  /**
   * Shifts all non-null elements in the array to the right without
   * changing the order.  This operation modifies the given array.
   * The return value is a list of the indices of elements that 
   * were actually shifted by this operation, ordered left to right
   * (this means the index of the element in the modified array, 
   * not the original). 
   * @param arr
   *   array of Jewel objects, possibly containing null cells
   * @return
   *   list of the new indices of moved elements
   */
  public static ArrayList<Integer> shiftNonNullElements(Jewel[] arr)
  {
    ArrayList<Integer> indicies = new ArrayList<Integer>();
    ArrayList<Jewel> temp = new ArrayList<Jewel>();
    ArrayList<Jewel> temp2 = new ArrayList<Jewel>();
    for(int i = 0; i < arr.length; i++)
    {
    	if(!(arr[i] == null))
    	{	// adds to the temp arraylist
    		temp.add(arr[i]);
    		// takes value and position
    		//ogIndex[i] = arr[i];
    	}
    	else
    	{	// if null add to other temp arraylist
    		temp2.add(arr[i]);
    	}
    }
    // append both of the arraylists
    temp2.addAll(temp);
    for(int i = 0; i < temp2.size(); i++)
    {	// put all values from arraylist into an array
    	arr[i] = temp2.get(i);
    	if(!(arr[i] == null))
    	{	// adds index of non null elements
    		indicies.add(i);
    	}
    }
    return indicies;
  }
  
  /**
   * Creates an array of Jewel objects from a string of 
   * numbers separated by whitespace.
   * @param values
   *   string containing values for the Jewel types
   * @return
   *   array of Jewel objects with types determined by the given string
   */
  public static Jewel[] createFromString(String values)
  {
    ArrayList<Jewel> temp = new ArrayList<>();
    Scanner scanner = new Scanner(values);
    while (scanner.hasNextInt())
    {
      int value = scanner.nextInt();
      temp.add(new Jewel(value));
    }
    Jewel[] ret = temp.toArray(new Jewel[0]);
    return ret;
  }
  
  /**
   * Returns a grid initialized from an array of strings.  Each string
   * consists of numbers, separated by whitespace, for the Jewel types for 
   * the corresponding row of the grid.  Throws IllegalArgumentException
   * if the strings in the array do not all contain the same number of
   * values.
   * @param descriptor
   *   array of strings containing numbers
   * @return
   *   a 2D array of Jewel objects whose types are determined by the
   *   given strings
   */
  public static Jewel[][] createFromStringArray(String[] descriptor)
  {
    int height = descriptor.length;
 
    // creates an uninitialized array of Jewel[]
    Jewel[][] grid = new Jewel[height][];
    
    // make rows from the strings of the given array
    for (int row = 0; row < height; row += 1)
    {
      grid[row] = createFromString(descriptor[row]);
    }
    
    // sanity check that all rows are the same length
    int width = grid[0].length;
    for (int row = 1; row < height; row += 1)
    {
      if (grid[row].length != width)
      {
        throw new IllegalArgumentException("Rows of descriptor are not the same length. ");
      }
    }

    return grid;
  }
  
  /**
   * Returns a String representation of the given 2D array, with rows
   * delimited by newlines.
   */
  public static String convertToString(Jewel[][] grid)
  {
    StringBuilder sb = new StringBuilder();
    for (int row = 0; row < grid.length; ++row)
    {
      for (int col = 0; col < grid[0].length; ++col)
      {
        Jewel jewel = grid[row][col];
        String s = String.format("%2s",
            (jewel == null ? "*" : "" + jewel.getType()));
        sb.append(s);
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
