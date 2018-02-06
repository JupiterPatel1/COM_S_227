package hw4;

/**
 * @author Richard Smith
 */
import api.Card;
import api.Hand;

/**
 * Evaluator for a hand containing (at least) two cards of the same rank. The
 * number of main cards is two.
 * 
 * The name of this evaluator is "One Pair".
 */
// Note: You must edit this declaration to extend AbstractEvaluator
// or to extend some other class that extends AbstractEvaluator
public class OnePairEvaluator extends AbstractEvaluator {

	/**
	 * Constructs the evaluator.
	 * 
	 * @param ranking
	 *            ranking of this type of hand
	 * @param handSize
	 *            total number of cards in a hand
	 */
	public OnePairEvaluator(int ranking, int handSize) {

		super("One Pair", ranking, 2, handSize);

	}

	@Override
	public boolean satisfiedBy(Card[] mainCards) {
		// variable to hold satisfied status
		boolean satisfied = false;
		// length less than three to ensure its less than the required amount of
		// cards
		if (mainCards.length < 3) { // both for loops iterate through cards to
									// see if rank matches
			for (int j = 0; j < mainCards.length; j++) {

				for (int i = 1 + j; i < mainCards.length; i++) {
					// changes satisfied variable to true if any matches are
					// found
					if (mainCards[i].getRank() == mainCards[j].getRank()) {

						satisfied = true;
					}
				}
			}
		}
		return satisfied;
	}

	@Override
	public boolean canSubsetSatisfy(Card[] allCards) {
		// same as method "satisfiedBy"
		boolean satisfied = false;

		for (int j = 0; j < allCards.length; j++) {

			for (int i = 1 + j; i < allCards.length; i++) {

				if (allCards[i].getRank() == allCards[j].getRank()) {
					// changes satisfied variable to true if any matches are
					// found
					satisfied = true;

				}

			}

		}

		return satisfied;

	}

	@Override

	public Hand createBestHand(Card[] allCards) {

		Card[] mainCards = new Card[numMainCards()];

		Card[] sideCards = new Card[numMainCards()];

		int mainIndex = 0;

		int sideIndex = 0;

		if (allCards.length >= totalCards()) {
			// iterates through allCards[] and adds matches
			for (int i = 1; i < allCards.length; i++) {
				// if there's a match. store both cards
				if (allCards[i - 1].getRank() == allCards[i].getRank()) {

					mainCards[mainIndex] = allCards[i - 1];

					mainIndex++;

					mainCards[mainIndex] = allCards[i];

					mainIndex++;

				}

				if (allCards[i - 1].getSuit() == allCards[i].getSuit()) {

					sideCards[sideIndex] = allCards[i - 1];

					sideIndex++;

					sideCards[sideIndex] = allCards[i];

					sideIndex++;

				}

			}

		}

		else {

			return null;

		}

		Hand bestHand = new Hand(mainCards, sideCards, name, getRanking());

		return bestHand;

	}
}
