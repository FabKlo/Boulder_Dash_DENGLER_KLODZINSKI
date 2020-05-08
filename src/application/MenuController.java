package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import toutlessongs.Musique;

public class MenuController {
    
    @FXML 
    private Stage primaryStage;

    private FenetrePrincipale fen;

    @FXML
    private Button reprendre, recommencer, quitter, menu;


    public MenuController(Stage primaryStage, FenetrePrincipale f) {
        Musique.musiqueDeFond.pause();
        Musique.initBruitage(Musique.START);
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
            case "menu":
                menu.setTextFill(Color.DARKRED);
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
            case "menu":
                menu.setTextFill(Color.WHITE);
                break;
        }
        
    }

    public void continu(ActionEvent event) throws IOException {
        Musique.musiqueDeFond.play();
        fen.continu(primaryStage);
    }

    public void restart(ActionEvent event) throws IOException {
        fen.initStart(primaryStage);
    }

    public void quit(ActionEvent event) throws IOException {
        System.exit(0);
    }

    public void goMenu(ActionEvent event) throws IOException {

        Musique.initMusiqueDeFond(Musique.TITRE);

        FXMLLoader l = new FXMLLoader(getClass().getResource("MenuPrincipal.fxml"));
        MenuPrincipalController ac = new MenuPrincipalController(primaryStage, fen);
        l.setController(ac);		

        Pane root;
        root = l.load();
        Scene scene = new Scene(root,1920,1000);			
    
        scene.getStylesheets().add(getClass().getResource("Bouton.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}