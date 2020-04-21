package chuterocher;

import lagrille.Grille;
import lescases.Diamant;
import lescases.Vide;
import modele.exceptions.BoulderMortException;

public class ChuteDuDiamantCORSurVide extends ChuteObjGraviteCOR {

    public ChuteDuDiamantCORSurVide(ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerRocherVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Diamant)) {
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls+1) instanceof Vide) || grille.getCaseDuTab(cs,ls+1).estOccupee()) {
            //System.out.println("la case target n'est pas du vide");
            return false;
        }

        ((Diamant)(grille.getCaseDuTab(cs,ls))).setEnMouvement(true);
        grille.setCaseDuTab(cs,ls+1,grille.getCaseDuTab(cs,ls));
        grille.setCaseDuTab(cs,ls,new Vide(cs,ls));
        //System.out.println("tab["+cs+"]["+ls+"] = " + grille.getCaseDuTab(cs,ls).getClass().getSimpleName());
        //System.out.println("tab["+cs+"]["+(ls+1)+"] = " + grille.getCaseDuTab(cs,ls+1).getClass().getSimpleName());
        return true;
    }

}