package hw4;
/**
 * @author Richard Smith
 */
import api.Card;
import api.Hand;
import api.Suit;

/**
 * 
 * Evaluator for a hand that contains at least one card from
 * 
 * each suit. The number of main cards is four.
 * 
 * The name of this evaluator is "All Suits".
 * 
 */

public class AllSuitsEvaluator extends AbstractEvaluator {

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

	public AllSuitsEvaluator(int ranking, int handSize) {

		super("All Suits", ranking, 4, handSize);

	}

	@Override

	public boolean satisfiedBy(Card[] mainCards) {

		boolean result = false;

		if (mainCards.length == numMainCards()) {

			for (int i = 0; i < mainCards.length - 1; i++) {

				for (int j = i + 1; j < mainCards.length; j++) {

					if (mainCards[i].getSuit() != mainCards[j].getSuit()) {

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

		Card[] mainCards = new Card[numMainCards()];

		Card[] sideCards = new Card[allCards.length - numMainCards()];

		int mainCardIndex = 0;

		int sideCardIndex = 0;

		int count = 0;

		int suitIndex = 0;

		Suit currentSuit = allCards[suitIndex].getSuit();

		if (allCards.length >= totalCards()) {

			for (int i = 1; i < allCards.length; i++) {

				if (count < 5) {

					if (currentSuit != allCards[i].getSuit()) {

						mainCards[mainCardIndex] = allCards[mainCardIndex];

						mainCardIndex++;

						suitIndex++;

					}

					else {

						sideCards[sideCardIndex] = allCards[i];

						sideCardIndex++;

					}

					count++;

				}

				else {

					sideCards[sideCardIndex] = allCards[i];

					sideCardIndex++;

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

		boolean containsClub = false;

		boolean containsDiamond = false;

		boolean containsHeart = false;

		boolean containsSpade = false;

		if (allCards.length >= numMainCards()) {

			for (int i = 0; i < allCards.length; i++) {

				switch (allCards[i].getSuit()) {

				case CLUBS:
					containsClub = true;

				case DIAMONDS:
					containsDiamond = true;

				case HEARTS:
					containsHeart = true;

				case SPADES:
					containsSpade = true;

				default:
					return result;

				}

			}

			if (containsClub == true && containsDiamond == true && containsHeart == true && containsSpade == true) {

				result = true;

			}

		}

		return result;

	}

}