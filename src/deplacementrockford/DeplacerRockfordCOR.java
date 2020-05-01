package deplacementrockford;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public abstract class DeplacerRockfordCOR implements DeplacerRockford {

    private DeplacerRockfordCOR suivant;

    /**
     * Chaine de responsabilite
     * @param s
     */
    public DeplacerRockfordCOR(DeplacerRockfordCOR s) {
        this.suivant = s;
    }

    /**
     * deplace rockford vers un type de case precis
     * @param grille c'est la grille
     * @param cs    colonne source
     * @param ls    ligne source
     * @param ct    colonne target
     * @param lt    ligne target
     * @return faux s'il l'effectue pas, true si oui
     */
    protected abstract boolean deplacerRockfordVersCase(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException;

    @Override
    /**
     * methode recursive pour traverse tout les maillons de la chaine
     */
    public boolean deplaceRockford(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException {
        if(!(this.deplacerRockfordVersCase(grille, cs, ls, ct, lt))) {
            if(suivant != null) {
                return suivant.deplaceRockford(grille, cs, ls, ct, lt);
            }
            else
                return false;
        }
        else
            return true;
    }

    /**
     * initialisation de la chaine
     * @return
     */
    public static DeplacerRockfordCOR initCOR() {

        DeplacerRockfordCORVersVide vide = new DeplacerRockfordCORVersVide(null);
        DeplacerRockfordCORVersTerre terre = new DeplacerRockfordCORVersTerre(vide);
        DeplacerRockfordCORVersDiamant diamant = new DeplacerRockfordCORVersDiamant(terre);
        DeplacerRockfordCORVersMonstre monstre = new DeplacerRockfordCORVersMonstre(diamant);
        DeplacerRockfordCORVersRocher rocher = new DeplacerRockfordCORVersRocher(monstre);
        DeplacerRockfordCORVersAcier acier = new DeplacerRockfordCORVersAcier(rocher);
        DeplacerRockfordCORVersSortie sortie = new DeplacerRockfordCORVersSortie(acier);
        
        return sortie;

    }

}