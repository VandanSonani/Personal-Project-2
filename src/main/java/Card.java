public class Card {
    private char suit;
    private int value;


    public Card(char suit, int value) {
        this.suit = Character.toUpperCase(suit);
        this.value = value;
    }

    public char getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String getValueAsString() {
        if(value == 14){
            return "Ace";
        }
        else if (value == 13) {
            return "King";
        }
        else if (value == 12) {
            return "Queen";
        }
        else if (value == 11) {
            return "Jack";
        }
        else {
            return String.valueOf(value);
        }
    }

    public String getSuitAsString() {
        if(suit == 'H'){
            return "Hearts";
        }
        else if(suit == 'D'){
            return "Diamonds";
        }
        else if(suit == 'C'){
            return "Clubs";
        }
        else if(suit == 'S'){
            return "Spades";
        }
        else{
            return "Unknown";
        }
    }
}