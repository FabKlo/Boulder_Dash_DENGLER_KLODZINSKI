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

public class NiveauxController {

    @FXML
    private Button menu, niveau1, niveau2, niveau3;

    @FXML 
    private Stage primaryStage;

    private FenetrePrincipale fen;

    public NiveauxController(Stage primaryStage, FenetrePrincipale f) {
        Musique.initMusiqueDeFond(Musique.NIVEAU);
        this.primaryStage = primaryStage;
        this.fen = f;
    }

    public void levelOne(ActionEvent event) throws IOException {

        fen.getGrille().setNiveau(1);
        fen.initStart(primaryStage);
        
    }

    public void levelTwo(ActionEvent event) throws IOException {

        fen.getGrille().setNiveau(2);
        fen.initStart(primaryStage);
        
    }

    public void levelThree(ActionEvent event) throws IOException {

        fen.getGrille().setNiveau(3);
        fen.initStart(primaryStage);
        
    }
    

    public void mouseMoved(MouseEvent event) throws IOException {
        menu.setTextFill(Color.DARKRED);
    }

    
    public void mouseExited(MouseEvent event) throws IOException {
        menu.setTextFill(Color.WHITE);
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