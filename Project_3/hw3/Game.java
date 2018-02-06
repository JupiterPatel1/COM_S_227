/**
 * @author Richard Smith
 */
package hw3;
import java.lang.Object;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import api.GridPosition;
import api.Jewel;
import hw3.Game;
import hw3.JewelFactory;

public class Game extends java.lang.Object {
	// holds the grid of the game
	private Jewel[][] grid = null;
	// holds score
	private int score;
	// holds JewelFactory generator for fill all method
	private JewelFactory gen;
	
	/**
	 * Constructs a game with the given number of columns and rows that
	 * will use the given JewelFactory instance to create new jewels.
	 * The grid is initialized according to the factory's createGrid method.
	 * @param width
	 * 	width - number of columns
	 * @param height
	 * 	height - number of rows
	 * @param generator
	 * 	generator - generator for new jewels
	 */
	public Game(int width, int height, JewelFactory generator)
	{
		// constructs new game with given width, height, and JewelFactory instance
		grid = generator.createGrid(width, height);	
		gen = generator;
	}
	
	/**
	 * Constructs a game whose size and initial configuration are determined
	 * by the given string array and that will use the given JewelFactory instance 
	 * to create new jewels. Each string in the given array consists of 
	 * whitespace-separated integers corresponding to the initial jewel type for each cell.
	 * @param descriptor
	 * 	descriptor - array of strings
	 * @param generator
	 * 	generator - generator for new jewels
	 */
	public Game(java.lang.String[] descriptor, JewelFactory generator)
	{
		// constructs new game from given string array and JewelFactory instance
		grid = Util.createFromStringArray(descriptor);
	}
	
	/**
	 * Replaces each null cell in the grid with a new Jewel created by a call to this 
	 * Game's JewelFactory. The return value is a list containing a GridPosition 
	 * object for each newly assigned cell.
	 * @return
	 * 	list of GridPosition objects for the filled cells
	 */
	public java.util.ArrayList<GridPosition> fillAll()
	{	// ArrayList to hold grid positions where null values are turned into new jewels
		ArrayList<GridPosition> list = new ArrayList<GridPosition>();
		// iterates through all grid rows
		for(int i = 0; i < grid.length; i++)
		{	// iterates through all grid columns
			for(int j = 0; j < grid[0].length; j++)
			{	// if spot in grid is null
				if(grid[i][j] == null)
				{	// sets jewel in null spot of grid
					setJewel(i, j, gen.generate());
					GridPosition g = new GridPosition(i, j, grid[i][j]);
					// adds gridposition object to list
					list.add(g);
				}
			}
		}
		return list;
	}
	
	/**
	 * Finds all horizontal and vertical runs, and returns a list containing a 
	 * GridPosition object for all cells that are part of a run. The list is 
	 * in no particular order and may contain duplicates. All grid positions 
	 * that are part of a run are set to null in the grid, and the score is updated.
	 * @return
	 * 	list of GridPosition objects for all cells that are part of a run
	 */
	public java.util.ArrayList<GridPosition> findAndMarkAllRuns() 
	{	// arraylist for runs to be held
		ArrayList<GridPosition> runs = new ArrayList<GridPosition>();
		for(int i = 0; i < getHeight(); i++)
		{	// for loop finds all horizontal runs and adds them to the runs arraylist
			runs.addAll(findHorizontalRuns(i)); 
		}
		for(int i = 0; i < getWidth(); i++)
		{	// for loop finds all vertical runs and adds them to the runs arraylist
			runs.addAll(findVerticalRuns(i)); 
		}
		for(int i = 0; i < runs.size(); i++)
		{	// adds one to score for each object in array
			score += 1; 
			// sets all Jewels that are part of run to null
			setJewel(runs.get(i).row(), runs.get(i).col(), null);
		}
		return runs;
	}
	
