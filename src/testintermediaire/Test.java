package testintermediaire;

import java.io.IOException;

import lagrille.Grille;
import modele.exceptions.BoulderException;

public class Test {
    public static void main(String[] args) throws IOException, BoulderException {
        Grille tab = new Grille();
        tab.creerGrille();
        //tab.searchAllPers();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("POUR LES TESTS INTERMEDIAIRES : \nQuand le prof demande de bouger un perso de (1,6) en (1,7)," +
        "chez nous ça correspond a (5,0) en (6,0) car lui commence son tableau a 1 et non 0,\n" +
        "et commence par y (les lignes) puis par x (les colonnes), et nous c'est l'inverse");
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        System.out.println("\nVers vide");
        tab.déplacerPerso(0,0,1,0); //vers vide

        System.out.println();

        System.out.println("\nVers acier");
        tab.déplacerPerso(1,1,0,1); //vers acier
        System.out.println("\nVers monstre");
        tab.déplacerPerso(5,0,6,0); //vers monstre
        System.out.println("\nVers terre");
        tab.déplacerPerso(3,1,4,1); //vers terre
        System.out.println("\nVers diamant");
        tab.déplacerPerso(6,1,7,1); //vers diamant
        System.out.println("\nVers rocher qui bouge");
        tab.déplacerPerso(0,3,1,3); //vers rocher qui bouge
        System.out.println("\nVers rocher qui bouge pas");
        tab.déplacerPerso(3,4,2,4); //vers rocher qui bouge pas = pas de déplacement
    }
}