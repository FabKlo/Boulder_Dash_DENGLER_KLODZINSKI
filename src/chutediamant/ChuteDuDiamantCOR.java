package chutediamant;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public abstract class ChuteDuDiamantCOR implements ChuteDuDiamant {

    private ChuteDuDiamantCOR suivant;

    public ChuteDuDiamantCOR(ChuteDuDiamantCOR s) {
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
    protected abstract boolean deplacerDiamantVersCase(Grille grille, int cs, int ls) throws BoulderMortException;

    @Override
    public boolean deplaceDiamant(Grille grille, int cs, int ls) throws BoulderMortException {
        if(!(this.deplacerDiamantVersCase(grille, cs, ls))) {
            if(suivant != null) {
                return suivant.deplaceDiamant(grille, cs, ls);
            }
            else
                return false;
        }
        else
            return true;
    }

    public static ChuteDuDiamantCOR initCOR() {
        ChuteDuDiamantCORSurVide vide = new ChuteDuDiamantCORSurVide(null);
        ChuteDuDiamantCORSurMonstre monstre = new ChuteDuDiamantCORSurMonstre(vide);
        ChuteDuDiamantCORSurRockford rockford = new ChuteDuDiamantCORSurRockford(monstre);
        ChuteDuDiamantCORSurDiamant diamant = new ChuteDuDiamantCORSurDiamant(rockford);
        ChuteDuDiamantCORSurRocher rocher = new ChuteDuDiamantCORSurRocher(diamant);
        ChuteDuDiamantCORSurAcier acier = new ChuteDuDiamantCORSurAcier(rocher);
        return acier;
    }

}