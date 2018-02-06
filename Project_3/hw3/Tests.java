/**
 * @author Richard Smith
 */
package hw3;
import java.util.ArrayList;
import java.util.Arrays;

import api.GridPosition;
import api.Jewel;

public class Tests {

	public static void main(String[] args) {
//		int[][] arr = new int[2][4];
//		System.out.println(arr[0].length); // amount of columns
//		System.out.println(arr.length); // amount of rows
		 //descriptor for initial grid of a 3 x 4 game
//		String[] desc =
//		{
//		"2 0 1 3",		
//		"1 0 1 3",
//		"2 1 2 1"
//		};
//
//		JewelFactory generator = new JewelFactory(4);
//	Game g = new Game(desc, generator);
//		System.out.println(g);
//		System.out.println(g.getJewel(2, 1)); // expected 1
//		g.setJewel(2, 1, new Jewel(3));
//		System.out.println(g.getJewel(2, 1)); // expected 3
//		g.setJewel(2, 1, null);
//		System.out.println(g);
//
//		Jewel[] arr1 = g.getRow(1);
//		System.out.println(Arrays.toString(arr1));
//		System.out.println();
//		
//		Jewel[] arr2 = g.getCol(1);
//		System.out.println(Arrays.toString(arr2));
//		System.out.println();
//		
//		Jewel[] testCol = Util.createFromString("5 6 4");
//		g.setCol(1, testCol);
//		System.out.println(g);
//		
//		Jewel[] testRow = Util.createFromString("5 6 7 2");
//		g.setRow(1, testRow);
//		System.out.println(g);
//		
//		Jewel[] test = Util.createFromString("4 2 2 2 4 4 3 3 3 3 2");
//		ArrayList<Integer> result = Util.findRuns(test);
//		System.out.println(result); // expected [1, 2, 3, 6, 7, 8, 9]

//		String[] desc =
//			{
//			"4 2 2 2 4 4 3 3 3 3 2",
//			"1 2 3 4 5 1 2 3 4 5 1"
//			};
//			JewelFactory generator = new JewelFactory(6);
//			Game p = new Game(desc, generator);
//			ArrayList<GridPosition> result = p.findHorizontalRuns(0);
//			System.out.println(result);
		
		String[] desc =
			{
			"3 3 2 1 0",
			"3 3 0 0 1",
			"2 2 3 2 1",
			"0 2 3 1 0"
			};
			JewelFactory generator = new JewelFactory(6);
			Game g = new Game(desc, generator);
			 // ok to use null here since the jewel will be ignored
			GridPosition c0 = new GridPosition(1, 1, null);
			GridPosition c1 = new GridPosition(1, 2, null);
			boolean ret = g.select(c0, c1);
			System.out.println(ret);
			System.out.println(g);
			g.findAndMarkAllRuns();
			System.out.println(g);
		
//		String[] desc1 =
//			{
//			"2 4 1 3",
//			"1 4 1 3",
//			"2 1 2 1"
//			};
//
//			Game ga = new Game(desc1, new JewelFactory(5));
//			ga.setJewel(0, 1, null);
//			ga.setJewel(1, 1, null);
//			ga.setJewel(0, 3, null);
//			ga.setJewel(2, 0, null);
//			System.out.println(ga);
//			ArrayList<GridPosition> result = ga.fillAll();
//			System.out.println(ga);
//			System.out.println(result);


	}

}
