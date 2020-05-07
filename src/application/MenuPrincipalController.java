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

public class MenuPrincipalController {
    
    @FXML 
    private Stage primaryStage;

    @FXML
    private Button choixNiveau, regles, quitter, touches;

    private FenetrePrincipale fen;

    public MenuPrincipalController(Stage primaryStage, FenetrePrincipale f) {
        this.primaryStage = primaryStage;
        this.fen = f;
     }

     public void mouseMoved(MouseEvent event) throws IOException {
        switch (((Button) event.getSource()).getId()) {
            case "choixNiveau":
                choixNiveau.setTextFill(Color.DARKMAGENTA);
                break;
            case "regles":
                regles.setTextFill(Color.DARKRED);
                break;
            case "quitter":
                quitter.setTextFill(Color.BLUE);
                break;
            case "touches":
                touches.setTextFill(Color.DARKORANGE);
                break;
        }
        
    }

    
    public void mouseExited(MouseEvent event) throws IOException {
        switch (((Button) event.getSource()).getId()) {
            case "choixNiveau":
                choixNiveau.setTextFill(Color.WHITE);
                break;
            case "regles":
                regles.setTextFill(Color.WHITE);
                break;
            case "quitter":
                quitter.setTextFill(Color.WHITE);
                break;
            case "touches":
                touches.setTextFill(Color.WHITE);
                break;
        }
        
    }

    public void choiceLevel(ActionEvent event) throws IOException {

        FXMLLoader l = new FXMLLoader(getClass().getResource("Niveaux.fxml"));
        NiveauxController ac = new NiveauxController(primaryStage, fen);
        l.setController(ac);		

        Pane root;
        root = l.load();
        Scene scene = new Scene(root,1920,1000);			
    
        scene.getStylesheets().add(getClass().getResource("Bouton.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public void law(ActionEvent event) throws IOException {
        
        FXMLLoader l = new FXMLLoader(getClass().getResource("Regles.fxml"));
        ReglesController ac = new ReglesController(primaryStage, fen);
        l.setController(ac);		

        Pane root;
        root = l.load();
        Scene scene = new Scene(root,1920,1000);			
    
        scene.getStylesheets().add(getClass().getResource("Bouton.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void keys(ActionEvent event) throws IOException {
        
        FXMLLoader l = new FXMLLoader(getClass().getResource("Touches.fxml"));
        TouchesController ac = new TouchesController(primaryStage, fen);
        l.setController(ac);		

        Pane root;
        root = l.load();
        Scene scene = new Scene(root,1920,1000);			
    
        scene.getStylesheets().add(getClass().getResource("Bouton.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void quit(ActionEvent event) throws IOException {
        System.exit(0);
    } 
    
}