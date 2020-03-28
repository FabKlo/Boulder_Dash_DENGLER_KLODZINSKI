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
        System.out.println("POUR LES TESTS INTERMEDIAIRES : quand le prof demande de bouger un perso de (1,6) en (1,7)," +
        "chez nous ça correspond a (5,0) en (6,0) car lui commence son tableau a 1 et non 0," +
        "et commence par y (les lignes) puis par x (les colonnes), et nous c'est l'inverse");
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("TEST DE BASE SANS CHAINE DE RESPONSABILITEE\n");
        
        System.out.println("\n");
        tab.déplacerPerso(0,0,1,0);
        System.out.println("\n");
        tab.déplacerPerso(1,1,0,1);
        System.out.println("\n");
        tab.déplacerPerso(5,0,6,0);
        System.out.println("\n");
        tab.déplacerPerso(3,1,4,1);
        System.out.println("\n");
        tab.déplacerPerso(6,1,7,1);
        System.out.println("\n");
        tab.déplacerPerso(0,3,1,3);
    }
}