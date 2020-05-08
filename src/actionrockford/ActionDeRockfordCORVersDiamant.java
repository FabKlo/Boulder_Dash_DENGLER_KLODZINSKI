package actionrockford;

import entitesvivantes.Rockford;
import lagrille.Grille;
import lescases.Diamant;
import lescases.Vide;
import modele.exceptions.BoulderMortException;
import toutlessongs.Musique;

public class ActionDeRockfordCORVersDiamant extends ActionDeRockfordCOR {

    public ActionDeRockfordCORVersDiamant(ActionDeRockfordCOR s) {
        super(s);
    }

    @Override
    protected boolean actionRockfordVersCase(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException {
        if(!(grille.getCaseDuTab(cs,ls).estOccupee() && grille.getCaseDuTab(cs,ls).getEstIci() instanceof Rockford)) {
            return false;
        }

        if(!(grille.getCaseDuTab(ct,lt) instanceof Diamant)) {
            return false;
        }

        if(grille.getCaseDuTab(ct,lt).getEstIci() == null && !((Diamant)(grille.getCaseDuTab(ct,lt))).isEnMouvement()) {
            Rockford temp = (Rockford) grille.getCaseDuTab(cs,ls).getEstIci();
            temp.setCompteurDiamant(temp.getCompteurDiamant() + 1);
            grille.setCaseDuTab(ct,lt,new Vide(ct,lt));
            Musique.initBruitage(Musique.PIECE);
            return true;
        }
        return false;
    }
    
}