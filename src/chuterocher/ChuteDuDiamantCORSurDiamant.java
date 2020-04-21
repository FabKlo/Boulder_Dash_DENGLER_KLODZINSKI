package chuterocher;

import lagrille.Grille;
import lescases.Diamant;
import modele.exceptions.BoulderMortException;

public class ChuteDuDiamantCORSurDiamant extends ChuteObjGraviteCOR {

    public ChuteDuDiamantCORSurDiamant(ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerRocherVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Diamant)) {
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls+1) instanceof Diamant)) {
            //System.out.println("la case target n'est pas un diamant");
            return false;
        }

        ((Diamant)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
        return true;
    }

}