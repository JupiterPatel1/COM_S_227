package hw4;

/**
 * @author Richard Smith
 */
import api.Card;
import api.Hand;
import api.IEvaluator;
import api.Util;

/**
 *  CONTROL/SHIFT/F command made code beautiful but double spaced in some classes for some reason
 *  so please excuse that
 *  and not enough time to note all code. sorry.
 */

/**
 * The class AbstractEvaluator includes common code for all evaluator types.
 * 
 * TODO: Expand this comment with an explanation of how your class hierarchy is
 * organized.
 */
public abstract class AbstractEvaluator implements IEvaluator {
	// variable to hold name of evaluator
	protected String name;
	// variable to hold ranking
	protected int rank;
	// variable to hold the hand size (totalCards)
	protected int handSize;
	// variable to hold number of main cards
	protected int mainCardAmnt;

	public AbstractEvaluator(String name, int rank, int mainCardAmnt, int handSize) {
		this.name = name;
		this.rank = rank;
		this.mainCardAmnt = mainCardAmnt;
		this.handSize = handSize;
	}

	public String getName() {
		return name;
	}

	public int getRanking() {
		return rank;
	}

	public int numMainCards() {
		return mainCardAmnt;
	}

	public int totalCards() {
		return handSize;
	}

	public Hand createHand(Card[] allCards, int[] subset) {
		Card[] sideCards = new Card[allCards.length - subset.length];

		Card[] mainCards = new Card[subset.length];

		if (subset.length > allCards.length) {

			return null;

		}

		else if (allCards.length != this.totalCards()) {

			return null;

		}

		else {

			for (int i = 0; i < subset.length; i++) {

				mainCards[i] = allCards[subset[i]];

			}

			for (int i = 0; i < allCards.length; i++) {
				for (int j = 0; j < mainCards.length; j++) {

					if (allCards[i] != mainCards[j]) {
						sideCards[i] = allCards[i];

					}

				}

				if (sideCards.length >= allCards.length - mainCards.length) {

					break;

				}

			}

		}

		Hand newHand = new Hand(mainCards, sideCards, getName(), getRanking());
		return newHand;

	}
}
