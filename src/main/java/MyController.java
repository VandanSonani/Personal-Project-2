import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyController implements Initializable {

    public Button exitGame;
    private ArrayList<String> sheetArray = new ArrayList<>();
    private ArrayList<String> welcomeArray = new ArrayList<>();


    public Button playGame;
    private int sheetIndex = 0;

    public Player player1;
    public Player player2;

    private Dealer dealer = new Dealer();
    private Player playerOne = new Player();
    private Player playerTwo = new Player();


    private boolean isPlayer1Folded = false;
    private boolean isPlayer2Folded = false;

    @FXML
    private Button foldPlayer1Button;

    @FXML
    private Button dealButton;

    @FXML
    private Button playButton;

    @FXML
    private Button foldPlayer2Button;

    @FXML
    private TextField Ante;

    @FXML
    private TextField Ante2;
    @FXML
    private TextField PairPlus;
    @FXML
    private TextField PairPlus2;
    @FXML
    private TextField PlayBet;
    @FXML
    private TextField PlayBet2;


    @FXML
    private Label player1HandTypeLabel;
    @FXML
    private Label player2HandTypeLabel;
    @FXML
    private Label dealerHandTypeLabel;


    @FXML
    private HBox player1Hand1;
    @FXML
    private HBox player2Hand2;
    @FXML
    private HBox dealerHand1;


    @FXML
    private VBox root;


    @FXML
    private TextField Cash;

    @FXML
    private TextField Cash2;

    @FXML
    private Pane root2;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub


    }


        //<----MENU ITEM FUNCTIONALITY---->
        @FXML
    public void handleExit(ActionEvent e){
        System.exit(0);
    }
    // method for exitting the application with a pop-up specifically through the menu bar
    @FXML
    private void exitApplication(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("Choose 'Yes' to exit or 'No' to resume.");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No", ButtonType.CANCEL.getButtonData());

        alert.getButtonTypes().setAll(yesButton, noButton);

        alert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                System.exit(0);
            }
        });
    }

    @FXML
     private Boolean isNumeric(String s){
        try{
            Double.parseDouble(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

//    Ante.setPromptText("Enter Ante Bet");

    // changes the stylesheet of the application changing the visual background of the gameplay.

    @FXML
    private void applyGameNewLook(ActionEvent e) {
//        root2.getStylesheets().add("/styles/gameNewLook.css");


        if(root2 != null){
            root2.getStylesheets().clear();
            sheetArray.clear();

        }
        if(root != null){
            root.getStylesheets().clear();
            welcomeArray.clear();

        }

        sheetArray.add("/styles/style2.css");
        sheetArray.add("/styles/gameNewLook.css");

        welcomeArray.add("/styles/style1.css");
        welcomeArray.add("/styles/welcomeNewLook.css");

        sheetIndex = (sheetIndex + 1) % sheetArray.size();
        String currentSheet = sheetArray.get(sheetIndex);
        String currentWelcomeSheet = welcomeArray.get(sheetIndex);
        if(root2 !=null ){
            root2.getStylesheets().add(currentSheet);
        }
        if(root!=null){
            root.getStylesheets().add(currentWelcomeSheet);
        }

    }

    //completely restarts the game, resetting all the previous bets made and hands dealt, going back to the welcome screen.
    @FXML
    private void freshStartMethod(ActionEvent event) {
        setDefaults();
    }

    @FXML
    private void setDefaults() {
        player1Hand1.getChildren().clear();
        player2Hand2.getChildren().clear();
        dealerHand1.getChildren().clear();
        Ante.setPromptText("Enter Ante Bet");
        Ante2.setPromptText("Enter Ante Bet");
        PairPlus.setPromptText("Enter Pair Plus Bet");
        PairPlus2.setPromptText("Enter Pair Plus Bet");

        playerOne.setTotalWinnings(0);
        playerTwo.setTotalWinnings(0);
        player1HandTypeLabel.setText("");
        player2HandTypeLabel.setText("");
        dealerHandTypeLabel.setText("");

        Ante.setText("");
        Ante2.setText("");
        PlayBet.setText("0");
        PlayBet2.setText("0");
        PairPlus.setText("");
        PairPlus2.setText("");
        Cash.setText("0");
        Cash2.setText("0");
        Ante.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {

            if(!isNumeric(keyEvent.getCharacter())){
                keyEvent.consume();
            }
        });
        Ante2.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if(!isNumeric(keyEvent.getCharacter())){
                keyEvent.consume();
            }
        });
        PairPlus.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if(!isNumeric(keyEvent.getCharacter())){
                keyEvent.consume();
            }
        });
        PairPlus2.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if(!isNumeric(keyEvent.getCharacter())){
                keyEvent.consume();
            }
        });


    }


    //<----PLAY GAME FUNCTIONALITY---->

    @FXML
    private void loadGamePage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GamePage.fxml"));
            Parent root2 = loader.load();
