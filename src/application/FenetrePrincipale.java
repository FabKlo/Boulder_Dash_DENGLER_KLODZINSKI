package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import entitesvivantes.Personnage;
import entitesvivantes.Rockford;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import lagrille.Grille;
import lescases.Case;
import lescases.Diamant;
import lescases.Rocher;
import modele.exceptions.BoulderMortException;
import ui.PanneauFooter;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class FenetrePrincipale extends Application {
	private Canvas grillePane;
	private BorderPane root;
	private Scene scene;
	private PanneauFooter panneauFooter;

	private Timeline timeline;

	private int tailleImageX = 64;
	private int tailleImageY = 64;


	// YL : Les déclarations ci-dessous devront être remplacées par des classes et
	// des
	// objets que vous devez développer
	// <----

	private HashMap<Integer, Image> tabImage;
	public Grille grille = new Grille();
	private ArrayList<Case> allRocherEtDiamant;
	private ArrayList<Personnage> allPers;

	private int xRockford;
	private int yRockford;
	private boolean rockfordPeutSeDepl = true;

	// --->

	public Canvas getGrillePane() {
		return grillePane;
	}

	@Override
	public void start(Stage primaryStage) {
		try {

			primaryStage.setTitle("Boulder Dash");

			root = new BorderPane(grillePane);

			scene = new Scene(root);

			scene.setOnKeyPressed(new HandlerClavier());

			initImages();
			initGrille();
			dessinerGrille();

			initFooter();
			chuteItem(primaryStage);

			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			initGagner(primaryStage);
		}
	}

	/**
	 * fenêtre qui s'ouvre en cas de victoire sur tout les niveaux
	 * @param primaryStage la scène
	 */
	private void initGagner(Stage primaryStage) {

		Alert dialog = new Alert(AlertType.CONFIRMATION);
		
		dialog.setTitle("Et c'est gagné !");
		dialog.setHeaderText("Vous avez fini le jeu, félicitation !");

		ButtonType quitter = new ButtonType("Quitter", ButtonData.CANCEL_CLOSE);
		dialog.getButtonTypes().setAll(quitter);
		
		dialog.show();
		
		final Button btnQuit = (Button) dialog.getDialogPane().lookupButton(quitter);
		
		btnQuit.setOnAction( event -> {
			System.exit(0);
		});

	}

	/**
	 * fenêtre qui s'ouvre quand un niveau est fini, et propose de lancer le suivant ou de quitter le jeu
	 * @param primaryStage la scène
	 * @throws IOException
	 */
	private void initSuivant(Stage primaryStage) throws IOException {

		Alert dialog = new Alert(AlertType.CONFIRMATION);
		
		dialog.setTitle("Bien joué !");
		dialog.setHeaderText("Vous avez gagné, vous pouvez continuer !");

		ButtonType suivant = new ButtonType("continuer");
		ButtonType quitter = new ButtonType("Quitter", ButtonData.CANCEL_CLOSE);
		dialog.getButtonTypes().setAll(suivant,quitter);
		
		dialog.show();
		
		final Button bntSuivant = (Button) dialog.getDialogPane().lookupButton(suivant);
		final Button bntQuit = (Button) dialog.getDialogPane().lookupButton(quitter);
		
		bntSuivant.setOnAction( event -> {
			dialog.close();
			
			grille.setNiveau(grille.getNiveau()+1);
			try {
				start(primaryStage);;

			} catch (Exception e) {
				initGagner(primaryStage);
			}

		});
		
		bntQuit.setOnAction( event -> {
			System.exit(0);
		});
	}

	/**
	 * fenêtre qui s'ouvre quand l'on échoue dans le niveau, et propose de recommencer ou de quitter
	 * @param primaryStage  la scène
	 */
	private void initPerdu(Stage primaryStage) {

		Alert dialog = new Alert(AlertType.CONFIRMATION);
		
		dialog.setTitle("Défaite");
		dialog.setHeaderText("Vous avez perdu, voulez-vous rejouer ?");

		ButtonType oui = new ButtonType("Oui");
		ButtonType non = new ButtonType("Non", ButtonData.CANCEL_CLOSE);
		dialog.getButtonTypes().setAll(oui,non);
		
		dialog.show();
		
		final Button btnOui = (Button) dialog.getDialogPane().lookupButton(oui);
		final Button btnNon = (Button) dialog.getDialogPane().lookupButton(non);
		
		btnOui.setOnAction( event -> {
			dialog.close();
			try {
				start(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}

		});
		
		btnNon.setOnAction( event -> {
			System.exit(0);
		});

	}

	// YL : à compléter pour utiliser toutes les images nécessaires
	private void initImages() {
		tabImage = new HashMap<Integer, Image>();
		Image image;
		image = new Image(getClass().getResourceAsStream("/Terre.png"),tailleImageX,tailleImageY,false,false);
		tabImage.put(6, image);
		image = new Image(getClass().getResourceAsStream("/Rocher.png"),tailleImageX,tailleImageY,false,false);
		tabImage.put(5, image);
		image = new Image(getClass().getResourceAsStream("/Monstre.png"),tailleImageX,tailleImageY,false,false);
		tabImage.put(4, image);
		image = new Image(getClass().getResourceAsStream("/Diamant.png"),tailleImageX,tailleImageY,false,false);
		tabImage.put(3, image);
		image = new Image(getClass().getResourceAsStream("/Acier.png"),tailleImageX,tailleImageY,false,false);
		tabImage.put(2, image);
		image = new Image(getClass().getResourceAsStream("/Rockford.png"),tailleImageX,tailleImageY,false,false);
		tabImage.put(1, image);
		image = new Image(getClass().getResourceAsStream("/Vide.png"),tailleImageX,tailleImageY,false,false);
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

				getGrillePane().getGraphicsContext2D().drawImage(image, i * tailleImageX, j * tailleImageY);
			}
		}
	}

	// YL : à remanier complètrement pour tenir compte de vos classes
	private void initGrille() throws IOException {

		grille.creerGrille();
		
		allPers = grille.searchAllPers();
		for (Personnage p : allPers) {
			if(p instanceof Rockford) {
				yRockford = p.getPositionY();
				xRockford = p.getPositionX();
			}
		}
		allRocherEtDiamant = grille.searchAllRocherEtDiamant();

		int lGrille = tailleImageX * grille.getXMAX();
		int hGrille = tailleImageY * grille.getYMAX();
		grillePane = new Canvas(lGrille, hGrille);
		((BorderPane) root).setCenter(grillePane);

		grillePane.getGraphicsContext2D();
	}


	/**
	 * Selon le bouton appuyé, rockford se déplace vers une direction 
	 */
	private final class HandlerClavier implements EventHandler<KeyEvent> {
		public void handle(KeyEvent ke) {

			// YL : il faudra naturellement remanier cette fonction pour qu'elle
			// utilise vos classes...

			if(rockfordPeutSeDepl) {
				switch (ke.getCode()) {
					case Z: {
						try {
							if(yRockford - 1 >= 0) {
								if(grille.déplacerPerso(xRockford, yRockford, xRockford, yRockford - 1))
									yRockford -= 1;
							}
							
						} catch (BoulderMortException e) {
							e.printStackTrace();
						}
						break;
					}
					case S: {
						try {
							if(yRockford + 1 < grille.getYMAX()) {
								if(grille.déplacerPerso(xRockford, yRockford, xRockford, yRockford + 1))
									yRockford += 1;
							}
							
						} catch (BoulderMortException e) {
							e.printStackTrace();
						}
						break;
					}
					case D: {
						try {
							if(xRockford + 1 < grille.getXMAX()) {
								if(grille.déplacerPerso(xRockford, yRockford, xRockford + 1, yRockford))
									xRockford += 1;
							}
							
						} catch (BoulderMortException e) {
							e.printStackTrace();
						}
						break;
					}
					case Q: {
						try {
							if(xRockford - 1 >= 0) {
								if(grille.déplacerPerso(xRockford, yRockford, xRockford - 1, yRockford))
									xRockford -= 1;
							}
							
						} catch (BoulderMortException e) {
							e.printStackTrace();
						}
						break;
					}
					default:
						return;

				}

				rockfordPeutSeDepl = false;
			}
			
			dessinerGrille();

		}
	}

	private void initFooter() {
		panneauFooter = new PanneauFooter(grille, ((Rockford)(grille.getCaseDuTab(xRockford, yRockford).getEstIci())));
		((BorderPane) root).setBottom(panneauFooter);
	}

	/**
	 * fait chuter tout les rochers et diamants grâce à une keyframe qui boucle à l'infini
	 */
	private void chuteItem(Stage primaryStage) {
		KeyFrame chuteItem = new KeyFrame(Duration.seconds(0.3), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				for (Case c : allRocherEtDiamant) {
					
					if(c.getPositionY() < grille.getYMAX()) {
						if(c instanceof Rocher) {
						try {
							grille.déplacerRocher(c.getPositionX(), c.getPositionY());
							allRocherEtDiamant = grille.searchAllRocherEtDiamant(); //si ça écrase un monstre, ajoute son diamant créé dans l'arraylist
						} catch (BoulderMortException e) {							
							e.printStackTrace();
						}
						}
						else if(c instanceof Diamant) {
							try {
								grille.déplacerDiamant(c.getPositionX(), c.getPositionY());
							} catch (BoulderMortException e) {
								e.printStackTrace();
							}
						}
					}

					if(grille.verifObjectif()) {
						try {
							initSuivant(primaryStage);
						} catch (Exception e) {
							e.printStackTrace();
						}
						timeline.stop();
						break;
					}

					if(grille.getCaseDuTab(xRockford, yRockford).getEstIci().getVie() <= 0) {
						initPerdu(primaryStage);
						timeline.stop();
						break;
					}

					dessinerGrille();
				}	

				
				rockfordPeutSeDepl = true;
	
			}
	
		});
	
		timeline = new Timeline(chuteItem);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public int getxRockford() {
		return xRockford;
	}

	public void setxRockford(int xRockford) {
		this.xRockford = xRockford;
	}

	public int getyRockford() {
		return yRockford;
	}

	public void setyRockford(int yRockford) {
		this.yRockford = yRockford;
	}

	
}
