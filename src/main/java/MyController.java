

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MyController implements Initializable {

    public Button exitGame;

    public Button playGame;

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
    private HBox player1Hand1;
    @FXML
    private HBox player2Hand2;
    @FXML
    private HBox dealerHand1;


    @FXML
    private VBox root;

    @FXML
    private Pane root2;

    @FXML
    private Menu settingsMenu;

    @FXML
    private TextField textField;



    @FXML
    private TextField putText;

    //static so each instance of controller can access to update 
    private static String textEntered = "";



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    @FXML
    public void handleExit(ActionEvent e){
        System.exit(0);
    }

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
    private void loadGamePage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GamePage.fxml"));
            Parent root2 = loader.load();
            root2.getStylesheets().add("/styles/style2.css");//set style
            root.getScene().setRoot(root2);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void dealCardsFunc() {
        // Clear existing hands
        playerOne.setHand(dealer.dealHand());
        playerTwo.setHand(dealer.dealHand());
        dealer.dealersHand = dealer.dealHand();

        System.out.println("Player 1 Hand: " + playerOne.getHand());
        System.out.println("Player 2 Hand: " + playerTwo.getHand());
        System.out.println("Dealer Hand: " + dealer.dealersHand);
        System.out.println("Cards left: " + dealer.theDeck.remainingCards());

        foldPlayer1Button.setDisable(false);
        foldPlayer2Button.setDisable(false);
        Ante.setDisable(true);
        Ante2.setDisable(true);
        PairPlus.setDisable(true);
        PairPlus2.setDisable(true);

        displayHand(playerOne.getHand(), player1Hand1, false);
        displayHand(playerTwo.getHand(), player2Hand2, false);
        displayHand(dealer.dealersHand, dealerHand1, true);


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
            showFoldMessage("Player 1 has folded. Dealer wins this round!");
        } else if (playerNumber == 2) {
            isPlayer2Folded = true;
            displayHand(playerTwo.getHand(), player2Hand2, true);

            showFoldMessage("Player 2 has folded. Dealer wins this round!");
        }
    }

    @FXML
    private void playCards() {
        // Check if both players have folded
        if (isPlayer1Folded && isPlayer2Folded) {
            resetGameForNextRound();
            return;
        }

        foldPlayer1Button.setDisable(true);
        foldPlayer2Button.setDisable(true);
        Ante.setDisable(false);
        Ante2.setDisable(false);
        PairPlus.setDisable(false);
        PairPlus2.setDisable(false);


        displayHand(dealer.dealersHand, dealerHand1, false);

        if (!isPlayer1Folded) {
            int player1Result = ThreeCardLogic.compareHands(dealer.dealersHand, playerOne.getHand());
//            updatePlayerWinnings(player1, player1Result);
        }

        if (!isPlayer2Folded) {
            int player2Result = ThreeCardLogic.compareHands(dealer.dealersHand, playerTwo.getHand());
//            updatePlayerWinnings(player2, player2Result);
        }

    }

    private void updatePlayerWinnings(Player player, int compareResult) {
        int anteBet = player.getAnteBet();
        int playBet = player.getPlayBet();
        int pairPlusBet = player.getPairPlusBet();

        if (compareResult == 1) {
            // Dealer wins
            player.setTotalWinnings(player.getTotalWinnings() - anteBet - playBet - pairPlusBet);
        }
        else if (compareResult == 2) {
            // Player wins
            player.updateWinnings(anteBet + playBet);
        }
        else {
            // Tie
            player.setTotalWinnings(player.getTotalWinnings() - pairPlusBet);
        }
        }


}