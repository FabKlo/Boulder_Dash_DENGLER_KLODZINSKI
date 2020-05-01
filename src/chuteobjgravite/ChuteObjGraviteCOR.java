package chuteobjgravite;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public abstract class ChuteObjGraviteCOR implements ChuteObjGravite {

    private ChuteObjGraviteCOR suivant;

    /**
     * Chaine de responsabilite
     * @param s
     */
    public ChuteObjGraviteCOR(ChuteObjGraviteCOR s) {
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
    protected abstract boolean deplacerObjGraviteVersCase(Grille grille, int cs, int ls) throws BoulderMortException;

    @Override
    /**
     * methode recursive pour traverse tout les maillons de la chaine
     */
    public boolean deplaceObjGravite(Grille grille, int cs, int ls) throws BoulderMortException {
        if(!(this.deplacerObjGraviteVersCase(grille, cs, ls))) {
            if(suivant != null) {
                return suivant.deplaceObjGravite(grille, cs, ls);
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
        ChuteDuDiamantCORSurSortie diamantSurSortie = new ChuteDuDiamantCORSurSortie(diamantSurAcier);

        ChuteDuRocherCORSurVide rocherSurVide = new ChuteDuRocherCORSurVide(diamantSurSortie);
        ChuteDuRocherCORSurMonstre rocherSurMonstre = new ChuteDuRocherCORSurMonstre(rocherSurVide);
        ChuteDuRocherCORSurRockford rocherSurRockford = new ChuteDuRocherCORSurRockford(rocherSurMonstre);
        ChuteDuRocherCORSurDiamant rocherSurDiamant = new ChuteDuRocherCORSurDiamant(rocherSurRockford);
        ChuteDuRocherCORSurRocher rocherSurRocher = new ChuteDuRocherCORSurRocher(rocherSurDiamant);
        ChuteDuRocherCORSurTerre rocherSurTerre = new ChuteDuRocherCORSurTerre(rocherSurRocher);
        ChuteDuRocherCORSurAcier rocherSurAcier = new ChuteDuRocherCORSurAcier(rocherSurTerre);
        ChuteDuRocherCORSurSortie rocherSurSortie = new ChuteDuRocherCORSurSortie(rocherSurAcier);

        return rocherSurSortie;
    }

}