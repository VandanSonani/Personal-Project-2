import java.util.ArrayList;
import java.util.Collections;

public class ThreeCardLogic {

    public static int evalHand(ArrayList<Card> hand) {
        Collections.sort(hand, (a, b) -> Integer.compare(a.getValue(), b.getValue()));

        boolean isFlush = isFlush(hand);
        boolean isStraight = isStraight(hand);

        if (isFlush && isStraight) return 1;  // Straight Flush
        if (isThreeOfAKind(hand)) return 2;   // Three of a Kind
        if (isStraight) return 3;             // Straight
        if (isFlush) return 4;                // Flush
        if (isPair(hand)) return 5;           // Pair

        return 0; // High Card
    }


    public static int evalPPWinnings(ArrayList<Card> hand, int bet) {
        int handType = evalHand(hand);

        switch (handType) {
            case 1: return bet * 40; // Straight Flush
            case 2: return bet * 30; // Three of a Kind
            case 3: return bet * 6;  // Straight
            case 4: return bet * 3;  // Flush
            case 5: return bet ;  // Pair
            default: return 0;       // No Pair Plus winnings
        }
    }

    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        int dealerHandValue = evalHand(dealer);
        int playerHandValue = evalHand(player);

        if (dealerHandValue == 0 && dealer.get(2).getValue() < 12) {
            return -1; // Dealer does not qualify
        }

        if (playerHandValue > dealerHandValue) return 2;  // Player wins
        if (playerHandValue < dealerHandValue) return 1;  // Dealer wins

        for (int i = 2; i >= 0; i--) {
            if (player.get(i).getValue() > dealer.get(i).getValue()) return 2;
            if (player.get(i).getValue() < dealer.get(i).getValue()) return 1;
        }

        return 0; // Tie
    }

    public static boolean isFlush(ArrayList<Card> hand) {
        char suit = hand.get(0).getSuit();
        return hand.stream().allMatch(card -> card.getSuit() == suit);
    }

    public static boolean isStraight(ArrayList<Card> hand) {
        return hand.get(1).getValue() == hand.get(0).getValue() + 1 &&
                hand.get(2).getValue() == hand.get(1).getValue() + 1;
    }

    public static boolean isThreeOfAKind(ArrayList<Card> hand) {
        return hand.get(0).getValue() == hand.get(1).getValue() &&
                hand.get(1).getValue() == hand.get(2).getValue();
    }

    public static boolean isPair(ArrayList<Card> hand) {
        return hand.get(0).getValue() == hand.get(1).getValue() ||
                hand.get(1).getValue() == hand.get(2).getValue() ||
                hand.get(0).getValue() == hand.get(2).getValue();
    }
}
