package org.example.tictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import static javafx.scene.layout.CornerRadii.EMPTY;

public class HelloApplication extends Application {

    private static Scene scene;
    private final GridPane gridPane = new GridPane();
    private final BorderPane borderPane = new BorderPane();
  // Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/example/tictactoe/unnamed.png")));
   //ImageView iView = new ImageView(img);
    private final Label title = new Label("Tic-Tac-Toe");
    Image img2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/example/tictactoe/unnamed.png")));
    ImageView iView2 = new ImageView(img2);
    private final Button restartButton = new Button("Restart", iView2);

    Font font = Font.font("Calibre", FontWeight.BOLD, FontPosture.ITALIC, 30);

    private Button[] btns = new Button[9];

    private boolean gameOver = false;
    private int activePlayer = 0;
    int[] gameState = {3,3,3,3,3,3,3,3,3};
    int[][] winingPosition = {{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};


    @Override
    public void start(Stage stage) throws IOException {

        this.createGUI();
        this.handleEvent();
        Scene scene = new Scene(borderPane, 550, 650, Color.BLUEVIOLET);
        scene.setCursor(Cursor.HAND);
        stage.setTitle("Tic-Tac-Toe");

      // Image image = new Image(getClass().getResourceAsStream("/org.example.tictactoe/tictactoe.png"));

        Image icon = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/org/example/tictactoe/unnamed.png"))
        );
        stage.getIcons().add(icon);
       /* ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/example/tictactoe/tictactoe.png"))));
        imageView.setFitWidth(100); // Adjust the size
        imageView.setFitHeight(100);
        borderPane.setTop(imageView); // Add image to the layout
        BorderPane.setAlignment(imageView, Pos.CENTER);

        */

        stage.setScene(scene);
        stage.show();
    }
    //This funcn is for creating GUI

    private void createGUI() {
        //creating title
        title.setFont(font);
        title.setTextFill(Color.BLUE);
        title.setUnderline(true);
       // title.setEffect(new Reflection());
        title.setEffect(new DropShadow());
       // title.setEffect(new BoxBlur());

        restartButton.setFont(font);
        restartButton.setTextFill(Color.BLUE);
        restartButton.setDisable(true);

        //setting title and restart button to borderpane
        borderPane.setTop(title);
        //borderPane.setCenter(restartButton);

        //setting borderPane component next to center

        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setAlignment(restartButton, Pos.CENTER);
       BorderPane.setMargin(title, new Insets(10, 10, 10, 10));
       borderPane.setBottom(restartButton);
       borderPane.setAlignment(restartButton, Pos.CENTER);
       borderPane.setPadding(new Insets(10, 10, 10, 10));

       //configure gridPane
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);


        //9 game buttons (0-8)
           int lebel =0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){


                Button button = new Button();
                button.setId(lebel + "");
                button.setFont(font);
                button.setPrefHeight(150);
                button.setPrefWidth(150);

                gridPane.add(button,j,i);
                gridPane.setAlignment(Pos.CENTER);
                btns[lebel]=button;
                lebel++;


            }
        }
        borderPane.setCenter(gridPane);

    }

    private void handleEvent(){
        //restart button clicked
        restartButton.setOnAction(event -> {
            for(int i=0;i<9;i++){
                gameState[i]=3;
                //btns[i].setText("");
                btns[i].setGraphic(null);
                btns[i].setBackground(null);
                btns[i].setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

                gameOver=false;
                restartButton.setDisable(true);

            }

        });

        for(Button button:btns){
            button.setOnAction(event -> {
                System.out.println("number button");
                Button currentButton = (Button) event.getSource();
                String ids = currentButton.getId();
                int idI = Integer.parseInt(ids);
                System.out.println("Btn clicked of Id " + idI);

                if(gameOver){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Game Over");
                    alert.setHeaderText("Please restart the game");
                    alert.show();
                } else{
                    if(gameState[idI]==3){
                        //proceed
                        if(activePlayer==1){
                            //chance of 1
                          //  currentButton.setText( activePlayer +"");
                            Image x = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/example/tictactoe/x.png")));
                            ImageView img3 = new ImageView(x);
                            currentButton.setGraphic(img3);
                            gameState[idI]= activePlayer;
                            checkForWinner();
                            activePlayer = 0;


                        }else{
                            //chance of 0
                           // currentButton.setText( activePlayer +"");
                            Image o = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/example/tictactoe/o.png")));
                            ImageView img4 = new ImageView(o);
                            currentButton.setGraphic(img4);
                            gameState[idI]= activePlayer;
                            checkForWinner();
                            checkForDraw();
                            activePlayer = 1;

                        }

                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Please choose another brick.");
                        alert.show();
                    }

                }
            });
        }

    }
    //This method checks for winner
    private void checkForWinner(){
        if(!gameOver){
            for(int wp[]:winingPosition){
                if(gameState[wp[0]] == gameState[wp[1]] && gameState[wp[1]] == gameState[wp[2]] && gameState[wp[1]]!= 3)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText((activePlayer==1?"X ":"O ") + "has won the Game");
                    Background blueBackground = new Background(
                            new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)
                    );
                    btns[wp[0]].setBackground(blueBackground);
                    btns[wp[1]].setBackground(blueBackground);
                    btns[wp[2]].setBackground(blueBackground);

                    alert.show();
                    gameOver = true;
                    restartButton.setDisable(false);
                    break;

                }
            }
        }


    }
    private void checkForDraw() {
        if (!gameOver && Arrays.stream(gameState).noneMatch(state -> state == 3)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Draw");
            alert.setHeaderText("The game is a draw!");
            alert.show();
            gameOver = true;
            restartButton.setDisable(false);
        }
    }


    public static void main(String[] args) {
        launch();
    }
}