package testintermediaire;

import java.io.IOException;

import lagrille.Grille;
import modele.exceptions.BoulderException;

public class Test {
    public static void main(String[] args) throws IOException, BoulderException {
        Grille tab = new Grille();
        tab.creerGrille();
        tab.searchAllPers();
    }
}