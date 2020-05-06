package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuController {
    
    @FXML 
    private Stage primaryStage;

    private FenetrePrincipale fen;

    @FXML
    private Button reprendre, recommencer, quitter;


    public MenuController(Stage primaryStage, FenetrePrincipale f) {
       this.primaryStage = primaryStage;
       this.fen = f;
    }

    public void mouseMoved(MouseEvent event) throws IOException {
        switch (((Button) event.getSource()).getId()) {
            case "reprendre":
                reprendre.setTextFill(Color.DARKCYAN);
                break;
            case "recommencer":
                recommencer.setTextFill(Color.DARKVIOLET);
                break;
            case "quitter":
                quitter.setTextFill(Color.BLUE);
                break;
        }
        
    }

    
    public void mouseExited(MouseEvent event) throws IOException {
        switch (((Button) event.getSource()).getId()) {
            case "reprendre":
                reprendre.setTextFill(Color.WHITE);
                break;
            case "recommencer":
                recommencer.setTextFill(Color.WHITE);
                break;
            case "quitter":
                quitter.setTextFill(Color.WHITE);
                break;
        }
        
    }

    public void continu(ActionEvent event) throws IOException {
        fen.continu(primaryStage);
    }

    public void restart(ActionEvent event) throws IOException {
        fen.initStart(primaryStage);
    }

    public void quit(ActionEvent event) throws IOException {
        System.exit(0);
    }
}