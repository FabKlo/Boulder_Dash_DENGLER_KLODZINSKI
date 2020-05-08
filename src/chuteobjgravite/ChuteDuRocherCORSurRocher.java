package chuteobjgravite;

import java.util.ArrayList;
import java.util.Random;

import lagrille.Grille;
import lescases.Rocher;
import lescases.Vide;
import modele.exceptions.BoulderMortException;
import toutlessongs.Musique;

public class ChuteDuRocherCORSurRocher extends ChuteObjGraviteCOR {

    public ChuteDuRocherCORSurRocher (ChuteObjGraviteCOR s) {
        super(s);
    }

    @Override
    protected boolean deplacerObjGraviteVersCase(Grille grille, int cs, int ls) throws BoulderMortException {

        if(!(grille.getCaseDuTab(cs,ls) instanceof Rocher)) {
            //System.out.println("la case source n'est pas un rocher");
            return false;
        }

        if(!(grille.getCaseDuTab(cs,ls+1) instanceof Rocher)) {
            //System.out.println("la case target n'est pas un rocher");
            return false;
        }
        
        ArrayList<Integer> oui = new ArrayList<Integer>();
        oui.add(-1);
        oui.add(1);
        Random r = new Random();
        int temp = r.nextInt((1 - 0) + 1) + 0;
        temp = oui.get(temp);

        //int temp = 1;

        if(cs + temp < 0 || cs + temp >= grille.getXMAX() || !(grille.getCaseDuTab(cs + temp, ls) instanceof Vide)) {
            //System.out.println("cs + temp = " + cs+temp);
            temp *= -1;
        }

        if(cs + temp < 0 || cs + temp >= grille.getXMAX() || !(grille.getCaseDuTab(cs + temp, ls) instanceof Vide)) {
            //System.out.println("cs + temp = " + cs+temp);
            return true;
        }

        if(!((Rocher)(grille.getCaseDuTab(cs,ls))).isEnMouvement()) {

            if(grille.getCaseDuTab(cs + temp, ls) instanceof Vide &&
            grille.getCaseDuTab(cs + temp, ls + 1) instanceof Vide) {

                //System.out.println("cs : "+(cs)+" l : "+(ls));
                //System.out.println("ct : "+(cs+temp)+" l : "+(ls));
                if(!(grille.getCaseDuTab(cs + temp, ls).estOccupee()) && !(grille.getCaseDuTab(cs + temp, ls + 1).estOccupee())) {

                    grille.setCaseDuTab(cs + temp, ls, grille.getCaseDuTab(cs,ls));
                    ((Rocher)(grille.getCaseDuTab(cs + temp,ls))).setEnMouvement(true);
                    grille.setCaseDuTab(cs,ls, new Vide(cs, ls));
                    temp = 0;
                    return true;
                } else {
                    temp = 0;
                    return true;
                }

            } else {
                temp = 0;
                return true;
            }
                

        } else {
            if(grille.getCaseDuTab(cs + temp, ls) instanceof Vide &&
            grille.getCaseDuTab(cs + temp, ls + 1) instanceof Vide) {
                
                Musique.initBruitage(Musique.ROCHER);

                if(!(grille.getCaseDuTab(cs + temp, ls).estOccupee())) {
                    grille.setCaseDuTab(cs + temp, ls, grille.getCaseDuTab(cs,ls));
                    grille.setCaseDuTab(cs,ls, new Vide(cs, ls));
                    temp = 0;
                    return true;
                }
                else {
                    ((Rocher)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false); 
                    temp = 0;
                    return true;
                }
            } else {
                Musique.initBruitage(Musique.ROCHER);
                ((Rocher)(grille.getCaseDuTab(cs,ls))).setEnMouvement(false); 
                temp = 0;
                return true;
            }

            
        }


    }

}