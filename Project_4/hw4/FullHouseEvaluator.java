package hw4;

/**
 * @author Richard Smith
 */
import java.util.ArrayList;
import java.util.Arrays;
import api.Card;
import api.Hand;

/**
 * Evaluator for a generalized full house. The number of main cards is equal to
 * the total cards. If the total cards is an odd number n, then there must be (n
 * / 2) + 1 cards of matching rank and the remaining (n / 2) cards must be of
 * matching rank. In this case, when creating a hand, <strong>the larger group
 * must be listed first even if of lower rank than the smaller group</strong>
 * (e.g. as [3 3 3 5 5] rather than [5 5 3 3 3]). If the hand size is even, then
 * half the cards must be of matching rank and the remaining half of matching
 * rank. A group of cards of the same rank always satisfies this evaluator.
 * 
 * The name of this evaluator is "Full House".
 */
// Note: You must edit this declaration to extend AbstractEvaluator
// or to extend some other class that extends AbstractEvaluator
public class FullHouseEvaluator extends AbstractEvaluator {

	/**
	 * Constructs the evaluator.
	 * 
	 * @param ranking
	 *            ranking of this type of hand
	 * @param handSize
	 *            total number of cards in a hand
	 */
	public FullHouseEvaluator(int ranking, int handSize) {
		super("Full House", ranking, handSize, handSize);
	}

