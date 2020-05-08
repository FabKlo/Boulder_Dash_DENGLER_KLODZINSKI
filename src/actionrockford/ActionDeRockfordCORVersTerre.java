package actionrockford;

import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Terre;
import lescases.Vide;
import modele.exceptions.BoulderMortException;
import toutlessongs.Musique;

public class ActionDeRockfordCORVersTerre extends ActionDeRockfordCOR {

    public ActionDeRockfordCORVersTerre(ActionDeRockfordCOR s) {
        super(s);
    }

    @Override
    protected boolean actionRockfordVersCase(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException {
        if(!(grille.getCaseDuTab(cs,ls).estOccupee() && grille.getCaseDuTab(cs,ls).getEstIci() instanceof Rockford)) {
            return false;
        }

        if(!(grille.getCaseDuTab(ct,lt) instanceof Terre))
            return false;

        if(grille.getCaseDuTab(ct,lt).getEstIci() == null) {
            grille.setCaseDuTab(ct,lt,new Vide(ct,lt));
            Musique.initBruitage(Musique.TERRE);
            return true;
        }
        
        return false;
    }
    
}