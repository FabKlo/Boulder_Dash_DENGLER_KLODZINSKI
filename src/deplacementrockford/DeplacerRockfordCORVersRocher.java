package deplacementrockford;

import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Rocher;
import lescases.Vide;
import modele.exceptions.BoulderMortException;

/**
 * DeplacerRockfordCORVersVide
 */
public class DeplacerRockfordCORVersRocher extends DeplacerRockfordCOR {

    public DeplacerRockfordCORVersRocher(DeplacerRockfordCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerRockfordVersCase(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException {
        if(!(grille.getCaseDuTab(cs,ls).estOccupee() && grille.getCaseDuTab(cs,ls).getEstIci() instanceof Rockford)) {
            return false;
        }

        if(!(grille.getCaseDuTab(ct,lt) instanceof Rocher)) {
            return false;
        }

        int xPourRocher = ct + (ct - cs);

        if(xPourRocher >= 0 && xPourRocher < grille.getXMAX()) {
            if(grille.getCaseDuTab(xPourRocher, lt) instanceof Vide) {
                grille.setCaseDuTab(xPourRocher, lt, new Rocher(xPourRocher, lt));
                grille.setCaseDuTab(ct,lt,new Vide(ct, lt));
                grille.getCaseDuTab(ct,lt).mettrePersoSurCase(grille.getCaseDuTab(cs,ls).getEstIci());
                grille.getCaseDuTab(cs,ls).setEstIci(null);

                System.out.println("tableau["+cs+"]["+ls+"] = " + grille.getCaseDuTab(cs,ls).getClass().getSimpleName() +
                ", perso dessus : " + grille.getCaseDuTab(cs, ls).getEstIci());
                System.out.println("tableau["+ct+"]["+lt+"] = " + grille.getCaseDuTab(ct, lt).getClass().getSimpleName() +
                ", perso dessus : " + grille.getCaseDuTab(ct, lt).getEstIci());
                System.out.println("tableau["+xPourRocher+"]["+ls+"] = " + grille.getCaseDuTab(xPourRocher, lt).getClass().getSimpleName() +
                ", perso dessus : " + grille.getCaseDuTab(xPourRocher, lt).getEstIci());

                return true;
            } else {
                System.out.println("tableau["+cs+"]["+ls+"] = " + grille.getCaseDuTab(cs,ls).getClass().getSimpleName() +
                ", perso dessus : " + grille.getCaseDuTab(cs, ls).getEstIci());
                System.out.println("tableau["+ct+"]["+lt+"] = " + grille.getCaseDuTab(ct, lt).getClass().getSimpleName() +
                ", perso dessus : " + grille.getCaseDuTab(ct, lt).getEstIci());
                System.out.println("tableau["+xPourRocher+"]["+ls+"] = " + grille.getCaseDuTab(xPourRocher, lt).getClass().getSimpleName() +
                ", perso dessus : " + grille.getCaseDuTab(xPourRocher, lt).getEstIci());
                System.out.println("!!! Le rocher ne peut pas bouger !!!");
            }
        }
        else {
            System.out.println("le rocher aux coord x = " + ct + ", y = " + lt + " est en bord de tableau");
        }
        return false;
    }
}