package chuteobjgravite;

import lagrille.Grille;
import lescases.Rocher;
import lescases.Vide;
import modele.exceptions.BoulderMortException;

public class ChuteDuRocherCORSurVide extends ChuteObjGraviteCOR {

    public ChuteDuRocherCORSurVide(ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerRocherVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Rocher)) {
            //System.out.println("la case source n'est pas un rocher");
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls+1) instanceof Vide) || grille.getCaseDuTab(cs,ls+1).estOccupee()) {
            return false;
        }

        ((Rocher)(grille.getCaseDuTab(cs,ls))).setEnMouvement(true);
        grille.setCaseDuTab(cs,ls+1,grille.getCaseDuTab(cs,ls));
        //grille.getCaseDuTab(cs,ls+1).setPositionY(ls+1);
        grille.setCaseDuTab(cs,ls,new Vide(cs,ls));
        //System.out.println("tab["+cs+"]["+ls+"] = " + grille.getCaseDuTab(cs,ls).getClass().getSimpleName());
        //System.out.println("tab["+cs+"]["+(ls+1)+"] = " + grille.getCaseDuTab(cs,ls+1).getClass().getSimpleName());
        return true;
    }

}