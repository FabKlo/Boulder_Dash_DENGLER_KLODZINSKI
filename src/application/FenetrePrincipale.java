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
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import lagrille.Grille;
import lescases.Case;
import lescases.Sortie;
import modele.exceptions.BoulderMortException;
import toutlessongs.Musique;
import ui.PanneauVie;
import ui.PanneauTime;
import ui.PanneauDiams;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class FenetrePrincipale extends Application {
	private Canvas grillePane;
	private BorderPane root;
	private Scene scene;
	private PanneauVie panneauVie;
	private PanneauDiams panneauDiams;
	private PanneauTime panneauTime;

	private Timeline timelineChute;
	private Timeline timelineMonstre;
	private Timeline timelineSortie;
	private Timeline timelineDessin;

	private double secondes;

	private int tailleImageX = 40;
	private int tailleImageY = 40;

	private int depRockford = 8; 	//sert pour l'affichage du gif de deplacement de rockford


	// YL : Les declarations ci-dessous devront etre remplacees par des classes et
	// des
	// objets que vous devez developper
	// <----

	private HashMap<Integer, Image> tabImage;

	public Grille grille = new Grille();

	private int xRockford;
	private int yRockford;
	private boolean rockfordPeutSeDepl = true;

	private Sortie laSortie;

	private Stage primaryStage;
	

	// --->

	public Canvas getGrillePane() {
		return grillePane;
	}

	/**
	 * initialise un nouveau niveau et le montre
	 * @param primaryStage
	 */
	public void initStart(Stage primaryStage) {
		try {

			this.primaryStage = primaryStage;

			secondes = 0;

			primaryStage.setTitle("Boulder Dash");

			root = new BorderPane(grillePane);

			root.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.BLACK, null, null)));

			scene = new Scene(root, 1920, 1000);

			scene.setOnKeyPressed(new HandlerClavier());

			initImages();

			initGrille();
			initFooter();
			dessinerGrille();

			dessin(primaryStage);
			sortie();
			chuteItem();
			deplacementMonstre();

			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();

			switch(grille.getNiveau()) {
				case 1:
					Musique.initMusiqueDeFond(Musique.WORLD1);
					break;
				case 2:
					Musique.initMusiqueDeFond(Musique.WORLD2);
					break;
				case 3:
					Musique.initMusiqueDeFond(Musique.WORLD3);
					break;
			}

		} catch (Exception e) {
			initGagner(primaryStage);
		}
	}

	/**
	 * Change le stage pour montrer le menu
	 * @param primaryStage
	 * @throws IOException
	 */
	public void initMenu(Stage primaryStage) {

		try {
			Musique.musiqueDeFond.pause();

			FXMLLoader l = new FXMLLoader(getClass().getResource("Menu.fxml"));
			MenuController ac = new MenuController(primaryStage, this);
			l.setController(ac);		

			Pane root;
			root = l.load();
			Scene scene = new Scene(root,1920,1000);			
		
			scene.getStylesheets().add(getClass().getResource("Bouton.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reprend la partie en cours
	 * @param primaryStage
	 */
	public void continu(Stage primaryStage) {

		try {

			primaryStage.setTitle("Boulder Dash");

			root = new BorderPane(grillePane);

			root.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.BLACK, null, null)));

			scene = new Scene(root, 1920, 1000);

			scene.setOnKeyPressed(new HandlerClavier());

			initFooter();
			dessinerGrille();

			dessin(primaryStage);
			sortie();
			chuteItem();
			deplacementMonstre();

			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();

			

		} catch (Exception e) {
			initGagner(primaryStage);
		}

	}


	@Override
	public void start(Stage primaryStage) {
		try {

			Musique.initAllMusiques();
			
			Musique.initMusiqueDeFond(Musique.TITRE);

			FXMLLoader l = new FXMLLoader(getClass().getResource("MenuPrincipal.fxml"));
			MenuPrincipalController ac = new MenuPrincipalController(primaryStage, this);
			l.setController(ac);		

			Pane root;
			root = l.load();
			Scene scene = new Scene(root,1920,1000);			
		
			scene.getStylesheets().add(getClass().getResource("Bouton.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * fenetre qui s'ouvre en cas de victoire sur tout les niveaux
	 * @param primaryStage la scene
	 */
	private void initGagner(Stage primaryStage) {

		
		try {
			FXMLLoader l = new FXMLLoader(getClass().getResource("EcranFinJeu.fxml"));
			EcranFinJeuController ac = new EcranFinJeuController(primaryStage, this);
			l.setController(ac);		

			Pane root;
			root = l.load();
			Scene scene = new Scene(root,1920,1000);			
	
			scene.getStylesheets().add(getClass().getResource("Bouton.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * fenetre qui s'ouvre quand un niveau est fini, et propose de lancer le niveau suivant ou de quitter le jeu
	 * @param primaryStage la scene
	 */
	private void initSuivant(Stage primaryStage) {

		if(grille.getNiveau() + 1 == 4)
			initGagner(primaryStage);

		else {
			
			try {
				FXMLLoader l = new FXMLLoader(getClass().getResource("EcranVictoire.fxml"));
				EcranVictoireController ac = new EcranVictoireController(primaryStage, this);
				l.setController(ac);		

				Pane root;
				root = l.load();
				Scene scene = new Scene(root,1920,1000);			
		
				scene.getStylesheets().add(getClass().getResource("Bouton.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * fenetre qui s'ouvre quand l'on echoue dans le niveau, et propose de recommencer ou de quitter
	 * @param primaryStage  la scene
	 */
	private void initPerdu(Stage primaryStage) throws IOException {

		FXMLLoader l = new FXMLLoader(getClass().getResource("EcranDefaite.fxml"));
		EcranDefaiteController ac = new EcranDefaiteController(primaryStage, this);
		l.setController(ac);		

		Pane root;
		root = l.load();
		Scene scene = new Scene(root,1920,1000);			
	
		scene.getStylesheets().add(getClass().getResource("Bouton.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	/**
	 * initialise la Hashmap avec toutes les images necessaires
	 */
	private void initImages() {
		tabImage = new HashMap<Integer, Image>();
		Image image;
		image = new Image(getClass().getResourceAsStream("/MouvementRockfordDroite (1).gif"),tailleImageX,tailleImageY,false,false);
		tabImage.put(10, image);
		image = new Image(getClass().getResourceAsStream("/MouvementRockfordGauche (1).gif"),tailleImageX,tailleImageY,false,false);
		tabImage.put(9, image);
		image = new Image(getClass().getResourceAsStream("/Luciole.gif"),tailleImageX,tailleImageY,false,false);
		tabImage.put(8, image);
		image = new Image(getClass().getResourceAsStream("/Sortie.gif"),tailleImageX,tailleImageY,false,false);
		tabImage.put(7, image);
		image = new Image(getClass().getResourceAsStream("/Terre.gif"),tailleImageX,tailleImageY,false,false);
		tabImage.put(6, image);
		image = new Image(getClass().getResourceAsStream("/Rocher.gif"),tailleImageX,tailleImageY,false,false);
		tabImage.put(5, image);
		image = new Image(getClass().getResourceAsStream("/Papillon.gif"),tailleImageX,tailleImageY,false,false);
		tabImage.put(4, image);
		image = new Image(getClass().getResourceAsStream("/Diamant.gif"),tailleImageX,tailleImageY,false,false);
		tabImage.put(3, image);
		image = new Image(getClass().getResourceAsStream("/Acier.gif"),tailleImageX,tailleImageY,false,false);
		tabImage.put(2, image);
		image = new Image(getClass().getResourceAsStream("/Rockford.gif"),tailleImageX,tailleImageY,false,false);
		tabImage.put(1, image);
		image = new Image(getClass().getResourceAsStream("/Vide.gif"),tailleImageX,tailleImageY,false,false);
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
			
				if(c.estOccupee() && c.getEstIci() instanceof Rockford) {
					if(!rockfordPeutSeDepl) {
						image = tabImage.get(c.caseEnInt() + depRockford);
					}					

				}
					
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

		laSortie = grille.chercheSortie();
		
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

			KeyCombination shiftZ = new KeyCodeCombination(KeyCode.Z, KeyCodeCombination.SHIFT_DOWN);
			KeyCombination shiftS = new KeyCodeCombination(KeyCode.S, KeyCodeCombination.SHIFT_DOWN);
			KeyCombination shiftQ = new KeyCodeCombination(KeyCode.Q, KeyCodeCombination.SHIFT_DOWN);
			KeyCombination shiftD = new KeyCodeCombination(KeyCode.D, KeyCodeCombination.SHIFT_DOWN);

			// YL : il faudra naturellement remanier cette fonction pour qu'elle
			// utilise vos classes...

			if(rockfordPeutSeDepl) {

				switch (ke.getCode()) {
					case ESCAPE: {

						timelineChute.stop();
						timelineDessin.stop();
						timelineMonstre.stop();
						timelineSortie.stop();
						panneauTime.getTimer().getTimeline().stop();
						panneauVie.getTimer().getTimeline().stop();
						panneauDiams.getTimer().getTimeline().stop();
						secondes = panneauTime.getAfficheur().getSecondes();

						initMenu(primaryStage);

						break;
					}
					case Z: {
						try {
							if(shiftZ.match(ke)) {
								grille.actionPerso(xRockford, yRockford, xRockford, yRockford - 1);
							}

							else if(yRockford - 1 >= 0) {
								if(grille.deplacerPerso(xRockford, yRockford, xRockford, yRockford - 1)) {
									ke.consume();
									yRockford -= 1;	
								}
									
								
							}
							
						} catch (BoulderMortException e) {
							e.printStackTrace();
						}
						break;
					}
					case S: {
						try {
							if(shiftS.match(ke)) {
								grille.actionPerso(xRockford, yRockford, xRockford, yRockford + 1);
							}

							else if(yRockford + 1 < grille.getYMAX()) {
								if(grille.deplacerPerso(xRockford, yRockford, xRockford, yRockford + 1)) {
									ke.consume();
									yRockford += 1;
								}
									
							}
							
						} catch (BoulderMortException e) {
							e.printStackTrace();
						}
						break;
					}
					case D: {
						try {
							if(shiftD.match(ke)) {
								grille.actionPerso(xRockford, yRockford, xRockford + 1, yRockford);
								depRockford = 9;
							}

							else if(xRockford + 1 < grille.getXMAX()) {
								if(grille.deplacerPerso(xRockford, yRockford, xRockford + 1, yRockford)) {
									ke.consume();
									xRockford += 1;
									depRockford = 9;
								}
									
							}
							
						} catch (BoulderMortException e) {
							e.printStackTrace();
						}
						break;
					}
					case Q: {
						try {
							if(shiftQ.match(ke)) {
								grille.actionPerso(xRockford, yRockford, xRockford - 1, yRockford);
								depRockford = 8;
							}

							else if(xRockford - 1 >= 0) {
								if(grille.deplacerPerso(xRockford, yRockford, xRockford - 1, yRockford)) {
									ke.consume();
									xRockford -= 1;
									depRockford = 8;
								}
									
							}
							
						} catch (BoulderMortException e) {
							e.printStackTrace();
						}
						break;
					}
					case P: {
						grille.getCaseDuTab(xRockford, yRockford).getEstIci().setVie(0);
					}
					default:
						return;

				}

				rockfordPeutSeDepl = false;
			}
			
		}
	}

	/**
	 * Initialise les informations à avoir
	 */
	private void initFooter() {
		panneauVie = new PanneauVie((Rockford)(grille.getCaseDuTab(xRockford, yRockford).getEstIci()));

		panneauDiams = new PanneauDiams(grille, ((Rockford)(grille.getCaseDuTab(xRockford, yRockford).getEstIci())));

		if(secondes != 0)
			panneauTime = new PanneauTime(secondes);
		else
			panneauTime = new PanneauTime(0);

		panneauTime.setAlignment(Pos.CENTER);

		HBox h = new HBox();
		VBox v = new VBox();

		h.getChildren().addAll(panneauVie, panneauDiams);
		h.setAlignment(Pos.CENTER);

		v.getChildren().addAll(panneauTime,h);
		v.setSpacing(25);
		v.setAlignment(Pos.BOTTOM_CENTER);

		((BorderPane) root).setTop(v);
	}

	/**
	 * dessine la grille dans un laps de temps très court : permet une bonne fluidité des gifs
	 */
	private void dessin(Stage primaryStage) {
		KeyFrame dessin = new KeyFrame(Duration.seconds(0.05), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				dessinerGrille();
				try {
					changementFenetre(primaryStage);
				} catch (IOException e) {
					e.printStackTrace();
				}
	
			}
	
		});
	
		timelineDessin = new Timeline(dessin);
		timelineDessin.setCycleCount(Animation.INDEFINITE);
		timelineDessin.play();

	}

	/**
	 * Vérifie dans un laps de temps court si la case de sortie peut-être ouverte ou non
	 * Cette timeline s'arrête quand cette case de sortie est ouverte
	 * @param primaryStage la scène
	 */
	private void sortie() {
		KeyFrame sortie = new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				grille.ouvrirSortie(laSortie);

			}
	
		});
	
		timelineSortie = new Timeline(sortie);
		timelineSortie.setCycleCount(Animation.INDEFINITE);
		timelineSortie.play();

	}

	/**
	 * fait chuter tout les rochers et diamants grace a une keyframe qui boucle a l'infini
	 * et, etant une keyframe qui boucle tres rapidement, je verifie ici si on a gagne ou perdu
	 */
	private void chuteItem() {
		KeyFrame chuteItem = new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				grille.chuteItem();
				rockfordPeutSeDepl = true;
	
			}
		});
	
		timelineChute = new Timeline(chuteItem);
		timelineChute.setCycleCount(Animation.INDEFINITE);
		timelineChute.play();

	}

	private void deplacementMonstre() {
		KeyFrame deplMonstre = new KeyFrame(Duration.seconds(0.3), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				grille.mouvementMonstres();

			}	

		});

		timelineMonstre = new Timeline(deplMonstre);
		timelineMonstre.setCycleCount(Animation.INDEFINITE);
		timelineMonstre.play();

	}

	private void changementFenetre(Stage primaryStage) throws IOException {

		if(grille.mortRockford(xRockford, yRockford)) {
			timelineChute.stop();
			timelineMonstre.stop();
			timelineSortie.stop();
			timelineDessin.stop();
			panneauTime.getTimer().getTimeline().stop();
			panneauVie.getTimer().getTimeline().stop();
			panneauDiams.getTimer().getTimeline().stop();
			initPerdu(primaryStage);
		}
			
		else if (laSortie.isPorteOuverte()) {
			timelineSortie.stop();
			
			if(laSortie.estOccupee() && laSortie.getEstIci() instanceof Rockford) {
				timelineDessin.stop();
				timelineChute.stop();
				timelineMonstre.stop();
				panneauTime.getTimer().getTimeline().stop();
				panneauVie.getTimer().getTimeline().stop();
				panneauDiams.getTimer().getTimeline().stop();
				initSuivant(primaryStage);
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
