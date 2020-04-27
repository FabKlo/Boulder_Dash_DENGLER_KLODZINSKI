package application;

import java.io.IOException;
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

	private Timeline timelineChute;
	private Timeline timelineMonstre;

	private int tailleImageX = 64;
	private int tailleImageY = 64;


	// YL : Les declarations ci-dessous devront etre remplacees par des classes et
	// des
	// objets que vous devez developper
	// <----

	private HashMap<Integer, Image> tabImage;
	public Grille grille = new Grille();

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
			deplacementMonstre(primaryStage);

			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			initGagner(primaryStage);
		}
	}

	/**
	 * fenetre qui s'ouvre en cas de victoire sur tout les niveaux
	 * @param primaryStage la scene
	 */
	private void initGagner(Stage primaryStage) {

		Alert dialog = new Alert(AlertType.CONFIRMATION);
		
		dialog.setTitle("Et c'est gagne !");
		dialog.setHeaderText("Vous avez fini le jeu, felicitation !");

		ButtonType quitter = new ButtonType("Quitter", ButtonData.CANCEL_CLOSE);
		dialog.getButtonTypes().setAll(quitter);
		
		dialog.show();
		
		final Button btnQuit = (Button) dialog.getDialogPane().lookupButton(quitter);
		
		btnQuit.setOnAction( event -> {
			System.exit(0);
		});

	}

	/**
	 * fenetre qui s'ouvre quand un niveau est fini, et propose de lancer le suivant ou de quitter le jeu
	 * @param primaryStage la scene
	 * @throws IOException
	 */
	private void initSuivant(Stage primaryStage) throws IOException {

		Alert dialog = new Alert(AlertType.CONFIRMATION);
		
		dialog.setTitle("Bien joue !");
		dialog.setHeaderText("Vous avez gagne, vous pouvez continuer !");

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
				start(primaryStage);

			} catch (Exception e) {
				initGagner(primaryStage);
			}

		});
		
		bntQuit.setOnAction( event -> {
			System.exit(0);
		});
	}

	/**
	 * fenetre qui s'ouvre quand l'on echoue dans le niveau, et propose de recommencer ou de quitter
	 * @param primaryStage  la scene
	 */
	private void initPerdu(Stage primaryStage) {

		Alert dialog = new Alert(AlertType.CONFIRMATION);
		
		dialog.setTitle("Defaite");
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

	/**
	 * initialise la Hashmap avec toutes les images necessaires
	 */
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

	/**
	 * dessine la grille, tout simplement
	 */
	private void dessinerGrille() {
		for (int i = 0; i < grille.getXMAX(); i++) {
			for (int j = 0; j < grille.getYMAX(); j++) {
				Case c = grille.getCaseDuTab(i,j);
			
				Image image = tabImage.get(c.caseEnInt());

				getGrillePane().getGraphicsContext2D().drawImage(image, i * tailleImageX, j * tailleImageY);
			}
		}
	}

	/**
	 * initialise la grille grace a la fonction dans Grille.java
	 * Initialise aussi la taille de la fenetre, et les coordonnees de Rockford
	 * @throws IOException
	 */
	private void initGrille() throws IOException {

		grille.creerGrille();

		for (Personnage p : Grille.allPers) {
			if(p instanceof Rockford) {
				yRockford = p.getPositionY();
				xRockford = p.getPositionX();
			}
		}
		

		int lGrille = tailleImageX * grille.getXMAX();
		int hGrille = tailleImageY * grille.getYMAX();
		grillePane = new Canvas(lGrille, hGrille);
		((BorderPane) root).setCenter(grillePane);

		grillePane.getGraphicsContext2D();
	}


	/**
	 * Selon le bouton appuye, rockford se deplace vers une direction 
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
								if(grille.deplacerPerso(xRockford, yRockford, xRockford, yRockford - 1))
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
								if(grille.deplacerPerso(xRockford, yRockford, xRockford, yRockford + 1))
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
								if(grille.deplacerPerso(xRockford, yRockford, xRockford + 1, yRockford))
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
								if(grille.deplacerPerso(xRockford, yRockford, xRockford - 1, yRockford))
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
	 * fait chuter tout les rochers et diamants grace a une keyframe qui boucle a l'infini
	 * et, etant une keyframe qui boucle tres rapidement, je verifie ici si on a gagne ou perdu
	 */
	private void chuteItem(Stage primaryStage) {
		KeyFrame chuteItem = new KeyFrame(Duration.seconds(0.18), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				grille.chuteItem();

				changementFenetre(primaryStage);

				dessinerGrille();
	
				rockfordPeutSeDepl = true;
	
			}
	
		});
	
		timelineChute = new Timeline(chuteItem);
		timelineChute.setCycleCount(Animation.INDEFINITE);
		timelineChute.play();

	}

	private void deplacementMonstre(Stage primaryStage) {
		KeyFrame deplMonstre = new KeyFrame(Duration.seconds(0.18), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				grille.mouvementMonstres();

				changementFenetre(primaryStage);

				dessinerGrille();
			}	

		});

		timelineMonstre = new Timeline(deplMonstre);
		timelineMonstre.setCycleCount(Animation.INDEFINITE);
		timelineMonstre.play();

	}

	private void changementFenetre(Stage primaryStage) {
		if(grille.mortRockford(xRockford, yRockford)) {
			timelineChute.stop();
			timelineMonstre.stop();
			initPerdu(primaryStage);
		}
			
		else if (grille.verifObjectif()) {
			try {
				timelineChute.stop();
				timelineMonstre.stop();
				initSuivant(primaryStage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
