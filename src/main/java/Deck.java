import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card>{

    Deck(){
        newDeck();
    }

    public void newDeck(){
        this.clear();


        char[] suits = {'H', 'D', 'C', 'S'};

        for(int i = 0; i < suits.length; i++){
            for(int j = 2; j <= 14; j++){
                this.add(new Card(suits[i], j));
            }
        }

        Collections.shuffle(this);



    }

    public Card dealCard() {
        if (this.isEmpty()) {
            System.out.println("Deck is empty. Shuffling new deck.");
            this.newDeck();
        }
        return this.remove(0);
    }

    public int remainingCards() {
        return this.size();
    }


}