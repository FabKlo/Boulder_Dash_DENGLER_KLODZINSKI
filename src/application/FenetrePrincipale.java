package application;

import java.util.HashMap;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
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
	private static final int NB_LIG = 10;
	private static final int NB_COL = 10;
	private HashMap<Integer, Image> tabImage;
	private int[][] grille = new int[NB_LIG][NB_COL];

	private int xRockford;
	private int yRockford;

	private static final int VIDE = 0;
	private static final int ROCKFORD = 1;
	// --->

	public Canvas getGrillePane() {
		return grillePane;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Boulder Dash");

			root = new BorderPane(grillePane);

			initFooter();

			scene = new Scene(root);

			scene.setOnKeyPressed(new HandlerClavier());

			initImages();
			initGrille();
			dessinerGrille();
			
			
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
		image = new Image(getClass().getResourceAsStream("/Rockford.png"));
		tabImage.put(1, image);
		image = new Image(getClass().getResourceAsStream("/Vide.png"));
		tabImage.put(0, image);
	}

	private void dessinerGrille() {
		for (int l = 0; l < NB_LIG; l++) {
			for (int c = 0; c < NB_COL; c++) {
				int objet = grille[l][c];
// YL : On pourrait faire comme ceci si la grille ne contenait pas des entiers
// mais directement la classe de l'objet qui s'y trouve. C'est une solution
// beaucoup plus extensible qu'un entier
//				Image image = tabImage.get(objet.getClass());

// YL : Mais pour l'instant, on travaille avec l'entier qui se trouve dans la case
				Image image = tabImage.get(objet);

				getGrillePane().getGraphicsContext2D().drawImage(image, c * 64, l * 64);
			}
		}
	}

// YL : à remanier complètrement pour tenir compte de vos classes
	private void initGrille() {
		int lGrille = 64 * NB_LIG;
		int hGrille = 64 * NB_COL;
		grillePane = new Canvas(lGrille, hGrille);
		((BorderPane) root).setCenter(grillePane);

		grillePane.getGraphicsContext2D();

		// YL : Initialisation en dur. Faudra faire mieux !
		// notamment lire un fichier décrivant le contenu d'un plateau
		for (int l = 0; l < NB_LIG; l++) {
			for (int c = 0; c < NB_COL; c++) {
				grille[l][c] = VIDE;
			}
		}
		xRockford = 5;
		yRockford = 5;
		grille[yRockford][xRockford] = ROCKFORD;
	}

	public static void main(String[] args) {
		launch(args);
	}

	private final class HandlerClavier implements EventHandler<KeyEvent> {
		public void handle(KeyEvent ke) {

			// YL : il faudra naturellement remanier cette fonction pour qu'elle
			// utilise vos classes...
			grille[yRockford][xRockford] = VIDE;
			switch (ke.getCode()) {
			case UP: {
				if (yRockford == 0)
					return;
				yRockford--;
				break;
			}
			case DOWN: {
				if (yRockford == NB_LIG - 1)
					return;
				yRockford++;
				break;
			}
			case RIGHT: {
				if (xRockford == NB_COL - 1)
					return;
				xRockford++;
				break;
			}
			case LEFT: {
				if (xRockford == 0)
					return;
				xRockford--;
				break;
			}
			default:
				return;

			}

			grille[yRockford][xRockford] = ROCKFORD;

			dessinerGrille();
		}
	}

	private void initFooter() {
		panneauFooter = new PanneauFooter();
		((BorderPane) root).setBottom(panneauFooter);
	}
}