//            root2.getStylesheets().add(sheetArray.get(sheetIndex));//set style
            root2.getStylesheets().add("/styles/style2.css");//set style
            root.getScene().setRoot(root2);
            MyController controller = loader.getController();
            controller.setDefaults();


        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getHandType(ArrayList<Card> hand) {
        // Implement the logic to determine the hand type (flush, straight, high-card, etc.)
        // This is a placeholder implementation
        if (ThreeCardLogic.isPair(hand)) {
            return "Pair";
        } else if (ThreeCardLogic.isStraight(hand)) {
            return "Straight";
        }
        else if(ThreeCardLogic.isFlush(hand)){
            return "Flush";
        }
        else if(ThreeCardLogic.isThreeOfAKind(hand)){
            return "Three of a Kind";
        }
        else if(ThreeCardLogic.isStraight(hand) && ThreeCardLogic.isFlush(hand)){
            return "Straight Flush";
        }
                else {
            return "High Card";
        }
    }


    //<----DEALING CARDS FUNCTIONALITY---->

    @FXML
    private void dealCardsFunc() {
        dealerHandTypeLabel.setText("");
        playerOne.setHand(dealer.dealHand());
        playerTwo.setHand(dealer.dealHand());
        dealer.dealersHand = dealer.dealHand();


        System.out.println("Player 1 Hand: " + playerOne.getHand());
        System.out.println("Player 2 Hand: " + playerTwo.getHand());
        System.out.println("Dealer Hand: " + dealer.dealersHand);
        System.out.println("Cards left: " + dealer.theDeck.remainingCards());


        PlayBet.setText(Ante.getText());
        PlayBet2.setText(Ante2.getText());


        foldPlayer1Button.setDisable(false);
        foldPlayer2Button.setDisable(false);

        playButton.setDisable(false);
        dealButton.setDisable(true);
        Ante.setDisable(true);
        Ante2.setDisable(true);
        PairPlus.setDisable(true);
        PairPlus2.setDisable(true);

        displayHand(playerOne.getHand(), player1Hand1, false);
        displayHand(playerTwo.getHand(), player2Hand2, false);
        displayHand(dealer.dealersHand, dealerHand1, true);

        player1HandTypeLabel.setText(getHandType(playerOne.getHand()));
        player2HandTypeLabel.setText(getHandType(playerTwo.getHand()));


    }

    private ImageView createCardImageView(String imagePath) {
        Image cardImage = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(cardImage);
        imageView.setFitWidth(100);
        imageView.setFitHeight(175);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    private void displayHand(ArrayList<Card> hand, HBox container, boolean faceDown) {
        container.getChildren().clear();

        for (Card card : hand) {
            ImageView cardView;
            if (faceDown) {
                cardView = createCardImageView("/Cards/back-card.png");
            } else {
                cardView = createCardImageView(card.getImagePath());
            }
            container.getChildren().add(cardView);
        }
    }


    //<----FOLDING FUNCTIONALITY---->

    @FXML
    public void foldPlayer1() {
        foldCards(1);
    }

    @FXML
    public void foldPlayer2() {
        foldCards(2);
    }

    @FXML
    private void showFoldMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fold");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void resetGameForNextRound() {
        isPlayer1Folded = false;
        isPlayer2Folded = false;

    }

    @FXML
    private void foldCards(int playerNumber) {
        if (playerNumber == 1) {
            isPlayer1Folded = true;
            displayHand(playerOne.getHand(), player1Hand1, true);
            player1HandTypeLabel.setText("Folded");
            showFoldMessage("Player 1 has folded. Dealer wins this round!");
        } else if (playerNumber == 2) {
            isPlayer2Folded = true;
            displayHand(playerTwo.getHand(), player2Hand2, true);
            player2HandTypeLabel.setText("Folded");
            showFoldMessage("Player 2 has folded. Dealer wins this round!");
        }
    }



    //<----PLAYING CURRENT CARDS FUNCTIONALITY---->

    @FXML
    private void playCards() {
        if (isPlayer1Folded && isPlayer2Folded) {
            resetGameForNextRound();
            return;
        }

        // Disable fold buttons and re-enable betting fields for next round
        foldPlayer1Button.setDisable(true);
        foldPlayer2Button.setDisable(true);
        playButton.setDisable(true);
        dealButton.setDisable(false);
        Ante.setDisable(false);
        Ante2.setDisable(false);
        PairPlus.setDisable(false);
        PairPlus2.setDisable(false);

        // Show dealer's hand
        displayHand(dealer.dealersHand, dealerHand1, false);

        dealerHandTypeLabel.setText(getHandType(dealer.dealersHand));


        StringBuilder resultMessage = new StringBuilder();


        // Process results for Player 1 if they haven't folded
        if (!isPlayer1Folded) {
            int player1Result = ThreeCardLogic.compareHands(dealer.dealersHand, playerOne.getHand());
            int player1PairPlusBet = Integer.parseInt(PairPlus.getText());
            int player1AnteBet = Integer.parseInt(Ante.getText());

            // Update winnings or losses based on result
            updatePlayerWinnings(playerOne, player1Result, player1AnteBet, player1PairPlusBet);

        }
        else{
            playerOne.setTotalWinnings(playerOne.getTotalWinnings() - (playerOne.getAnteBet() + playerOne.getPairPlusBet()));
        }

        // Process results for Player 2 if they haven't folded
        if (!isPlayer2Folded) {
            int player2Result = ThreeCardLogic.compareHands(dealer.dealersHand, playerTwo.getHand());
            int player2PairPlusBet = Integer.parseInt(PairPlus2.getText());
            int player2AnteBet = Integer.parseInt(Ante2.getText());

            // Update winnings or losses based on result
            updatePlayerWinnings(playerTwo, player2Result, player2AnteBet, player2PairPlusBet);

        }
        else{
            playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() - (playerTwo.getAnteBet() + playerTwo.getPairPlusBet()));
        }


    }

    private void updatePlayerWinnings(Player player, int result, int anteBet, int pairPlusBet) {
        int pairPlusWinnings = ThreeCardLogic.evalPPWinnings(player.getHand(), pairPlusBet);

        if (result == 1) { // Dealer wins
            player.setTotalWinnings(player.getTotalWinnings() - (anteBet * 2) - pairPlusBet);
        }
        else if (result == 2 || !isPlayer1Folded || !isPlayer2Folded) { // Player wins
            player.updateWinnings(anteBet * 2); // Payout for ante
        }
        else if(result == -1){ // Dealer does not qualify
            player.setTotalWinnings(player.getTotalWinnings() + anteBet + pairPlusWinnings);
        }

        player.updateWinnings(pairPlusWinnings);

        if (player == playerOne) {
            Cash.setText(String.valueOf(player.getTotalWinnings()));
        }
        if (player == playerTwo) {
            Cash2.setText(String.valueOf(player.getTotalWinnings()));
        }
    }



}