	@Override
	public boolean satisfiedBy(Card[] mainCards) {
		// if the card sets aren't the same length then it returns false
		// immediately
		// this if statement is the only difference between the satisfiedBy()
		// and canSubsetSatisfy() methods
		if (mainCards.length != numMainCards()) {
			return false;
		}
		int count = 0;
		// Two arraylists so it can alternate between two different sets of
		// matches and determine
		// if it makes a full house or not
		ArrayList<Card> temp = new ArrayList<Card>();
		ArrayList<Card> temp2 = new ArrayList<Card>();
		Arrays.sort(mainCards);
		// if amount of cards are odd
		if (mainCards.length % 2 == 1) {
			// both for loops iterate through the cards and find matches of 2 or
			// longer
			for (int j = 1; j < mainCards.length; j++) {
				for (int i = 0; j < mainCards.length; i++) {
					// made switch statement to know whether or not to add to
					// temp or temp2 based off of succession of matches
					switch (count) {
					case 0:
						if (mainCards[i].getRank() == mainCards[j].getRank()) {
							// adds matches to temp arraylist
							temp.add(mainCards[i]);
							temp.add(mainCards[j]);
							count = 1;
						}
						break;
					case 1:
						if (mainCards[i].getRank() == mainCards[j].getRank()) {
							temp.add(mainCards[j]);
						}
						if (mainCards[i].getRank() != mainCards[j].getRank()) {
							count = 2;
						}
						break;
					case 2:
						if (mainCards[i].getRank() == mainCards[j].getRank()) {
							// adds second flow of matches to another arraylist
							// to compare sizes later
							temp2.add(mainCards[i]);
							temp2.add(mainCards[j]);
							count = 3;
						}
						break;
					case 3:
						if (mainCards[i].getRank() == mainCards[j].getRank()) {
							temp2.add(mainCards[j]);
						}
						if (mainCards[i].getRank() != mainCards[j].getRank()) {
							count = 0;
						}
						break;
					default:
						break;
					}
				}
			}
		}
		// if temp size is greater than 1 and temp2 size is greater than 2, visa
		// versa, you have a fullhouse.
		// or if temp size is as big as original cards that means its a group of
		// cards with same rank
		if ((temp.size() < 1 && temp2.size() < 2) || (temp2.size() < 1 && temp.size() < 2)
				|| (temp.size() == mainCards.length)) {
			return true;
		}
		if (mainCards.length % 2 == 0) {
			for (int j = 1; j < mainCards.length; j++) {
				for (int i = 0; j < mainCards.length; i++) {
					// made switch statement to know whether or not to add to
					// temp or temp2 based off of succession of matches
					switch (count) {
					case 0:
						if (mainCards[i].getRank() == mainCards[j].getRank()) {
							// adds matches to temp arraylist
							temp.add(mainCards[i]);
							temp.add(mainCards[j]);
							count = 1;
						}
						break;
					case 1:
						if (mainCards[i].getRank() == mainCards[j].getRank()) {
							temp.add(mainCards[j]);
						}
						if (mainCards[i].getRank() != mainCards[j].getRank()) {
							count = 2;
						}
						break;
					case 2:
						if (mainCards[i].getRank() == mainCards[j].getRank()) {
							// adds second flow of matches to another arraylist
							// to compare sizes later
							temp2.add(mainCards[i]);
							temp2.add(mainCards[j]);
							count = 3;
						}
						break;
					case 3:
						if (mainCards[i].getRank() == mainCards[j].getRank()) {
							temp2.add(mainCards[j]);
						}
						if (mainCards[i].getRank() != mainCards[j].getRank()) {
							count = 0;
						}
						break;
					default:
						break;
					}
				}
			}
		}
		// if both array sizes are equal then that means half and half of
		// mainCards are same rank.
		// or if temp size is as big as original cards that means its a group of
		// cards with same rank
		if (temp.size() == temp2.size() || temp.size() == mainCards.length) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean canSubsetSatisfy(Card[] allCards) {
		int count = 0;
		// Two arraylists so it can alternate between two different sets of
		// matches and determine
		// if it makes a full house or not
		ArrayList<Card> temp = new ArrayList<Card>();
		ArrayList<Card> temp2 = new ArrayList<Card>();
		Arrays.sort(allCards);
		// if amount of cards are odd
		if (allCards.length % 2 == 1) {
			// both for loops iterate through the cards and find matches of 2 or
			// longer
			for (int j = 1; j < allCards.length; j++) {
				for (int i = 0; j < allCards.length; i++) {
					// made switch statement to know whether or not to add to
					// temp or temp2 based off of succession of matches
					switch (count) {
					case 0:
						if (allCards[i].getRank() == allCards[j].getRank()) {
							// adds matches to temp arraylist
							temp.add(allCards[i]);
							temp.add(allCards[j]);
							count = 1;
						}
						break;
					case 1:
						if (allCards[i].getRank() == allCards[j].getRank()) {
							temp.add(allCards[j]);
						}
						if (allCards[i].getRank() != allCards[j].getRank()) {
							count = 2;
						}
						break;
					case 2:
						if (allCards[i].getRank() == allCards[j].getRank()) {
							// adds second flow of matches to another arraylist
							// to compare sizes later
							temp2.add(allCards[i]);
							temp2.add(allCards[j]);
							count = 3;
						}
						break;
					case 3:
						if (allCards[i].getRank() == allCards[j].getRank()) {
							temp2.add(allCards[j]);
						}
						if (allCards[i].getRank() != allCards[j].getRank()) {
							count = 0;
						}
						break;
					default:
						break;
					}
				}
			}
		}
		// if temp size is greater than 1 and temp2 size is greater than 2, visa
		// versa, you have a fullhouse.
		// or if temp size is as big as original cards that means its a group of
		// cards with same rank
		if ((temp.size() < 1 && temp2.size() < 2) || (temp2.size() < 1 && temp.size() < 2)
				|| (temp.size() == allCards.length)) {
			return true;
		}
		if (allCards.length % 2 == 0) {
			for (int j = 1; j < allCards.length; j++) {
				for (int i = 0; j < allCards.length; i++) {
					// made switch statement to know whether or not to add to
					// temp or temp2 based off of succession of matches
					switch (count) {
					case 0:
						if (allCards[i].getRank() == allCards[j].getRank()) {
							// adds matches to temp arraylist
							temp.add(allCards[i]);
							temp.add(allCards[j]);
							count = 1;
						}
						break;
					case 1:
						if (allCards[i].getRank() == allCards[j].getRank()) {
							temp.add(allCards[j]);
						}
						if (allCards[i].getRank() != allCards[j].getRank()) {
							count = 2;
						}
						break;
					case 2:
						if (allCards[i].getRank() == allCards[j].getRank()) {
							// adds second flow of matches to another arraylist
							// to compare sizes later
							temp2.add(allCards[i]);
							temp2.add(allCards[j]);
							count = 3;
						}
						break;
					case 3:
						if (allCards[i].getRank() == allCards[j].getRank()) {
							temp2.add(allCards[j]);
						}
						if (allCards[i].getRank() != allCards[j].getRank()) {
							count = 0;
						}
						break;
					default:
						break;
					}
				}
			}
		}
		// if both array sizes are equal then that means half and half of
		// mainCards are same rank.
		// or if temp size is as big as original cards that means its a group of
		// cards with same rank
		if (temp.size() == temp2.size() || temp.size() == allCards.length) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Hand createBestHand(Card[] allCards) {
		// two card variables, one for main cards and one for side cards
		Card[] mainCards = new Card[numMainCards()];

		Card[] sideCards = new Card[allCards.length - numMainCards()];
		// fills mainCards if totalCards() is greater or equal to given cards
		if (totalCards() <= allCards.length) {

			for (int i = 0; i < allCards.length; i++) {

				// FINISH LATER

			}

		}
		// creates new bestHand with new maincards and sidecards
		Hand bestHand = new Hand(mainCards, sideCards, name, getRanking());

		return bestHand;
	}
}
