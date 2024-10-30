import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {

	private Card aceHearts;
	private Card kingSpades;
	private Card twoClubs;

	@BeforeEach
	void setUp() {
		aceHearts = new Card('H', 14);
		kingSpades = new Card('S', 13);
		twoClubs = new Card('C', 2);
	}

	@Test
	void testValidCardCreation() {
		assertEquals('H', aceHearts.getSuit());
		assertEquals(14, aceHearts.getValue());
		assertEquals('S', kingSpades.getSuit());
		assertEquals(13, kingSpades.getValue());
		assertEquals('C', twoClubs.getSuit());
		assertEquals(2, twoClubs.getValue());
		assertEquals("Hearts", aceHearts.getSuitAsString());
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
	void GetHighCardNames(){
		Card newCard = new Card ('h', 12);
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



}
