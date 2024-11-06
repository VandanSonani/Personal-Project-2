import java.util.ArrayList;

    public class Dealer {

        public ArrayList<Card> dealersHand;
        Deck theDeck;

    ArrayList<Card> DealersHand;

    Dealer(){
        theDeck = new Deck();
        DealersHand = new ArrayList<>();
    }

    public ArrayList<Card> dealHand(){
        ArrayList<Card> hand = new ArrayList<Card>();
        if(theDeck.remainingCards() < 3){
            theDeck.newDeck();
        }
        for(int i = 0; i < 3; i++){
            hand.add(theDeck.dealCard());
        }
        return hand;
    }

    }
