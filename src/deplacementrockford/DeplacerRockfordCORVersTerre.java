package deplacementrockford;

import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Terre;
import lescases.Vide;

/**
 * DeplacerRockfordCORVersVide
 */
public class DeplacerRockfordCORVersTerre extends DeplacerRockfordCOR {

    public DeplacerRockfordCORVersTerre(DeplacerRockfordCOR s) {
        super(s);
    }

	@Override
    protected boolean deplacerRockfordVersCase(Grille grille, int cs, int ls, int ct, int lt) {
        if(!(grille.getCaseDuTab(cs,ls).estOccupee() && grille.getCaseDuTab(cs,ls).getEstIci() instanceof Rockford)) {
            return false;
        }

        if(!(grille.getCaseDuTab(ct,lt) instanceof Terre))
            return false;

        if(grille.getCaseDuTab(ct,lt).getEstIci() == null) {
            grille.setCaseDuTab(ct,lt,new Vide(ct,lt));
            grille.getCaseDuTab(ct,lt).mettrePersoSurCase(grille.getCaseDuTab(cs,ls).getEstIci());
            //grille.transfoCaseEnVide(ct,lt);
            grille.getCaseDuTab(cs,ls).setEstIci(null);
            System.out.println("tableau["+ct+"]["+lt+"] = " + grille.getCaseDuTab(ct,lt).getClass().getSimpleName() +
            ", personnage dessus : " + grille.getCaseDuTab(ct,lt).getEstIci());
            System.out.println("tableau["+cs+"]["+ls+"] = " + grille.getCaseDuTab(cs,ls).getClass().getSimpleName() +
            ", personnage dessus : " + grille.getCaseDuTab(cs,ls).getEstIci());  
            return true;
        }

        return false;
    }

}