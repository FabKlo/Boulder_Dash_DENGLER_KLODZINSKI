package chuterocher;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public abstract class ChuteDuRocherCOR implements ChuteDuRocher {

    private ChuteDuRocherCOR suivant;

    public ChuteDuRocherCOR(ChuteDuRocherCOR s) {
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
    protected abstract boolean deplacerRocherVersCase(Grille grille, int cs, int ls) throws BoulderMortException;

    @Override
    public boolean deplaceRocher(Grille grille, int cs, int ls) throws BoulderMortException {
        if(!(this.deplacerRocherVersCase(grille, cs, ls))) {
            if(suivant != null) {
                return suivant.deplaceRocher(grille, cs, ls);
            }
            else
                return false;
        }
        else
            return true;
    }

    public static ChuteDuRocherCOR initCOR() {
        ChuteDuRocherCORSurVide vide = new ChuteDuRocherCORSurVide(null);
        ChuteDuRocherCORSurMonstre monstre = new ChuteDuRocherCORSurMonstre(vide);
        ChuteDuRocherCORSurRockford rockford = new ChuteDuRocherCORSurRockford(monstre);
        ChuteDuRocherCORSurDiamant diamant = new ChuteDuRocherCORSurDiamant(rockford);
        ChuteDuRocherCORSurRocher rocher = new ChuteDuRocherCORSurRocher(diamant);
        ChuteDuRocherCORSurAcier acier = new ChuteDuRocherCORSurAcier(rocher);
        return acier;
    }

}