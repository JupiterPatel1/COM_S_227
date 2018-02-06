package hw4;
/**
 * @author Richard Smith
 */
import api.Card;
import api.Hand;

/**
 * 
 * Evaluator satisfied by any set of cards. The number of
 * 
 * main cards is equal to the total cards.
 *
 * The name of this evaluator is "Catch All".
 * 
 */

public class CatchAllEvaluator extends AbstractEvaluator {
	//FINISHED
	
	/**
	 * Constructs the evaluator.
	 * 
	 * @param ranking
	 * 
	 *            ranking of this type of hand
	 * 
	 * @param handSize
	 * 
	 *            total number of cards in a hand
	 */

	public CatchAllEvaluator(int ranking, int handSize) {

		super("Catch All", ranking, handSize, handSize);

	}

	@Override

	public boolean satisfiedBy(Card[] mainCards) {
		// returns true if totalCards is equal to given cards
		if (totalCards() == mainCards.length) {

			return true;

		}

		else {

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

				mainCards[i] = allCards[i];

			}

		}
		// creates new bestHand with new maincards and sidecards
		Hand bestHand = new Hand(mainCards, sideCards, name, getRanking());

		return bestHand;

	}

	@Override

	public boolean canSubsetSatisfy(Card[] allCards) {
		// returns true is given card amount is greater than or equal to
		// numMainCards()
		if (numMainCards() <= allCards.length) {

			return true;

		}

		return false;

	}

}
