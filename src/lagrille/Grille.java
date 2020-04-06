package lagrille;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import chutediamant.ChuteDuDiamantCOR;
import chuterocher.ChuteDuRocherCOR;
import deplacementrockford.DeplacerRockfordCOR;
import entitesvivantes.Monstre;
import entitesvivantes.Personnage;
import entitesvivantes.Rockford;
import lescases.*;
import modele.exceptions.BoulderException;
import modele.exceptions.BoulderMortException;

public class Grille {

    private Case[][] tableau;
    private int XMAX;
    private int YMAX;
    private int niveau;

    public Grille() {
        niveau = 1;
    }

    public void déplacerPerso(int cs, int ls, int ct, int lt) throws BoulderMortException {
        DeplacerRockfordCOR corRock = DeplacerRockfordCOR.initCOR();
        corRock.deplaceRockford(this, cs, ls, ct, lt);
    }

    public void déplacerRocher(int cs, int ls) throws BoulderMortException {
        ChuteDuRocherCOR corRocher = ChuteDuRocherCOR.initCOR();
        corRocher.deplaceRocher(this, cs, ls);
    }

    public void déplacerDiamant(int cs, int ls) throws BoulderMortException {
        ChuteDuDiamantCOR corDiamant = ChuteDuDiamantCOR.initCOR();
        corDiamant.deplaceDiamant(this, cs, ls);

    }

    public void verifVieAll() throws BoulderMortException {
        ArrayList<Personnage> pers = searchAllPers();
        for (Personnage personnage : pers) {
            if(personnage.getVie() == 0) {
                impactVieEnMoins(personnage);
                tableau[personnage.getPositionX()][personnage.getPositionY()].setEstIci(null);
            }
        }
    }

    public void impactVieEnMoins(Personnage pers) throws BoulderMortException {
        if(pers instanceof Rockford) {
            if(pers.getVie() == 0)
                throw new BoulderMortException("Rockford est mort, c'est perdu !");
        } else {
            if(pers.getVie() == 0)
                tableau[pers.getPositionX()][pers.getPositionY()] = new Diamant(pers.getPositionX(), pers.getPositionY());
        }
    }

    public ArrayList<Personnage> searchAllPers() {
        ArrayList<Personnage> pers = new ArrayList<Personnage>();
        for(int i = 0; i < XMAX; i++) {
            for(int j = 0; j < YMAX; j++) {
                if(tableau[i][j].estOccupee())
                    pers.add(tableau[i][j].getEstIci());
            }
        }
        
        /*for (Personnage c : pers) {
            System.out.println("Personnage : " + c.getClass().getSimpleName() +
            " avec x = " + c.getPositionX() + ", y = " + c.getPositionY() + " qui correspond a une case : " + 
            getCaseDuTab(c.getPositionX(), c.getPositionY()).getClass().getSimpleName());  
        }*/

        return pers;
    }

    public Case getCaseDuTab(int x, int y) {
        return tableau[x][y];
    }

    public void setCaseDuTab(int x, int y, Case c) {
        tableau[x][y] = c;
    }

    /**
     * Pour transformer les entiers des .csv en Case (Meringues, Bonbons ou Vide)
     * 
     * @param num l'entier du fichier .csv
     * @return un objet de type Case
     * @throws BoulderException
     */
    public Case charEnCase(char c, int x, int y) {
        Case typeDeCase;
        switch (c) {
        case 'T':
            typeDeCase = new Terre(x,y);
            break;
        case 'A':
            typeDeCase = new Acier(x,y);
            break;
        case 'R':
            typeDeCase = new Rocher(x,y);
            break;

        case 'V':
            typeDeCase = new Vide(x,y);
            break;

        case 'D':
            typeDeCase = new Diamant(x,y);
            break;

        case 'M':
            typeDeCase = new Vide(x,y);
            typeDeCase.mettrePersoSurCase(new Monstre());
            break;

        case 'E':
            typeDeCase = new Vide(x,y);
            typeDeCase.mettrePersoSurCase(new Rockford());
            break;

        default:
            typeDeCase = new Vide(x,y);
        }

        return typeDeCase;
    }

        /**
     * Vérifie si le fichier .csv est lisible et le retourne si c'est le cas
     * @param orderPath le fichier
     * @return le fichier
     */
    public List<String> verifFichier(Path orderPath) {

        List<String> lines = null;

        try {
            lines = Files.readAllLines(orderPath);          //Verifie si le fichier est lisible
        } catch (IOException e) {
            System.out.println("Impossible de lire le fichier\n");
        }
        return lines;
    }

