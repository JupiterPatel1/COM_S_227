package hw4;

/**
 * @author Richard Smith
 */
import api.Card;
import api.Hand;

/**
 * Evaluator for a hand containing (at least) four cards of the same rank. The
 * number of main cards is four.
 * 
 * The name of this evaluator is "Four of a Kind".
 */
// Note: You must edit this declaration to extend AbstractEvaluator
// or to extend some other class that extends AbstractEvaluator
public class FourOfAKindEvaluator extends AbstractEvaluator {
	// FINISHED

	/**
	 * 
	 * Constructs the evaluator.
	 * 
	 * @param ranking
	 * 
	 *            ranking of this type of hand
	 * 
	 * @param handSize
	 * 
	 *            total number of cards in a hand
	 * 
	 */

	public FourOfAKindEvaluator(int ranking, int handSize) {

		super("Four of a Kind", ranking, 4, handSize);

	}

	@Override

	public boolean satisfiedBy(Card[] mainCards) {
		if (numMainCards() != mainCards.length) {
			return false;
		}
		int count = 0;

		if (mainCards.length == numMainCards()) {
			// for loop to iterate through cards and if statement to find
			// matches
			for (int i = 1; i < mainCards.length; i++) {

				if (mainCards[i - 1].getRank() == mainCards[i].getRank()) {
					// count of matches goes up
					count++;

				}

				else {
					// if succession of matches stop then count restarts
					count = 0;

				}

			}
			// if count = 4 you found four of a kind
			if (count == 4) {

				return true;

			}

		}
		return false;
	}

	@Override

	public Hand createBestHand(Card[] allCards) {

		if (allCards.length < totalCards()) {

			return null;

		}
		// count to let sideCards array know when to add a card
		int count = 0;
		// variable to hold mainCards
		Card[] mainCards = new Card[numMainCards()];
		// variable to hold sideCards
		Card[] sideCards = new Card[allCards.length - numMainCards()];
		// iterates through all cards to see if there's any matches
		for (int i = 1; i < allCards.length; i++) {

			if (allCards[i - 1].getRank() == allCards[i].getRank()) {
				// if there's a match then mainCards[] is populated with matches
				mainCards[i - 1] = allCards[i - 1];

				mainCards[i] = allCards[i];
				// count is set to 0 so sideCards don't get populated
				count = 0;
			}

			else {
				// if matches are over then count = 1 so the if statement below
				// can start filling sideCards[i]
				count = 1;

			}

			if (count == 1) {

				sideCards[i] = allCards[i];

			}

		}
		// creates new besthand
		Hand bestHand = new Hand(mainCards, sideCards, name, getRanking());

		return bestHand;

	}

	@Override

	public boolean canSubsetSatisfy(Card[] allCards) {

		int count = 0;

		if (allCards.length >= numMainCards()) {
			// for loop iterates through all the cards and finds
			for (int i = 1; i < allCards.length; i++) {

				if (allCards[i - 1].getRank() == allCards[i].getRank()) {

					count++;

				}

				else {

					count = 0;

				}

			}

			if (count == 4) {

				return true;

			}

		}

		return false;

	}

}
