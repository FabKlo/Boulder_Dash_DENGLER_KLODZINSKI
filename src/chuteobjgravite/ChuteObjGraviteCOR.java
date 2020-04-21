package chuteobjgravite;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public abstract class ChuteObjGraviteCOR implements ChuteObjGravite {

    private ChuteObjGraviteCOR suivant;

    /**
     * Chaine de responsabilité
     * @param s
     */
    public ChuteObjGraviteCOR(ChuteObjGraviteCOR s) {
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
    /**
     * méthode récursive pour traversé tout les maillons de la chaine
     */
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

    /**
     * initialisation de la chaine
     * @return
     */
    public static ChuteObjGraviteCOR initCOR() {

        ChuteDuDiamantCORSurVide diamantSurVide = new ChuteDuDiamantCORSurVide(null);
        ChuteDuDiamantCORSurMonstre diamantSurMonstre = new ChuteDuDiamantCORSurMonstre(diamantSurVide);
        ChuteDuDiamantCORSurRockford diamantSurRockford = new ChuteDuDiamantCORSurRockford(diamantSurMonstre);
        ChuteDuDiamantCORSurDiamant diamantSurDiamant = new ChuteDuDiamantCORSurDiamant(diamantSurRockford);
        ChuteDuDiamantCORSurRocher diamantSurRocher = new ChuteDuDiamantCORSurRocher(diamantSurDiamant);
        ChuteDuDiamantCORSurTerre diamantSurTerre = new ChuteDuDiamantCORSurTerre(diamantSurRocher);
        ChuteDuDiamantCORSurAcier diamantSurAcier = new ChuteDuDiamantCORSurAcier(diamantSurTerre);

        ChuteDuRocherCORSurVide rocherSurVide = new ChuteDuRocherCORSurVide(diamantSurAcier);
        ChuteDuRocherCORSurMonstre rocherSurMonstre = new ChuteDuRocherCORSurMonstre(rocherSurVide);
        ChuteDuRocherCORSurRockford rocherSurRockford = new ChuteDuRocherCORSurRockford(rocherSurMonstre);
        ChuteDuRocherCORSurDiamant rocherSurDiamant = new ChuteDuRocherCORSurDiamant(rocherSurRockford);
        ChuteDuRocherCORSurRocher rocherSurRocher = new ChuteDuRocherCORSurRocher(rocherSurDiamant);
        ChuteDuRocherCORSurTerre rocherSurTerre = new ChuteDuRocherCORSurTerre(rocherSurRocher);
        ChuteDuRocherCORSurAcier rocherSurAcier = new ChuteDuRocherCORSurAcier(rocherSurTerre);

        return rocherSurAcier;
    }

}