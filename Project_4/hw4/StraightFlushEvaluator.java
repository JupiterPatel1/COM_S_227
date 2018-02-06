package hw4;

/**
 * @author Richard Smith
 */
import api.Card;
import api.Hand;
import api.Suit;

/**
 * 
 * Evaluator for a hand consisting of a "straight" in which the
 * 
 * card ranks are consecutive numbers AND the cards all
 * 
 * have the same suit. The number of main
 * 
 * cards is equal to the total cards. An ace (card of rank 1)
 * 
 * may be treated as the highest possible card or as the lowest
 * 
 * (not both) To evaluate a straight containing an ace it is
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
 * The name of this evaluator is "Straight Flush".
 * 
 */

public class StraightFlushEvaluator extends StraightEvaluator {

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

	public StraightFlushEvaluator(int ranking, int handSize, int maxCardRank) {

		super(ranking, handSize, maxCardRank);

	}

	@Override

	public boolean satisfiedBy(Card[] mainCards) {

		boolean result = false;

		Suit suit = mainCards[0].getSuit();

		if (mainCards.length == numMainCards()) {

			for (int i = 0; i < mainCards.length; i++) {

				if (suit == mainCards[i].getSuit() && satisfiedBy(mainCards)) {

					result = true;

				}

				else {

					return false;

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

		int suitIndex = 0;

		Suit currentSuit = allCards[suitIndex].getSuit();

		if (allCards.length >= totalCards()) {

			for (int i = 0; i < allCards.length; i++) {

				if (currentSuit == allCards[i].getSuit()) {

					mainCardSize++;

					mainCards[mainIndex] = allCards[i];

					mainIndex++;

				}

				else {

					sideCardSize++;

					sideCards[sideIndex] = allCards[i];

					sideIndex++;

					suitIndex = i;

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

		boolean result = false;

		int suitIndex = 0;

		int suitCount = 0;

		Suit currentSuit = allCards[suitIndex].getSuit();

		if (allCards.length >= numMainCards()) {

			for (int i = 0; i < allCards.length; i++) {

				if (currentSuit == allCards[i].getSuit() && canSubsetSatisfy(allCards)) {

					suitCount++;

				}

				else {

					suitCount = 0;
					suitIndex = i;

				}

			}

			if (suitCount >= 5) {

				result = true;

			}

			else {

				result = false;

			}

		}

		return result;

	}

}