    /**
     * Cherche XMAX et YMAX dans le fichier .csv et les attributs aux variables de la classe corespondant
     * @param orderPath le fichier de base
     * @param split une certaine ligne modifié (généralement sans ';')
     * @param lines une certaine ligne du fichier
     */
    public void trouverXmaxetYmax(Path orderPath, String[] split, List<String> lines) {

        boolean colTrouv = false;   //Variable qui verifie si on a bien trouvé le XMAX
        boolean ligTrouv = false;   //Idem avec YMAX

        int nbrLigne = 0;           //compteur
        
        do {

            split = lines.get(nbrLigne).split(";");

            if(split[0].equals("NB_LIG") && !(ligTrouv)) {
                this.YMAX = Integer.valueOf(split[1]);
                ligTrouv = true;
            }
            if(split[0].equals("NB_COL") && !(colTrouv)) {
                this.XMAX = Integer.valueOf(split[1]);
                colTrouv = true;
            }

            nbrLigne++;

        }while(!(nbrLigne >= lines.size() || (colTrouv && ligTrouv)));      /*Cherche XMAX et YMAX dans le fichier et attribut
                                                                            les valeurs aux variables correspondantes*/

        this.tableau = new Case[XMAX][YMAX];     //Initialise le tableau
    }

    /**
     * Fonction qui retourne le n° correspondant à la première ligne de fichier qui commence par un char (= début de la grille)
     * @param orderPath le fichier
     * @param split une certaine ligne modifié (généralement sans ';')
     * @param lines une certaine ligne du fichier
     * @return
     */
    public int trouverDebLigne(Path orderPath, String[] split, List<String> lines) {

        boolean trouver = false;    //Cherche la première ligne rencontrée qui commence par un char
        int nbrLigne = 0;               //compteur remit à 0
        int debLigne = 0;

        do {

            split = lines.get(nbrLigne).split(";");

            try {
                if (split[0].length() == 1) {
                    split[0].charAt(0); //Vérifie chaque premier terme du split pour voir si c'est un char
                    trouver = true;
                    debLigne = nbrLigne;
                }

            } catch (NumberFormatException nfe) { }

            nbrLigne++;

        } while(!(nbrLigne >= lines.size() || (trouver)));     //Cherche la première ligne rencontrée qui commence par un char

        return debLigne;

    }

    /**
     * Fonction qui retourne le n° correspondant à la dernière ligne de fichier qui commence par un char (= début de la grille)
     * @param orderPath le fichier
     * @param split une certaine ligne modifié (généralement sans ';')
     * @param lines une certaine ligne du fichier
     * @return
     */
    public int trouverFinLigne(Path orderPath, String[] split, List<String> lines) {

        int nbrLigne = lines.size() - 1;    //Compteur initialisé au max de ligne
        boolean trouver = false;                //booléen remit a false
        int finLigne = 0;
     
        do {

            split = lines.get(nbrLigne).split(";");

            try {
                split[0].charAt(0); //Vérifie chaque premier terme du split pour voir si c'est un char
                trouver = true;
                finLigne = nbrLigne;
            } catch (NumberFormatException nfe) { }

            nbrLigne = nbrLigne - 1;

        } while(!(nbrLigne < 0 || (trouver)));     //Cherche la dernière ligne rencontrée qui commence par un char

        return finLigne;

    }

    /**
     * Créer la grille du niveau voulu
     * 
     * @throws IOException
     */
    public void creerGrille() throws IOException {

        Path orderPath = Paths.get("plateaux/plateauTest.csv");
        String[] split = null;
        List<String> lines = null;
        int debLigne = 0;           //Cherche le début de ligne qui correspond au tableau
        int finLigne = 0;           //Cherche la fin de ligne qui correspond au tableau

        lines = verifFichier(orderPath);

        trouverXmaxetYmax(orderPath, split, lines);

        debLigne = trouverDebLigne(orderPath, split, lines);

        finLigne = trouverFinLigne(orderPath, split, lines);

        ////////////////////////////////////////////////////////////////////////////

        int compteur = 0;       //Comme la boucle "pour" ne commence pas à 0, il nous faut un compteur

        for(int i = debLigne; i <= finLigne; i++) {

            split = lines.get(i).split(";");

            try {
                for(int ParcoursSplit = 0; ParcoursSplit < split.length; ParcoursSplit++) {
                    tableau[ParcoursSplit][compteur] = charEnCase(split[ParcoursSplit].charAt(0), ParcoursSplit, compteur);
                    //tableau[ParcoursSplit][compteur].setPositionX(ParcoursSplit);
                    //tableau[ParcoursSplit][compteur].setPositionY(compteur);
                }
                
            } catch (NumberFormatException nfe) {
                System.out.println("Je sais pas comment tu as fait pour arriver là, mais bien joué !");
            }  

            compteur++;
            
        }


    }

    public Case[][] getTableau() {
        return tableau;
    }

    public void setTableau(Case[][] tableau) {
        this.tableau = tableau;
    }

    public int getXMAX() {
        return XMAX;
    }

    public void setXMAX(int xMAX) {
        XMAX = xMAX;
    }

    public int getYMAX() {
        return YMAX;
    }

    public void setYMAX(int yMAX) {
        YMAX = yMAX;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    
}