package deplacementrockford;

import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Acier;

/**
 * DeplacerRockfordCORVersVide
 */
public class DeplacerRockfordCORVersAcier extends DeplacerRockfordCOR {

    public DeplacerRockfordCORVersAcier(DeplacerRockfordCOR s) {
        super(s);
    }

	@Override
    protected boolean deplacerRockfordVersCase(Grille grille, int cs, int ls, int ct, int lt) {
        if(!(grille.getCaseDuTab(cs,ls).estOccupee() && grille.getCaseDuTab(cs,ls).getEstIci() instanceof Rockford)) {
            return false;
        }

        if((ct < 0 || ct >= grille.getXMAX()) || (lt < 0 || lt >= grille.getYMAX())) {
            //System.out.println("Case target en dehors du tableau");
            return true;
        }

        if(!(grille.getCaseDuTab(ct,lt) instanceof Acier)) {
            return false;
        }

        //System.out.println("tableau["+ct+"]["+lt+"] = " + grille.getCaseDuTab(ct,lt).getClass().getSimpleName() +
        //", personnage dessus : " + grille.getCaseDuTab(ct,lt).getEstIci());
        //System.out.println("tableau["+cs+"]["+ls+"] = " + grille.getCaseDuTab(cs,ls).getClass().getSimpleName() +
        //", personnage dessus : " + grille.getCaseDuTab(cs,ls).getEstIci());  
        
        return false;
    }

}