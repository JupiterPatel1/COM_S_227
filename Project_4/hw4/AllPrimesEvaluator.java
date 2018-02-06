package hw4;
/**
 * @author Richard Smith
 */
import api.Card;
import api.Hand;

/**
 * 
 * Evaluator for a hand in which the rank of each card is a prime number.
 * 
 * The number of main cards required is equal to the total cards. 
 * 
 * The name of this evaluator is "All Primes".
 * 
 */

public class AllPrimesEvaluator extends AbstractEvaluator { 

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

	public AllPrimesEvaluator(int ranking, int handSize) { 

		super("All primes", ranking, handSize, handSize);

	}

	@Override

	public boolean satisfiedBy(Card[] mainCards) { 

		boolean result = false;

		if (mainCards.length == numMainCards()) {

			for (int i = 0; i < mainCards.length; i++) {

				if (!((mainCards[i].getRank() % 2 == 0) && (mainCards[i].getRank() % 3 == 0))) {

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

		Card[] mainCards = new Card[numMainCards()];

		int numSideCards = 0;

		Card[] sideCards = new Card[numSideCards];

		if (allCards.length >= totalCards()) {

			int index = 0;

			for (int i = 0; i < allCards.length; i++) {

				if (!((allCards[i].getRank() % 2 == 0) && (allCards[i].getRank() % 3 == 0))) {

					mainCards[i] = allCards[i];

				}

				else {

					numSideCards++;

					sideCards[index] = allCards[i];

					index++;

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

		int count = 0;

		if (allCards.length >= numMainCards()) {

			for (int i = 0; i < allCards.length; i++) {

				if (!((allCards[i].getRank() % 2 == 0) && (allCards[i].getRank() % 3 == 0))) {

					count++;

				}

				if (count >= 2) {

					result = true;

				}

			}

		}

		return result;

	}

}