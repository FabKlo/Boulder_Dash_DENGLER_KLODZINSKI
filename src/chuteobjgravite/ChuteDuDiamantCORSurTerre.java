package chuteobjgravite;

import lagrille.Grille;
import lescases.Diamant;
import lescases.Terre;
import modele.exceptions.BoulderMortException;
import toutlessongs.Musique;

public class ChuteDuDiamantCORSurTerre extends ChuteObjGraviteCOR {

    public ChuteDuDiamantCORSurTerre(ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerObjGraviteVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Diamant)) {
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls+1) instanceof Terre)) {
            //System.out.println("la case target n'est pas de l'acier");
            return false;
        }

        if(((Diamant)(grille.getCaseDuTab(cs,ls))).isEnMouvement()) {
            Musique.initBruitage(Musique.DIAMANT);
        }

        ((Diamant)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false);
        return true;
    }

}