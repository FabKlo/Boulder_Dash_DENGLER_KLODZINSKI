package deplacementmonstre;

import entitesvivantes.Monstre;
import lagrille.Grille;
import lescases.Vide;
import modele.exceptions.BoulderMortException;

/**
 * DeplacerMonstreCORVersRockford
 */
public class DeplacerMonstreCORVersVide extends DeplacerMonstreCOR {

    public DeplacerMonstreCORVersVide(DeplacerMonstreCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerMonstreVersCase(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException {
        if(!(grille.getCaseDuTab(cs,ls).estOccupee() && grille.getCaseDuTab(cs,ls).getEstIci() instanceof Monstre)) {
            return false;
        }

        if((ct < 0 || ct >= grille.getXMAX()) || (lt < 0 || lt >= grille.getYMAX())) {
            //System.out.println("Case target en dehors du tableau");
            return false;
        }

        if(!(grille.getCaseDuTab(ct,lt) instanceof Vide))
            return false;

        if(!grille.getCaseDuTab(ct,lt).estOccupee()) {
            grille.getCaseDuTab(ct,lt).mettrePersoSurCase(grille.getCaseDuTab(cs,ls).getEstIci());
            grille.setCaseDuTab(cs,ls, new Vide(cs, ls));
            
            return true;
        }

        return false;
    }
}