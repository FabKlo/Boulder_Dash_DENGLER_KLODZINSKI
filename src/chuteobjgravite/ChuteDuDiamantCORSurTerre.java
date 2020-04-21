package chuteobjgravite;

import lagrille.Grille;
import lescases.Diamant;
import lescases.Terre;
import modele.exceptions.BoulderMortException;

public class ChuteDuDiamantCORSurTerre extends ChuteObjGraviteCOR {

    public ChuteDuDiamantCORSurTerre(ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerRocherVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Diamant)) {
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls+1) instanceof Terre)) {
            //System.out.println("la case target n'est pas de l'acier");
            return false;
        }

        ((Diamant)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
        return true;
    }

}