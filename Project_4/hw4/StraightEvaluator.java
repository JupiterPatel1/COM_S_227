package hw4;

/**
 * @author Richard Smith
 */
import api.Card;
import api.Hand;

/**
 * 
 * Evaluator for a hand consisting of a "straight" in which the
 * 
 * card ranks are consecutive numbers. The number of main
 * 
 * cards is equal to the total cards. An ace (card of rank 1)
 * 
 * may be treated as the highest possible card or as the lowest
 * 
 * (not both). To evaluate a straight containing an ace it is
 * 
 * necessary to know what the highest card rank will be in a
 * 
 * given game; therefore, this value must be specified when the
 * 
 * evaluator is constructed. In a hand created by this
 * 
 * evaluator the cards are listed in descending order with high
 * 
 * card first, e.g. [10 9 8 7 6] or [A K Q J 10], with
 * 
 * one exception: In case of an ace-low straight, the ace
 * 
 * must appear last, as in [5 4 3 2 A]
 * 
 * The name of this evaluator is "Straight".
 * 
 */

public class StraightEvaluator extends AbstractEvaluator {

	protected int maxCardRank;

	/**
	 * 
	 * Constructs the evaluator. Note that the maximum rank of
	 * 
	 * the cards to be used must be specified in order to
	 * 
	 * correctly evaluate a straight with ace high.
	 * 
	 * @param ranking
	 * 
	 *            ranking of this type of hand
	 * 
	 * @param handSize
	 * 
	 *            total number of cards in a hand
	 * 
	 * @param maxCardRank
	 * 
	 *            largest rank of any card to be used
	 * 
	 */

	public StraightEvaluator(int ranking, int handSize, int maxCardRank) {

		super("Straight", ranking, handSize, handSize);

		this.maxCardRank = maxCardRank;

	}

	@Override

	public boolean satisfiedBy(Card[] mainCards) {

		boolean result = false;

		if (mainCards.length == numMainCards()) {

			for (int i = 0; i < mainCards.length; i++) {

				if (maxCardRank == 1) {

					if (mainCards[0].getRank() != 1) {

						return false;

					}

					else {

						if (i == 0) {

							if (mainCards[i].getRank() + 12 == mainCards[i + 1].getRank()) {

								result = true;

							}

							else {

								return false;

							}

						}

						else {

							if (mainCards[i].getRank() - 1 == mainCards[i + 1].getRank()) {

								result = true;

							}

							else {

								return false;

							}

						}

					}

				}

				if (maxCardRank != 1) {

					if (mainCards[i].getRank() - 1 == mainCards[i + 1].getRank()) {

						result = true;

					}

					else {

						return false;

					}

				}

			}

		}

		return result;

	}

	@Override

	public Hand createBestHand(Card[] allCards) {

		int mainCardSize = 1;

		int sideCardSize = 1;

		Card[] mainCards = new Card[mainCardSize];

		Card[] sideCards = new Card[sideCardSize];

		int mainIndex = 0;

		int sideIndex = 0;

		if (allCards.length >= totalCards()) {

			for (int i = 1; i < allCards.length; i++) {

				if (maxCardRank == 1) {

					if (i == 0) {

						if (allCards[i - 1].getRank() + 12 == allCards[i].getRank()) {
							// If the first card is an ace

							mainCards[mainIndex] = allCards[i - 1];
							// Add it to the first index of mainCards

							mainIndex++;
							// Go to the next index

							mainCardSize++;
							// Increase the size of the main card array

						}

						else {
							// If the first card is not an ace

							sideCards[sideIndex] = allCards[i - 1];
							// Add the card to the side card array

							sideIndex++;
							// Go to the next index

							sideCardSize++;

						}

					}

					else {

						if (allCards[i - 1].getRank() - 1 == allCards[i].getRank()) {

							mainCards[mainIndex] = allCards[i - 1];

							mainIndex++;

							mainCardSize++;

						}

						else {

							sideCards[sideIndex] = allCards[i - 1];

							sideIndex++;

							sideCardSize++;

						}

					}

				}

				if (maxCardRank != 1) {

					if (mainCards[i - 1].getRank() - 1 == mainCards[i].getRank()) {

						mainCards[mainIndex] = allCards[i - 1];

						mainIndex++;

						mainCardSize++;

					}

					else {

						sideCards[sideIndex] = allCards[i - 1];

						sideIndex++;

						sideCardSize++;

					}

				}

			}

		}

		else {

			return null;

		}

		Hand bestHand = new Hand(mainCards, sideCards, name, getRanking());

		return bestHand;

	}

	@Override

	public boolean canSubsetSatisfy(Card[] allCards) {

		boolean foo = false;

		if (allCards.length >= numMainCards()) {
			// iterate through the cards
			for (int i = 1; i < allCards.length; i++) {
				// If highest card is ace
				if (maxCardRank == 1) {
					// If first card is not an ace boolean returns false
					if (allCards[0].getRank() != 1) {

						return foo;

					}
					// else check through hierarchy
					else {
						// If the current index is 0
						if (i == 0) {
							// If a king is the next card then boolean turns
							// true
							if (allCards[i - 1].getRank() == (allCards[i].getRank() + 12)) {

								foo = true;

							}

							else {
								// If the next card is not a king, there's no
								// subset
								return false;

							}

						}

						else {
							// If the next card is 1 less rank than the current
							// or the same rank change boolean to true
							if ((allCards[i - 1].getRank() == allCards[i].getRank())
									|| (allCards[i - 1].getRank() == allCards[i].getRank() - 1)) {

								foo = true;

							}

							else {

								return false;

							}

						}

					}

				}
				// If the highest card is not an ace
				if (maxCardRank != 1) {
					// Check hierarchy again
					if (allCards[i - 1].getRank() >= allCards[i].getRank() + 1) {

						foo = true;

					}

					else {

						return false;

					}

				}

			}

		}

		return foo;

	}

}