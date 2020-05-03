package actionrockford;

import entitesvivantes.Monstre;
import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Vide;
import modele.exceptions.BoulderMortException;

public class ActionDeRockfordCORVersMonstre extends ActionDeRockfordCOR {

    public ActionDeRockfordCORVersMonstre(ActionDeRockfordCOR s) {
        super(s);
    }

    @Override
    protected boolean actionRockfordVersCase(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException {
        if(!(grille.getCaseDuTab(cs,ls).estOccupee() && grille.getCaseDuTab(cs,ls).getEstIci() instanceof Rockford)) {
            return false;
        }

        if(!(grille.getCaseDuTab(ct,lt) instanceof Vide))
            return false;

        if(grille.getCaseDuTab(ct,lt).estOccupee() && grille.getCaseDuTab(ct,lt).getEstIci() instanceof Monstre) {
            return true;
        }
        
        return false;
    }
    
}