import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private int anteBet;
    private int playBet;
    private int pairPlusBet;
    private int totalWinnings;
    private int Cash;
    private int Cash2;

    public Player() {
        this.hand = new ArrayList<>();
        this.anteBet = 0;
        this.playBet = 0;
        this.pairPlusBet = 0;
        this.totalWinnings = 0;
        this.Cash = 25;
        this.Cash2 = 25;
    }


    public int getCash() {
        return Cash;
    }

    public void setCash(int Cash) {
        this.Cash = Cash;
    }

    public int getCash2() {
        return Cash2;
    }

    public void setCash2(int Cash2) {
        this.Cash2 = Cash2;
    }


    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public void setTotalWinnings(int totalWinnings) {
        this.totalWinnings = totalWinnings;
    }

    public int getAnteBet() {
        return anteBet;
    }

    public void setAnteBet(int anteBet) {
        if (anteBet >= 5 && anteBet <= 25) {
            this.anteBet = anteBet;
        }
    }

    public int getPlayBet() {
        return playBet;
    }

    public void setPlayBet(int playBet) {
        if (playBet == this.anteBet) {
            this.playBet = playBet;
        } else {
            throw new IllegalArgumentException("Play bet must equal ante bet");
        }
    }

    public int getPairPlusBet() {
        return pairPlusBet;
    }

    public void setPairPlusBet(int pairPlusBet) {
        if (pairPlusBet >= 5 && pairPlusBet <= 25 || pairPlusBet == 0) {
            this.pairPlusBet = pairPlusBet;
        } else {
            throw new IllegalArgumentException("Pair Plus bet must be between $5 and $25");
        }
    }

    public int getTotalWinnings() {
        return totalWinnings;
    }

    public void updateWinnings(int amount) {
        this.totalWinnings += amount;
    }

    public void clearBets() {
        this.anteBet = 0;
        this.playBet = 0;
        this.pairPlusBet = 0;
    }

    public void clearHand() {
        this.hand.clear();
    }

    public void reset() {
        clearHand();
        clearBets();
        this.totalWinnings = 0;
    }
}