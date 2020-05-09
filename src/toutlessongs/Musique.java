package toutlessongs;

import java.io.File;
import java.util.HashMap;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Class qui initialise toutes les musiques et bruitages 
 * necessaires au jeu
 * Les 2 variables musiqueDeFond et bruitage sont en static et donc accessible partout
 * dans le code, et existent en une seule fois pour pas superposer les sons
 */
public class Musique {

    public static final String TITRE = "Titre";
    public static final String WORLD1 = "World1";
    public static final String WORLD2 = "World2";
    public static final String WORLD3 = "World3";
    public static final String NIVEAU = "ChoixNiveau";
    public static final String PIECE = "Piece";
    public static final String MORT = "Death";
    public static final String START = "Menu";
    public static final String ROCHER = "Rocher";
    public static final String TERRE = "Terre";
    public static final String EXPLOSION = "Explosion";
    public static final String DIAMANT = "Diamant";
    public static final String VICTOIRE = "Victoire";
    public static final String DMG = "Damage";


    public static MediaPlayer musiqueDeFond;
    public static AudioClip bruitage;
    public static HashMap<String, MediaPlayer> tabMusique = new HashMap<String, MediaPlayer>();
    public static HashMap<String, AudioClip> tabBruitage = new HashMap<String, AudioClip>();


    public Musique() {
    }

    public static void initMusiqueDeFond(String s) {

        musiqueDeFond.stop();
        musiqueDeFond = tabMusique.get(s);
        musiqueDeFond.play();
    }

    public static void initBruitage(String s) {
        bruitage = tabBruitage.get(s);
        bruitage.play();
    }

    public static void initAllMusiques() {
        initBruitages();
        initMusiques();
        musiqueDeFond = tabMusique.get(TITRE);
        bruitage = tabBruitage.get(PIECE);
    }

    public static void initBruitages() {
        AudioClip son;
        son = new AudioClip(new File("sounds/MarioBros_Coin.mp3").toURI().toString());
        son.setVolume(0.1);
        tabBruitage.put(PIECE, son);
        son = new AudioClip(new File("sounds/MarioBros_Start.mp3").toURI().toString());
        son.setVolume(0.1);
        tabBruitage.put(START, son);
        son = new AudioClip(new File("sounds/MarioBros_BlockBreak.mp3").toURI().toString());
        son.setVolume(0.1);
        tabBruitage.put(ROCHER, son);
        son = new AudioClip(new File("sounds/Minecraft_Dirt.mp3").toURI().toString());
        son.setVolume(0.5);
        tabBruitage.put(TERRE, son);
        son = new AudioClip(new File("sounds/Minecraft_TNT.mp3").toURI().toString());
        son.setVolume(0.35);
        tabBruitage.put(EXPLOSION, son);
        son = new AudioClip(new File("sounds/Zelda_Rubis.mp3").toURI().toString());
        son.setVolume(0.1);
        tabBruitage.put(DIAMANT, son);
        son = new AudioClip(new File("sounds/MarioBros_Dmg.mp3").toURI().toString());
        son.setVolume(0.03);
        tabBruitage.put(DMG, son);
    }

    public static void initMusiques() {
		Media sound;
		MediaPlayer media;
		sound = new Media(new File("sounds/MarioBros_Title.mp3").toURI().toString());
        media = new MediaPlayer(sound);
        media.setVolume(0.1);
		tabMusique.put(TITRE, media);
		sound = new Media(new File("sounds/MarioBros_World1.mp3").toURI().toString());
        media = new MediaPlayer(sound);
        media.setVolume(0.05);
		tabMusique.put(WORLD1, media);
		sound = new Media(new File("sounds/MarioBros_World2.mp3").toURI().toString());
        media = new MediaPlayer(sound);
        media.setVolume(0.1);
		tabMusique.put(WORLD2, media);
		sound = new Media(new File("sounds/MarioBros_World3.mp3").toURI().toString());
        media = new MediaPlayer(sound);
        media.setVolume(0.1);
		tabMusique.put(WORLD3, media);
		sound = new Media(new File("sounds/MarioBros_ChoixNiveau.mp3").toURI().toString());
        media = new MediaPlayer(sound);
        media.setVolume(0.1);
		tabMusique.put(NIVEAU, media);
		sound = new Media(new File("sounds/MarioBros_Death.mp3").toURI().toString());
        media = new MediaPlayer(sound);
        media.setVolume(0.1);
		tabMusique.put(MORT, media);
		sound = new Media(new File("sounds/FF7_Victory.mp3").toURI().toString());
        media = new MediaPlayer(sound);
        media.setVolume(0.1);
		tabMusique.put(VICTOIRE, media);


	}
}