	/**
	 * Finds runs in the given row of the grid using the method Util.findRuns 
	 * The return value is a list containing a GridPosition object for each 
	 * cell that is part of a run, in left-to-right order. This method does 
	 * not modify the grid or update the score.
	 * @param row
	 * 	row - the row in which to find runs
	 * @return
	 * 	list of GridPosition objects for cells that are part of a run
	 */
	public java.util.ArrayList<GridPosition> findHorizontalRuns(int row)
	{	// same method from getcol()
		Jewel[] arr = new Jewel[grid[0].length];
		for(int i=0; i<grid[0].length; i++)
		{	
			arr[i] = grid[row][i]; 
		}
		// create objects arraylist to hold objects involved in runs and runs arraylist to hold runs
		ArrayList<GridPosition> objects = new ArrayList<GridPosition>();
		ArrayList<Integer> runsList = new ArrayList<Integer>();
		runsList = Util.findRuns(arr);
		
		for(int i = 0; i < runsList.size(); i++)
		{	// fills objects arraylist with all objects involved in runs
			objects.add(new GridPosition(row, runsList.get(i), arr[runsList.get(i)]));
		}
		return objects;
	}
	
	/**
	 * Finds runs in the given column of the grid using the method Util.
	 * findRuns The return value is a list containing a GridPosition 
	 * object for each cell that is part of a run, in top-to-bottom order. 
	 * This method does not modify the grid or update the score.
	 * @param col
	 * 	col - the column in which to find runs
	 * @return
	 * 	list of GridPosition objects for cells that are part of a run
	 */	
	public java.util.ArrayList<GridPosition> findVerticalRuns(int col)
	{	// same method from getcol()
		Jewel[] arr = new Jewel[grid.length];
		for(int i=0; i<grid.length; i++)
		{	
			arr[i] = grid[i][col]; 
		}
		// create objects arraylist to hold objects involved in runs and runs arraylist to hold runs
		ArrayList<GridPosition> objects = new ArrayList<GridPosition>();
		ArrayList<Integer> runsList = new ArrayList<Integer>();
		runsList = Util.findRuns(arr);
		
		for(int i = 0; i < runsList.size(); i++)
		{	// fills objects arraylist with all objects involved in runs
			objects.add(new GridPosition(runsList.get(i), col, arr[runsList.get(i)]));
		}
		return objects;
	}
	
	/**
	 * Returns a copy of the given column of the grid.
	 * @param col
	 * 	col - given col
	 * @return
	 * 	a new array containing the Jewels from the given col
	 */
	public Jewel[] getCol(int col)
	{
		Jewel[] arr = new Jewel[grid.length];
		for(int i=0; i<grid.length; i++)
		{	// fills array with given col from the grid
			arr[i] = grid[i][col]; 
		}
		return arr;
	}
	
	/**
	 * Returns the height of the grid for this game (number of rows).
	 * @return
	 * 	number of rows in the grid for this game
	 */
	public int getHeight() 
	{
		return grid.length;
	}
	
	/**
	 * Returns the Jewel at the given row and column.
	 * @param row
	 * 	row - given row in the grid
	 * @param col
	 * 	col - given column in the grid
	 * @return
	 * 	Jewel at the given row and column
	 */
	public Jewel getJewel(int row, int col)
	{
		return grid[row][col];
	}
	
	/**
	 * Returns a copy of the given row of the grid.
	 * @param row
	 * 	row - given row
	 * @return
	 * 	a new array containing the Jewels from the given row
	 */
	public Jewel[] getRow(int row)
	{
		Jewel[] arr = new Jewel[grid[0].length];
		for(int i=0; i<grid[0].length; i++)
		{	// fills array with given row from the grid
			arr[i] = grid[row][i]; 
		}
		return arr;
	}
	
	/**
	 * Returns the current score for this game.
	 * @return
	 * 	current score
	 */
	public int getScore()
	{
		return score;
	}
	
	/**
	 * Returns the width of the grid for this game (number of columns).
	 * @return
	 * 	width of the grid
	 */
	public int getWidth()
	{
		return grid[0].length;
	}
	
