package deplacementmonstre;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public abstract class DeplacerMonstreCOR implements DeplacerMonstre {

    private DeplacerMonstreCOR suivant;

    /**
     * Chaine de responsabilité
     * @param s
     */
    public DeplacerMonstreCOR(DeplacerMonstreCOR s) {
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
    protected abstract boolean deplacerMonstreVersCase(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException;

    @Override
    /**
     * méthode récursive pour traversé tout les maillons de la chaine
     */
    public boolean deplaceMonstre(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException {
        if(!(this.deplacerMonstreVersCase(grille, cs, ls, ct, lt))) {
            if(suivant != null) {
                return suivant.deplaceMonstre(grille, cs, ls, ct, lt);
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
    //public static DeplacerMonstreCOR initCOR() {

        /*DeplacerMonstreCORVersVide vide = new DeplacerMonstreCORVersVide(null);
        DeplacerMonstreCORVersMonstre monstre = new DeplacerMonstreCORVersMonstre(vide);
        DeplacerMonstreCORVersTerre terre = new DeplacerMonstreCORVersTerre(monstre);
        DeplacerMonstreCORVersDiamant diamant = new DeplacerMonstreCORVersDiamant(terre);
        DeplacerMonstreCORVersRocher rocher = new DeplacerMonstreCORVersRocher(diamant);
        DeplacerMonstreCORVersAcier acier = new DeplacerMonstreCORVersAcier(rocher);
        return acier;*/

    //}

}