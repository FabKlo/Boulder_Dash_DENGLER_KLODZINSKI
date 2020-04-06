package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import entitesvivantes.Personnage;
import entitesvivantes.Rockford;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import lagrille.Grille;
import lescases.Case;
import modele.exceptions.BoulderMortException;
import ui.PanneauFooter;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class FenetrePrincipale extends Application {
	private Canvas grillePane;
	private BorderPane root;
	private Scene scene;
	private PanneauFooter panneauFooter;

	// YL : Les déclarations ci-dessous devront être remplacées par des classes et
	// des
	// objets que vous devez développer
	// <----

	private HashMap<Integer, Image> tabImage;
	public Grille grille = new Grille();
	private ArrayList<Case> allRocherEtDiamant;
	private ArrayList<Personnage> allPerso;


	private int xRockford;
	private int yRockford;

	// --->

	public Canvas getGrillePane() {
		return grillePane;
	}

	@Override
	public void start(Stage primaryStage) {
		try {

			primaryStage.setTitle("Boulder Dash");

			root = new BorderPane(grillePane);

			//initFooter();

			scene = new Scene(root);

			scene.setOnKeyPressed(new HandlerClavier());

			initImages();
			initGrille();
			dessinerGrille();

			initFooter();

			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// YL : à compléter pour utiliser toutes les images nécessaires
	private void initImages() {
		tabImage = new HashMap<Integer, Image>();
		Image image;
		image = new Image(getClass().getResourceAsStream("/Terre.png"));
		tabImage.put(6, image);
		image = new Image(getClass().getResourceAsStream("/Rocher.png"));
		tabImage.put(5, image);
		image = new Image(getClass().getResourceAsStream("/Monstre.png"));
		tabImage.put(4, image);
		image = new Image(getClass().getResourceAsStream("/Diamant.png"));
		tabImage.put(3, image);
		image = new Image(getClass().getResourceAsStream("/Acier.png"));
		tabImage.put(2, image);
		image = new Image(getClass().getResourceAsStream("/Rockford.png"));
		tabImage.put(1, image);
		image = new Image(getClass().getResourceAsStream("/Vide.png"));
		tabImage.put(0, image);
	}

	private void dessinerGrille() {
		for (int i = 0; i < grille.getXMAX(); i++) {
			for (int j = 0; j < grille.getYMAX(); j++) {
				Case c = grille.getCaseDuTab(i,j);
				
				// YL : On pourrait faire comme ceci si la grille ne contenait pas des entiers
				// mais directement la classe de l'objet qui s'y trouve. C'est une solution
				// beaucoup plus extensible qu'un entier
				// Image image = tabImage.get(objet.getClass());

				// YL : Mais pour l'instant, on travaille avec l'entier qui se trouve dans la
				// case

				Image image = tabImage.get(c.caseEnInt());

				getGrillePane().getGraphicsContext2D().drawImage(image, i * 64, j * 64);
			}
		}
	}

	// YL : à remanier complètrement pour tenir compte de vos classes
	private void initGrille() throws IOException {

		grille.creerGrille();
		
		allPerso = grille.searchAllPers();
		for (Personnage p : allPerso) {
			if(p instanceof Rockford) {
				yRockford = p.getPositionY();
				xRockford = p.getPositionX();
			}
		}
		allRocherEtDiamant = grille.searchAllRocherEtDiamant();

		int lGrille = 64 * grille.getXMAX();
		int hGrille = 64 * grille.getYMAX();
		grillePane = new Canvas(lGrille, hGrille);
		((BorderPane) root).setCenter(grillePane);

		grillePane.getGraphicsContext2D();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private final class HandlerClavier implements EventHandler<KeyEvent> {
		public void handle(KeyEvent ke) {

			// YL : il faudra naturellement remanier cette fonction pour qu'elle
			// utilise vos classes...
			switch (ke.getCode()) {
			case Z: {
				try {
					grille.déplacerPerso(xRockford, yRockford, xRockford, yRockford - 1);
					yRockford -= 1;
				} catch (BoulderMortException e) {
					e.printStackTrace();
				}
				break;
			}
			case S: {
				try {
					grille.déplacerPerso(xRockford, yRockford, xRockford, yRockford + 1);
					yRockford += 1;
				} catch (BoulderMortException e) {
					e.printStackTrace();
				}
				break;
			}
			case D: {
				try {
					grille.déplacerPerso(xRockford, yRockford, xRockford + 1, yRockford);
					xRockford += 1;
				} catch (BoulderMortException e) {
					e.printStackTrace();
				}
				break;
			}
			case Q: {
				try {
					grille.déplacerPerso(xRockford, yRockford, xRockford - 1, yRockford);
					xRockford -= 1;
				} catch (BoulderMortException e) {
					e.printStackTrace();
				}
				break;
			}
			default:
				return;

			}

			dessinerGrille();
		}
	}

	private void initFooter() {
		panneauFooter = new PanneauFooter(grille, allRocherEtDiamant);
		((BorderPane) root).setBottom(panneauFooter);
	}
}
