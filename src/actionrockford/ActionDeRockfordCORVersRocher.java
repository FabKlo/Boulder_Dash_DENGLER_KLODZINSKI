package actionrockford;

import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Rocher;
import lescases.Vide;
import modele.exceptions.BoulderMortException;

public class ActionDeRockfordCORVersRocher extends ActionDeRockfordCOR {

    public ActionDeRockfordCORVersRocher(ActionDeRockfordCOR s) {
        super(s);
    }

    @Override
    protected boolean actionRockfordVersCase(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException {
        if(!(grille.getCaseDuTab(cs,ls).estOccupee() && grille.getCaseDuTab(cs,ls).getEstIci() instanceof Rockford)) {
            return false;
        }

        if(!(grille.getCaseDuTab(ct,lt) instanceof Rocher)) {
            return false;
        }

        int xPourRocher = ct + (ct - cs);

        if(xPourRocher >= 0 && xPourRocher < grille.getXMAX() && !(((Rocher)(grille.getCaseDuTab(ct,lt))).isEnMouvement())) {
            if(grille.getCaseDuTab(xPourRocher, lt) instanceof Vide && !grille.getCaseDuTab(xPourRocher,lt).estOccupee()) {
                grille.setCaseDuTab(xPourRocher, lt, grille.getCaseDuTab(ct,lt));
                grille.setCaseDuTab(ct,lt,new Vide(ct, lt));

                return true;
            }
        }
        return false;
    }
    
}