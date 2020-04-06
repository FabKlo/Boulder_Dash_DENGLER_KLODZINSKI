package deplacementrockford;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public abstract class DeplacerRockfordCOR implements DeplacerRockford {

    private DeplacerRockfordCOR suivant;

    /**
     * Chaine de responsabilité
     * @param s
     */
    public DeplacerRockfordCOR(DeplacerRockfordCOR s) {
        this.suivant = s;
    }

    /**
     * déplace rockford vers un type de case précis
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
     * méthode récursive pour traversé tout les maillons de la chaine
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
        DeplacerRockfordCORVersMonstre monstre = new DeplacerRockfordCORVersMonstre(vide);
        DeplacerRockfordCORVersTerre terre = new DeplacerRockfordCORVersTerre(monstre);
        DeplacerRockfordCORVersDiamant diamant = new DeplacerRockfordCORVersDiamant(terre);
        DeplacerRockfordCORVersRocher rocher = new DeplacerRockfordCORVersRocher(diamant);
        DeplacerRockfordCORVersAcier acier = new DeplacerRockfordCORVersAcier(rocher);
        return acier;

    }

}