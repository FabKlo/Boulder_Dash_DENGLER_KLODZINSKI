package deplacementrockford;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public abstract class DeplacerRockfordCOR implements DeplacerRockford {

    private DeplacerRockfordCOR suivant;

    public DeplacerRockfordCOR(DeplacerRockfordCOR s) {
        this.suivant = s;
    }

    public DeplacerRockfordCOR() {
        this.suivant = null;
    }

    /**
     * déplace rockford vers un type de case précis
     * @param grille c'est la grille
     * @param cs
     * @param ls
     * @param ct
     * @param lt
     * @return faux s'il l'effectue pas, true si oui
     */
    protected abstract boolean deplacerRockfordVersCase(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException;

    @Override
    public boolean deplaceRockford(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException {
        if(!this.deplacerRockfordVersCase(grille, cs, ls, ct, lt)) {
            if(suivant != null) {
                return suivant.deplacerRockfordVersCase(grille, cs, ls, ct, lt);
            }
            else
                return false;
        }
        else
            return true;
    }

    public static DeplacerRockfordCOR initCOR() {

        DeplacerRockfordCORVersVide vide = new DeplacerRockfordCORVersVide(null);
        DeplacerRockfordCORVersMonstre monstre = new DeplacerRockfordCORVersMonstre(vide);
        return monstre;

    }

}