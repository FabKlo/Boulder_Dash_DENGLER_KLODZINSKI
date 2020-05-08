package chuteobjgravite;

import lagrille.Grille;
import lescases.Diamant;
import modele.exceptions.BoulderMortException;

import toutlessongs.Musique;

public class ChuteDuDiamantCORSurDiamant extends ChuteObjGraviteCOR {

    public ChuteDuDiamantCORSurDiamant(ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerObjGraviteVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Diamant)) {
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls+1) instanceof Diamant)) {
            //System.out.println("la case target n'est pas un diamant");
            return false;
        }

        if(((Diamant)(grille.getCaseDuTab(cs,ls))).isEnMouvement()) {
            Musique.initBruitage(Musique.DIAMANT);
        }
        ((Diamant)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
        return true;
    }

}