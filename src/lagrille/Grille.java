package lagrille;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import chuteobjgravite.ChuteObjGraviteCOR;
import deplacementmonstre.DeplacerMonstreCOR;
import deplacementrockford.DeplacerRockfordCOR;
import entitesvivantes.Luciole;
import entitesvivantes.Monstre;
import entitesvivantes.Papillon;
import entitesvivantes.Personnage;
import entitesvivantes.Rockford;
import lescases.*;
import modele.exceptions.BoulderMortException;

public class Grille {

    private Case[][] tableau;
    private int XMAX;
    private int YMAX;
    private int niveau;
    private String objectif;
    private int diamsMax;

    public static ArrayList<Case> allObjGravite = new ArrayList<Case>();
    public static ArrayList<Case> allDiamant = new ArrayList<Case>();
	public static ArrayList<Personnage> allPers = new ArrayList<Personnage>();
	public static ArrayList<Monstre> allMonstres = new ArrayList<Monstre>();

    private DeplacerMonstreCOR corMonstre = DeplacerMonstreCOR.initCOR();
    private DeplacerRockfordCOR corRock = DeplacerRockfordCOR.initCOR();
    private ChuteObjGraviteCOR corObjGravite = ChuteObjGraviteCOR.initCOR();


    public Grille() {
        niveau = 1;
    }

    /**
     * deplace un monstre de [cs][ls] vers [ct][lt]
     * @param cs colonne source
     * @param ls ligne source
     * @param ct colonne target
     * @param lt ligne target
     * @throws BoulderMortException
     */
    public boolean deplacerMonstre(int cs, int ls, int ct, int lt) throws BoulderMortException {
        return corMonstre.deplaceMonstre(this, cs, ls, ct, lt);
    }

    /**
     * deplace rockford de [cs][ls] vers [ct][lt]
     * @param cs colonne source
     * @param ls ligne source
     * @param ct colonne target
     * @param lt ligne target
     * @throws BoulderMortException
     */
    public boolean deplacerPerso(int cs, int ls, int ct, int lt) throws BoulderMortException {
        return corRock.deplaceRockford(this, cs, ls, ct, lt);
    }

    /**
     * deplace l'objet soumis par la gravite de [cs][ls] vers [cs][ls-1]
     * @param cs colonne source
     * @param ls ligne source
     * @throws BoulderMortException
     */
    public void deplacerObjGravite(int cs, int ls) throws BoulderMortException {
        corObjGravite.deplaceObjGravite(this, cs, ls);
    }

    /**
     * 
     * @return la case qui correspond à la sortie dans la grille
     */
    public Sortie chercheSortie() {

        for(int i = XMAX-1; i >= 0; i--) {
            for(int j = YMAX-1; j >= 0; j--) {
                if(tableau[i][j] instanceof Sortie) {
                    return (Sortie)(tableau[i][j]);
                }
                    
            }
        }

        return null;
    }


    /**
     * Ouvre la sortie si et seulement si l'objectif du tableau est rempli
     * @param s la sortie de la grille
     */
    public void ouvrirSortie(Sortie s) {
        if(verifObjectif()) {
            s.setPorteOuverte(true);
        }
    }

    /**
     * Fait chuter tous les objets soumis a la gravite de la grille
     */
    public void chuteItem() {

        for (Case c : allObjGravite) {
					
            if(c.getPositionY() < YMAX) {
                try {
                    
                    deplacerObjGravite(c.getPositionX(), c.getPositionY());
                    
                } catch (BoulderMortException e) {							
                    e.printStackTrace();
                }
            }
        }

        searchAll();

    }

