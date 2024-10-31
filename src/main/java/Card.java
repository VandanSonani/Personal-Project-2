public class Card {
    private char suit;
    private int value;


    public Card(char suit, int value) {
        if (!isValidSuit(suit)) {
            System.out.println("Invalid suit. Must be H, D, C, or S");
        }
        if (!isValidValue(value)) {
            System.out.println("Invalid value. Must be between 2 and 14");
        }
        this.suit = Character.toUpperCase(suit);
        this.value = value;
    }

    public char getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public boolean isValidSuit(char suit) {
        char upperSuit = Character.toUpperCase(suit);
        return upperSuit == 'H' || upperSuit == 'D' || upperSuit == 'C' || upperSuit == 'S';
    }

    public boolean isValidValue(int value) {
        return value >= 2 && value <= 14;
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