	/**
	 * Exchanges the Jewels described by the given GridPositions, if possible. 
	 * The caller is responsible for ensuring that the given positions are 
	 * horizontally or vertically adjacent. The exchange is allowed only if 
	 * one of the affected cells forms part of a run after the jewels are swapped. 
	 * If so, the jewels for the two cells are exchanged and the method returns 
	 * true; otherwise, the method returns false. No other aspects of the game state 
	 * are modified. (Note that only the row and column of the given GridPositions are 
	 * used; the Jewel attribute is ignored.)
	 * @param c0
	 * 	c0 - grid position
	 * @param c1
	 * 	c1 - grid position adjacent to c0
	 * @return
	 * 	true if the two given cells are exchanged, false otherwise
	 */
	public boolean select(GridPosition c0, GridPosition c1)
	{	// create Jewel object for c0
		Jewel j0 = getJewel(c0.row(), c0.col()); 
		// create Jewel object for c1
		Jewel j1 = getJewel(c1.row(), c1.col()); 
		// swaps Jewels
		setJewel(c0.row(), c0.col(), j1);
		setJewel(c1.row(), c1.col(), j0); 
		// ArrayList to hold amount of runs after swap
		ArrayList<GridPosition> runs = new ArrayList<GridPosition>(); 
		// fills ArrayList with runs if there are any
		runs.addAll(findHorizontalRuns(c0.row()));
		runs.addAll(findHorizontalRuns(c1.row()));
		runs.addAll(findVerticalRuns(c0.col()));
		runs.addAll(findVerticalRuns(c1.col())); 
		// checks if the ArrayList filled up with runs or not
		if(runs.size() > 0) 
		{
			return true;
		}
		else
		{	// if no runs then switches jewels back
			setJewel(c0.row(), c0.col(), j0); 
			setJewel(c1.row(), c1.col(), j1); 
			return false;
		}
	}
	
	/**
	 * Copies the given array values into the specified column of 
	 * the grid. given array.
	 * @param col
	 * 	col - given col
	 * @param arr
	 * 	arr - array of Jewel containing the values for the column
	 */
	public void setCol(int col, Jewel[] arr)
	{
		for(int i=0; i<grid.length; i++)
		{	//copies given array values into selected column
			grid[i][col] = arr[i];
		}
	}
	
	/**
	 * Sets the Jewel at the given row and column.
	 * @param row
	 * 	row - given row in the grid
	 * @param col
	 * 	col - given column in the grid
	 * @param jewel
	 * 	jewel - value to be set
	 */
	public void setJewel(int row, int col, Jewel jewel)
	{	// sets Jewel at given row and column
		grid[row][col] = jewel;
	}
	
	/**
	 * Copies the given array values into the specified row of the grid.
	 * @param row
	 * 	row - given row
	 * @param arr
	 * 	arr - array of Jewel containing the values for the row
	 */
	public void setRow(int row, Jewel[] arr)
	{
		for(int i=0; i<grid[0].length; i++)
		{	//copies given array values into selected row
			grid[row][i] = arr[i];
		}
	}
	
	/**
	 * Shifts the Jewels in a given column downward using the method 
	 * Util.shiftNonNullElements. After executing this method the null 
	 * cells, if any, are at the top of the column. The order of the 
	 * Jewels is not changed. The return value is a list containing a 
	 * GridPosition object for each Jewel that was moved by this operation.
	 * The GridPosition's row and column should be the position of the moved 
	 * Jewel when the operation completes.
	 * @param col
	 * 	col - the given column
	 * @return
	 * 	list of GridPosition objects for elements that are moved
	 */
	public java.util.ArrayList<GridPosition> shiftColumnDown(int col)
	{	// temp array to hold col values
		Jewel[] temp = new Jewel[grid.length];
		for(int i = 0; i < grid.length; i++)
		{	// fills temp array with given col
			temp[i] = grid[i][col];
		}
		// arraylist to hold shifted elements from temp array
		ArrayList<Integer> list = Util.shiftNonNullElements(temp);
		for(int i = 0; i < grid.length; i++)
		{	// fills grid with shifted temp array
			grid[i][col] = temp[i];
		}
		// arraylist to hold new gridpositions
		ArrayList<GridPosition> r = new ArrayList<GridPosition>();
		for(int i = 0; i < list.size(); i++)
		{	// holds indicies of list array
			int index = list.get(i);
			// creates new grid based off index
			GridPosition g = new GridPosition(index, col, grid[index][col]);
			// adds new gridpositions to arraylist r
			r.add(g);
		}
		return r;
	}
	
	/**
	 * Returns a String representation of the grid for this game, 
	 * with rows delimited by newlines.
	 * @Override
	 * 	toString in class java.lang.Object
	 */
	public java.lang.String toString() 
	{
		return Util.convertToString(grid);
	}
}
