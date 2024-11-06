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
            return "hearts";
        }
        else if(suit == 'D'){
            return "diamonds";
        }
        else if(suit == 'C'){
            return "clubs";
        }
        else if(suit == 'S'){
            return "spades";
        }
        else{
            return "Unknown";
        }
    }

    public String getImagePath() {
        System.out.println("/Cards/" + value + "_of_" + getSuitAsString() + ".png");
        if(value<11){
            return "/Cards/" + value + "_of_" + getSuitAsString() + ".png";
        }
        else if(value == 11){
            return "/Cards/jack_of_" + getSuitAsString() + ".png";
        }
        else if(value == 12){
            return "/Cards/queen_of_" + getSuitAsString() + ".png";
        }
        else if(value == 13){
            return "/Cards/king_of_" + getSuitAsString() + ".png";
        }
        else{
            return "/Cards/ace_of_" + getSuitAsString() + ".png";
        }
    }

    public String toString() {
        return value + " of " + getSuitAsString();
    }

}