    /**
     * Fait bouger tout les monstres de la grille
     */
    public void mouvementMonstres() {

        for(Monstre m : Grille.allMonstres) {
            switch(m.getDirection()) {

                case Monstre.GAUCHE:
                    try {

                        if (!(deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX(),
                            m.getPositionY() + 1))) {

                                if(!deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX() - 1,
                                    m.getPositionY())) {

                                        if(!deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX(),
                                            m.getPositionY() - 1)) {

                                                deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX() + 1,
                                                m.getPositionY());
                                                m.setDirection(Monstre.DROITE);

                                        }
                                        else
                                            m.setDirection(Monstre.HAUT);

                                }

                        } else 
                            m.setDirection(Monstre.BAS);
                                

                    } catch (BoulderMortException e) {
                        e.printStackTrace();
                    }

                    break;

                case Monstre.BAS:
                    try {

                        if (!deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX() + 1,
                            m.getPositionY())) {

                                if(!deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX(),
                                    m.getPositionY() + 1)) {

                                        if(!deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX() - 1,
                                            m.getPositionY())) {

                                                deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX(),
                                                m.getPositionY() - 1);
                                                m.setDirection(Monstre.HAUT);

                                        }
                                        else
                                            m.setDirection(Monstre.GAUCHE);

                                }

                        } else 
                            m.setDirection(Monstre.DROITE);
                                

                    } catch (BoulderMortException e) {
                        e.printStackTrace();
                    }

                    break;

                case Monstre.DROITE:
                    try {

                        if (!deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX(),
                            m.getPositionY() - 1)) {

                                if(!deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX() + 1,
                                    m.getPositionY())) {

                                        if(!deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX(),
                                            m.getPositionY() + 1)) {

                                                deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX() - 1,
                                                m.getPositionY());
                                                m.setDirection(Monstre.GAUCHE);

                                        }
                                        else
                                            m.setDirection(Monstre.BAS);

                                }

                        } else 
                            m.setDirection(Monstre.HAUT);
                                

                    } catch (BoulderMortException e) {
                        e.printStackTrace();
                    }
                    break;

                case Monstre.HAUT:
                    try {

                        if (!deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX() - 1,
                            m.getPositionY())) {

                                if(!deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX(),
                                    m.getPositionY() - 1)) {

                                        if(!deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX() + 1,
                                            m.getPositionY())) {

                                                deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX(),
                                                m.getPositionY() + 1);
                                                m.setDirection(Monstre.BAS);

                                        }
                                        else
                                            m.setDirection(Monstre.DROITE);

                                }

                        } else 
                            m.setDirection(Monstre.GAUCHE);
                                

                    } catch (BoulderMortException e) {
                        e.printStackTrace();
                    }
                    break;





                /*case Monstre.DROITE:
                    try {
                        if (!deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX() + 1,
                                m.getPositionY()))
                            m.setDirection(Monstre.BAS);

                    } catch (BoulderMortException e) {
                        e.printStackTrace();
                    }


                    break;

                case Monstre.BAS:
                    try {
                        if (!deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX(),
                                m.getPositionY() + 1))
                            m.setDirection(Monstre.GAUCHE);
                    } catch (BoulderMortException e) {
                        e.printStackTrace();
                    }

                    break;
                    
                case Monstre.GAUCHE:
                    try {
                        if (!deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX() - 1,
                                m.getPositionY()))
                            m.setDirection(Monstre.HAUT);
                    } catch (BoulderMortException e) {
                        e.printStackTrace();
                    }
                    break;

                case Monstre.HAUT:
                    try {
                        if (!deplacerMonstre(m.getPositionX(), m.getPositionY(), m.getPositionX(),
                                m.getPositionY() - 1))
                            m.setDirection(Monstre.DROITE);
                    } catch (BoulderMortException e) {
                        e.printStackTrace();
                    }

                    break;

                default:*/

            }
        }

        //searchAllPers();
    }

    /**
     * verifie la vie de tout les persos sur la grille, et lance un gameOver si rockford est mort
     * @throws BoulderMortException
     */
    public void verifVieAll() throws BoulderMortException {

        searchAllPers();

        for (Personnage personnage : Grille.allPers) {
            if(personnage.getVie() == 0) {
                if(personnage instanceof Rockford) {
                        System.out.println("Rockford est mort, c'est perdu !");
                        break;
                }
            }
        }

        searchAll();

    }


    /**
     * 
     * @param x coord x du tableau
     * @param y coord y du tableau
     * @return true si rockford est mort, false si non
     */
    public boolean mortRockford(int x, int y) {
        if(tableau[x][y].estOccupee()) {
            if(tableau[x][y].getEstIci() instanceof Rockford) {
                if(tableau[x][y].getEstIci().getVie() <= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return true si l'objectif est accompli, false si non
     */
    public boolean verifObjectif() {

        switch(objectif) {
            case "COLLECTER_DIAMANT":
                for (Personnage p : Grille.allPers) {
                    if(p instanceof Rockford) {
                        if( ((Rockford)(p)).getCompteurDiamant() >= getDiamsMax())
                            return true;
                        else
                            return false;
                    }     
                }
                break;
            case "ELIMINER_MONSTRES":
                if(Grille.allMonstres.size() == 0)
                    return true;
                else
                    return false;
            default:
        }


        return false;
    }

    /**
     * Cherche absolument tout ce qu'il y a dans la grille de specifique
     */
    public void searchAll() {

        ArrayList<Case> diams = new ArrayList<Case>();
        ArrayList<Case> obj = new ArrayList<Case>();
        ArrayList<Personnage> pers = new ArrayList<Personnage>();
        ArrayList<Monstre> monstres = new ArrayList<Monstre>();

        for(int i = 0; i < XMAX; i++) {
            for(int j = 0; j < YMAX; j++) {

                if(tableau[i][j].isEstSoumisALaGravite())
                    obj.add(tableau[i][j]);

                if(tableau[i][j] instanceof Diamant)
                    diams.add(tableau[i][j]);

                if(tableau[i][j].estOccupee()) {
                    pers.add(tableau[i][j].getEstIci());
                    if(tableau[i][j].estOccupee() && tableau[i][j].getEstIci() instanceof Monstre) {
                        monstres.add((Monstre)(tableau[i][j].getEstIci()));
                    }
                }
            }
        }

        Grille.allDiamant = diams;
        Grille.allMonstres = monstres;
        Grille.allPers = pers;
        Grille.allObjGravite = obj;

    }

        /**
     * @return une arraylist de case qui contient que des diamants
     */
    public void searchAllDiamantMap() {
        ArrayList<Case> diams = new ArrayList<Case>();
        for(int i = 0; i < XMAX; i++) {
            for(int j = 0; j < YMAX; j++) {

                if(tableau[i][j] instanceof Diamant)
                    diams.add(tableau[i][j]);

            }
        }

        Grille.allDiamant = diams;
    }

    /**
     * @return une arraylist de case qui contient que des rochers et diamants
     */
    public void searchAllObjetSoumisParLaGravite() {
        ArrayList<Case> obj = new ArrayList<Case>();
        for(int i = 0; i < XMAX; i++) {
            for(int j = 0; j < YMAX; j++) {

                if(tableau[i][j].isEstSoumisALaGravite())
                    obj.add(tableau[i][j]);

            }
        }

        Grille.allObjGravite = obj;
    }

    /**
     * cherche tout les persos de la grille
     * @return une arraylist de personnages
     */
    public void searchAllPers() {
        ArrayList<Personnage> pers = new ArrayList<Personnage>(); 
        ArrayList<Monstre> monstres = new ArrayList<Monstre>();  

        for(int i = 0; i < XMAX; i++) {
            for(int j = 0; j < YMAX; j++) {
                if(tableau[i][j].estOccupee()) {
                    pers.add(tableau[i][j].getEstIci());
                    if(tableau[i][j].getEstIci() instanceof Monstre)
                        monstres.add((Monstre)tableau[i][j].getEstIci());
                }
                    
                
            }
        }

        Grille.allPers = pers;
        Grille.allMonstres = monstres;
    }



    /**
     * retourne la case associee a ces coordonnees
     * @param x position X dans la grille
     * @param y position Y dans la grille
     * @return
     */
    public Case getCaseDuTab(int x, int y) {
        return tableau[x][y];
    }

    /**
     * transforme la case associee a ces coordonnees en la case c
     * @param x position X dans la grille
     * @param y position Y dans la grille
     * @param c la nouvelle case
     * @return
     */
    public void setCaseDuTab(int x, int y, Case c) {
        tableau[x][y] = c;
        c.setPositionX(x);
        c.setPositionY(y);
    }

    /**
     * transforme les caracteres du .csv en case
     * @param c le caractere
     * @param x position X
     * @param y position Y
     * @return la case associee au caractere
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
        case 'S':
            typeDeCase = new Sortie(x,y);
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

        case 'L':
            typeDeCase = new Vide(x,y);
            typeDeCase.mettrePersoSurCase(new Luciole());
            break;

        case 'P':
            typeDeCase = new Vide(x,y);
            typeDeCase.mettrePersoSurCase(new Papillon());
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
     * Verifie si le fichier .csv est lisible et le retourne si c'est le cas
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
     * @param split une certaine ligne modifie (generalement sans ';')
     * @param lines une certaine ligne du fichier
     */
    public void trouverXmaxetYmax(Path orderPath, String[] split, List<String> lines) {

        boolean colTrouv = false;   //Variable qui verifie si on a bien trouve le XMAX
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
     * Fonction qui retourne le n° correspondant a la premiere ligne de fichier qui commence par un char (= debut de la grille)
     * @param orderPath le fichier
     * @param split une certaine ligne modifie (generalement sans ';')
     * @param lines une certaine ligne du fichier
     * @return
     */
    public int trouverDebLigne(Path orderPath, String[] split, List<String> lines) {

        boolean trouver = false;    //Cherche la premiere ligne rencontree qui commence par un char
        int nbrLigne = 0;               //compteur remit a 0
        int debLigne = 0;

        do {

            split = lines.get(nbrLigne).split(";");

            try {
                if (split[0].length() == 1) {
                    split[0].charAt(0); //Verifie chaque premier terme du split pour voir si c'est un char
                    trouver = true;
                    debLigne = nbrLigne;
                }

            } catch (NumberFormatException nfe) { }

            nbrLigne++;

        } while(!(nbrLigne >= lines.size() || (trouver)));     //Cherche la premiere ligne rencontree qui commence par un char

        return debLigne;

    }

    /**
     * Fonction qui retourne le n° correspondant a la derniere ligne de fichier qui commence par un char (= debut de la grille)
     * @param orderPath le fichier
     * @param split une certaine ligne modifie (generalement sans ';')
     * @param lines une certaine ligne du fichier
     * @return
     */
    public int trouverFinLigne(Path orderPath, String[] split, List<String> lines) {

        int nbrLigne = lines.size() - 1;    //Compteur initialise au max de ligne
        boolean trouver = false;                //booleen remit a false
        int finLigne = 0;
     
        do {

            split = lines.get(nbrLigne).split(";");

            try {
                split[0].charAt(0); //Verifie chaque premier terme du split pour voir si c'est un char
                trouver = true;
                finLigne = nbrLigne;
            } catch (NumberFormatException nfe) { }

            nbrLigne = nbrLigne - 1;

        } while(!(nbrLigne < 0 || (trouver)));     //Cherche la derniere ligne rencontree qui commence par un char

        return finLigne;

    }

    /**
     * Cherche l'objectif dans le fichier .csv
     * @param orderPath le fichier
     * @param split une certaine ligne modifie (generalement sans ';')
     * @param lines une certaine ligne du fichier
     */
    public void trouverObjectif(Path orderPath, String[] split, List<String> lines) {

        boolean objTrouv = false;   //Variable qui verifie si on a bien trouve un objectif
        boolean diamsTrouv = false;
        boolean monstreTrouv = false;

        int nbrLigne = 0;           //compteur
        int compteurIntermediaire = 0; 

        String[] splitInter = null;
        
        do {

            split = lines.get(nbrLigne).split(";");
            if(split[0].equals("SANS_OBJECTIF")) {
                                                            /*Recherche le mode de jeu où il n'y a pas d'objectif */
                objTrouv = true;
                this.objectif = "SANS_OBJECTIF";

            }
            else if(split[0].equals("COLLECTER_DIAMANT")) {          //Sinon cherche le mode de jeu avec temps limite

                this.objectif = "COLLECTER_DIAMANT";
                do {

                    splitInter = lines.get(compteurIntermediaire).split(";");

                    if(splitInter[0].equals("NBR_DIAMANT")) {
                        diamsTrouv = true;
                        this.diamsMax = Integer.valueOf(splitInter[1]);
                    }

                    compteurIntermediaire++;

                } while(!(compteurIntermediaire >= lines.size() || diamsTrouv));

            }
            else if (split[0].equals("ELIMINER_MONSTRES")) {       //Sinon cherche le mode de jeu où il faut detruire toute les meringues

                this.objectif = "ELIMINER_MONSTRES";
                do {

                    splitInter = lines.get(compteurIntermediaire).split(";");

                    monstreTrouv = true;

                    compteurIntermediaire++;

                } while(!(compteurIntermediaire >= lines.size() || (monstreTrouv)));
            }

            nbrLigne++;

        } while(!(nbrLigne >= lines.size() || (objTrouv)));      /*Cherche un objectif*/

    }

    /**
     * Creer la grille du niveau voulu
     * 
     * @throws IOException
     */
    public void creerGrille() throws IOException {

        Path orderPath = Paths.get("plateaux/plateau"+niveau+".csv");
        String[] split = null;
        List<String> lines = null;
        int debLigne = 0;           //Cherche le debut de ligne qui correspond au tableau
        int finLigne = 0;           //Cherche la fin de ligne qui correspond au tableau

        lines = verifFichier(orderPath);

        trouverXmaxetYmax(orderPath, split, lines);

        trouverObjectif(orderPath, split, lines);

        debLigne = trouverDebLigne(orderPath, split, lines);

        finLigne = trouverFinLigne(orderPath, split, lines);

        ////////////////////////////////////////////////////////////////////////////

        int compteur = 0;       //Comme la boucle "pour" ne commence pas a 0, il nous faut un compteur

        for(int i = debLigne; i <= finLigne; i++) {

            split = lines.get(i).split(";");

            try {
                for(int ParcoursSplit = 0; ParcoursSplit < split.length; ParcoursSplit++) {
                    tableau[ParcoursSplit][compteur] = charEnCase(split[ParcoursSplit].charAt(0), ParcoursSplit, compteur);
                    //tableau[ParcoursSplit][compteur].setPositionX(ParcoursSplit);
                    //tableau[ParcoursSplit][compteur].setPositionY(compteur);
                }
                
            } catch (NumberFormatException nfe) {
                System.out.println("Je sais pas comment tu as fait pour arriver la, mais bien joue !");
            }  

            compteur++;
            
        }

        searchAll();


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

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public int getDiamsMax() {
        return diamsMax;
    }

    public void setDiamsMax(int diamsMax) {
        this.diamsMax = diamsMax;
    }
    
}