import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyTest {

	private Card aceHearts;
	private Card kingSpades;
	private Card twoClubs;
	private Deck deck;


	@BeforeEach
	void setUp() {
		aceHearts = new Card('H', 14);
		kingSpades = new Card('S', 13);
		twoClubs = new Card('C', 2);
		deck = new Deck();

	}

	@Test
	void testValidCardCreation() {
		assertEquals('H', aceHearts.getSuit());
		assertEquals(14, aceHearts.getValue());
		assertEquals('S', kingSpades.getSuit());
		assertEquals(13, kingSpades.getValue());
		assertEquals('C', twoClubs.getSuit());
		assertEquals(2, twoClubs.getValue());
		assertEquals("hearts", aceHearts.getSuitAsString());
	}

	@Test
	void testLowercaseSuitIsAccepted() {
		Card lowercaseClub = new Card('c', 2);
		System.out.println("lowercaseClub: " + lowercaseClub.getSuit());
		assertEquals('C', lowercaseClub.getSuit());
	}

	@Test
	void testInvalidSuit() {
		Card invalidSuit = new Card('X', 2);
		System.out.println("invalidSuit: " + invalidSuit.getSuit());
		assertEquals('X', invalidSuit.getSuit());
	}

	@Test
	void GetHighCardNames() {
		Card newCard = new Card('h', 12);
		assertEquals('H', newCard.getSuit());
		System.out.println("newCard: " + newCard.getValueAsString());

		assertEquals("Queen", newCard.getValueAsString());

		Card newCard2 = new Card('s', 11);
		System.out.println("newCard2: " + newCard2.getValueAsString());

		assertEquals("Jack", newCard2.getValueAsString());

		Card newCard3 = new Card('d', 14);
		System.out.println("newCard3: " + newCard3.getValueAsString());
		assertEquals("Ace", newCard3.getValueAsString());

		Card newCard4 = new Card('c', 13);
		System.out.println("newCard4: " + newCard4.getValueAsString());
		assertEquals("King", newCard4.getValueAsString());
	}

	@Test
	void testShuffleChangesOrder() {
		Deck firstDeck = new Deck();
		Deck secondDeck = new Deck();
		System.out.println("First deck: " + firstDeck);
		System.out.println("Second deck: " + secondDeck);

		boolean foundDifference = false;
		for (int i = 0; i < firstDeck.size(); i++) {
			if (!firstDeck.get(i).equals(secondDeck.get(i))) {
				foundDifference = true;
				break;
			}
		}
		assertTrue(foundDifference);
	}

	@Test
	void testNewDeckHas52Cards() {
		System.out.println("Deck size: " + deck.size());
		assertEquals(52, deck.size());

	}

	@Test
	void testNewDeckAfterDealing() {
		// Deal some cards
		deck.dealCard();
		deck.dealCard();

		// Create new deck
		deck.newDeck();

		assertEquals(52, deck.size());
	}

	@Test
	void testRemainingCards() {
		assertEquals(52, deck.remainingCards());
		deck.dealCard();
		System.out.println("Card Dealt: " + deck.dealCard());
		assertEquals(50, deck.remainingCards());
	}


	@Test
	void testDealEmptyDeck() {
		while (!deck.isEmpty()) {
			deck.dealCard();
		}
	}

	@Test
	void testNewDeckHasAllCards() {
		// Count number of each suit
		int hearts = 0, diamonds = 0, clubs = 0, spades = 0;
		for (Card card : deck) {
			if (card.getSuit() == 'H') {
				hearts++;
			} else if (card.getSuit() == 'D') {
				diamonds++;
			} else if (card.getSuit() == 'C') {
				clubs++;
			} else if (card.getSuit() == 'S') {
				spades++;
			}
		}

		System.out.println("Hearts: " + hearts);
		System.out.println("Diamonds: " + diamonds);
		System.out.println("Clubs: " + clubs);
		System.out.println("Spades: " + spades);

		assertEquals(13, hearts);
		assertEquals(13, diamonds);
		assertEquals(13, clubs);
		assertEquals(13, spades);
	}


	// <----TESTING THREE CARD LOGIC HERE ---->

	@Test
	void testStraightFlush() {
		ArrayList<Card> straightFlush = new ArrayList<>(Arrays.asList(
				new Card('H', 10),
				new Card('H', 9),
				new Card('H', 8)
		));
		System.out.println("Straight Flush: " + ThreeCardLogic.evalHand(straightFlush));
		assertEquals(1, ThreeCardLogic.evalHand(straightFlush));
	}


	@Test
	void testThreeOfKind() {
		ArrayList<Card> threeOfKind = new ArrayList<>(Arrays.asList(
				new Card('H', 10),
				new Card('D', 10),
				new Card('S', 10)
		));
		System.out.println("Three of a Kind: " + ThreeCardLogic.evalHand(threeOfKind));
		assertEquals(2, ThreeCardLogic.evalHand(threeOfKind));
	}



	@Test
	void testStraight() {
	// Test Straight
	ArrayList<Card> straight = new ArrayList<>(Arrays.asList(
			new Card('H', 10),
			new Card('D', 9),
			new Card('S', 8)
	));

	assertEquals(3,ThreeCardLogic.evalHand(straight));

}
	@Test
			void testFlush() {
	// Test Flush
	ArrayList<Card> flush = new ArrayList<>(Arrays.asList(
			new Card('H', 10),
			new Card('H', 5),
			new Card('H', 3)
	));

	assertEquals(4,ThreeCardLogic.evalHand(flush));

}

	@Test
	void testPair() {
		// Test Pair
		ArrayList<Card> pair = new ArrayList<>(Arrays.asList(
				new Card('H', 10),
				new Card('D', 10),
				new Card('S', 5)
		));
		assertEquals(5, ThreeCardLogic.evalHand(pair));
	}

	@Test
	void testHighCard() {
	// Test High Card
	ArrayList<Card> highCard = new ArrayList<>(Arrays.asList(
			new Card('H', 10),
			new Card('D', 8),
			new Card('S', 5)
	));
	assertEquals(0, ThreeCardLogic.evalHand(highCard));
		}



	@Test
	void testEvalPPWinnings() {
		int bet = 10;

		//  (40:1)
		ArrayList<Card> straightFlush = new ArrayList<>(Arrays.asList(
				new Card('H', 10),
				new Card('H', 9),
				new Card('H', 8)
		));
		assertEquals(400, ThreeCardLogic.evalPPWinnings(straightFlush, bet));

		//  (30:1)
		ArrayList<Card> threeOfKind = new ArrayList<>(Arrays.asList(
				new Card('H', 10),
				new Card('D', 10),
				new Card('S', 10)
		));
		assertEquals(300, ThreeCardLogic.evalPPWinnings(threeOfKind, bet));

		// Test no win
		ArrayList<Card> highCard = new ArrayList<>(Arrays.asList(
				new Card('H', 10),
				new Card('D', 8),
				new Card('S', 5)
		));
		assertEquals(0, ThreeCardLogic.evalPPWinnings(highCard, bet));
	}

	@Test
	void testPairVariations() {
		// Test pair with high card
		ArrayList<Card> highPair = new ArrayList<>();
		highPair.add(new Card('H', 14)); // Ace
		highPair.add(new Card('D', 14)); // Ace
		highPair.add(new Card('S', 13)); // King
		assertEquals(5, ThreeCardLogic.evalHand(highPair));

		// Test pair with low card
		ArrayList<Card> lowPair = new ArrayList<>();
		lowPair.add(new Card('H', 2));
		lowPair.add(new Card('D', 2));
		lowPair.add(new Card('S', 3));
		assertEquals(5, ThreeCardLogic.evalHand(lowPair));
	}


	@Test
	void testCompareHandsComprehensive() {
		// Dealer doesn't qualify (no queen or higher)
		ArrayList<Card> dealerNoQualify = new ArrayList<>();
		dealerNoQualify.add(new Card('H', 2));
		dealerNoQualify.add(new Card('D', 5));
		dealerNoQualify.add(new Card('S', 10));
		System.out.println("Dealer No Qualify: " + ThreeCardLogic.compareHands(dealerNoQualify, dealerNoQualify));

		ArrayList<Card> playerHand = new ArrayList<>();
		playerHand.add(new Card('H', 2));
		playerHand.add(new Card('D', 3));
		playerHand.add(new Card('S', 4));
		System.out.println("Player Hand: " + ThreeCardLogic.compareHands(dealerNoQualify, playerHand));

		assertEquals(-1, ThreeCardLogic.compareHands(dealerNoQualify, playerHand));

		// Equal hands, compare high cards
		ArrayList<Card> dealerHighCard = new ArrayList<>();
		dealerHighCard.add(new Card('H', 14)); // Ace
		dealerHighCard.add(new Card('D', 5));
		dealerHighCard.add(new Card('S', 3));
		System.out.println("Dealer High Card: " + ThreeCardLogic.compareHands(dealerHighCard, dealerHighCard));

		ArrayList<Card> playerLowerCard = new ArrayList<>();
		playerLowerCard.add(new Card('C', 13)); // King
		playerLowerCard.add(new Card('H', 5));
		playerLowerCard.add(new Card('D', 3));
		System.out.println("Player Lower Card: " + ThreeCardLogic.compareHands(dealerHighCard, playerLowerCard));

		assertEquals(1, ThreeCardLogic.compareHands(dealerHighCard, playerLowerCard));
	}

	@Test
	void testPPWinningsEdgeCases() {
		int bet = 25;

		// Test straight flush with minimum bet
		ArrayList<Card> straightFlush = new ArrayList<>();
		straightFlush.add(new Card('H', 2));
		straightFlush.add(new Card('H', 3));
		straightFlush.add(new Card('H', 4));
		System.out.println("Straight Flush: " + ThreeCardLogic.evalPPWinnings(straightFlush, bet));
		assertEquals(1000, ThreeCardLogic.evalPPWinnings(straightFlush, bet));

		// Test Three of a Kind with maximum value cards
		ArrayList<Card> threeAces = new ArrayList<>();
		threeAces.add(new Card('H', 14));
		threeAces.add(new Card('D', 14));
		threeAces.add(new Card('S', 14));
		System.out.println("Three Aces: " + ThreeCardLogic.evalPPWinnings(threeAces, bet));
		assertEquals(750, ThreeCardLogic.evalPPWinnings(threeAces, bet));

		// zero bet
		ArrayList<Card> straight = new ArrayList<>();
		straight.add(new Card('H', 3));
		straight.add(new Card('D', 4));
		straight.add(new Card('S', 5));
		System.out.println("Straight: " + ThreeCardLogic.evalPPWinnings(straight, 0));
		assertEquals(0, ThreeCardLogic.evalPPWinnings(straight, 0));
	}


	@Test
	void testStraightEdgeCases() {
		// Test Ace-2-3 straight (lowest possible)
		ArrayList<Card> lowStraight = new ArrayList<>(Arrays.asList(
				new Card('H', 2), // Ace
				new Card('D', 3),
				new Card('S', 4)
		));
		assertEquals(3, ThreeCardLogic.evalHand(lowStraight));

		// Test Queen-King-Ace straight (highest possible)
		ArrayList<Card> highStraight = new ArrayList<>(Arrays.asList(
				new Card('H', 12), // Queen
				new Card('C', 13), // King
				new Card('S', 14)  // Ace
		));
		assertEquals(3, ThreeCardLogic.evalHand(highStraight));
	}


	@Test
	void testDealerQualificationEdgeCases() {
		ArrayList<Card> playerHand = new ArrayList<>(Arrays.asList(
				new Card('H', 2),
				new Card('D', 3),
				new Card('S', 4)


		));

		// deal qualifies
		ArrayList<Card> dealerQueenHigh = new ArrayList<>(Arrays.asList(
				new Card('H', 12), // Queen
				new Card('D', 5),
				new Card('S', 2)
		));
		assertEquals(2, ThreeCardLogic.compareHands(dealerQueenHigh, playerHand));

		//shouldn't qualify
		ArrayList<Card> dealerJackHigh = new ArrayList<>(Arrays.asList(
				new Card('H', 11), // Jack
				new Card('D', 10),
				new Card('S', 9)
		));
		assertEquals(1, ThreeCardLogic.compareHands(dealerJackHigh, playerHand));
	}





}



