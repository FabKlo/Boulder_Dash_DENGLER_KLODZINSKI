package actionrockford;

import lagrille.Grille;
import modele.exceptions.BoulderMortException;

public abstract class ActionDeRockfordCOR implements ActionDeRockford {
    
    private ActionDeRockfordCOR suivant;

    /**
     * Chaine de responsabilite
     * @param s
     */
    public ActionDeRockfordCOR(ActionDeRockfordCOR s) {
        this.suivant = s;
    }

    /**
     * deplace rockford vers un type de case precis
     * @param grille c'est la grille
     * @param cs    colonne source
     * @param ls    ligne source
     * @param ct    colonne target
     * @param lt    ligne target
     * @return faux s'il l'effectue pas, true si oui
     */
    protected abstract boolean actionRockfordVersCase(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException;

    @Override
    /**
     * methode recursive pour traverse tout les maillons de la chaine
     */
    public boolean actionRockford(Grille grille, int cs, int ls, int ct, int lt) throws BoulderMortException {
        if(!(this.actionRockfordVersCase(grille, cs, ls, ct, lt))) {
            if(suivant != null) {
                return suivant.actionRockford(grille, cs, ls, ct, lt);
            }
            else
                return false;
        }
        else
            return true;
    }

    /**
     * initialisation de la chaine
     * @return
     */
    public static ActionDeRockfordCOR initCOR() {

        ActionDeRockfordCORVersVide vide = new ActionDeRockfordCORVersVide(null);
        ActionDeRockfordCORVersTerre terre = new ActionDeRockfordCORVersTerre(vide);
        ActionDeRockfordCORVersDiamant diamant = new ActionDeRockfordCORVersDiamant(terre);
        ActionDeRockfordCORVersMonstre monstre = new ActionDeRockfordCORVersMonstre(diamant);
        ActionDeRockfordCORVersRocher rocher = new ActionDeRockfordCORVersRocher(monstre);
        ActionDeRockfordCORVersAcier acier = new ActionDeRockfordCORVersAcier(rocher);
        ActionDeRockfordCORVersSortie sortie = new ActionDeRockfordCORVersSortie(acier);
        
        return sortie;

    }
}