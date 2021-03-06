package deplacementmonstre;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public abstract class DeplacerMonstreCOR implements DeplacerMonstre {

    private DeplacerMonstreCOR suivant;

    /**
     * Chaine de responsabilite
     * @param s
     */
    public DeplacerMonstreCOR(DeplacerMonstreCOR s) {
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
    protected abstract boolean deplacerMonstreVersCase(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException;

    @Override
    /**
     * methode recursive pour traverse tout les maillons de la chaine
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
    public static DeplacerMonstreCOR initCOR() {

        DeplacerMonstreCORVersVide vide = new DeplacerMonstreCORVersVide(null);
        DeplacerMonstreCORVersMonstre monstre = new DeplacerMonstreCORVersMonstre(vide);
        DeplacerMonstreCORVersRockford rockford = new DeplacerMonstreCORVersRockford(monstre);
        DeplacerMonstreCORVersLeReste leReste = new DeplacerMonstreCORVersLeReste(rockford);

        return leReste;

    }

}